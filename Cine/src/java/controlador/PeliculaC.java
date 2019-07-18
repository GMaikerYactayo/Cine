package controlador;

import Reportes.report;
import dao.PeliculaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Pelicula;

@Named(value = "peliculaC")
@SessionScoped
public class PeliculaC implements Serializable {

    private Pelicula pelicula = new Pelicula();
    private Pelicula select;
    private List<Pelicula> listadoPel;
    private List<Pelicula> listadoPel2;
    SimpleDateFormat formateador = new SimpleDateFormat("dd/MMM/yyyy");
    SimpleDateFormat sdf_d = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
    private Date fechaFormulario = null;

    @PostConstruct
    public void iniciar() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        PeliculaImpl dao;
        try {
            pelicula.setFECPEL(formateador.format(fechaFormulario));
            dao = new PeliculaImpl();
            dao.regitrar(pelicula);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado..."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        PeliculaImpl dao;
        sdf_d = new SimpleDateFormat("MM-dd-yyyy");
        try {

            System.out.println(sdf_d.format(fechaFormulario));
            select.setFECPEL(sdf_d.format(fechaFormulario));
            dao = new PeliculaImpl();
            dao.modificar(select);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Completado.."));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void rellenar() throws Exception {
        System.out.println(sdf_d.parse(select.getFECPEL()));
        fechaFormulario = sdf_d.parse(select.getFECPEL());
    }

    public void eliminar() throws Exception {
        PeliculaImpl dao;
        try {
            dao = new PeliculaImpl();
            dao.eliminar(select);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminación", "Completado.."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        PeliculaImpl dao;
        try {
            dao = new PeliculaImpl();
            listadoPel = dao.listar();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void REPORTE_PDF_PELICULA(String pelicula) throws Exception {
        report reportPeli = new report();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put(null, pelicula); //Insertamos un parametro
            reportPeli.exportarPDF_PELICULA(parameters);//Pido exportar Reporte con los parametros
//            report.exportarPDF2(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        pelicula = new Pelicula();
        fechaFormulario = null;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Pelicula getSelect() {
        return select;
    }

    public void setSelect(Pelicula select) {
        this.select = select;
    }

    public List<Pelicula> getListadoPel() {
        return listadoPel;
    }

    public void setListadoPel(List<Pelicula> listadoPel) {
        this.listadoPel = listadoPel;
    }

    public List<Pelicula> getListadoPel2() {
        return listadoPel2;
    }

    public void setListadoPel2(List<Pelicula> listadoPel2) {
        this.listadoPel2 = listadoPel2;
    }

    public SimpleDateFormat getFormateador() {
        return formateador;
    }

    public void setFormateador(SimpleDateFormat formateador) {
        this.formateador = formateador;
    }

    public Date getFechaFormulario() {
        return fechaFormulario;
    }

    public void setFechaFormulario(Date fechaFormulario) {
        this.fechaFormulario = fechaFormulario;
    }

}
