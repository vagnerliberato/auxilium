package br.com.softland.dthelp.bean.conhecimento;

import br.com.softland.dthelp.model.connection.ConexaoAgenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "pesquisa")
@ViewScoped
public class PesquisaBean {

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
            String query = "select l.data as data, c.razao as razao, l.solucao as solucao, "
                    + "l.comunicado as comunicado, l.descricao as descricao "
                    + " from ligaatende l"
                    + " join clientes c on (l.cliente = c.cod_cli)"
                    + " where l.descricao like ?"
                    + " order by l.data desc";

            PreparedStatement stm = ConexaoAgenda.getConnection().prepareStatement(query);

            stm.setString(1, "%" + getPesquisa() + "%");

            ResultSet result = stm.executeQuery();

            if (result.next()) {

                dados.clear();

                while (result.next()) {
                    ConhecimentoBean conhecimento = new ConhecimentoBean();

                    conhecimento.setFato(result.getString("descricao"));
                    conhecimento.setReferencia(result.getString("descricao"));
                    conhecimento.setData(result.getDate("data"));
                    conhecimento.setEsclarecimento(result.getString("solucao"));
                    /*
                    conhecimento.setAnalista(pesquisa);
                    conhecimento.setCampo(erro);
                    conhecimento.setData(result.getDate("data"));
                    conhecimento.setEsclarecimento(erro);
                    conhecimento.setFato(erro);
                    conhecimento.setId_Conhecimento(Id_Conhecimento);
                    conhecimento.setReferencia(previa);
                    conhecimento.setTags(null);
                    conhecimento.setVisual(Visual);
                    */
                    dados.add(conhecimento);
                }

                setVisivel(true);

                result.close();
                stm.close();

                return dados;

            } else {
                setMsg("Atenção", "Não foram encontrados resultados para sua pesquisa \n\n"
                        + "Tente novamente!");

                result.close();
                stm.close();

                return null;
            }
        } catch (Exception erro) {
            setErro("Erro-Fatal", erro.getMessage());
            return null;
        }
    }
}