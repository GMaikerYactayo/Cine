package controlador;

import dao.SalaImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Sala;

@Named(value = "salaC")
@SessionScoped
public class SalaC implements Serializable{

    private Sala sala = new Sala();
    private Sala select;
    private List<Sala> listadoSal;
    private List<Sala> listadoSal2;
    SalaImpl dao;

    @PostConstruct
    public void iniciar() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        try {
            dao = new SalaImpl();
            dao.regitrar(sala);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado..."));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public boolean validarExistenciaSala(String numSala) {
        try {
            dao = new SalaImpl();
            Sala salaValidacion = new Sala();
            salaValidacion = dao.validarExistenciaSala(numSala);
            String NUMSAL = salaValidacion.getNUMSAL().toLowerCase().trim();
            if (NUMSAL.equals(numSala)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void modificar() throws Exception {
        try {
            dao = new SalaImpl();
            dao.modificar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Completado.."));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao = new SalaImpl();
            dao.eliminar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminación", "Completado.."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            dao = new SalaImpl();
            listadoSal = dao.listar();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        sala = new Sala();
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Sala getSelect() {
        return select;
    }

    public void setSelect(Sala select) {
        this.select = select;
    }

    public List<Sala> getListadoSal() {
        return listadoSal;
    }

    public void setListadoSal(List<Sala> listadoSal) {
        this.listadoSal = listadoSal;
    }

    public List<Sala> getListadoSal2() {
        return listadoSal2;
    }

    public void setListadoSal2(List<Sala> listadoSal2) {
        this.listadoSal2 = listadoSal2;
    }

    public SalaImpl getDao() {
        return dao;
    }

    public void setDao(SalaImpl dao) {
        this.dao = dao;
    }
    
    

}
