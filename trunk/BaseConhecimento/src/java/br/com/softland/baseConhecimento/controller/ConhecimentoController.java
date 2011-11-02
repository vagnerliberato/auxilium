package br.com.softland.baseConhecimento.controller;

import br.com.softland.baseConhecimento.bean.ConhecimentoBean;
import br.com.softland.baseConhecimento.bean.TagBean;
import br.com.softland.baseConhecimento.global.Funcoes;
import br.com.softland.baseConhecimento.model.connection.ConexaoAgenda;
import br.com.softland.baseConhecimento.model.dao.ConhecimentoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "conhecimentoControl")
@ViewScoped
public class ConhecimentoController {

    private String idAnalista = null;
    private String referencia = null;
    private String fato = null;
    private String esclarecimento = null;
    private String arquivo = null;
    private String erro = null;
    private String sucesso = null;
    private int selecionado;
    private List<SelectItem> analistas = new ArrayList<SelectItem>();
    private TagBean tag = new TagBean();
    private List<TagBean> ListaTags = new ArrayList<TagBean>();

    public ConhecimentoController() {
        carregaAnalistas();
    }

    public TagBean getTag() {
        return tag;
    }

    public List<TagBean> getListaTags() {
        return ListaTags;
    }
    
     public String NovaTag(){
        this.tag = new TagBean();                 
        
        return null;
    }

    public void setListaTags(List<TagBean> ListaTags) {
        this.ListaTags = ListaTags;
    }
    
    public void setTag(TagBean tag) {
        this.tag = tag;
    }

    public int getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(int selecionado) {
        this.selecionado = selecionado;
    }

    public List<SelectItem> getAnalistas() {
        return analistas;
    }

    public void setAnalistas(List<SelectItem> analistas) {
        this.analistas = analistas;
    }

    public List<SelectItem> carregaAnalistas() {
        try {
            String query = "select codigo, descricao from analista order by descricao";

            PreparedStatement stm = ConexaoAgenda.getConnection().prepareStatement(query);

            ResultSet result = stm.executeQuery();

            if (result.next()) {

                analistas.clear();

                while (result.next()) {

                    SelectItem item = new SelectItem(result.getString("codigo"), result.getString("descricao"));

                    analistas.add(item);
                }

                result.close();
                stm.close();

                return analistas;

            } else {

                result.close();
                stm.close();

                return null;
            }
        } catch (Exception erro) {
            setErro("Erro-Fatal", erro.getMessage());
            return null;
        }
    }

    public String getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(String idAnalista) {
        this.idAnalista = idAnalista;
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

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public void validaNovoConhecimento() {

        if (getReferencia() != null || getFato() != null || getEsclarecimento() != null) {

            try {
                ConhecimentoBean novo = new ConhecimentoBean();

                novo.setAnalista(getIdAnalista());
                novo.setReferencia(getReferencia());
                novo.setFato(getFato());
                novo.setEsclarecimento(getEsclarecimento());
                novo.setData(new java.sql.Date(new Date().getTime()));
                novo.setCampo(Funcoes.geraCampo(getIdAnalista()));
                novo.setVisual(0);
                novo.setArquivo(getArquivo());
                novo.setTags(ListaTags);

                ConhecimentoDAO dao = new ConhecimentoDAO();
                int gravacao = dao.addConhecimento(novo);

                if (gravacao < 0) {
                    setErro("Problemas...", "Não consegui grava novo conhecimento. \n Tente novamente.");
                } else {
                    setSucesso("Legal!", "Conhecimento gravado com sucesso!");
                }

            } catch (Exception e) {
                setErro("Erro-Fatal", e.getMessage());
            }
        }
    }

    public void fileUpload(FileUploadEvent event) {
        try {
            setArquivo("" + event.getFile().getInputstream());
        } catch (Exception erro) {
            setErro("Erro-Fatal", erro.getMessage());
        }
    }

    public void limparTela() {
        this.referencia = null;
        this.fato = null;
        this.esclarecimento = null;
        this.ListaTags.removeAll(ListaTags);
    }   
}
