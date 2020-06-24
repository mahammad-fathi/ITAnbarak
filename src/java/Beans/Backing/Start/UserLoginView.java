package Beans.Backing.Start;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import static java.lang.Math.log;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

@Named
@RequestScoped
public class UserLoginView implements Serializable {

    private String username;

    private String password;

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

    public void login() {
        FacesMessage message = null;
        boolean loggedIn = false;

        if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

    public void logout() throws IOException {
 
     //   ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
     //   ec.invalidateSession();
     //   ec.redirect(ec.getRequestContextPath() + "/welcome/login.xhtml" );

((HttpServletRequest)FacesContext.getCurrentInstance()
       .getExternalContext().getRequest()).changeSessionId();

    ExternalContext ec = FacesContext.getCurrentInstance()
        .getExternalContext();
try {
    ec.redirect(ec.getRequestContextPath()
            + "/welcome/login.xhtml");
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
    

    }
 
}
