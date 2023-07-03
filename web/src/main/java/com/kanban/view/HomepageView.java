package com.kanban.view;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


@SessionScoped
@ManagedBean(name = "homepageView", eager = true)
public class HomepageView implements Serializable{

    public String redirect() {
        return "/view/kanbans.xhtml?faces-redirect=true";
    }
}
