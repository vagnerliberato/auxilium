package br.com.softland.dthelp.model.dao.conhecimento;

import br.com.softland.dthelp.bean.conhecimento.ConhecimentoBean;
import br.com.softland.dthelp.bean.*;
import br.com.softland.dthelp.model.dao.GenericDAO;
import java.sql.SQLException;

public class ConhecimentoDAO extends GenericDAO {

    public int addConhecimento(ConhecimentoBean con) throws SQLException {
        con.setId_Conhecimento(getNextId("CONHECIMENTO", "ID_CONHECIMENTO"));
        String query = "INSERT INTO CONHECIMENTO(ID_CONHECIMENTO, ANALISTA, REFERENCIA, FATO, ESCLARECIMENTO, VISUAL, CAMPO, DATA) VALUES(?,?,?,?,?,?,?,?)";
        executeCommand(query, con.getId_Conhecimento(), con.getAnalista(), con.getReferencia(), con.getFato(), con.getEsclarecimento(), con.getVisual(), con.getCampo(), con.getData());
        return con.getId_Conhecimento();


    }

    public void deleteConhecimento(int idConhecimento) throws SQLException {
        executeCommand("DELETE FROM CONHECIMENTO WHERE ID_CONHECIMENTO=?");
    }

    public void updateConhecimento(ConhecimentoBean con) throws SQLException {
        String query = "UPDATE CONHECIMENTO SET ANALISTA=?, REFERENCIA=?, FATO=?, ESCLARECIMENTO=?, VISUAL=?, CAMPO=?, DATA=? WHERE ID_CONHECIMENTO=?";
        executeCommand(query, con.getAnalista(), con.getReferencia(), con.getFato(), con.getEsclarecimento(), con.getVisual(), con.getCampo(), con.getData(), con.getId_Conhecimento());
    }
}
