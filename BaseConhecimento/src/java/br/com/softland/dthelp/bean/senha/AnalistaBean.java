package br.com.softland.dthelp.bean.senha;

import java.io.Serializable;

public class AnalistaBean {

    private String codigo = null;
    private String nome = null;
    private String senha = null;
    private boolean ativo = false;
    private boolean desenvolvedor = false;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(boolean desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
