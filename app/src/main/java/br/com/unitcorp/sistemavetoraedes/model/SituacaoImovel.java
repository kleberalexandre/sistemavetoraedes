package br.com.unitcorp.sistemavetoraedes.model;

/**
 * Created by Kleber on 05/10/2016.
 */

public class SituacaoImovel {
    private int id;
    private String nome;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}


