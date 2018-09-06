package Bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.LocalDescarte;


public class SessionContext {
	
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static Integer getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (Integer) session.getAttribute("id");
		else
			return null;
	}
	public static Integer getUserId2() {
		HttpSession session = getSession();
		if (session != null)
			return (Integer) session.getAttribute("id2");
		else
			return null;
	}

}
