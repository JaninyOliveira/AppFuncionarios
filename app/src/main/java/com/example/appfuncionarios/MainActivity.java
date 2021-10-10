package com.example.appfuncionarios;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvFuncionarios;
    private List<Funcionario> listaDeFuncionarios;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FormularioActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });
        lvFuncionarios = findViewById(R.id.lvFuncionarios);


        lvFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,FormularioActivity.class);
                intent.putExtra("acao", "editar");
                int idFuncionario = listaDeFuncionarios.get(position).getId();
                intent.putExtra("idFuncionario",idFuncionario);
                startActivity(intent);
            }
        });

        lvFuncionarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                excluir(position);
                return true;
            }
        });

        carregarLista();
    }

    private void excluir(int posicao){

        Funcionario funcSelecionado = listaDeFuncionarios.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Confirma a exclusão do funcionário(a) " + funcSelecionado.getNome() +"?");
        alerta.setNeutralButton("Cancelar",null);

        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FuncionarioDAO.excluir(MainActivity.this, funcSelecionado.getId());
                carregarLista();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarLista();
    }


    private void carregarLista(){

        listaDeFuncionarios = FuncionarioDAO.getFuncionarios(this);

        if(listaDeFuncionarios.size() == 0){
            Funcionario fake = new Funcionario("Lista Vazia");
            listaDeFuncionarios.add(fake);
            lvFuncionarios.setEnabled(false);
        }else{
            lvFuncionarios.setEnabled(true);
        }

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaDeFuncionarios);
        lvFuncionarios.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}