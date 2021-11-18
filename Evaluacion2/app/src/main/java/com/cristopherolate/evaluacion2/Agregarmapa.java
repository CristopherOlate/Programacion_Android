package com.cristopherolate.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Agregarmapa extends AppCompatActivity implements View.OnClickListener {


    private TextView tbxDato;
    private TextView tbxLugar;

    private Button btnGuardar;
    private Button btnVerLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarmapa);

        tbxDato = findViewById(R.id.tbxDato);
        tbxLugar = findViewById(R.id.tbxLugar);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnVerLista = findViewById(R.id.btnVerLista);

        btnGuardar.setOnClickListener(this);
        btnVerLista.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnGuardar){
            Cone conn =   new Cone(this, "db_mapa", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            try{

                String lugar = tbxLugar.getText().toString();
                String datos =  tbxDato.getText().toString();

                String INSERT = "INSERT INTO mapa (lugar, datos) VALUES ('" + lugar + "', '" + datos + "')";
                db.execSQL(INSERT);
                Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                db.close();

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error al registrar mapa", Toast.LENGTH_SHORT).show();
            }

        }

        if(v.getId() == R.id.btnVerLista){
            Intent btnVerListaIntent = new Intent(this, Lista.class);
            startActivity(btnVerListaIntent);
        }
    }

}