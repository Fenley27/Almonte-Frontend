package com.example.hellojbhbjbjj.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellojbhbjbjj.Activities.InfoPrestamoActivity;
import com.example.hellojbhbjbjj.Activities.PaymentActivity;
import com.example.hellojbhbjbjj.Clients;
import com.example.hellojbhbjbjj.R;
import com.example.hellojbhbjbjj.Rutina;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoutinaAdapter extends RecyclerView.Adapter<RoutinaAdapter.routinaViewHolder> {

    Context context;
    List<Rutina> clientList;

    public RoutinaAdapter(Context context, List<Rutina> serverList) {
        this.context = context;
        this.clientList = serverList;
    }

    @NonNull
    @Override
    public RoutinaAdapter.routinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rutinas_list_content, null, false);
        return new RoutinaAdapter.routinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutinaAdapter.routinaViewHolder holder, final int position) {
        holder.nombre_cliente.setText(clientList.get(position).getFirstname());
        holder.apellido_cliente.setText(clientList.get(position).getLastname());
        holder.cedula_cliente.setText(clientList.get(position).getIdentification());
        holder.telefono_cliente.setText(clientList.get(position).getPhone());
        holder.monto_deuda.setText(clientList.get(position).getAmount());
        holder.ubicacion_cliente.setText(""+clientList.get(position).getAdress());
        holder.fecha_hoy.setText(clientList.get(position).getDate());
        holder.detailsClient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String id = clientList.get(position).getId();
                Intent i = new Intent(context, InfoPrestamoActivity.class);
                i.putExtra("id",id);
                context.startActivity(i);
            }
        });

        holder.payment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String id = clientList.get(position).getId();
                Intent i = new Intent(context, PaymentActivity.class);
                i.putExtra("id",id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }


    public class routinaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre_cliente, apellido_cliente, monto_deuda, cedula_cliente, telefono_cliente,
                ubicacion_cliente, fecha_hoy;
        Button detailsClient, payment;
        public routinaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_cliente = itemView.findViewById(R.id.nombre_cliente);
            apellido_cliente = itemView.findViewById(R.id.apellido_cliente);
            monto_deuda= itemView.findViewById(R.id.monto_deuda);
            cedula_cliente = itemView.findViewById(R.id.cedula_cliente);
            telefono_cliente = itemView.findViewById(R.id.telefono_cliente);
            ubicacion_cliente = itemView.findViewById(R.id.ubicacion_cliente);
            fecha_hoy = itemView.findViewById(R.id.fecha_hoy);
            detailsClient = itemView.findViewById(R.id.btnVerCliente);
            payment = itemView.findViewById(R.id.btnPagar);
        }
    }

}
