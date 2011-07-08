package br.com.softland.baseConhecimento.controller;

import br.com.softland.baseConhecimento.bean.ConhecimentoBean;
import br.com.softland.baseConhecimento.model.dao.ConhecimentoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "pesquisa")
@ViewScoped
public class PesquisaController {

    private List<ConhecimentoBean> dados = new ArrayList<ConhecimentoBean>();
    private ConhecimentoBean selecao = new ConhecimentoBean();
    private String pesquisa = null;
    private boolean visivel = false;
    private String titulo = null;
    private String previa = null;
    private String erro = null;
    private String msg = null;

    public ConhecimentoBean getSelecao() {
        return selecao;
    }

    public void setSelecao(ConhecimentoBean selecao) {
        this.selecao = selecao;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String tipo, String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, tipo, this.msg));
    }

    public String getPrevia() {
        return previa;
    }

    public void setPrevia(String previa) {
        this.previa = previa;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String tipo, String erro) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, tipo, this.erro));
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public List<ConhecimentoBean> getDados() {
        return dados;
    }

    public void setDados(ArrayList<ConhecimentoBean> dados) {
        this.dados = dados;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public List<String> pesquisaRapida(String referencia) {

        List<String> lista = new ArrayList<String>();

        //realizar uma pesquisa LIKE no banco de dados e trazer os valores
        //do campo referencia da tabela CONHECIMENTO

        for (int i = 0; i < 10; i++) {
            lista.add(referencia + " Teste " + i);
        }

        return lista;
    }

    public List<ConhecimentoBean> carregaPesquisa() {

        if (getPesquisa().isEmpty()) {
            return null;
        }

        try {
            ConhecimentoDAO dao = new ConhecimentoDAO();

            return dados = dao.BuscaConhecimento(getPesquisa());

        } catch (Exception erro) {
            setErro("Erro-Fatal", erro.getMessage());
            return null;
        }
    }

    public void atualizaVisualizacao() {
        try {
            ConhecimentoDAO dao = new ConhecimentoDAO();
            dao.updateVisual(selecao.getId_Conhecimento());
        } catch (Exception erro) {
            setErro("Erro-Fatal", erro.getMessage());
        }
    }
}