package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 60, nullable=false)
	private String estado;
	
	@Column(length = 60, nullable=false)
	private String cidade;
	
	@Column(length = 60, nullable=false)
	private String bairro;
	
	@Column(length = 60, nullable=false)
	private String rua;
	
	@Column(length = 60, nullable=false)
	private String numero;
	
	@ManyToOne
	private LocalDescarte localDescarte;
	

	public Endereco(String estado, String cidade, String bairro, String rua, String numero) {
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}

	public Endereco() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDescarte getLocalDescarte() {
		return localDescarte;
	}

	public void setLocalDescarte(LocalDescarte localDescarte) {
		this.localDescarte = localDescarte;
	}
}
