package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cartelera;

public class CarteleraImpl extends Conexion implements ICRUD<Cartelera> {

    @Override
    public void regitrar(Cartelera cartelera) throws Exception {
        this.conectar();
        try {
            System.out.println(cartelera.getIDPEL());
            String sql = "insert into CARTELERA (PRECAR,HORCAR,FECCAR,IDPEL,ESTCAR) "
                    + "values (?,?,?,?,?)";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, cartelera.getPRECAR());
            ps.setString(2, cartelera.getHORCAR());
            ps.setString(3, cartelera.getFECCAR());
            ps.setInt(4, cartelera.getIDPEL());
            ps.setString(5, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Cartelera cartelera) throws Exception {
        try {
            this.conectar();
            String sql = "update CARTELERA set PRECAR=?,HORCAR=?,FECCAR=?,IDPEL=?,ESTCAR=? where IDCAR=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cartelera.getPRECAR());
            ps.setString(2, cartelera.getHORCAR());
            ps.setString(3, cartelera.getFECCAR());
            ps.setInt(4, cartelera.getIDPEL());
            ps.setString(5, cartelera.getESTCAR());
            ps.setInt(6, cartelera.getIDCAR());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(Cartelera cartelera) throws Exception {
        try {
            this.conectar();
            String sql = "delete from CARTELERA where IDCAR=?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setInt(1, cartelera.getIDCAR());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Cartelera> listar() throws Exception {
        List<Cartelera> listado;
        Cartelera car;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_CARTELERA WHERE ESTCAR='A'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                car = new Cartelera();
                car.setIDCAR(rs.getInt("IDCAR"));
                car.setHORCAR(rs.getString("HORCAR"));
                car.setFECCAR(rs.getString("FECCAR"));
                car.setIDPEL(rs.getInt("IDPEL"));
                car.setNOMPEL(rs.getString("NOMPEL"));
                car.setPRECAR(rs.getString("PRECAR"));
                System.out.println(rs.getDouble("PRECAR"));
                car.setESTCAR(rs.getString("ESTCAR"));
                listado.add(car);
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

    public String obtenerCodigoPelicula(String Pelicula) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT IDPEL FROM PELICULA WHERE NOMPEL LIKE ?;";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Pelicula);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPEL");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompletePelicula(String Consulta) throws SQLException {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select top 10 NOMPEL AS PELICULA from PELICULA WHERE NOMPEL LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("PELICULA"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }

    }

}
