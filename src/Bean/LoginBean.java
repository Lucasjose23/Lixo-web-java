package Bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import DAO.EmpresaDAO;
import DAO.UsuarioDAO;
import modelo.Empresa;
import modelo.Usuario;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	UsuarioDAO usuarioDao = new UsuarioDAO();
	Usuario usuarioLogin = new Usuario();

	private String login;
	private String senha;
	
	
	//Empresa Login DAO e Objeto
	private EmpresaDAO empresaDao = new EmpresaDAO();
	private Empresa empresa = new Empresa();
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	// variaveis para guardar valor do RadioButton
	private boolean radio;

	public boolean getRadio() {
		return radio;
	}

	public void setRadio(boolean radio) {
		this.radio = radio;
	}

	// variavel para guarda nome para mostrar na tela
	private String nomeDoLogado;

	public String getNomeDoLogado() {
		return nomeDoLogado;
	}

	public void setNomeDoLogado(String nomeDoLogado) {
		this.nomeDoLogado = nomeDoLogado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// objeto Usuario
	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public void validaLogin() throws IOException {

		//Login usuario
		if (this.radio == true) {
			usuarioLogin = usuarioDao.getUsuario(this.login, this.senha);

			if (usuarioLogin == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} else {
				HttpSession session = SessionContext.getSession();
				session.setAttribute("id", usuarioLogin.getId());
				session.setAttribute("username", usuarioLogin.getNome());
				this.setNomeDoLogado(SessionContext.getUserId().toString());
				FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroEquipamento.xhtml");
			}

			//this.setNomeDoLogado(SessionContext.getUserName());
		}else{
			//Login Empresa
			this.empresa = empresaDao.getEmpresa(this.login, this.senha);
			
			if (empresa == null) {
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
				 FacesContext.getCurrentInstance().addMessage("xxx", new FacesMessage("Nome ou senha invalidos!"));
		    
			}else {
				HttpSession session = SessionContext.getSession();
				session.setAttribute("id", empresa.getId());
				session.setAttribute("username", empresa.getNome());
				this.setNomeDoLogado(SessionContext.getUserId().toString());
				FacesContext.getCurrentInstance().getExternalContext().redirect("localDescarteEmpresa.xhtml");
			}
		}
	}

	public void EncerraSession() throws IOException {
		HttpSession session = SessionContext.getSession();
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
	}
}
