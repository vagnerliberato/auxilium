package br.com.softland.auxilium.dao;

import br.com.softland.auxilium.bean.*;
import java.sql.SQLException;

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
}
