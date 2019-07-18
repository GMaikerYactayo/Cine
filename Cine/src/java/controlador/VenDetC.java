package control;

import dao.VenDetImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.VenDet;

@Named(value = "venDetC")
@SessionScoped
public class VenDetC implements Serializable {

    private List<VenDet> listadoDet;
    private List<VenDet> listadoVista = new ArrayList();
    private VenDet modelo;
    private VenDet select;
    private VenDetImpl dao;
    
    private boolean activo;
    private int index = 0;

    public VenDetC() {
        modelo = new VenDet();
        select = new VenDet();
        listadoDet = new ArrayList();
        dao = new VenDetImpl();

    }

    @PostConstruct
    public void inicio() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void enviar() throws Exception {
        try {
            modelo.setIDDETVEN(listadoVista.size());
            this.listadoVista.add(modelo);
            System.out.println(listadoVista.size());
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {

            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void newItem() {
        try {
            if (listadoVista.isEmpty()) {
                index = 0;
            } else {
                index++;
            }
            modelo.setPRECCANT(String.valueOf(modelo.getCATVEN() * 2.50));
            modelo.setIDDETVEN(index);
            this.listadoVista.add(modelo);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
        }
    }

    public void deleteItem() throws Exception {
        try {
            for (Iterator<VenDet> iterator = listadoVista.iterator(); iterator.hasNext();) {
                VenDet next = iterator.next();
                if (select.getIDDETVEN() == next.getIDDETVEN()) {
                    iterator.remove();
                }
            }
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Eliminación Exitosa."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw ex;
        }
    }

    public void limpiar() throws Exception {
        try {
            modelo = new VenDet();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listadoDet = dao.listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public List<VenDet> getListadoDet() {
        return listadoDet;
    }

    public void setListadoDet(List<VenDet> listadoDet) {
        this.listadoDet = listadoDet;
    }

    public VenDet getModelo() {
        return modelo;
    }

    public void setModelo(VenDet modelo) {
        this.modelo = modelo;
    }

    public VenDetImpl getDao() {
        return dao;
    }

    public void setDao(VenDetImpl dao) {
        this.dao = dao;
    }

    public List<VenDet> getListadoVista() {
        return listadoVista;
    }

    public void setListadoVista(List<VenDet> listadoVista) {
        this.listadoVista = listadoVista;
    }

    public VenDet getSelect() {
        return select;
    }

    public void setSelect(VenDet select) {
        this.select = select;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}