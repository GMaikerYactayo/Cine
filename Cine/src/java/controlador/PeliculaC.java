package controlador;

import dao.PeliculaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
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
        try {
            dao = new PeliculaImpl();
            dao.modificar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Completado.."));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        PeliculaImpl dao;
        try {
            dao = new PeliculaImpl();
            dao.eliminar(select);
            listar();
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


    public void limpiar() throws Exception {
        pelicula = new Pelicula();
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

    
    
}
