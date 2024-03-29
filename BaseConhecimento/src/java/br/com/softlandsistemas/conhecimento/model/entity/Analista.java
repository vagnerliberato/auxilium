package br.com.softlandsistemas.conhecimento.model.entity;
// Generated 01/11/2011 21:54:20 by Hibernate Tools 3.2.1.GA



/**
 * Analista generated by hbm2java
 */
public class Analista  implements java.io.Serializable {


     private String codigo;
     private String descricao;
     private String analistaPass;
     private Integer ativo;
     private short desenvolvedor;

    public Analista() {
    }

	
    public Analista(String codigo, String analistaPass, short desenvolvedor) {
        this.codigo = codigo;
        this.analistaPass = analistaPass;
        this.desenvolvedor = desenvolvedor;
    }
    public Analista(String codigo, String descricao, String analistaPass, Integer ativo, short desenvolvedor) {
       this.codigo = codigo;
       this.descricao = descricao;
       this.analistaPass = analistaPass;
       this.ativo = ativo;
       this.desenvolvedor = desenvolvedor;
    }
   
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getAnalistaPass() {
        return this.analistaPass;
    }
    
    public void setAnalistaPass(String analistaPass) {
        this.analistaPass = analistaPass;
    }
    public Integer getAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }
    public short getDesenvolvedor() {
        return this.desenvolvedor;
    }
    
    public void setDesenvolvedor(short desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }




}


