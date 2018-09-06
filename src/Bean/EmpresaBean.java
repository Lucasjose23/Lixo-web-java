package Bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.EmpresaDAO;
import modelo.Empresa;

@ManagedBean(name="empresaBean")
@ViewScoped
public class EmpresaBean {
	private EmpresaDAO empresaDao = new EmpresaDAO();
	
	private Empresa empresa = new Empresa();

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}



	public void adicionaEmpresa() throws IOException {
		this.empresaDao.create(empresa);
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		
	}
	
	
	//teste SESSION
	private String nomeLogado;
	

	public String getNomeLogado() {
		return nomeLogado;
	}

	public void setNomeLogado(String nomeLogado) {
		this.nomeLogado = nomeLogado;
	}
	
//	@PostConstruct
	public void GravaValorSessio() {
		this.nomeLogado = SessionContext.getUserName();
		this.setNomeLogado(nomeLogado);
	}
	
	
}
