package com.example.hellojbhbjbjj.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellojbhbjbjj.Adapter.PagoAdapter;
import com.example.hellojbhbjbjj.Adapter.PaymentAdapter;
import com.example.hellojbhbjbjj.Adapter.RoutinaAdapter;
import com.example.hellojbhbjbjj.AdminSQLiteOpenHelper;
import com.example.hellojbhbjbjj.Payment;
import com.example.hellojbhbjbjj.R;

import android.app.AlertDialog;

import android.text.Html;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentActivity extends AppCompatActivity {

    EditText emonto;
    Button pagar;
    List<Payment> pago = new ArrayList<>();
    RecyclerView rvPago;
    AdminSQLiteOpenHelper myDb;


    PagoAdapter pagoAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pago);

        rvPago = findViewById(R.id.rvPago);
        rvPago.setLayoutManager(new GridLayoutManager(this, 1));

        pagar =findViewById(R.id.pagarBtn);
        Bundle extra = getIntent().getExtras();
        final String id = extra.getString("id");
        myDb = new AdminSQLiteOpenHelper(PaymentActivity.this, "dbSystem", null, 1);

        getInfopago(id);
        getPayment(id);

        pagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    double cuota = 6000;
                    long isInserted = myDb.insertDues(id, String.valueOf(cuota), "04-03-2020");
                Toast.makeText(PaymentActivity.this, "" + isInserted, Toast.LENGTH_LONG).show();

                    getPayment(id);
            }
        });
    }

    public void savePaymentLoan(double cuota, String _id)  {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(PaymentActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String fecha = "20-02-2020";

        ContentValues register = new ContentValues();
            register.put("idLoan", ""+_id);
            register.put("amount", "5000");
            register.put("date", fecha);
            db.insertWithOnConflict("Dues", null, register,SQLiteDatabase.CONFLICT_REPLACE);
    }


    /*
     public void backupPayment(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(RutinaActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues register = new ContentValues();
            register.put("idLoan", paymentList.get(i).getIdLoan());
            register.put("amount", paymentList.get(i).getMonto());
            register.put("date", paymentList.get(i).getFecha());
            db.insert("Dues", null, register);

        db.close();
    }
    * */



    private void getPayment(String idLoan){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(PaymentActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor res = db.rawQuery(" select * " +
                " from Dues where idLoan like  '"+ idLoan+"'" , null);

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("amount :"+ res.getString(1)+"\n\n");
        }

        // Show all data
        Toast.makeText(PaymentActivity.this, "" + buffer.toString()+" "+res.getCount(), Toast.LENGTH_LONG).show();

        res.close();

    }

    public void getInfopago(String idLoan)  {
        JSONObject jsonpayment = new JSONObject();
        JSONArray jsonArray = new JSONArray();
         AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(PaymentActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select c.firstname,c.lastname, c.identification," +
                "l.amount," +"p.name, p.numberCuotes, p.rateOfInterest, l.date, l.id" +
                " from Clients c inner join Loans l on c.id  like l.client " +
                "inner join Plans p on  l.plane like p.id  where l.id like  '"+ idLoan+"'" , null);
        if (fila != null && fila.getCount() != 0) {
            fila.moveToFirst();

            if (fila.moveToFirst()) {
                do {

                    try {
                        jsonpayment.put("firstname", fila.getString(0));
                        jsonpayment.put("lastname", fila.getString(1));
                        jsonpayment.put("identification", fila.getString(2));
                        jsonpayment.put("amount", fila.getString(3));
                        jsonpayment.put("plan", fila.getString(4));
                        jsonpayment.put("cuotesNumber", fila.getString(5));
                        jsonpayment.put("rateOfInterest", fila.getString(6));
                        jsonpayment.put("date", fila.getString(7));
                        jsonpayment.put("id", fila.getString(8));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } while (fila.moveToNext());
            }
        }

        fila.close();
        jsonArray.put(jsonpayment);
        pagoAdapter = new PagoAdapter(PaymentActivity.this, jsonArray);
        rvPago.setAdapter(pagoAdapter);
    }

}
