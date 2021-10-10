package com.example.appfuncionarios;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "AppFuncionarios";
    private static final int VERSAO = 1;

    public Banco(Context context){

        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS funcionario " +
                        "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "cpf TEXT NOT NULL," +
                        "nome TEXT NOT NULL," +
                        "funcao TEXT NOT NULL," +
                        "estado_civil TEXT);");

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
