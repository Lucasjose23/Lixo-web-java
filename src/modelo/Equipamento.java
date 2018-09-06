package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="equipamento")
public class Equipamento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 60, nullable=false)
	private String nome;
	
	@Column(length = 60, nullable=false)
	private String status;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private LocalDescarte localDescarte;
	
	public Equipamento(String nome, String status) {
		this.nome = nome;
		this.status = status;
	}


	public Equipamento() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public LocalDescarte getLocalDescarte() {
		return localDescarte;
	}


	public void setLocalDescarte(LocalDescarte localDescarte) {
		this.localDescarte = localDescarte;
	}
	
	
}
