package com.cristopherolate.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Editor extends AppCompatActivity implements View.OnClickListener {

    private Button btnDelete;
    private Button btnEditor;
    private Button btnActualizar;

    private TextView tbxDatos;
    private TextView tbxLugar;

    private Cone cone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        btnEditor=(Button) findViewById(R.id.btnGuardarCambios);
        btnDelete=(Button) findViewById(R.id.btnDelete);
        btnActualizar=(Button)  findViewById(R.id.btnVerLista2);

        btnEditor.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        tbxDatos = (TextView) findViewById(R.id.tbxDato);
        tbxLugar = (TextView) findViewById(R.id.tbxLugar);

        tbxLugar.setEnabled(false);

        Bundle datos =  getIntent().getExtras();

        String datoObtendio1 = datos.getString("datos");
        tbxDatos.setText(datoObtendio1);

        Bundle lugar = getIntent().getExtras();
        String datoObtendio2 = lugar.getString("lugar");


        tbxLugar.setText(datoObtendio2);

        cone2 = new Cone(getApplicationContext(), "db_mapa", null, 1);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnGuardarCambios) {

            SQLiteDatabase database = cone2.getWritableDatabase();

            try {

                database.execSQL("UPDATE mapa SET datos = '" + tbxDatos.getText().toString() + "' WHERE lugar = '" + tbxLugar.getText().toString() + "'");
                database.close();

                Toast.makeText(getApplicationContext(), "Datos Actualizados", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
            }

        }

        if (view.getId() == R.id.btnDelete) {

            try {
                SQLiteDatabase database = cone2.getWritableDatabase();

                String lugar = tbxLugar.getText().toString();

                database.execSQL("DELETE FROM mapa WHERE lugar = '"+ lugar + "'");
                database.close();


                tbxLugar.setText("");
                tbxDatos.setText("");

                Toast.makeText(getApplicationContext(),"Lugar eliminado", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error al eliminar ", Toast.LENGTH_SHORT).show();
            }
        }
       if(view.getId()== R.id.btnVerLista2){
           String lugar =  tbxLugar.getText().toString();
           String dato =  tbxDatos.getText().toString();

           Intent mapsIntent = new Intent(Editor.this, MapsActivity.class);

           mapsIntent.putExtra("datos", dato.toString());
           mapsIntent.putExtra("lugar", lugar.toString());

           startActivity(mapsIntent);

        }
    }
}