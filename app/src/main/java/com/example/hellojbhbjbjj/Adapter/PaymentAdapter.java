package com.example.hellojbhbjbjj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hellojbhbjbjj.R;
import com.example.hellojbhbjbjj.Rutina;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  class PaymentAdapter  extends RecyclerView.Adapter<PaymentAdapter.routinaViewHolder>{
    Context context;
    JSONArray jsonpayments = new JSONArray();

    public PaymentAdapter(Context context, JSONArray jsonpayments) {
        this.context = context;
        this.jsonpayments = jsonpayments;
    }

    @NonNull
    @Override
    public PaymentAdapter.routinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_pago, null, false);
        return new PaymentAdapter.routinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull routinaViewHolder holder, int position) {
        try {
            holder.monto_pago.setText(jsonpayments.getJSONObject(position).getString("amount"));
            holder.fecha_pago.setText(jsonpayments.getJSONObject(position).getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return jsonpayments.length();
    }

    public class routinaViewHolder extends RecyclerView.ViewHolder {
        TextView monto_pago, fecha_pago;
        public routinaViewHolder(@NonNull View itemView) {
            super(itemView);
            monto_pago= itemView.findViewById(R.id.monto_pago);
            fecha_pago = itemView.findViewById(R.id.fecha_pago);
        }
    }
}
