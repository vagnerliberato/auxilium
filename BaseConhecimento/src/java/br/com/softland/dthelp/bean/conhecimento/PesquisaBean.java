package br.com.softland.dthelp.bean.conhecimento;

import br.com.softland.dthelp.model.connection.ConexaoAgenda;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "pesquisa")
public class PesquisaBean {

    private ArrayList<ConhecimentoBean> dados = new ArrayList<ConhecimentoBean>();
    private String referencia = null;
    private boolean visivel = false;
    private String titulo = null;
    private String previa = null;
    private String erro = null;
    private String msg = null;

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

    public ArrayList<ConhecimentoBean> getDados() {
        return dados;
    }

    public void setDados(ArrayList<ConhecimentoBean> dados) {
        this.dados = dados;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<String> pesquisa(String referencia) {

        List<String> lista = new ArrayList<String>();

        //realizar uma pesquisa LIKE no banco de dados e trazer os valores
        //do campo referencia da tabela CONHECIMENTO

        for (int i = 0; i < 10; i++) {
            lista.add(referencia + " Teste " + i);
        }

        return lista;
    }

    public ArrayList<ConhecimentoBean> carregaDados() {
        try {
            String query = "select l.data as data, c.razao as razao, l.comunicado as comunicado, l.descricao as descricao "
                    + " from ligaatende l"
                    + " join clientes c on (l.cliente = c.cod_cli)"
                    + " where l.descricao like ?"
                    + " order by l.data";

            PreparedStatement stm = ConexaoAgenda.getConnection().prepareStatement(query);

            stm.setString(1, "%" + getReferencia() + "%");

            ResultSet result = stm.executeQuery();

            if (result.next()) {



                while (result.next()) {
                    ConhecimentoBean con = new ConhecimentoBean();

                    con.setFato(result.getString("descricao"));
                    con.setReferencia(result.getString("comunicado"));

                    dados.add(con);
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