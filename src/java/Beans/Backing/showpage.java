package Beans.Backing;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author mahamad
 */
@Named(value = "indexService")
@ViewScoped
public class showpage implements Serializable{

    private String contentName="login";

    public showpage() {
    }

    public String getContentName() {
      
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

}
