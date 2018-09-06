package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.EnderecoDAO;
import DAO.UsuarioDAO;
import modelo.Endereco;
import modelo.Usuario;

@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean {
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private EnderecoDAO enderecoDao = new EnderecoDAO();
	
	private Usuario usuario = new Usuario();
	private Endereco endereco =  new Endereco();
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void adicionaUsuario() throws IOException {
		this.enderecoDao.create(endereco);
		List<Endereco> enderecoUsuario = new ArrayList<Endereco>();
		enderecoUsuario.add(this.endereco);
		this.usuario.setEndereco(enderecoUsuario);
		this.usuarioDao.create(usuario);
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		
	}
	
	

}
