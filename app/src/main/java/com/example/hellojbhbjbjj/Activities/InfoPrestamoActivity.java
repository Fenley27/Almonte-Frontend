package com.example.hellojbhbjbjj.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hellojbhbjbjj.Adapter.ClientAdapter;
import com.example.hellojbhbjbjj.Adapter.PaymentAdapter;
import com.example.hellojbhbjbjj.Adapter.RoutinaAdapter;
import com.example.hellojbhbjbjj.AdminSQLiteOpenHelper;
import com.example.hellojbhbjbjj.R;
import com.example.hellojbhbjbjj.Rutina;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoPrestamoActivity extends AppCompatActivity {

    RecyclerView rvPayment;
    RecyclerView rvCient;
    PaymentAdapter paymentAdapter;
    ClientAdapter clientAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_info);
        Bundle extra = getIntent().getExtras();
        String id = extra.getString("id");
        rvPayment = findViewById(R.id.rvPagos);
        rvPayment.setLayoutManager(new GridLayoutManager(this, 1));
        rvCient = findViewById(R.id.rvClient);
        rvCient.setLayoutManager(new GridLayoutManager(this, 1));

        getInfoLoan(id);
        getPayment(id);
    }
    //Print loan's info
    public void getInfoLoan( String idLoan) {
        JSONObject jsonpayment = new JSONObject();
        JSONArray jsonClient = new JSONArray();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(InfoPrestamoActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select c.firstname,c.lastname, c.identification," +
                "l.amount," + "c.phone,c.city || ' '|| c.adress|| ' '|| c.description, l.status, l.date" +
                " from Clients c inner join Loans l on c.id like l.client where l.id like  '"+ idLoan+"'" , null);
        if (fila != null && fila.getCount() != 0) {
            fila.moveToFirst();

            if (fila.moveToFirst()) {
                do {

                    try {
                        jsonpayment.put("firstname", fila.getString(0));
                        jsonpayment.put("lastname", fila.getString(1));
                        jsonpayment.put("identification", fila.getString(2));
                        jsonpayment.put("amount", fila.getString(3));
                        jsonpayment.put("phone", fila.getString(4));
                        jsonpayment.put("adress", fila.getString(5));
                        jsonpayment.put("status", fila.getString(6));
                        jsonpayment.put("date", fila.getString(7));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } while (fila.moveToNext());
            }
        }
        jsonClient.put(jsonpayment);
        clientAdapter = new ClientAdapter(InfoPrestamoActivity.this, jsonClient);
        rvCient.setAdapter(clientAdapter);

    }

    private void getPayment(String idLoan){
        JSONArray jsonpayments = new JSONArray();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(InfoPrestamoActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor filaPagos = db.rawQuery("select idLoan, amount " +
                " from Dues  where idLoan  like '"+idLoan+"'" , null);
        if (filaPagos != null && filaPagos.getCount() != 0) {
            filaPagos.moveToFirst();

            if (filaPagos.moveToFirst()) {
                do {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("id", filaPagos.getString(0));
                        object.put("amount", filaPagos.getString(1));
                        jsonpayments.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } while (filaPagos.moveToNext());
            }
        }
        paymentAdapter = new PaymentAdapter(InfoPrestamoActivity.this, jsonpayments);
        rvPayment.setAdapter(paymentAdapter);
    }
}
