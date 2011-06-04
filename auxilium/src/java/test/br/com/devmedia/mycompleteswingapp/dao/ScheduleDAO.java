/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.br.com.devmedia.mycompleteswingapp.dao;

import test.br.com.devmedia.mycompleteswingapp.entity.Schedule;
import test.br.com.devmedia.mycompleteswingapp.entity.ScheduleType;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleDAO extends GenericDAO {

    public int addSchedule(Schedule sd) throws SQLException {
        sd.setId(getNextId("SCHEDULE"));
        String query = "INSERT INTO APP.SCHEDULE (ID, ID_ANIMAL, DATE, TYPE, TOTAL) VALUES (?,?,?,?,?)";
        executeCommand(query, sd.getId(), sd.getAnimal().getId(), sd.getDate(), sd.getType().toString(), sd.getTotal());
        return sd.getId();
    }

    public void removeSchedule(int idSchedule) throws SQLException {
        executeCommand("DELETE FROM APP.SCHEDULE WHERE ID=?");
    }

    public void updateSchedule(Schedule sd) throws SQLException {
        String query = "UPDATE APP.SCHEDULE SET ID_ANIMAL=?, DATE=?, TYPE=?, TOTAL=? WHERE ID=?";
        executeCommand(query, sd.getAnimal().getId(), sd.getDate(), sd.getType().toString(), sd.getTotal(), sd.getId());
    }

    public Schedule getSchedule(int idSchedule) throws SQLException {
        ResultSet rs = executeQuery("SELECT * FROM APP.SCHEDULE WHERE ID=?", idSchedule);
        Schedule sd = populateSchedule(rs);
        rs.close();
        return sd;
    }

    private Schedule populateSchedule(ResultSet rs) throws SQLException {
        AnimalDAO animalDAO = new AnimalDAO();
        Schedule toReturn = new Schedule();
        toReturn.setId(rs.getInt("ID"));
        toReturn.setDate(rs.getDate("DATE"));
        toReturn.setTotal(rs.getFloat("TOTAL"));
        toReturn.setType(ScheduleType.valueOf(rs.getString("TYPE")));
        toReturn.setAnimal(animalDAO.getAnimal(rs.getInt("ID_ANIMAL")));
        return toReturn;
    }
}
