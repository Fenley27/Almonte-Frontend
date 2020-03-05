package com.example.hellojbhbjbjj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hellojbhbjbjj.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.routinaViewHolder>{
    Context context;
    JSONArray jsonpayment = new JSONArray();

    public ClientAdapter(Context context, JSONArray jsonpayments) {
        this.context = context;
        this.jsonpayment = jsonpayments;
    }

    @NonNull
    @Override
    public ClientAdapter.routinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_client, null, false);
        return new ClientAdapter.routinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull routinaViewHolder holder, int position) {
        try {
            holder.nombre_cliente.setText(jsonpayment.getJSONObject(position).getString("firstname"));
            holder.apellido_cliente.setText(jsonpayment.getJSONObject(position).getString("lastname"));
            holder.cedula_cliente.setText(jsonpayment.getJSONObject(position).getString("identification"));
            holder.telefono_cliente.setText(jsonpayment.getJSONObject(position).getString("phone"));
            holder.monto_deuda.setText(jsonpayment.getJSONObject(position).getString("amount"));
            holder.ubicacion_cliente.setText(jsonpayment.getJSONObject(position).getString("adress"));
            holder.ubicacion_cliente.setText(jsonpayment.getJSONObject(position).getString("status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonpayment.length();
    }

    public class routinaViewHolder extends RecyclerView.ViewHolder {
        TextView  nombre_cliente, apellido_cliente, monto_deuda, cedula_cliente, telefono_cliente,
                ubicacion_cliente, status ;
        public routinaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_cliente = itemView.findViewById(R.id.nombre_cliente);
            apellido_cliente = itemView.findViewById(R.id.apellido_cliente);
            monto_deuda= itemView.findViewById(R.id.monto_deuda);
            cedula_cliente = itemView.findViewById(R.id.cedula_cliente);
            telefono_cliente = itemView.findViewById(R.id.telefono_cliente);
            ubicacion_cliente = itemView.findViewById(R.id.ubicacion_cliente);
            status = itemView.findViewById(R.id.status);
        }
    }
}
