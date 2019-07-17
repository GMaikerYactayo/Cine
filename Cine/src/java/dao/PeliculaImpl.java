package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Pelicula;

public class PeliculaImpl extends Conexion implements ICRUD<Pelicula> {

    @Override
    public void regitrar(Pelicula pelicula) throws Exception {
        try {
            this.conectar();
            String sql = "insert into PELICULA"
                    + "(NOMPEL,GENPEL,RESTPEL,LENPEL,DURPEL,HORPEL,FECPEL,ESTPEL)"
                    + "values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, pelicula.getNOMPEL());
            ps.setString(2, pelicula.getGENPEL());
            ps.setString(3, pelicula.getRESTPEL());
            ps.setString(4, pelicula.getLENPEL());
            ps.setString(5, pelicula.getDURPEL());
            ps.setString(6, pelicula.getHORPEL());
            ps.setString(7, pelicula.getFECPEL());
            ps.setString(8, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en registrarAlumno " + e.getMessage());
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Pelicula pelicula) throws Exception {
        try {
            this.conectar();
            String sql = "update PELICULA set NOMPEL=?,GENPEL=?,RESTPEL=?,LENPEL=?,DURPEL=?,HORPEL=?,FECPEL=?,ESTPEL=? where IDPEL=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, pelicula.getNOMPEL());
            ps.setString(2, pelicula.getGENPEL());
            ps.setString(3, pelicula.getRESTPEL());
            ps.setString(4, pelicula.getLENPEL());
            ps.setString(5, pelicula.getDURPEL());
            ps.setString(6, pelicula.getHORPEL());
            ps.setString(7, pelicula.getFECPEL());
            ps.setString(8, pelicula.getESTPEL());
            ps.setInt(9, pelicula.getIDPEL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(Pelicula pelicula) throws Exception {
        try {
            this.conectar();
            String sql = "update PELICULA set ESTPEL = 'I' where IDPEL=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, pelicula.getIDPEL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Pelicula> listar() throws Exception {
        List<Pelicula> listado;
        Pelicula pel;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_PELICULA WHERE ESTPEL='A'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pel = new Pelicula();
                pel.setIDPEL(rs.getInt("IDPEL"));
                pel.setNOMPEL(rs.getString("NOMPEL"));
                pel.setGENPEL(rs.getString("GENPEL"));
                pel.setRESTPEL(rs.getString("RESTPEL"));
                pel.setLENPEL(rs.getString("LENPEL"));
                pel.setDURPEL(rs.getString("DURPEL"));
                pel.setHORPEL(rs.getString("HORPEL"));
                pel.setFECPEL(rs.getString("FECPEL"));
                pel.setESTPEL(rs.getString("ESTPEL"));
                listado.add(pel);
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
