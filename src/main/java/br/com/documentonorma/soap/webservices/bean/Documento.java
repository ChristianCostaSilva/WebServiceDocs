package br.com.documentonorma.soap.webservices.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table (name="docs")
public class Documento {

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private Integer id;
	private String norma;
	
	@Lob
	private byte [] documento;
	
	
	
	public Documento(Integer id, String norma, byte[] documento) {
		super();
		this.id = id;
		this.norma = norma;
		this.documento = documento;
	}
	
	public Documento() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNorma() {
		return norma;
	}
	public void setNorma(String norma) {
		this.norma = norma;
	}
	public byte[] getDocumento() {
		return documento;
	}
	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "documento [id=" + id + ", norma=" + norma + ", documento=" + documento + "]";
	}

	
	
	 
}
