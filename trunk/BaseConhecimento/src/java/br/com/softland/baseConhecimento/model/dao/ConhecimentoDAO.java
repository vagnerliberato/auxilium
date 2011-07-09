package br.com.softland.baseConhecimento.model.dao;

import br.com.softland.baseConhecimento.bean.ConhecimentoBean;
import br.com.softland.baseConhecimento.bean.TagBean;
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

    public int addTags(TagBean tg) throws SQLException {
        tg.setId_Tag(getNextId("TAG", "ID_TAG"));
        String query = "INSERT INTO TAG (ID_TAG, NOME) VALUES (?, ?)";
        executeCommand(query, tg.getId_Tag(), tg.getNome());
        return tg.getId_Tag();
    }

    public int updateTags(TagBean tg) throws SQLException {
        String query = "UPDATE TAG SET NOME = ? WHERE ID_TAG = ?";
        executeCommand(query, tg.getNome(), tg.getId_Tag());
        return tg.getId_Tag();
    }

    public int updateVisual(int idConhecimento) throws SQLException {
        String query = "UPDATE CONHECIMENTO SET VISUAL = ? WHERE ID_CONHECIMENTO = ?";
        executeCommand(query, getNextId("CONHECIMENTO", "VISUAL"), idConhecimento);
        return getNextId("CONHECIMENTO", "VISUAL") - 1;
    }

    public int addControleTags(TagBean tg, int idConhecimento) throws SQLException {
        String query = "INSERT INTO CONTROLETAG (ID_CONHECIMENTO, ID_TAG) VALUES (? , ?)";
        executeCommand(query, idConhecimento, tg.getId_Tag());
        return tg.getId_Tag();
    }

    public List<ConhecimentoBean> BuscaConhecimento(String dadosbusca) throws SQLException {
        List<ConhecimentoBean> toReturn = new LinkedList<ConhecimentoBean>();
        StringBuilder querySQL = new StringBuilder();

        try {
            
            String[] pedacos = dadosbusca.split("\\s");
            
            //A Select abaixo recebe a primeira palavra digitada pelo usuário.
            querySQL.append("SELECT c.ID_CONHECIMENTO, c.REFERENCIA, c.FATO, c.ESCLARECIMENTO, c.ANALISTA, c.VISUAL, c.CAMPO, c.DATA, c.ARQUIVO FROM CONHECIMENTO C "
                    + "LEFT JOIN CONTROLETAG CT ON (CT.ID_CONHECIMENTO = C.ID_CONHECIMENTO) "
                    + "LEFT JOIN TAG T ON (CT.id_tag = T.id_tag) "
                    + "WHERE UPPER(C.FATO) LIKE '%' || UPPER('" + pedacos[0] + "') || '%'"
                    + " OR UPPER(C.referencia) LIKE '%' || UPPER('" + pedacos[0] + "') || '%' "
                    + " OR UPPER(C.ESCLARECIMENTO) LIKE '%' || UPPER('" + pedacos[0] + "') || '%'"
                    + " OR UPPER(T.NOME) LIKE '%' || UPPER('" + pedacos[0] + "') || '%'");
            
            // Aqui estavamos fazendo uma verificação do número de palavras em que é adicionada dinamicamente na SELECT acima.
            for (int qtdpalavras = 1; qtdpalavras < pedacos.length; qtdpalavras++) {
                querySQL.append(" OR UPPER(C.FATO) LIKE '%' || UPPER('" + pedacos[qtdpalavras] + "') || '%'"
                    + " OR UPPER(C.referencia) LIKE '%' || UPPER('" + pedacos[qtdpalavras] + "') || '%' "
                    + " OR UPPER(C.ESCLARECIMENTO) LIKE '%' || UPPER('" + pedacos[qtdpalavras] + "') || '%'"
                    + " OR UPPER(T.NOME) LIKE '%' || UPPER('" + pedacos[qtdpalavras] + "') || '%'" );
            }
            
            // Fiz o agrupamento para trazer os melhores resultados conforme os dados mencionados pelo usuário.
            querySQL.append(" GROUP BY c.ID_CONHECIMENTO, c.REFERENCIA, c.FATO, c.ESCLARECIMENTO, c.ANALISTA, c.VISUAL, c.CAMPO, c.DATA, c.ARQUIVO");
                        
            ResultSet rs = executeQuery(querySQL.toString());

            if (rs != null) {

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
