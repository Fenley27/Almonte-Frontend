package com.example.hellojbhbjbjj.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellojbhbjbjj.Activities.InfoPrestamoActivity;
import com.example.hellojbhbjbjj.Activities.RutinaActivity;
import com.example.hellojbhbjbjj.AdminSQLiteOpenHelper;
import com.example.hellojbhbjbjj.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.routinaViewHolder>{
    Context context;
    JSONArray jsonpayment = new JSONArray();

    public PagoAdapter(Context context, JSONArray jsonpayments) {
        this.context = context;
        this.jsonpayment = jsonpayments;
    }

    @NonNull
    @Override
    public PagoAdapter.routinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_details, null, false);
        return new PagoAdapter.routinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull routinaViewHolder holder, int position) {
        try {
            final double cuota = jsonpayment.getJSONObject(position).getDouble("amount") / jsonpayment.getJSONObject(position).getDouble("cuotesNumber") +
                    ( (jsonpayment.getJSONObject(position).getDouble("amount") * jsonpayment.getJSONObject(position).getDouble("rateOfInterest")) / 100  );
            double interest = (jsonpayment.getJSONObject(position).getDouble("amount") * jsonpayment.getJSONObject(position).getDouble("rateOfInterest"));

            holder.nombre_cliente.setText(jsonpayment.getJSONObject(position).getString("firstname"));
            holder.apellido_cliente.setText(jsonpayment.getJSONObject(position).getString("lastname"));
            holder.cedula_cliente.setText(jsonpayment.getJSONObject(position).getString("identification"));
            holder.monto_deuda.setText(jsonpayment.getJSONObject(position).getString("amount"));
            holder.cuotas.setText(String.valueOf(cuota));
            holder.fecha_hoy.setText(jsonpayment.getJSONObject(position).getString("date"));
            final String id = jsonpayment.getJSONObject(position).getString("id");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonpayment.length();
    }

    public class routinaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre_cliente, apellido_cliente, monto_deuda, cedula_cliente, plan_Prestamo,
                cuotas, fecha_hoy;
        EditText TextMonto;
        Button btnPagar;
        public routinaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_cliente = itemView.findViewById(R.id.nombre_cliente);
            apellido_cliente = itemView.findViewById(R.id.apellido_cliente);
            monto_deuda = itemView.findViewById(R.id.monto_deuda);
            cedula_cliente = itemView.findViewById(R.id.cedula_cliente);
            plan_Prestamo = itemView.findViewById(R.id.plan_prestamo);
            cuotas = itemView.findViewById(R.id.cuotas_prestamo);
            fecha_hoy = itemView.findViewById(R.id.fecha_hoy);
            btnPagar= itemView.findViewById(R.id.pagarBtn);
        }
    }
}
