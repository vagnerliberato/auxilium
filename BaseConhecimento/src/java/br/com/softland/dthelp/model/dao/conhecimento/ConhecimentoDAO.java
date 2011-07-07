package br.com.softland.dthelp.model.dao.conhecimento;

import br.com.softland.dthelp.bean.conhecimento.ConhecimentoBean;
import br.com.softland.dthelp.bean.conhecimento.TagBean;
import br.com.softland.dthelp.model.dao.GenericDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConhecimentoDAO extends GenericDAO {

    public int addConhecimento(ConhecimentoBean con) throws SQLException {
        con.setId_Conhecimento(getNextId("CONHECIMENTO", "ID_CONHECIMENTO"));
        String query = "INSERT INTO CONHECIMENTO(ID_CONHECIMENTO, ANALISTA, REFERENCIA, FATO, ESCLARECIMENTO, VISUAL, CAMPO, DATA) VALUES(?,?,?,?,?,?,?,?)";
        executeCommand(query, con.getId_Conhecimento(), con.getAnalista(), con.getReferencia(), con.getFato(), con.getEsclarecimento(), con.getVisual(), con.getCampo(), con.getData());

        for (TagBean tags : con.getTags()) {
            addTags(tags);
            addControleTags(tags, con.getId_Conhecimento());
        }
        return con.getId_Conhecimento();
    }

    public void deleteConhecimento(int idConhecimento) throws SQLException {
        executeCommand("DELETE FROM TAG T WHERE T.ID_TAG IN (SELECT ct.ID_TAG from CONTROLETAG ct where ct.ID_CONHECIMENTO = ?)");
        executeCommand("DELETE FROM CONHECIMENTO WHERE ID_CONHECIMENTO=?");
    }

    public void updateConhecimento(ConhecimentoBean con) throws SQLException {
        String query = "UPDATE CONHECIMENTO SET ANALISTA=?, REFERENCIA=?, FATO=?, ESCLARECIMENTO=?, VISUAL=?, CAMPO=?, DATA=? WHERE ID_CONHECIMENTO=?";
        executeCommand(query, con.getAnalista(), con.getReferencia(), con.getFato(), con.getEsclarecimento(), con.getVisual(), con.getCampo(), con.getData(), con.getId_Conhecimento());

        for (TagBean tags : con.getTags()) {
            updateTags(tags);
        }
    }

    private int addTags(TagBean tg) throws SQLException {
        tg.setId_Tag(getNextId("TAG", "ID_TAG"));
        String query = "INSERT INTO TAG (ID_TAG, NOME) VALUES (?, ?)";
        executeCommand(query, tg.getId_Tag(), tg.getNome());
        return tg.getId_Tag();
    }

    private int updateTags(TagBean tg) throws SQLException {
        String query = "UPDATE TAG SET NOME = ? WHERE ID_TAG = ?";
        executeCommand(query, tg.getNome(), tg.getId_Tag());
        return tg.getId_Tag();
    }

    private int addControleTags(TagBean tg, int idConhecimento) throws SQLException {
        String query = "INSERT INTO CONTROLETAG (ID_CONHECIMENTO, ID_TAG) VALUES (? , ?)";
        executeCommand(query, idConhecimento, tg.getId_Tag());
        return tg.getId_Tag();
    }

    private List<ConhecimentoBean> BuscaConhecimento(String dadosbusca) throws SQLException {
        List<ConhecimentoBean> toReturn = new LinkedList<ConhecimentoBean>();
        StringBuilder querySQL = new StringBuilder();

        try {
            querySQL.append("SELECT c.ID_CONHECIMENTO, c.REFERENCIA, c.FATO, c.ESCLARECIMENTO, c.ANALISTA, c.VISUAL, c.CAMPO, c.DATA, c.ARQUIVO FROM CONHECIMENTO C "
                    + "LEFT JOIN CONTROLETAG CT ON (CT.ID_CONHECIMENTO = C.ID_CONHECIMENTO) "
                    + "LEFT JOIN TAG T ON (CT.id_tag = T.id_tag) "
                    + "WHERE UPPER(C.FATO) LIKE '%' || UPPER('" + dadosbusca + "') || '%'"
                    + "OR UPPER(C.referencia) LIKE '%' || UPPER('" + dadosbusca + " ') || '%' "
                    + "OR UPPER(C.ESCLARECIMENTO) LIKE '%' || UPPER('" + dadosbusca + " ') || '%'");

            ResultSet rs = executeQuery(querySQL.toString());

            if (rs.next()) {

                while (rs.next()) {
                    ConhecimentoBean conhecimento = new ConhecimentoBean();

                    conhecimento.setAnalista(rs.getString("ANALISTA"));
                    conhecimento.setCampo(rs.getString("CAMPO"));
                    conhecimento.setData(rs.getDate("DATA"));
                    conhecimento.setEsclarecimento(rs.getString("ESCLARECIMENTO"));
                    conhecimento.setFato(rs.getString("FATO"));
                    conhecimento.setId_Conhecimento(rs.getInt("ID_CONHECIMENTO"));
                    conhecimento.setReferencia(rs.getString("REFERENCIA"));
                    conhecimento.setTags(null);
                    conhecimento.setVisual(rs.getInt("VISUAL"));
                    conhecimento.setArquivo("ARQUIVO");

                    toReturn.add(conhecimento);
                }

            }

            rs.close();
            return toReturn;
            
        } catch (Exception e) {
            return null;
        }
    }
}
