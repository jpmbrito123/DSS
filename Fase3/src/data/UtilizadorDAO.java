package data;

import code.Utilizador;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class UtilizadorDAO implements Map<String, Utilizador> {
    private static UtilizadorDAO singleton = null;

    public UtilizadorDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Utilizadores (" +
                    "ID varchar(45) NOT NULL PRIMARY KEY," +
                    "Password varchar(45) NOT NULL," +
                    "Tipo int(4) NOT NULL )";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilizadorDAO getInstance() {
        if (UtilizadorDAO.singleton == null) {
            UtilizadorDAO.singleton = new UtilizadorDAO();
        }
        return UtilizadorDAO.singleton;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Utilizadores")) {
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
            String sql = "SELECT ID FROM Utilizadores WHERE ID='" + key.toString() + "'";
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
            String sql = "SELECT ID FROM Utilizadores WHERE Tipo='" + value.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
/*
    public boolean existeUser(String name, String pass) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT Id FROM Utilizadores WHERE Nome='" + name + "' AND Password='" + pass + "'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
 */
    public Utilizador get(Object Key) {
        Utilizador u = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM Utilizadores WHERE ID='" + Key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                u = new Utilizador(rs.getString("ID"), rs.getString("Password"),rs.getInt("Tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return u;
    }

    public Utilizador put(String key, Utilizador value) {
        Utilizador u = value;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "INSERT INTO Utilizadores VALUES('"+ u.getUtilizadorNome()+"','"+ u.getUtilizadorPassword()+"','"+ u.getUtilizadorTipo()+"');";
            stm.executeUpdate(sql);;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return u;
    }

    public Utilizador remove(Object key) {
        Utilizador u;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "DELETE FROM Utilizadores WHERE ID='"+key+"';";
            stm.executeUpdate(sql);
            u = new Utilizador();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return u;
    }

    public void putAll(Map<? extends String, ? extends Utilizador> m) {

    }

    public void clear() {

    }

    public Set<String> keySet() {
        return null;
    }

    public Collection<Utilizador> values() {
        return null;
    }

    public Set<Entry<String, Utilizador>> entrySet() {
        return null;
    }
}


