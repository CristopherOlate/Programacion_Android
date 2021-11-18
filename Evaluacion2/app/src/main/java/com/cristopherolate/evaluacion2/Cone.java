package com.cristopherolate.evaluacion2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Cone extends SQLiteOpenHelper {

    final String CREAR_TABLA_SENSORES = "CREATE TABLE sensores (nombre_sensor TEXT, valor_sensore TEXT, fecha_hora TEXT)";

    final String CREAR_TABLA_MAPS = "CREATE TABLE mapa (lugar TEXT, datos TEXT)";

    public Cone(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_SENSORES);
        db.execSQL(CREAR_TABLA_MAPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sensores");

        db.execSQL("DROP TABLE IF EXISTS mapa");

        onCreate(db);
    }
}

