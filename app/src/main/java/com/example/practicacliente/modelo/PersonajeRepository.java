package com.example.practicacliente.modelo;

import com.example.practicacliente.R;

import java.util.ArrayList;
import java.util.List;

public class PersonajeRepository {
    private List<Personaje> personajes;
    public static PersonajeRepository instance;
    public PersonajeRepository(){
        personajes=new ArrayList<>();
        personajes.add(new Personaje("Alex", R.drawable.ravenclaw));
        personajes.add(new Personaje("Pumuki",R.drawable.sliterin));
        personajes.add(new Personaje("Geles",R.drawable.grifindor));
        personajes.add(new Personaje("Iván",R.drawable.hufflepuf));
    }
    public static PersonajeRepository getInstance() {
        if (instance == null) {
            instance = new PersonajeRepository();
        }
        return instance;
    }
    public Personaje get(int index) {
        return personajes.get(index);
    }

    public int size() {
        return personajes.size();
    }

    public void remove(Personaje usuario) { personajes.remove(usuario); }

    public void add(Personaje usuario) { personajes.add(usuario); }

    public void add(int index, Personaje usuario) {
        personajes.add(index, usuario);
    }
    public List<Personaje> getPersonajes(){
        return personajes;
    }
}
