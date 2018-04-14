package com.subterrino.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mBeanOther")
public class MBeanOther {
	public String changeURL() {
		FacesContext fc = FacesContext.getCurrentInstance();		
		String base_url = fc.getExternalContext().getContextName();
		String next_page = fc.getViewRoot().getViewId();
		next_page = next_page.replace(".xhtml", ".jsf");
		
	    String url = "/"+base_url+next_page;
		
		String js = "<script>\n" + 
				"			window.history.pushState(\"\", \"\", \""+url+"\");\n" + 
				"		</script>";
		return js;
	}
}
