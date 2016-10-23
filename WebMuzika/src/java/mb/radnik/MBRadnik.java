/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.radnik;

import authentication.Sesija;
import domen.Radnik;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sb.radnik.SBRadnikLocal;

/**
 *
 * @author Filip
 */
@ManagedBean
@SessionScoped
public class MBRadnik implements Serializable {

    Radnik radnik;
    String username;
    String password;

    public static final String HOME_PAGE_REDIRECT = "index?faces-redirect=true";
    public static final String LOGIN_PAGE_REDIRECT = "logIn?faces-redirect=true";

    @EJB
    SBRadnikLocal sbRadnik;

    public MBRadnik() {
        radnik = new Radnik();
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logIn() {

        if (username != null && password != null) {

            try {
                radnik = sbRadnik.vratiRadnika(username, password);

                if (radnik != null) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Dobrodošli!", radnik.getIme() + " " + radnik.getPrezime()));
                    context.getExternalContext().getFlash().setKeepMessages(true);

                    HttpSession session = Sesija.getSession();
                    session.setAttribute("user", radnik);
                    return HOME_PAGE_REDIRECT;
                }

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Ne postoji radnik sa datim podacima!", "Pogrešni podaci"));
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Niste uneli sve podatke!", "Pogrešni podaci"));
        }
        return null;
    }
    
    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return LOGIN_PAGE_REDIRECT;
    }
    
    public String imePrezimeRadnika(){
        return radnik.getIme() + " " + radnik.getPrezime();
    }
}
