package controlador;

import dao.ClienteImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Cliente;

@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente persona = new Cliente();
    private Cliente personaEdit = new Cliente();
    private ClienteImpl dao;
    private List<Cliente> listadoCli;
    private List<Cliente> filterCli;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("error init Apoderado " + e.getMessage());
        }
    }

    public void listar() throws Exception {
       ClienteImpl dao;
        try {
            dao = new ClienteImpl();
            listadoCli = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        persona = new Cliente();

    }

    public void registrar() throws Exception {

        try {
            dao = new ClienteImpl();            
            dao.regitrar(persona);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {

        try {
            dao = new ClienteImpl();
            dao.modificar(personaEdit);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao = new ClienteImpl();
            dao.eliminar(personaEdit);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ELIMINADO", "Eliminado"));
        } catch (Exception e) {
            throw e;
        }
    }

    

    //CÓDIGO GENEREDO
    public Cliente getPersona() {
        return persona;
    }

    public void setPersona(Cliente persona) {
        this.persona = persona;
    }

    public Cliente getPersonaEdit() {
        return personaEdit;
    }

    public void setPersonaEdit(Cliente personaEdit) {
        this.personaEdit = personaEdit;
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public List<Cliente> getListadoCli() {
        return listadoCli;
    }

    public void setListadoCli(List<Cliente> listadoCli) {
        this.listadoCli = listadoCli;
    }

    public List<Cliente> getFilterCli() {
        return filterCli;
    }

    public void setFilterCli(List<Cliente> filterCli) {
        this.filterCli = filterCli;
    }
}
