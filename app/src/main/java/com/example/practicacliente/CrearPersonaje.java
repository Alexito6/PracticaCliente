package com.example.practicacliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicacliente.modelo.Personaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrearPersonaje extends AppCompatActivity {
    private TextView nomNuevo;
    private Spinner casasDisponibles;
    private Button enviar;
    private Button cancelar;
    private String casaSeleccionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creador_personajes);
        cancelar=findViewById(R.id.cancelar);
        nomNuevo=findViewById(R.id.nomNuevo);
        casasDisponibles=findViewById(R.id.casasDisponibles);
        enviar=findViewById(R.id.enviar);
        List<String> casas=new ArrayList<>(Arrays.asList("Grifindor","Hufflepuf","Ravenclaw","Sliterin"));
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,casas);
        casasDisponibles.setAdapter(arrayAdapter);
        casasDisponibles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                casaSeleccionada=casas.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                if (casaSeleccionada.equals("Grifindor")){
                    intent.putExtra("nuevoPersonaje", new Personaje(nomNuevo.getText().toString(),R.drawable.grifindor));
                } else if (casaSeleccionada.equals("Hufflepuf")) {
                    intent.putExtra("nuevoPersonaje", new Personaje(nomNuevo.getText().toString(),R.drawable.hufflepuf));
                } else if (casaSeleccionada.equals("Ravenclaw")) {
                    intent.putExtra("nuevoPersonaje", new Personaje(nomNuevo.getText().toString(),R.drawable.ravenclaw));
                } else if (casaSeleccionada.equals("Sliterin")) {
                    intent.putExtra("nuevoPersonaje", new Personaje(nomNuevo.getText().toString(),R.drawable.sliterin));
                }
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
