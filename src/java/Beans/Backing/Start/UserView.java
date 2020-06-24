/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.Backing.Start;

import Beans.Backing.showpage;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author mahamad
 */
@Named(value = "userView")
@RequestScoped

public class UserView {

    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Inject
    private showpage showpages;

    public void save() {

        if (firstname.equals("admin")) {
            PrimeFaces.current().executeScript("PF('loginDialog').hide()");
            PrimeFaces.current().executeScript("launchIntoFullscreen(document.body)");
            showpages.setContentName("Anbar");
            PrimeFaces.current().ajax().update(":content");

        } else {
            
        }
    }
}
