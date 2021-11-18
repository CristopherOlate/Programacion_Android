package com.cristopherolate.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class Lista extends AppCompatActivity {

    private ListView viewLista;

    Cone conn;
    ArrayList<String> listaInformacion;
    ArrayList<Mapa> listaMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        viewLista = findViewById(R.id.viewListaMapas);


        conn = new Cone(getApplicationContext(), "db_mapa", null,1);

        consultarMapa();

        ArrayAdapter adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        viewLista.setAdapter(adapter);

        viewLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lugar = listaMapa.get(position).getLugar();
                String dato =  listaMapa.get(position).getDatos();

                Toast.makeText(getApplicationContext(), lugar, Toast.LENGTH_SHORT).show();

                Intent editarIntent = new Intent(Lista.this, Editor.class);
                editarIntent.putExtra("datos", dato.toString());
                editarIntent.putExtra("lugar", lugar.toString());
                startActivity(editarIntent);

            }
        });

    }

    private void consultarMapa() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Mapa mapa = null;
        listaMapa =  new ArrayList<Mapa>();

        Cursor cursor = db.rawQuery("SELECT * FROM mapa", null);

        while (cursor.moveToNext()) {
            mapa =  new Mapa();
            mapa.setLugar(cursor.getString(0));
            mapa.setDatos(cursor.getString(1));
            listaMapa.add(mapa);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInformacion =  new ArrayList<String>();
        for (int i = 0; i < listaMapa.size(); i++){
            listaInformacion.add(listaMapa.get(i).getLugar()+ " \n "+ listaMapa.get(i).getDatos());
        }
    }

}