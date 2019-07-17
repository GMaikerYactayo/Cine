package dao;
import dao.Conexion;
import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbigeoImpl extends Conexion {
    
    public String obtenerCodigoUbigeo(String Ubigeo) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODUBI FROM dbo.UBIGEO WHERE CONCAT(CONCAT(CONCAT(DPTUBI,' '),PROVUBI,' '),DISTUBI) LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Ubigeo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public List<String> autocompleteUbigeo(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT top 5 CONCAT(DISTUBI,' ',PROVUBI,' ',DPTUBI) AS NOMBREUBIGEO FROM dbo.UBIGEO WHERE DISTUBI LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("NOMBREUBIGEO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
