package br.com.softlandsistemas.conhecimento.controller;

import br.com.softlandsistemas.conhecimento.model.bean.ConhecimentoBean;
import br.com.softlandsistemas.conhecimento.model.dao.ConhecimentoDAO;
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
    private String nomeTags = null;

    public ConhecimentoBean getSelecao() {
        return selecao;
    }

    public void setSelecao(ConhecimentoBean selecao) {
        montaTags(selecao);
        this.selecao = selecao;
    }

    public String getNomeTags() {
        return nomeTags;
    }

    public void setNomeTags(String nomeTags) {
        this.nomeTags = nomeTags;
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

    public String montaTags(ConhecimentoBean conhecimento) {

        try {
            ConhecimentoDAO dao = new ConhecimentoDAO();

            nomeTags = dao.BuscaTags(conhecimento.getId_Conhecimento());

            if (nomeTags != null) {
                return nomeTags;
            } else {
                return null;
            }

        } catch (Exception e) {
            setErro("Erro-Fatal", e.getMessage());
            return null;
        }
    }

    public List<ConhecimentoBean> carregaPesquisa() {

        if (getPesquisa().isEmpty()) {
            return null;
        }

        try {
            ConhecimentoDAO dao = new ConhecimentoDAO();

            this.dados = dao.BuscaConhecimento(this.getPesquisa());

            if (dados == null) {
                setMsg("Pesquisa", "Que pena, não foi encontrado nenhum resultado para sua"
                        + "sua pesquisa. \n Reescreva seu titulo e tente novamente.");

                return null;
            } else {
                return this.dados;
            }

        } catch (Exception erro) {
            setErro("Erro-Fatal", erro.getMessage());
            return null;
        }
    }

    public void atualizaVisualizacao(ConhecimentoBean conhecimento) {
        try {
            ConhecimentoDAO dao = new ConhecimentoDAO();
            dao.updateVisual(conhecimento.getId_Conhecimento());
        } catch (Exception erro) {
            setErro("Erro-Fatal", erro.getMessage());
        }
    }

    public void updateConhecimento() {

        if (!"".equals(selecao.getFato()) && !"".equals(selecao.getEsclarecimento()) && !"".equals(selecao.getReferencia())) {
            try {
                ConhecimentoDAO dao = new ConhecimentoDAO();

                dao.updateConhecimento(this.getSelecao());
            } catch (Exception erro) {
                setErro("Erro-Fatal", erro.getMessage());
            }
        }
    }
}