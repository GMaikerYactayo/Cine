package controlador;

import dao.CarteleraImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Cartelera;

@Named(value = "carteleraC")
@SessionScoped
public class CarteleraC implements Serializable {

    private Cartelera cartelera = new Cartelera();
    private Cartelera select;
    private List<Cartelera> listadoCar;
    CarteleraImpl dao;
    SimpleDateFormat formateador = new SimpleDateFormat("dd/MMM/yyyy");
    SimpleDateFormat sdf_d = new SimpleDateFormat("dd MMM yyyy",Locale.ENGLISH);
    private Date fechaFormulario;

    @PostConstruct
    public void iniciar() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void rellenar() throws Exception {
        System.out.println(sdf_d.parse(select.getFECCAR()));
        fechaFormulario = sdf_d.parse(select.getFECCAR());
    }
    
    public void registrar() throws Exception {
        try {
            dao = new CarteleraImpl();
            cartelera.setFECCAR(formateador.format(fechaFormulario));
            dao.regitrar(cartelera);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado..."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao = new CarteleraImpl();
            select.setFECCAR(formateador.format(fechaFormulario));
            dao.modificar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Completado.."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao = new CarteleraImpl();
            dao.eliminar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminación", "Completado.."));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            dao = new CarteleraImpl();
            listadoCar = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        try {
            cartelera = new Cartelera();
        } catch (Exception e) {
            throw e;
        }
    }

    public Cartelera getCartelera() {
        return cartelera;
    }

    public void setCartelera(Cartelera cartelera) {
        this.cartelera = cartelera;
    }

    public Cartelera getSelect() {
        return select;
    }

    public void setSelect(Cartelera select) {
        this.select = select;
    }

    public List<Cartelera> getListadoCar() {
        return listadoCar;
    }

    public void setListadoCar(List<Cartelera> listadoCar) {
        this.listadoCar = listadoCar;
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

    public CarteleraImpl getDao() {
        return dao;
    }

    public void setDao(CarteleraImpl dao) {
        this.dao = dao;
    }

    public SimpleDateFormat getSdf_d() {
        return sdf_d;
    }

    public void setSdf_d(SimpleDateFormat sdf_d) {
        this.sdf_d = sdf_d;
    }

    
    
}
