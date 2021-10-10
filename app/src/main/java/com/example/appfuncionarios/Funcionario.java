package com.example.appfuncionarios;

public class Funcionario {
    public int id;
    public String cpf, nome, funcao, estadoCivil;

    public Funcionario() {

    }

    public Funcionario(String cpf, String nome, String funcao, String estadoCivil) {
        this.cpf = cpf;
        this.nome = nome;
        this.funcao = funcao;
        this.estadoCivil = estadoCivil;
    }

    public Funcionario(int id, String cpf, String nome, String funcao, String estadoCivil) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.funcao = funcao;
        this.estadoCivil = estadoCivil;
    }

    public Funcionario(String listaVazia) {

    }


    @Override
    public String toString() {
        return "Funcionário(a) " + nome + " /CPF: " + cpf +
                " /Função: "+ funcao + " /Estado Civil: " + estadoCivil;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}

