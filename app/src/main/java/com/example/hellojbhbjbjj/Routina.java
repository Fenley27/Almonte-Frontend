package com.example.hellojbhbjbjj;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Routina {
    Context context;
    List<Loans> loansList = new ArrayList<>();
    List<Loans> clientList = new ArrayList<>();
    List<Loans> pagoList = new ArrayList<>();
    List<Loans> interestList = new ArrayList<>();

    public Routina(Context context, List<Loans> loansList, List<Loans> clientList, List<Loans> pagoList, List<Loans> interestList) {
        this.context = context;
        this.loansList = loansList;
        this.clientList = clientList;
        this.pagoList = pagoList;
        this.interestList = interestList;
    }

}