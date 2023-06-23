package com.example.teatroentradas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterObras extends RecyclerView.Adapter<AdapterObras.ViewHolderObras> {

    ArrayList<Obra> obras;

    public AdapterObras(ArrayList<Obra> obras) {
        this.obras = obras;
    }

    @NonNull
    @Override
    public AdapterObras.ViewHolderObras onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cartelera, parent, false);
        return new ViewHolderObras(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterObras.ViewHolderObras holder, int position) {
        holder.asignarDatos(obras.get(position));

    }


    @Override
    public int getItemCount() {
        return obras.size();
    }

    public class ViewHolderObras extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView id;

        public ViewHolderObras(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo_obra);
            id = itemView.findViewById(R.id.id_obra_card);
        }

        public void asignarDatos(Obra obra) {
            titulo.setText(obra.getTitulo());
            id.setText(String.valueOf(obra.getId()));
        }
    }
}

