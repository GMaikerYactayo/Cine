package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.GraficoCine;

public class GraficoCineImpl extends Conexion{
    public List<GraficoCine> listar() throws Exception {
        List<GraficoCine> listado;
        GraficoCine graficocine;
        
        try {
            this.conectar();
            String sql = "SELECT \n"
                + "	COUNT(IDPEL) AS CANTIDAD,\n"
                + "	CASE MONTH(FECPEL)\n"
                + "	WHEN 1 THEN 'Enero'\n"
                + "	WHEN 2 THEN 'Febrero'\n"
                + "	WHEN 3 THEN 'Marzo'\n"
                + "	WHEN 4 THEN 'Abril'\n"
                + "	WHEN 5 THEN 'Mayo'\n"
                + "	WHEN 6 THEN 'Junio'\n"
                + "	WHEN 7 THEN 'Julio'\n"
                + "	WHEN 8 THEN 'Agosto'\n"
                + "	WHEN 9 THEN 'Septiembre'\n"
                + "	WHEN 10 THEN 'Octubre'\n"
                + "	WHEN 11 THEN 'Noviembre'\n"
                + "	WHEN 12 THEN 'Diciembre'\n"
                + "	ELSE 'No Definido'\n"
                + "	END AS MES\n"
                + "FROM \n"
                + "	dbo.PELICULA\n"
                + "	WHERE ESTPEL = 'A'\n"
                + "	GROUP BY MONTH(FECPEL)";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                graficocine = new GraficoCine();
                graficocine.setCANTIDAD(rs.getString("CANTIDAD"));
                graficocine.setMES(rs.getString("MES"));
                listado.add(graficocine);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al listar el grafico " + e);
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }

}
