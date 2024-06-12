package com.example.practicacliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicacliente.modelo.Personaje;
import com.example.practicacliente.modelo.PersonajeRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private Switch ordenar;
    private FloatingActionButton addPersonaje;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ordenar=findViewById(R.id.cambiarOrdenacion);
        addPersonaje=findViewById(R.id.addPersonaje);
        recyclerView=findViewById(R.id.recyclerView);
        AdaptadorRecyclerView adaptadorRecyclerView=new AdaptadorRecyclerView(this);
        recyclerView.setAdapter(adaptadorRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        ordenar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ordenar.setText("Casa");
                    Collections.sort(PersonajeRepository.getInstance().getPersonajes(), new Comparator<Personaje>() {
                        @Override
                        public int compare(Personaje o1, Personaje o2) {
                            return o1.getNombre().compareTo(o2.getNombre());
                        }
                    });

                }else {
                    ordenar.setText("Nombre");
                    Collections.sort(PersonajeRepository.getInstance().getPersonajes(), new Comparator<Personaje>() {
                        @Override
                        public int compare(Personaje o1, Personaje o2) {
                            return Integer.compare(o1.getCasa(),o2.getCasa());
                        }
                    });
                }
                adaptadorRecyclerView.notifyDataSetChanged();
            }
        });
        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result ->{
                    if (result.getResultCode()==RESULT_CANCELED){
                        Toast.makeText(this,"Se ha cancelado el proceso para crear un nuevo personaje",Toast.LENGTH_SHORT).show();
                    } else if (result.getResultCode()==RESULT_OK) {
                        Personaje personaje=(Personaje) result.getData().getExtras().get("nuevoPersonaje");
                        PersonajeRepository.getInstance().add(personaje);
                        adaptadorRecyclerView.notifyDataSetChanged();
                    }
                }
        );
        addPersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CrearPersonaje.class);
                activityResultLauncher.launch(intent);
            }
        });
        adaptadorRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personaje personaje=PersonajeRepository.getInstance().get(recyclerView.getChildAdapterPosition(v));
                Intent intent=new Intent(getApplicationContext(), ObservarPersonaje.class);
                intent.putExtra("personaje",personaje);
                startActivity(intent);
            }
        });
    }
}