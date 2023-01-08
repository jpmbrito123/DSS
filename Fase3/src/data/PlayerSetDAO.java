package data;

import code.PlayerSet;

import java.sql.*;
import java.util.*;

public class PlayerSetDAO implements Map<String, PlayerSet> {
    private static PlayerSetDAO singleton = null;

    private PlayerSetDAO(){
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD); Statement stm = conn.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS PlayerSets ("+
                    "ID varchar(45) NOT NULL PRIMARY KEY,"+
                    "Agressividade double NOT NULL,"+
                    "Piloto varchar(45),foreign key(Piloto) references Pilotos(ID),"+
                    "Carro varchar(45) ,foreign key(Carro) references Carros(ID),"+
                    "Pneu varchar(45) NOT NULL,"+
                    "Alteracao int NOT NULL )";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static PlayerSetDAO getInstance(){
        if(PlayerSetDAO.singleton == null){
            PlayerSetDAO.singleton = new PlayerSetDAO();
        }
        return PlayerSetDAO.singleton;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM PlayerSets")) {
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
            String sql = "SELECT ID FROM PlayerSets WHERE ID='" + key.toString() + "'";
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
            String sql = "SELECT ID FROM PlayerSets WHERE Agressividade='" + value.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public PlayerSet get(Object key) {
        PlayerSet p = new PlayerSet();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM PlayerSets WHERE Nome='" + key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                p = new PlayerSet(rs.getString("ID"),rs.getDouble("Agressividade"),rs.getString("Piloto"),rs.getString("Carro"),rs.getString("Pneu"),rs.getInt("Alteracao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public PlayerSet put(String key,PlayerSet value) {
        PlayerSet p = value;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "INSERT INTO PlayerSets VALUES('"+ p.getIdPlayerSet()+"','"+ p.getAgressividadeMotriz()+"','"+p.getPiloto()+"','"+ p.getCarro()+"','"+p.getPneu()+"','"+ p.getCountAlteracoes()+"')";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public PlayerSet remove(Object key) {
        PlayerSet p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "DELETE FROM Carros WHERE ID='"+key+"';";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public void putAll(Map<? extends String, ? extends PlayerSet> m) {}

    public void clear() {}

    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM PlayerSets";
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

    public Collection<PlayerSet> values() {
        return null;
    }

    public Set<Map.Entry<String, PlayerSet>> entrySet() {
        return null;
    }
}