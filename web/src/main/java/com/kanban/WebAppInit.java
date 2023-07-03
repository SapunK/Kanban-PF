package com.kanban;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

@Named("webAppInit")
@ApplicationScoped
@Singleton
public class WebAppInit {

    /**
     * Load application properties on startup.
     */
    @PostConstruct
    public void init() {
        InputStream inputStream;
        Manifest manifest;

        try {
        	inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("META-INF/MANIFEST.MF");
        	if (inputStream != null) {
        		manifest = new Manifest(inputStream);
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
