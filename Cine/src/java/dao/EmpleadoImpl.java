package dao;

import dao.Conexion;
import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoImpl extends Conexion implements ICRUD<Empleado> {

    @Override
    public void regitrar(Empleado empleado) throws Exception {
        try {
            conectar();
            String sql = "insert into EMPLEADO (NOMEMP,APEEMP,DNIEMP,DIREMP,TIPEMP,SEXEMP,USUEMP,PWDEMP,CODUBI,ESTEMP)"
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, empleado.getNOMEMP());
            ps.setString(2, empleado.getAPEEMP());
            ps.setString(3, empleado.getDNIEMP());
            ps.setString(4, empleado.getDIREMP());
            ps.setString(5, empleado.getTIPEMP());
            ps.setString(6, empleado.getSEXEMP());
            ps.setString(7, empleado.getUSUEMP());
            ps.setString(8, empleado.getPWDEMP());
            ps.setString(9, empleado.getCODUBI());
            ps.setString(10, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar" + e.getMessage());
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Empleado empleado) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE EMPLEADO SET NOMEMP=?, APEEMP=?, DNIEMP=?, DIREMP=?, TIPEMP=?, SEXEMP=?,USUEMP=?, PWDEMP=?, CODUBI=?  WHERE IDEMP=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, empleado.getNOMEMP());
            ps.setString(2, empleado.getAPEEMP());
            ps.setString(3, empleado.getDNIEMP());
            ps.setString(4, empleado.getDIREMP());
            ps.setString(5, empleado.getTIPEMP());
            ps.setString(6, empleado.getSEXEMP());
            ps.setString(7, empleado.getUSUEMP());
            ps.setString(8, empleado.getPWDEMP());
            ps.setString(9, empleado.getCODUBI());
            ps.setInt(10, empleado.getIDEMP());
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
    public void eliminar(Empleado empleado) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE EMPLEADO SET ESTEMP = 'I' where IDEMP = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, empleado.getIDEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Empleado> listar() throws Exception {
        List<Empleado> listado;
        ResultSet rs;
        try {
            conectar();
            String sql = "SELECT IDEMP, NOMEMP, APEEMP, DNIEMP, DIREMP, TIPEMP, SEXEMP, USUEMP, PWDEMP, concat(UBIGEO.DISTUBI,' ',UBIGEO.PROVUBI,' ',UBIGEO.DPTUBI) as nombreUbigeo from EMPLEADO inner join UBIGEO on EMPLEADO.CODUBI = UBIGEO.CODUBI WHERE ESTEMP = 'A'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setIDEMP(rs.getInt("IDEMP"));
                emp.setNOMEMP(rs.getString("NOMEMP"));
                emp.setAPEEMP(rs.getString("APEEMP"));
                emp.setDNIEMP(rs.getString("DNIEMP"));
                emp.setDIREMP(rs.getString("DIREMP"));
                emp.setTIPEMP(rs.getString("TIPEMP"));
                emp.setSEXEMP(rs.getString("SEXEMP"));
                emp.setUSUEMP(rs.getString("USUEMP"));
                emp.setPWDEMP(rs.getString("PWDEMP"));
                emp.setNombreUbi(rs.getString("nombreUbigeo"));
                listado.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }

    //PARA OBTNER CÓDIGO
    public String obtenerCodigoEmpleado(String Ubigeo) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        System.out.println(Ubigeo);
        try {
            String sql = "SELECT CODUBI FROM UBIGEO WHERE CONCAT(DISTUBI,' ',PROVUBI,' ',DPTUBI) LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Ubigeo); //UBigeo
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODUBI");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    //PARA AUTOCOMPLETE
    public List<String> autocompleteUbigeo(String Consulta) throws SQLException, Exception {
        ResultSet rs;
        List<String> Lista;
        try {
            this.conectar();
            String sql = "select top 5 CONCAT(DISTUBI,' ',PROVUBI,' ',DPTUBI) AS DISTUBI from UBIGEO WHERE DISTUBI LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("DISTUBI"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
