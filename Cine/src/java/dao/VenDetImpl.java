package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.VenDet;

public class VenDetImpl extends Conexion implements ICRUD<VenDet> {

    @Override
    public void regitrar(VenDet vendet) throws Exception {
        this.conectar();
        try {
            String sql = "insert into DETALLE_VENTA (CATVEN,IDCAR,IDVEN,IDSAL,ESTDETVEN) values (?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, vendet.getCATVEN());
            ps.setString(2, vendet.getIDCAR());
            ps.setString(3, vendet.getIDVEN());
            ps.setString(4, vendet.getIDSAL());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(VenDet vendet) throws Exception {
        this.conectar();
        try {
            String sql = "update DETALLE_VENTA set CATVEN=?,IDCAR=?,IDVEN=?,IDSAL=?,ESTDETVEN=? where IDDETVEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, vendet.getCATVEN());
            ps.setString(2, vendet.getIDCAR());
            ps.setString(3, vendet.getIDVEN());
            ps.setString(4, vendet.getIDSAL());
            ps.setString(5, vendet.getESTDETVEN());
            ps.setInt(6, vendet.getIDDETVEN());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(VenDet vendet) throws Exception {
        this.conectar();
        try {
            String sql = "update DETALLE_VENTA set ESTDETVEN='I' where IDDETVEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, vendet.getIDDETVEN());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<VenDet> listar() throws Exception {
        List<VenDet> listado;
        VenDet vendet;
        try {
            this.conectar();
            String sql = "select * from DETALLE_VENTA where ESTDETVEN='A'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                vendet = new VenDet();
                vendet.setIDDETVEN(rs.getInt("IDDETVEN"));
                vendet.setCATVEN(rs.getInt("CATVEN"));
                vendet.setIDCAR(rs.getString("IDCAR"));
                vendet.setIDVEN(rs.getString("IDVEN"));
                vendet.setIDSAL(rs.getString("IDSAL"));
                vendet.setESTDETVEN(rs.getString("ESTDETVEN"));
                listado.add(vendet);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }

}