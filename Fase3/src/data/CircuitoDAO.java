package data;

import code.Circuito;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CircuitoDAO implements Map<String, Circuito> {
    private static CircuitoDAO singleton = null;
    private CircuitoDAO(){
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD); Statement stm = conn.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS Circuitos ("+
                    "ID varchar(45) NOT NULL PRIMARY KEY,"+ //nome
                    "Chicane int NOT NULL,"+
                    "Retas int NOT NULL,"+
                    "Curvas int NOT NULL,"+
                    "Distancia int NOT NULL,"+
                    "Nvoltas int NOT NULL,"+
                    "Tempomedio double NOT NULL,"+
                    "Record double NOT NULL,"+
                    "TempoBox double NOT NULL,"+
                    "TempoDesvio double NOT NULL,"+
                    "DificuldadeRetas varchar(100) NOT NULL,"+
                    "DificuldadeCurvas varchar(100) NOT NULL )";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static CircuitoDAO getInstance(){
        if(CircuitoDAO.singleton == null){
            CircuitoDAO.singleton = new CircuitoDAO();
        }
        return CircuitoDAO.singleton;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Circuitos")) {
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
            String sql = "SELECT ID FROM Circuitos WHERE ID='" + key + "'";
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
            String sql = "SELECT ID FROM Circuitos WHERE Distancia='" + value.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Circuito get(Object key) {
        Circuito p = new Circuito();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM Circuitos WHERE ID='" + key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()) {
                p = new Circuito(rs.getString("ID"), rs.getInt("Chicane"), rs.getInt("Retas"), rs.getInt("Curvas"), rs.getInt("Distancia"), rs.getInt("Nvoltas"), rs.getDouble("Tempomedio"), rs.getDouble("Record"), rs.getDouble("TempoBox"), rs.getDouble("TempoDesvio"), rs.getString("DificuldadeRetas"), rs.getString("DificuldadeCurvas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public Circuito put(String key,Circuito value) {
        Circuito c = value;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "INSERT INTO Circuitos VALUES('"+ c.getNomeCircuito()+"','"+ c.getChicane()+"','"+ c.getRetas()+"','"+ c.getCurvas()+"','"+ c.getDistancia()+"','"+ c.getNvoltas()+"','"+ c.getTempoMedio()+"','"+ c.getRecorde()+"','"+ c.getTempoBox()+"','"+ c.getTempoDesvio()+"','"+ c.getListaDificuldadeRetasString()+"','"+c.getListaDificuldadeCurvasString()+"')";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    public Circuito remove(Object key) {
        Circuito p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "DELETE FROM Circuitos WHERE ID='"+key+"';";
            stm.executeUpdate(sql);
            p = new Circuito();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public void putAll(Map<? extends String, ? extends Circuito> m) {}

    public void clear() {}

    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM Circuitos";
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

    public Collection<Circuito> values() {
        return null;
    }

    public Set<Entry<String, Circuito>> entrySet() {
        return null;
    }
}
