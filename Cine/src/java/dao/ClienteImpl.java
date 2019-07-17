package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author Ctorres
 */
public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void regitrar(Cliente cli) throws Exception {
        this.conectar();
        try {
            String sql = "insert into cliente (NOMCLI,APECLI,NACCLI,SEXCLI,ESTCLI) values (?,?,?,?,?)";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, cli.getNOMCLI());
            ps.setString(2, cli.getAPECLI());
            ps.setDate(3, new java.sql.Date(cli.getNACCLI().getTime()));
            ps.setString(4, cli.getSEXCLI());
            ps.setString(5, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Cliente> listarcli(String estado) throws Exception {
        Cliente cliente;
        List<Cliente> list;
        this.conectar();
        try {
            list = new ArrayList();
            String sql = "select*from CLIENTE where ESTCLI='" + estado + "'";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIDCLI(rs.getInt("IDCLI"));
                cliente.setNOMCLI(rs.getString("NOMCLI"));
                cliente.setAPECLI(rs.getString("APECLI"));
                cliente.setNACCLI(rs.getDate("NACCLI"));
                cliente.setSEXCLI(rs.getString("SEXCLI"));
                cliente.setESTCLI(rs.getString("ESTCLI"));
                list.add(cliente);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Erroe: " + e);
            throw e;
        } finally {
            this.Cerrar();
        }
        return list;
    }

    @Override
    public void modificar(Cliente cli) throws Exception {
        this.conectar();
        try {
            String sql = "update CLIENTE SET NOMCLI=?, APECLI=?, NACCLI=?, SEXCLI=? WHERE IDCLI=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, cli.getNOMCLI());
            ps.setString(2, cli.getAPECLI());
            ps.setDate(3, new java.sql.Date(cli.getNACCLI().getTime()));
            ps.setString(4, cli.getSEXCLI());
            ps.setInt(5, cli.getIDCLI());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error dao: " + e);
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    public void habilitar(Cliente cli) throws Exception{
         this.conectar();
        try {
            String sql = "update CLIENTE  set ESTCLI='A' where IDCLI=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, cli.getIDCLI());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(Cliente cli) throws Exception {
        this.conectar();
        try {
            String sql = "update CLIENTE  set ESTCLI='I' where IDCLI=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, cli.getIDCLI());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Cliente> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
