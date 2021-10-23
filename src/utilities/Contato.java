package utilities;

import java.util.Date;


public class Contato {
	private int id;
	private String nome;
	private String telefone;
	private int idade;
	private int idValida;
	private Date dataCadastro;

	public int getIdValida() {
		return idValida;
	}
	public void setIdValida(int idValida) {
		this.idValida = idValida;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
