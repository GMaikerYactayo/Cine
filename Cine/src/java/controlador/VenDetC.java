package controlador;

import dao.VenDetImpl;
import dao.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Cartelera;
import modelo.VenDet;

@Named(value = "venDetC")
@SessionScoped
public class VenDetC implements Serializable {

    private List<VenDet> listadoDet;
    private List<VenDet> listPeli;
    private List<Cartelera> listHora;
    private List<VenDet> listadoVista = new ArrayList();

    private VenDet modelo;
    private VenDet select;
    private VenDetImpl dao;

    private boolean activo;
    private int index = 0;

    public VenDetC() {
        dao = new VenDetImpl();
        modelo = new VenDet();
        select = new VenDet();
        listadoDet = new ArrayList();
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
            System.out.println("empezar a enviar");
            VentaImpl daov = new VentaImpl();
            String cod = daov.obtener();
            for (VenDet venDet : listadoVista) {
                System.out.println("enviando");
                venDet.setIDVEN(cod);
                dao.regitrar(venDet);
            }

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
            modelo.setNUMSAL(Integer.parseInt(modelo.getIDSAL().substring(modelo.getIDSAL().indexOf("-") + 1)));
            modelo.setIDSAL(modelo.getIDSAL().substring(0, modelo.getIDSAL().indexOf("-")));
            modelo.setIDCAR(modelo.getHORPELI().substring(0, modelo.getHORPELI().indexOf("-")));
            modelo.setHORPELI(modelo.getHORPELI().substring(modelo.getHORPELI().indexOf("-")));
            modelo.setNOMPEL(modelo.getNOMPEL().substring(modelo.getNOMPEL().indexOf("-") + 1, modelo.getNOMPEL().indexOf("_")));
            modelo.setPRECCANT(String.valueOf(modelo.getCATVEN() * Double.parseDouble(modelo.getPRECCANT())));
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

    public void tenerItem() throws Exception {
        try {
            for (Iterator<VenDet> iterator = listadoVista.iterator(); iterator.hasNext();) {
                VenDet next = iterator.next();
                if (select.getIDDETVEN() == next.getIDDETVEN()) {
                    select = next;
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

    public void ediItem() throws Exception {
        try {
            int i = 0;
            for (VenDet venDet : listadoDet) {
                if (venDet.getIDDETVEN() == select.getIDDETVEN()) {
                    listPeli.set(i, select);
                }
                i++;
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

    public void limpiarlist() throws Exception {
        try {
            listadoVista.clear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listPeli = dao.listarPeli();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void listarH() throws Exception {
        try {
            modelo.setIDPEL(Integer.parseInt(modelo.getNOMPEL().substring(0, modelo.getNOMPEL().indexOf("-"))));
            modelo.setPRECCANT(modelo.getNOMPEL().substring(modelo.getNOMPEL().indexOf("_") + 1));

            listHora = dao.listarHorario(modelo.getIDPEL());
        } catch (Exception e) {
            try {
                listHora = dao.listarHorario(select.getIDPEL());
            } catch (Exception ex) {
                throw e;
            }
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

    public List<VenDet> getListPeli() {
        return listPeli;
    }

    public void setListPeli(List<VenDet> listPeli) {
        this.listPeli = listPeli;
    }

    public List<Cartelera> getListHora() {
        return listHora;
    }

    public void setListHora(List<Cartelera> listHora) {
        this.listHora = listHora;
    }

}
