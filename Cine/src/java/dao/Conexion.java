package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private Connection cn;
    
    public void conectar() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://34.73.201.76;databaseName=CineVG", "DevDB", "DevDB2019");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
                
    }
    
    public void Cerrar() throws SQLException{
        if (cn!=null) {
            if (cn.isClosed()==false) {
                cn.close();
            }
        }
    }
    
    public static void main(String[] args) {
        Conexion dao= new Conexion();
        dao.conectar();
        if (dao.getCn()!=null) {
            System.out.println("Conectado");
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
    
}
