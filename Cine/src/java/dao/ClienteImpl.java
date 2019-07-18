package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void regitrar(Cliente cliente) throws Exception {
        try {
            conectar();
            String sql = "insert into CLIENTE (NOMCLI,APECLI,NACCLI,SEXCLI, ESTCLI)"
                    + "values(?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cliente.getNOMCLI());
            ps.setString(2, cliente.getAPECLI());
            ps.setString(3, cliente.getNACCLI());
            ps.setString(4, cliente.getSEXCLI());
            ps.setString(5, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar" + e.getMessage());
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Cliente cliente) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE EMPLEADO SET NOMCLI=?, APECLI=?, NACCLI=?, SEXCLI=?  WHERE IDCLI=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cliente.getNOMCLI());
            ps.setString(2, cliente.getAPECLI());
            ps.setString(3, cliente.getNACCLI());
            ps.setString(4, cliente.getSEXCLI());
            ps.setInt(5, cliente.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("error en actualizar" + e.getMessage());
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(Cliente cliente) throws Exception {
        try {
            this.conectar();
            String sql = "update CLIENTE set ESTCLI = 'I' where IDCLI = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, cliente.getIDCLI());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> listado;
        ResultSet rs;
        try {
            conectar();
            String sql = "SELECT * FROM CLIENTE WHERE ESTCLI='A'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIDCLI(rs.getInt("IDCLI"));
                cli.setNOMCLI(rs.getString("NOMCLI"));
                cli.setAPECLI(rs.getString("APECLI"));
                cli.setNACCLI(rs.getString("NACCLI"));
                cli.setSEXCLI(rs.getString("SEXCLI"));
                listado.add(cli);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }

}
