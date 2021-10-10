package com.example.appfuncionarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public static void inserir(Context context, Funcionario funcionario){

        ContentValues valores = new ContentValues();
        valores.put("cpf",funcionario.getCpf());
        valores.put("nome",funcionario.getNome());
        valores.put("funcao",funcionario.getFuncao());
        valores.put("estado_civil", funcionario.getEstadoCivil());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("funcionario", null, valores);


    }

    public static void editar(Context context, Funcionario funcionario){

        ContentValues valores = new ContentValues();
        valores.put("cpf",funcionario.getCpf());
        valores.put("nome",funcionario.getNome());
        valores.put("funcao",funcionario.getFuncao());
        valores.put("estado_civil", funcionario.getEstadoCivil());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("funcionario",valores, "id = " + funcionario.getId(),null);

    }

    public static void excluir(Context context, int idFuncionario){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("funcionario","id ="+idFuncionario,null);
    }

    public static List<Funcionario> getFuncionarios(Context context){

        List<Funcionario> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM funcionario ORDER BY nome",null);

        if (cursor.getCount() > 0 ){

            cursor.moveToFirst();

            do{
                Funcionario pro = new Funcionario();
                pro.setId(cursor.getInt(0));
                pro.setCpf(cursor.getString(1));
                pro.setNome(cursor.getString(2));
                pro.setFuncao(cursor.getString(3));
                pro.setEstadoCivil(cursor.getString(4));
                lista.add(pro);
            }while (cursor.moveToNext());

        }
        return lista;

    }

    public static Funcionario getFuncionarioByID(Context context, int idFuncionario){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM funcionario WHERE id ="+idFuncionario,null);

        if (cursor.getCount() > 0 ){

            cursor.moveToFirst();

            Funcionario pro = new Funcionario();
            pro.setId(cursor.getInt(0));
            pro.setCpf(cursor.getString(1));
            pro.setNome(cursor.getString(2));
            pro.setFuncao(cursor.getString(3));
            pro.setEstadoCivil(cursor.getString(4));

            return pro;
        } else{
            return null;}

    }
}
