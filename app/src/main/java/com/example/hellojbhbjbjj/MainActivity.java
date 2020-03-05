package com.example.hellojbhbjbjj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hellojbhbjbjj.Activities.RutinaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {

    Button btnSync, btnAdd;
    EditText etName, etUsername, etEmail, etPhone, etWebsite;
    RecyclerView rvServer, rvLocal;


    List<Plans> planList = new ArrayList<>();

    ServerAdapter serverAdapter;
    LocalAdapter localAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSync = findViewById(R.id.btnSync);
        btnAdd = findViewById(R.id.btnAdd);



        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent routinas = new Intent(MainActivity.this, RutinaActivity.class);
                startActivity(routinas);
            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backupPlanTable();
            }
        });


    }

    //Fill the plan's table if is empty

    public void backupPlanTable(){
        planList.clear();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select * from Plans", null);

        if(fila.getCount() == 0){
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            StringRequest stringRequest;
            stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.URL_GET_PLANS),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray  jsonResponse = new JSONArray(response);
                                for (int i = 0; i < jsonResponse.length(); i++) {
                                    JSONObject jsonObjectResponse = jsonResponse.getJSONObject(i);
                                    planList.add(
                                            new Plans(
                                                    jsonObjectResponse.getString("_id"),
                                                    jsonObjectResponse.getString("name"),
                                                    jsonObjectResponse.getString("cuotas"),
                                                    jsonObjectResponse.getString("percentaje"),
                                                    jsonObjectResponse.getString("fecha")
                                            )
                                    );
                                }
                                Toast.makeText(MainActivity.this, "l"+planList.size(), Toast.LENGTH_SHORT).show();
                                setLocal();
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
        }else{
            Toast.makeText(MainActivity.this, "Bienvenido !!", Toast.LENGTH_SHORT).show();
        }
    }



    public void setLocal() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        if(planList.size() > 0 ){
            for (int i = 0; i < planList.size(); i++) {

                ContentValues register = new ContentValues();
                register.put("id", planList.get(i).get_id());
                register.put("name", planList.get(i).getName());
                register.put("numberCuotes", planList.get(i).getCuotas());
                register.put("rateOfInterest", planList.get(i).getPercentaje());
                register.put("date", planList.get(i).getFecha());

                db.insert("Plans", null, register);
            }

            db.close();
        }else {
            Toast.makeText(MainActivity.this, "No hay planes predefinidas", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "Por favor crea por lo menos una", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "para poder crear prestamos", Toast.LENGTH_SHORT).show();
        }
    }
}

/*
    // Fill the database with an API
    public void setLocal() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this, "dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        for (int i = 0; i < serverList.size(); i++) {
            ContentValues register = new ContentValues();

            register.put("name", serverList.get(i).getName());
            register.put("username", serverList.get(i).getUsername());
            register.put("email", serverList.get(i).getEmail());
            register.put("phone", serverList.get(i).getPhone());
            register.put("website", serverList.get(i).getWebsite());

            db.insert("Users", null, register);
        }
        db.close();
        getLocal();
    }

    // Print the database information
    public void getLocal() {
        localList.clear();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this, "dbSystem", null, 3);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select * from Users", null);
        Toast.makeText(MainActivity.this, "Filas : " + fila.getCount(), Toast.LENGTH_LONG).show();
        if (fila != null && fila.getCount() != 0) {
            fila.moveToFirst();

            if(fila.moveToFirst()){
                do{
                    localList.add(
                            new Users(
                                    fila.getString(1),
                                    fila.getString(2),
                                    fila.getString(3),
                                    fila.getString(4),
                                    fila.getString(5)
                            )
                    );
                }while(fila.moveToNext());
            }
            localAdapter = new LocalAdapter(MainActivity.this, localList);
            rvLocal.setAdapter(localAdapter);
            Toast.makeText(MainActivity.this, "List: " + localList.size(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "No hay registros: " + localList.size(), Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

 */

    /*
    //Send data to server
        public void sincronizar() {
            JSONArray jsonUsers = new JSONArray();

            for(int i = 0; i < localList.size(); i++){
                JSONObject jsonUser = new JSONObject();
                try{
                    jsonUser.put("name", localList.get(i).getName());
                    jsonUser.put("username", localList.get(i).getUsername());
                    jsonUser.put("email", localList.get(i).getEmail());
                    jsonUser.put("phone", localList.get(i).getPhone());
                    jsonUser.put("website", localList.get(i).getWebsite());
                }catch (JSONException e){
                    e.printStackTrace();
                }
                jsonUsers.put(jsonUser);
            }

            JSONObject json = new JSONObject();
            try{
                json.put("Users", jsonUsers);
            }catch (JSONException e){
                e.printStackTrace();
            }

            String jsonStr = json.toString();
        }

    public void setLocal(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this,"dbSystem", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues register = new ContentValues();

        for(int i = 0; i < serverList.size(); i++ ){
            register.put("name", serverList.get(i).getName());
            register.put("username", serverList.get(i).getUsername());
            register.put("email", serverList.get(i).getEmail());
            register.put("phone", serverList.get(i).getPhone());
            register.put("website", serverList.get(i).getWebsite());

            db.insert("USERS", null,register);
            getLocal();
        }
        db.close();
    }
*/
