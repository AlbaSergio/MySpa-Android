package com.scd.myspa.ui.salas;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scd.myspa.R;
import com.scd.myspa.commons.MySPACommons;
import com.scd.myspa.ui.ActivityMain;
import com.systemComunity.myspa.model.Sala;

import java.util.List;

public class AdapterSala extends RecyclerView.Adapter<ViewHolderSala>
{
    ActivityMain activityMain;
    List<Sala> salas;

    public AdapterSala(ActivityMain am, List<Sala> salas)
    {
        this.activityMain = am;
        this.salas = salas;
    }

    @NonNull
    @Override
    public ViewHolderSala onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //1. Cargamos el layout con el diseño de co,o queremos que se vea cada mercancia:
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sala, parent, false);

        //2. Creamos el ViewHolder
        ViewHolderSala vhs = new ViewHolderSala(v);

        //3. Agregamos un oyenteal View para que se dispare una accion cuando el usuario hace click
        // sobre algun elemento del recycler view
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = vhs.getAdapterPosition();
                Log.i("info", "Click en posición: "+ pos);

            }
        });

        //4. Devolvemos el ViewHolder que acabamos de crear:
        return vhs;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSala holder, int position) {
        //1. Obtenemos la mercancia en la posicion que el RecyclerView nos indico:
        Sala s = salas.get(position);

        //2. Llenamos cada control del ViewHolder con los datos de la Sala:
        holder.txtNombreSala.setText(s.getNombre());
        holder.txtDescripcionSala.setText(s.getDescripcion());

        //3. Esta es para cargar la fotografia de la Sala:
        try {
            if (s.getFoto() != null && s.getFoto().length() > 64)
                holder.imgvFotoSala.setImageBitmap(MySPACommons.fromBase64(s.getFoto()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return salas == null ? 0 : salas.size();
    }


}
