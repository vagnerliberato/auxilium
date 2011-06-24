package br.com.softland.dthelp.controller.conhecimento;

import br.com.softland.dthelp.bean.conhecimento.ConhecimentoBean;
import br.com.softland.dthelp.model.dao.conhecimento.ConhecimentoDAO;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "conhecimentoControl")
public class ConhecimentoController {

    private String analista = null;
    private String referencia = null;
    private String fato = null;
    private String esclarecimento = null;
    private String erro = null;
    private String sucesso = null;

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public String getEsclarecimento() {
        return esclarecimento;
    }

    public void setEsclarecimento(String esclarecimento) {
        this.esclarecimento = esclarecimento;
    }

    public String getFato() {
        return fato;
    }

    public void setFato(String fato) {
        this.fato = fato;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String tipo, String erro) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, tipo, erro));
    }
    
    public String getSucesso() {
        return sucesso;
    }

    public void setSucesso(String tipo, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, tipo, msg));
    }

    public void validaNovoConhecimento() {

        if (getAnalista() != null || getReferencia() != null
                || getFato() != null || getEsclarecimento() != null) {

            try {
                ConhecimentoBean novo = new ConhecimentoBean();

                novo.setAnalista(getAnalista());
                novo.setReferencia(getReferencia());
                novo.setFato(getFato());
                novo.setEsclarecimento(getEsclarecimento());
                novo.setData(new java.sql.Date(new Date().getTime()));
                novo.setCampo(novo.getAnalista() + "-" + novo.getData());
                novo.setVisual(0);

                ConhecimentoDAO dao = new ConhecimentoDAO();
                int gravacao = dao.addConhecimento(novo);

                if (gravacao < 0) {
                    setErro("Erro","Não conseguiu grava novo conhecimento. \n Tente novamente.");
                } else {
                    setSucesso("Aeee","Conhecimento gravado com sucesso");
                }

            } catch (Exception e) {
                setErro("Erro", e.getMessage());
            }
        }
    }
}
