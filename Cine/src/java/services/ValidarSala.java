/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controlador.SalaC;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value = "validarExistenciaSala")
public class ValidarSala implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean existe = false;
        String NUMSAL = value.toString().trim();

        SalaC salaC = new SalaC();

        try {

            if (salaC.validarExistenciaSala(NUMSAL)) {
                existe = true;
            }

        } catch (Exception e) {

        }
        if (existe) {
            FacesMessage msg = new FacesMessage("NUMERO DE SALA EXISTENTE");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
}
