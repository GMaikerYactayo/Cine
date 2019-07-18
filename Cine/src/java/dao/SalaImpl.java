package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Sala;

public class SalaImpl extends Conexion implements ICRUD<Sala>{

    @Override
    public void regitrar(Sala sala) throws Exception {
        this.conectar();
        try {
            String sql = "insert into SALA(CAPSAL,ESTSAL,NUMSAL) values (?,?,?)";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, sala.getCAPSAL());
            ps.setString(2, "A");
            ps.setString(3, sala.getNUMSAL());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Sala sala) throws Exception {
        try {
            this.conectar();
            String sql = "update SALA set CAPSAL=?,ESTSAL=?,NUMSAL=? where IDSAL=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, sala.getCAPSAL());
            ps.setString(2, sala.getESTSAL());
            ps.setString(3, sala.getNUMSAL());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(Sala sala) throws Exception {
        try {
            this.conectar();
            String sql = "update SALA set ESTSAL='I' where IDSAL=?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setInt(1, sala.getIDSAL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Sala> listar() throws Exception {
        List<Sala> listado;
        Sala sal;
        try {
            this.conectar();
            String sql = "SELECT * FROM SALA WHERE ESTSAL='A'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sal = new Sala();
                sal.setIDSAL(rs.getInt("IDSAL"));
                sal.setCAPSAL(rs.getString("CAPSAL"));
                sal.setESTSAL(rs.getString("ESTSAL"));
                sal.setNUMSAL(rs.getString("NUMSAL"));
                listado.add(sal);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }
    
    
    
}
