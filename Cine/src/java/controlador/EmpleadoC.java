/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Reportes.report;
import dao.EmpleadoImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Empleado;

@Named(value = "empleadoC")
@SessionScoped
public class EmpleadoC implements Serializable {

    private Empleado empleado = new Empleado();
    private Empleado select = new Empleado();
    private EmpleadoImpl dao;
    private List<Empleado> listadoEmp;
    private List<Empleado> filterEmp;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("error init Apoderado " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        EmpleadoImpl dao;
        try {
            dao = new EmpleadoImpl();
            listadoEmp = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        empleado = new Empleado();

    }

    public void registrar() throws Exception {

        try {
            dao = new EmpleadoImpl();
            empleado.setCODUBI(dao.obtenerCodigoEmpleado(empleado.getNombreUbi()));
            dao.regitrar(empleado);
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
            dao = new EmpleadoImpl();
            select.setCODUBI(dao.obtenerCodigoEmpleado(select.getNombreUbi()));
            dao.modificar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao = new EmpleadoImpl();
            dao.eliminar(select);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro", "Eliminado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeTextUbigeo(String query) throws SQLException, Exception {
        EmpleadoImpl daoEmp = new EmpleadoImpl();
        return daoEmp.autocompleteUbigeo(query);
    }

    public void REPORTE_PDF_CLIENTE(String empleado) throws Exception {
        report reportEmp = new report();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put(null, empleado); //Insertamos un parametro
            reportEmp.exportarPDF_Empleado(parameters);//Pido exportar Reporte con los parametros
//            report.exportarPDF2(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    //CÓDIGO GENEREDO
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoImpl getDao() {
        return dao;
    }

    public void setDao(EmpleadoImpl dao) {
        this.dao = dao;
    }

    public List<Empleado> getListadoEmp() {
        return listadoEmp;
    }

    public void setListadoEmp(List<Empleado> listadoEmp) {
        this.listadoEmp = listadoEmp;
    }

    public List<Empleado> getFilterEmp() {
        return filterEmp;
    }

    public void setFilterEmp(List<Empleado> filterEmp) {
        this.filterEmp = filterEmp;
    }

    public Empleado getSelect() {
        return select;
    }

    public void setSelect(Empleado select) {
        this.select = select;
    }

}
