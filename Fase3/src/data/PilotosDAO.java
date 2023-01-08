package data;

import code.Piloto;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PilotosDAO implements Map<String, Piloto> {

    private static PilotosDAO singleton = null;

    private PilotosDAO(){
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);Statement stm = conn.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS Pilotos ("+
                    "ID varchar(45) NOT NULL PRIMARY KEY,"+
                    "CTS int NOT NULL,"+
                    "SBA double NOT NULL,"+
                    "Nacionalidade varchar(45) NOT NULL)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static PilotosDAO getInstance(){
        if(PilotosDAO.singleton == null){
            PilotosDAO.singleton = new PilotosDAO();
        }
        return PilotosDAO.singleton;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Pilotos")) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT ID FROM Pilotos WHERE ID='" + key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public boolean containsValue(Object value) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT ID FROM Pilotos WHERE Nacionalidade='" + value.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Piloto get(Object key) {
        Piloto p = new Piloto();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM Pilotos WHERE ID='" + key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                p = new Piloto(rs.getString("ID"),rs.getInt("CTS"),rs.getDouble("SBA"),rs.getString("Nacionalidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public Piloto put(String key, Piloto value) {
        Piloto p = value;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "INSERT INTO Pilotos VALUES('"+p.getNomePiloto()+"','"+ p.getCts()+"','"+ p.getSba()+"','"+ p.getNacionalidade()+"´)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public Piloto remove(Object key) {
        Piloto p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "DELETE FROM Pilotos WHERE ID='"+ key +"';";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public void putAll(Map<? extends String, ? extends Piloto> m) {}

    public void clear() {}

    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM Pilotos";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                set.add(rs.getString("ID"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return set;
    }

    public Collection<Piloto> values() {
        return null;
    }

    public Set<Entry<String, Piloto>> entrySet() {
        return null;
    }
}
