package com.example.hellojbhbjbjj.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hellojbhbjbjj.Adapter.RoutinaAdapter;
import com.example.hellojbhbjbjj.AdminSQLiteOpenHelper;
import com.example.hellojbhbjbjj.Clients;
import com.example.hellojbhbjbjj.Interest;
import com.example.hellojbhbjbjj.Loans;
import com.example.hellojbhbjbjj.MainActivity;
import com.example.hellojbhbjbjj.Payment;
import com.example.hellojbhbjbjj.Plans;
import com.example.hellojbhbjbjj.PreviesLoans;
import com.example.hellojbhbjbjj.R;
import com.example.hellojbhbjbjj.Rutina;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

public class RutinaActivity extends AppCompatActivity {

    List<Clients> clientList = new ArrayList<>();
    List<Loans> loansList = new ArrayList<>();
    List<Payment> paymentList = new ArrayList<>();
    List<Plans> plansList = new ArrayList<>();
    List<Interest> interestList = new ArrayList<>();
    List<PreviesLoans> previewLoansList = new ArrayList<>();
    List<Rutina> rutinaList = new ArrayList<>();

    Button detailsClient;
    RecyclerView rvRoutina;

    RoutinaAdapter routinaAdapter;


    //  rvServer.setLayoutManager(new GridLayoutManager(this, 1));

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rutina_list);

        rvRoutina = findViewById(R.id.rvRoutina);
        rvRoutina.setLayoutManager(new GridLayoutManager(this, 1));
        detailsClient = findViewById(R.id.btnVerCliente);
        printDatafromDatabase();
      // getRoutina();
    }

    public void getRoutina() {
        loansList.clear();
        clientList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(RutinaActivity.this);
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.URL_GET_LOANS_BY_ROUTINA),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonCities = new JSONArray();
                            JSONObject jsonRoutina = new JSONObject(response);
                            JSONArray  jsonResponse = jsonRoutina.getJSONArray("response");
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObjectResponse = jsonResponse.getJSONObject(i);
                                jsonCities = jsonObjectResponse.getJSONArray("city");

                                for (int a = 0; a < jsonCities.length(); a++) {

                                    //Get the object at this index
                                    JSONObject loans = jsonCities.getJSONObject(a);

                                    //Dispatch the API Response
                                    JSONObject clients = loans.getJSONObject("client");
                                    JSONObject plans = loans.getJSONObject("plan");
                                    JSONArray payment = loans.getJSONArray("pagos");
                                    JSONArray interest = loans.getJSONArray("interes");
                                    JSONArray previews = loans.getJSONArray("antecedentes");

                                    // Get the sub-document id
                                    String plan = plans.getString("_id");
                                    String client = clients.getString("_id");
                                    //Fill the loans list
                                    loansList.add(
                                            new Loans(
                                                    loans.getString("_id"),
                                                    client,
                                                    loans.getString("monto"),
                                                    plan,
                                                    loans.getBoolean("estadoPago"),
                                                    loans.getString("status"),
                                                    loans.getString("fecha")
                                            )
                                    );
                                    //Fill the clientList
                                    clientList.add(
                                            new Clients(
                                                    clients.getString("_id"),
                                                    clients.getString("name"),
                                                    clients.getString("apellido"),
                                                    clients.getString("cedula"),
                                                    clients.getString("telefono"),
                                                    clients.getString("dirreccion"),
                                                    clients.getString("ciudad"),
                                                    clients.getString("DirReferencia"),
                                                    clients.getString("puntos")
                                            )
                                    );

                                    for (int p = 0; p < payment.length(); p++){
                                        //Get the object payment at this index
                                        JSONObject object = payment.getJSONObject(p);
                                        //Fill the paymentList
                                        paymentList.add(
                                                new Payment(
                                                        loans.getString("_id"),
                                                        object.getString("fecha"),
                                                        object.getString("monto")
                                                )
                                        );
                                    }

                                    for (int p = 0; p < previews.length(); p++){
                                        //Get the object previews at this index
                                        JSONObject object = previews.getJSONObject(p);
                                        String idPreviewLoan = object.getString("id");
                                        //Fill the previewList
                                        previewLoansList.add(
                                                new PreviesLoans(
                                                        loans.getString("_id"),
                                                        idPreviewLoan
                                                )
                                        );
                                    }


                                    for (int p = 0; p < interest.length(); p++){
                                        //Get the object interest at this index
                                        JSONObject object = interest.getJSONObject(p);
                                        //Fill the interestList
                                        interestList.add(
                                                new Interest(
                                                        loans.getString("_id"),
                                                        object.getString("fecha"),
                                                        object.getString("monto")
                                                )
                                        );
                                    }


                                }
                            }

                            backupRoutinaInLocal();
                           // routinaAdapter = new RoutinaAdapter(RutinaActivity.this, clientList);
                          //  rvRoutina.setAdapter(routinaAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }

    // Clone the data which I received from the API.....
    public void backupClient(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(RutinaActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        for (int i = 0; i < clientList.size(); i++) {
            ContentValues register = new ContentValues();
            register.put("id", clientList.get(i).getId());
            register.put("firstname", clientList.get(i).getName());
            register.put("lastname", clientList.get(i).getApellido());
            register.put("identification", clientList.get(i).getCedula());
            register.put("phone", clientList.get(i).getTelefono());
            register.put("adress", clientList.get(i).getDireeccion());
            register.put("city", clientList.get(i).getCiudad());
            register.put("description", clientList.get(i).getDirReferencia());
            db.insertWithOnConflict("Clients", null, register,CONFLICT_REPLACE);
        }

        db.close();
    }

    public void backupLoans(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(RutinaActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        for (int i = 0; i < loansList.size(); i++) {

            ContentValues register = new ContentValues();
            register.put("id", loansList.get(i).get_id());
            register.put("amount", loansList.get(i).getMonto());
            register.put("payStatus", loansList.get(i).isEstadoPago());
            register.put("status", loansList.get(i).getStatus());
            register.put("date", loansList.get(i).getFecha());
            register.put("client", loansList.get(i).getClient());
            register.put("plane", loansList.get(i).getPlan());

            db.insert("Loans", null, register);
        }

        db.close();
    }

    public void backupPayment(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(RutinaActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        for (int i = 0; i < paymentList.size(); i++) {

            ContentValues register = new ContentValues();
            register.put("idLoan", paymentList.get(i).getIdLoan());
            register.put("amount", paymentList.get(i).getMonto());
            register.put("date", paymentList.get(i).getFecha());
            db.insert("Dues", null, register);
        }

        db.close();
    }

    public void backupInterest(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(RutinaActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        for (int i = 0; i < interestList.size(); i++) {
            ContentValues register = new ContentValues();
            register.put("idLoan", interestList.get(i).getIdLoan());
            register.put("amount", interestList.get(i).getMonto());
            register.put("date", interestList.get(i).getFecha());
            db.insert("Interest", null, register);
        }
        db.close();
    }


    public void backupPreview(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(RutinaActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        for (int i = 0; i < previewLoansList.size(); i++) {
            ContentValues register = new ContentValues();
            register.put("idLoan", previewLoansList.get(i).getIdLoan());
            register.put("idPreviewsLoan", previewLoansList.get(i).getIdPreviewLoan());
            db.insert("PreviewLoans ", null, register);
        }
        db.close();
    }

    public void backupRoutinaInLocal(){
        backupClient();
        backupLoans();
        backupPayment();
        backupInterest();
        backupPreview();
        printDatafromDatabase();
    }


    public void printDatafromDatabase(){
        rutinaList.clear();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(RutinaActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
         Cursor fila = db.rawQuery("select c.firstname,c.lastname, c.identification," +
                "l.id, l.amount,"+"c.phone,c.city || ' '|| c.adress|| ' '|| c.description, l.payStatus, l.status, l.date" +
                " from Clients c inner join Loans l on c.id like l.client where l.upTOdate like 'false'", null);
        if (fila != null && fila.getCount() != 0) {
            fila.moveToFirst();

            if(fila.moveToFirst()){
                do{
                    rutinaList.add(

                            new Rutina (
                                    fila.getString(0),
                                    fila.getString(1),
                                    fila.getString(2),
                                    fila.getString(3),
                                    fila.getString(4),
                                    fila.getString(5),
                                    fila.getString(6),
                                    fila.getString(7),
                                    fila.getString(8),
                                    fila.getString(9)
                            )
                    );
                }while(fila.moveToNext());
            }
            routinaAdapter = new RoutinaAdapter(RutinaActivity.this, rutinaList);
            rvRoutina.setAdapter(routinaAdapter);
        } else {
            Toast.makeText(RutinaActivity.this, "No hay registros: " + clientList.size(), Toast.LENGTH_SHORT).show();
        }
    }
}



