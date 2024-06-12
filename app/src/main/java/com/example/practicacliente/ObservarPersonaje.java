package com.example.practicacliente;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicacliente.modelo.Personaje;

public class ObservarPersonaje extends AppCompatActivity {
    private ImageView casa;
    private TextView nombre;
    private Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observar_personaje);
        casa=findViewById(R.id.casa);
        nombre=findViewById(R.id.nombre);
        volver=findViewById(R.id.volver);
        Bundle extras= getIntent().getExtras();
        Personaje personaje=(Personaje) extras.get("personaje");
        assert personaje != null;
        casa.setImageResource(personaje.getCasa());
        nombre.setText(personaje.getNombre());
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
