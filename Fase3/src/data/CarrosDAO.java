package data;

import code.*;

import java.sql.*;
import java.util.*;

public class CarrosDAO implements Map<String,Carro> {
    private static CarrosDAO singleton = null;

    private CarrosDAO(){
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD); Statement stm = conn.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS Carros ("+
                    "ID varchar(45) NOT NULL PRIMARY KEY,"+
                    "Tipo varchar(10) NOT NULL,"+
                    "Marca varchar(45) NOT NULL,"+
                    "Modelo varchar(45) NOT NULL,"+
                    "Cilindrada int(4) NOT NULL,"+
                    "Potencia int(4) NOT NULL,"+
                    "Pac double NOT NULL,"+
                    "Fiabilidade double NOT NULL," +
                    "Preparacao double )";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static CarrosDAO getInstance(){
        if(CarrosDAO.singleton == null){
            CarrosDAO.singleton = new CarrosDAO();
        }
        return CarrosDAO.singleton;
    }

    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Carros")) {
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
            String sql = "SELECT ID FROM Carros WHERE ID='" + key.toString() + "'";
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
            String sql = "SELECT ID FROM Carros WHERE Marca='" + value.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Carro get(Object key) {
        Carro c = new C1();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM Carros WHERE Nome='" + key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()) {
                String id = rs.getString("ID");
                String tipo = rs.getString("Tipo");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                int cilindrada = rs.getInt("Cilindrdada");
                int potencia = rs.getInt("Potencia");
                double pac = rs.getDouble("Pac");
                double fiabilidade = rs.getDouble("Fiabilidade");
                if (Objects.equals(tipo, "C1")) {
                    c = new C1(id, marca, modelo, cilindrada, potencia, pac, fiabilidade);
                } else if (Objects.equals(tipo, "C2")) {
                    double prep = rs.getDouble("Preparacao");
                    c = new C2(id, marca, modelo, cilindrada, potencia, pac, fiabilidade, prep);
                } else if (Objects.equals(tipo, "GT")) {
                    c = new GT(id, marca, modelo, cilindrada, potencia, pac, fiabilidade);
                } else if (Objects.equals(tipo, "SC")) {
                    c = new SC(id, marca, modelo, cilindrada, potencia, pac, fiabilidade);
                } else c = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    public Carro put(String key,Carro value) {
        Carro c = value;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "";
            String type = "";
            if (c.getClass() == C2.class){
                sql = "INSERT INTO Carros VALUES('"+ c.getIdCarro()+"','"+ "C2 "+"','"+ c.getMarca()+"','"+c.getModelo()+"','"+ c.getCilindrada()+"','"+c.getPotencia()+"','"+ c.getPac()+"','"+c.getFiabilidade()+"','"+((C2) c).getPreparacaoMecaninca()+"')";
            }else if(c.getClass() == C1.class){
                type = "C1";
            } else if(c.getClass() == GT.class) {
                type = "GT";
            } else if (c.getClass() == SC.class) {
                type = "SC";
            }
            sql = "INSERT INTO Carros VALUES('"+ c.getIdCarro()+"','"+ type +"','"+ c.getMarca()+"','"+c.getModelo()+"','"+ c.getCilindrada()+"','"+c.getPotencia()+"','"+ c.getPac()+"','"+c.getFiabilidade()+"','" + 0 + "')";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    public Carro remove(Object key) {
        Carro c = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "DELETE FROM Carros WHERE ID='"+key+"';";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    public void putAll(Map<? extends String, ? extends Carro> m) {}

    public void clear() {}

    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); Statement stm = conn.createStatement()) {
            String sql = "SELECT * FROM Carros";
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

    public Collection<Carro> values() {
        return null;
    }

    public Set<Map.Entry<String, Carro>> entrySet() {
        return null;
    }
}
