package br.com.softlandsistemas.conhecimento.model.bean;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ConhecimentoBean {

    private int Id_Conhecimento = 0;
    private String Analista = null;
    private String IDAnalista = null;
    private String Referencia = null;
    private String Fato = null;
    private String Esclarecimento = null;
    private int Visual = 0;
    private String Campo = null;
    private Date Data = new Date();
    private String Arquivo = null;
    private List<TagBean> tags = new LinkedList<TagBean>();

    public List<TagBean> getTags() {
        return tags;
    }

    public void setTags(List<TagBean> tags) {
        this.tags = tags;
    }

    public String getArquivo() {
        return Arquivo;
    }

    public void setArquivo(String Arquivo) {
        this.Arquivo = Arquivo;
    }

    public String getIDAnalista() {
        return IDAnalista;
    }

    public void setIDAnalista(String IDAnalista) {
        this.IDAnalista = IDAnalista;
    }

    public String getAnalista() {
        return Analista;
    }

    public void setAnalista(String Analista) {
        this.Analista = Analista;
    }

    public String getCampo() {
        return Campo;
    }

    public void setCampo(String Campo) {
        this.Campo = Campo;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public String getEsclarecimento() {
        return Esclarecimento;
    }

    public void setEsclarecimento(String Esclarecimento) {
        this.Esclarecimento = Esclarecimento;
    }

    public String getFato() {
        return Fato;
    }

    public void setFato(String Fato) {
        this.Fato = Fato;
    }

    public int getId_Conhecimento() {
        return Id_Conhecimento;
    }

    public void setId_Conhecimento(int Id_Conhecimento) {
        this.Id_Conhecimento = Id_Conhecimento;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public int getVisual() {
        return Visual;
    }

    public void setVisual(int Visual) {
        this.Visual = Visual;
    }
}
