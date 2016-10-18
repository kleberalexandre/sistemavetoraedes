package br.com.unitcorp.sistemavetoraedes.model;

/**
 * Created by Kleber on 14/10/2016.
 */

public class Visita {
    private int id;
    private int codRua;
    private String numero;
    private int codResponsavel;
    private int codSituacaoImovel;
    private int codAgente;
    private int codTipoVisita;

    public void setId(int id) {
        this.id = id;
    }

    public void setCodAgente(int codAgente) {
        this.codAgente = codAgente;
    }

    public void setCodResponsavel(int codResponsavel) {
        this.codResponsavel = codResponsavel;
    }

    public void setCodRua(int codRua) {
        this.codRua = codRua;
    }

    public void setCodSituacaoImovel(int codSituacaoImovel) {
        this.codSituacaoImovel = codSituacaoImovel;
    }

    public void setCodTipoVisita(int codTipoVisita) {
        this.codTipoVisita = codTipoVisita;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public int getCodAgente() {
        return codAgente;
    }

    public int getCodResponsavel() {
        return codResponsavel;
    }

    public int getCodRua() {
        return codRua;
    }

    public int getCodSituacaoImovel() {
        return codSituacaoImovel;
    }

    public int getCodTipoVisita() {
        return codTipoVisita;
    }

    public String getNumero() {
        return numero;
    }
}

