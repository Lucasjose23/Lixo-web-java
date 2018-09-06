package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.EmpresaDAO;
import DAO.EnderecoDAO;
import DAO.EquipamentoDAO;
import DAO.LocalDescarteDAO;
import modelo.Endereco;
import modelo.LocalDescarte;

@ManagedBean(name="localDescarteBean")
@ViewScoped
public class LocalDescarteBean {
	//DAOS
	private LocalDescarteDAO localDescarteDao = new LocalDescarteDAO();
	private EnderecoDAO enderecoDao = new EnderecoDAO();
	private EmpresaDAO empresaDao = new EmpresaDAO();
	
	//Objetos
	private LocalDescarte localDescarte = new LocalDescarte();
	private Endereco endereco = new Endereco();
	
	//Empresa Id
	private int empresaId;
	
	
	//Getters and Setters
	public LocalDescarte getLocalDescarte() {
		return localDescarte;
	}

	public void setLocalDescarte(LocalDescarte localDescarte) {
		this.localDescarte = localDescarte;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}
	
	
	
	//recebe id da empresa Logado
	@PostConstruct
	public void GravaValorSession() {
		this.empresaId = SessionContext.getUserId();
		this.setEmpresaId(empresaId);
		this.locaisdeDescarte = localDescarteDao.getReadAllId(this.empresaId);
	}
	
	//Adiciona Local de descarte
	public void createLocalDescarte() throws IOException {
		
		//Empresa LocalDescarte
		this.localDescarte.setEmpresa(empresaDao.readId(this.empresaId));
		
		//LocalDescarte
		this.localDescarteDao.create(localDescarte);
		
		this.endereco.setLocalDescarte(localDescarte);
		this.enderecoDao.create(endereco);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("localDescarteEmpresa.xhtml");
	}
	
	//Lista Locais de descarte Da Empresa
	private List<LocalDescarte> locaisdeDescarte;
	
	public List<LocalDescarte> getLocaisdeDescarte() {
		return locaisdeDescarte;
	}
	
	public void ConsultaLocaisDescartePorEmpresaId() throws IOException {
		//this.locaisdeDescarte = localDescarteDao.getReadAllporEmpresa(this.empresaId);
		//this.locaisdeDescarte = localDescarteDao.getReadAll();
		this.locaisdeDescarte = localDescarteDao.getReadAllId(this.empresaId);
		
	}
	
	//idLocalDescarte
	private int idLocalDescarte;
	
	public int getIdLocalDescarte() {
		return idLocalDescarte;
	}

	public void setIdLocalDescarte(int idLocalDescarte) {
		this.idLocalDescarte = idLocalDescarte;
	}
	
	
	//Ver Endereço
	public void verEndereco(LocalDescarte localdescarte) throws IOException {
		this.endereco = enderecoDao.getReadAllId(localdescarte.getId());
		//FacesContext.getCurrentInstance().getExternalContext().redirect("verEndereco.xhtml");
		//this.endereco = enderecoDao.getReadId(1);
	}
	
	public Endereco mostrarEnd() {
		return this.endereco;
	}
	
	//MOSTRA TODOS OS LOCAIS DE DESCARTE
	public void mostraTodosLocais() {
		this.locaisdeDescarte = localDescarteDao.getReadAll();
	}
	public void confirmarDescartes() throws IOException
	{
	
	
		FacesContext.getCurrentInstance().getExternalContext().redirect("descartesEmpresa.xhtml");
	}
	
	
}
