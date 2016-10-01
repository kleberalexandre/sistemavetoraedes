package br.com.unitcorp.sistemavetoraedes.model;

/**
 * Created by Kleber on 01/10/2016.
 */

public class Rua {
    private int id;
    private String nome;
    private int idquadra;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdquadra() {
        return idquadra;
    }

    public void setIdquadra(int idquadra) {
        this.idquadra = idquadra;
    }

    @Override
    public String toString() {
        return nome;
    }
}
