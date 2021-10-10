package com.example.appfuncionarios;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appfuncionarios.databinding.ActivityFormularioBinding;

public class FormularioActivity extends AppCompatActivity {


    private EditText etNome;
    private EditText etCPF;
    private EditText etFuncao;
    private Spinner spEstadoCivil;
    private Button btnSalvar;
    private String acao;
    private Funcionario funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etCPF = findViewById(R.id.etCPF);
        etNome = findViewById(R.id.etNome);
        etFuncao = findViewById(R.id.etFuncao);
        spEstadoCivil = findViewById(R.id.spEstadoCivil);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        acao = getIntent().getStringExtra("acao");
        if(acao.equals("editar")){
            carregarFormulario();
        }
    }

    private void carregarFormulario(){
        int idFuncionario = getIntent().getIntExtra("idFuncionario", 0);
        funcionario = FuncionarioDAO.getFuncionarioByID(this,idFuncionario);
        etCPF.setText(funcionario.getCpf());
        etNome.setText(funcionario.getNome());
        etFuncao.setText(funcionario.getFuncao());
        String[] estadoCivil = getResources().getStringArray(R.array.estadoCivil);

        for( int i = 0; i< estadoCivil.length; i++) {
            if (funcionario.getEstadoCivil().equals(estadoCivil[i])) {
                spEstadoCivil.setSelection(i);
                break;
            }
        }
    }
    private void salvar(){
        String cpf = etCPF.getText().toString();
        String nome = etNome.getText().toString();
        String funcao = etFuncao.getText().toString();

        if(nome.isEmpty() || spEstadoCivil.getSelectedItemPosition()==0){
            Toast.makeText(this,"Você deve preencher todos os campos!",Toast.LENGTH_LONG).show();
        }else{
            if (acao.equals("inserir")){
                funcionario = new Funcionario();
            }
            funcionario.setCpf(cpf);
            funcionario.setNome(nome);
            funcionario.setFuncao(funcao);
            funcionario.setEstadoCivil(spEstadoCivil.getSelectedItem().toString());
            if (acao.equals("inserir")){
                FuncionarioDAO.inserir(this,funcionario);
                etNome.setText("");
                etCPF.setText("");
                etFuncao.setText("");
                spEstadoCivil.setSelection(0, true);
            } else {
                FuncionarioDAO.editar(this,funcionario);
                finish();
            }


        }
    }
}