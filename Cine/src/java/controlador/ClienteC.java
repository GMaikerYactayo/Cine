package controlador;

import dao.ClienteImpl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Cliente;

/**
 *
 * @author Ctorres
 */
@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    Cliente persona = new Cliente();
    Cliente personaEdit = new Cliente();
    ClienteImpl dao;
    List<Cliente> listper;
    private boolean bt;

    @PostConstruct
    public void inicio() {
        try {
            listar("A");
        } catch (Exception e) {
            throw e;
        }
    }

    public void registrar() {
        dao = new ClienteImpl();
        try {
            dao.regitrar(persona);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "REGISTRO COMPLETO", null));
            limpiar();
        } catch (Exception e) {
        }
    }

    public void modificar() {
        dao = new ClienteImpl();
        try {
            dao.modificar(personaEdit);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICACION COMPLETA", null));
        } catch (Exception e) {
            System.out.println("Error controlador: " + e);
        }
    }

    public void listar(String estado) {
        dao = new ClienteImpl();
        try {
            listper = dao.listarcli(estado);
        } catch (Exception e) {
            System.out.println("Erroe: " + e);
        }
    }

    public void habilitar(Cliente cli)throws Exception{
        dao = new ClienteImpl();
        try {
            dao.eliminar(cli);
            listar("I");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "HABILITACION COMPLETA", null));
        } catch (Exception e) {
        }
    }
    
    public void invilitar(Cliente cli) throws Exception{
         dao = new ClienteImpl();
        try {
            dao.eliminar(cli);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "INAVILITACION COMPLETA", null));
        } catch (Exception e) {
        }
    }
//      public void REPORTECLIENTE(String idcli) throws Exception {
//        report reportCLIENTE = new report();
//        try {
//            Map<String, Object> parameters = new HashMap(); // Libro de parametros
//            parameters.put(null, idcli); //Insertamos un parametro
//            reportCLIENTE.exportarCLIENTE(parameters); //Pido exportar Reporte con los parametros
////            report.exportarPDF2(parameters);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    
    public void limpiar() {
        persona = new Cliente();
    }

    public Cliente getPersona() {
        return persona;
    }

    public void setPersona(Cliente persona) {
        this.persona = persona;
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public List<Cliente> getListper() {
        return listper;
    }

    public void setListper(List<Cliente> listper) {
        this.listper = listper;
    }

    public Cliente getPersonaEdit() {
        return personaEdit;
    }

    public void setPersonaEdit(Cliente personaEdit) {
        this.personaEdit = personaEdit;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

}
