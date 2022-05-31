package com.scd.myspa.ui.salas;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scd.myspa.R;

public class ViewHolderSala extends RecyclerView.ViewHolder
{
protected ImageView imgvFotoSala;
protected TextView txtNombreSala;
protected TextView txtDescripcionSala;

    public ViewHolderSala(@NonNull View itemView) {
        super(itemView);
        imgvFotoSala = itemView.findViewById(R.id.imgvFotoSala);
        txtNombreSala = itemView.findViewById(R.id.txtNombreSala);
        txtDescripcionSala = itemView.findViewById(R.id.txtDescripcionSala);
    }
}
