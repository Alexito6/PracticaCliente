package com.example.practicacliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicacliente.modelo.Personaje;
import com.example.practicacliente.modelo.PersonajeRepository;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecyclerView extends RecyclerView.Adapter<AdaptadorRecyclerView.MyViewHolder> implements View.OnClickListener {
    private List<Personaje> personajes;
    private LayoutInflater layoutInflater;
    private Context context;
    private View.OnClickListener onClickListener;
    public AdaptadorRecyclerView(Context context){
        this.context=context;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        personajes=PersonajeRepository.getInstance().getPersonajes();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.simple_element,parent,false);
        view.setOnClickListener(onClickListener);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imagenCasa.setImageResource(personajes.get(position).getCasa());
        holder.nombrePersonaje.setText(personajes.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onClickListener!=null){
            onClickListener.onClick(v);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagenCasa;
        private TextView nombrePersonaje;
        public MyViewHolder(@NonNull View view){
            super(view);
            imagenCasa=view.findViewById(R.id.imagenCasa);
            nombrePersonaje=view.findViewById(R.id.nomPersonaje);
        }
    }
}
