/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softland.dthelp.test;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author arthemus
 */
@ManagedBean(name = "conhecimento")
public class Conhecimentos {

    private String descricao = null;
    private String cliente = null;
    private String data = null;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
