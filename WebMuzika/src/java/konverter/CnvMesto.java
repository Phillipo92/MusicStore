/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konverter;

import domen.Mesto;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.mesto.SBMestoLocal;

/**
 *
 * @author Filip
 */
@FacesConverter(value = "mestoCNV")
public class CnvMesto implements Converter {
    
    @EJB
    SBMestoLocal sbMesto;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            
            Mesto m = sbMesto.vratiMestoPoID(Integer.parseInt(value));
            
            System.out.println("mesto konverter:" + m.getNazivMesta());
            return m;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && (value instanceof Mesto)) {
            Mesto mesto = (Mesto) value;
            return mesto.getMestoID().toString();
        }
        return "";
    }
    
}
