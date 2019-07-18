package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import dao.VentaImpl;
import java.util.ArrayList;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Venta;

@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    private List<Venta> listadoVen;
    private Venta modelo;
    private VentaImpl dao;
    private boolean activo = false;

    public VentaC() {
        modelo = new Venta();
        listadoVen = new ArrayList();
        dao = new VentaImpl();
    }

    public void registrar() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        ValueExpression vex = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{venDetC}", VenDetC.class);
            VenDetC bean = (VenDetC) vex.getValue(context.getELContext());
        try {
            modelo.setIDEMP("2");
            dao.regitrar(modelo);
            
            
            bean.enviar();
            bean.limpiarlist();
            bean.limpiar();
            limpiar();
            activo = false;
            
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
            dao.modificar(modelo);
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
            dao.eliminar(modelo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void limpiar() throws Exception {
        try {
            modelo = new Venta();
            } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
//            listadoVen = dao.listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro Exitoso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Estamos trabajando en ello."));
            throw e;
        }
    }

    public void activar() {
        System.out.println(modelo.getIDCLI());
        if (modelo.getIDCLI() > 0 ) {
            activo = true;
        } else {
            activo = false;
        }
    }

    /*Getter and Setter*/
    public List<Venta> getListadoVen() {
        return listadoVen;
    }

    public void setListadoVen(List<Venta> listadoVen) {
        this.listadoVen = listadoVen;
    }

    public Venta getModelo() {
        return modelo;
    }

    public void setModelo(Venta modelo) {
        this.modelo = modelo;
    }

    public VentaImpl getDao() {
        return dao;
    }

    public void setDao(VentaImpl dao) {
        this.dao = dao;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
