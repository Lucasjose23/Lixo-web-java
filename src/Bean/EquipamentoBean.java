package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.EquipamentoDAO;
import DAO.LocalDescarteDAO;
import DAO.UsuarioDAO;
import modelo.Equipamento;
import modelo.LocalDescarte;

@ManagedBean(name="equipamentoBean")
@SessionScoped
public class EquipamentoBean {
	//DAOS
	private EquipamentoDAO equipamentoDao = new EquipamentoDAO();
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private LocalDescarteDAO localdescarteDao = new LocalDescarteDAO();
	
	//Objetos
	private Equipamento equipamento = new Equipamento();
	private LocalDescarte localDescarte = new LocalDescarte();
	public LocalDescarte localDescarte2 = new LocalDescarte();
	//Variavel guarda id usuario logado
	private int idUsuario;
	
	
	//getters and setters
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public LocalDescarte getLocalDescarte() {
		return localDescarte;
	}

	public void setLocalDescarte(LocalDescarte localDescarte) {
		this.localDescarte = localDescarte;
	}

		//recebe id do usuario Logado
		@PostConstruct
		public void GravaValorSession() {
			this.idUsuario = SessionContext.getUserId();
			this.setIdUsuario(idUsuario);
			this.equipamentos = equipamentoDao.getReadAllId(this.idUsuario);
		
		}
		
		
	//cria equipamento
	public void criaEquipamento() throws IOException {
		this.equipamento.setUsuario(usuarioDao.readId(this.getIdUsuario()));
		this.equipamento.setStatus("NAO DESCARTADO");
		this.equipamentoDao.create(equipamento);
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroEquipamento.xhtml");
	}
	
	//listagem de equipamentos do cliente
	private List<Equipamento> equipamentos = new ArrayList<Equipamento>();

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void ConsultaEquipamentosUsuario() {
		this.equipamentos = equipamentoDao.getReadAllId(this.idUsuario);
	}
	
	
	//Responsavel por pegar o id do Local de descarte
	public void setaIdLocalDescarte(LocalDescarte localdescarte) throws IOException {
		this.equipamento.setLocalDescarte(localdescarteDao.readId(localdescarte.getId()));
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroEquipamentoOK.xhtml");
	}
	public void Deletar(Equipamento equipamento) throws IOException
	{
		this.equipamentoDao.delete(equipamento);
	
		
		
	}
	List<Equipamento> novalista=new ArrayList<Equipamento>();
	
	//TESTE ID LOCAL DESCARTE
	public void EquipamentosLocal()
	{
		
		for (Equipamento equipamento : this.equipamentos) 
		{
			if(this.equipamento.getLocalDescarte()==this.localDescarte2)
			{
				novalista.add(equipamento);
			}
			
			
		}
		
		
		
		
	}
	public List<Equipamento> getEquipamentos2() {
		return novalista;
	}

	
	
	
	
}
