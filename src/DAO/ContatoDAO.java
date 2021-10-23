package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConnectionBD.ConnetionBD;
import utilities.Contato;

public class ContatoDAO {
	
	/* CRUD
	 * C - CREATE - OK
	 * R - READ   - OK
	 * U - UPDATE - OK
	 * D - DELETE - OK
	*/ 
	
	//INSECAO DE DADOS
		public void insert(Contato contato) {
			String sql = "INSERT INTO contatos(nome, idade, telefone, dataCadastro) values(?, ?, ?, ?)";
			
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				//CRIANDO CONEXAO DO INSERT
				conn = ConnetionBD.createConnetion();
				
				//CRIAR O PREPAREDSTATEMENT - EXECUTAR SQL
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				
				//BUSCANDO VALORES ESPERADOS
				pstm.setString(1, contato.getNome());
				pstm.setInt(2, contato.getIdade());
				pstm.setString(3, contato.getTelefone());
				pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));
				
				//EXECUTAR QUERY
				pstm.execute();			
				JOptionPane.showMessageDialog(null,"Contato Incluido com sucesso");
				
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				//FINALIZAR CONEXOES ATIVAS
				try {
					if (pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
	//SELECT TODOS CONTATOS
		public static List<Contato> getContatos(){
			//SELECT DO BANCO
			String sql = "SELECT * FROM contatos";
			
			//GUARDANDO EM UM ARRAY
			List<Contato> contatos = new ArrayList<Contato>();	
			
			Connection conn = null;
			PreparedStatement pstm = null;
			//CLASSE DE RECUPERACAO DE DADOS
			ResultSet rset = null;
			
			try{
				//CRIANDO CONEXAO DO SELECT
				conn = ConnetionBD.createConnetion();
				
				//CRIAR O PREPAREDSTATEMENT - EXECUTAR SQL
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				
				//TRAZENDO AS INFORMA��ES DO BD
				rset = pstm.executeQuery();	
				
				while(rset.next()) {
					Contato contato = new Contato();
					
					//RECUPERA ID
					contato.setId(rset.getInt("id"));
					//RECUPERA NOME
					contato.setNome(rset.getString("nome"));
					//RECUPERA IDADE
					contato.setIdade(rset.getInt("idade"));
					//RECUPERA TELEFONE 
					contato.setTelefone(rset.getString("telefone"));
					//RECUPERA DATA 
					contato.setDataCadastro(rset.getDate("dataCadastro"));
					
					contatos.add(contato);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					//FINALIZAR CONEXOES ATIVAS
					if (rset!=null) {
						rset.close();
					}
					if (pstm!= null) {
						pstm.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			
			return contatos;
		}
		
	//EXIBIR TODOS DADOS DO SELECT
		public void exibirSelect() {
			for(Contato c: ContatoDAO.getContatos()){
				JOptionPane.showMessageDialog(null, "Contato "   + "\n"+
													"ID: "       + c.getId() + "\n" +
												    "Nome: "     + c.getNome() + "\n" +
													"Idade: "    + c.getIdade() + "\n" +
													"Telefone: " + c.getTelefone() + "\n" +
													"Data: "     + c.getDataCadastro());
			}
		}
	
	//VALIDAR ID NO BANCO
	public void validaDados(Contato contato){

		//SELECT DO BANCO
		String sql = "SELECT id FROM contatos where id=" + contato.getIdValida();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//CLASSE DE RECUPERACAO DE DADOS
		ResultSet rset = null;
		
		try{
			//CRIANDO CONEXAO DO SELECT
			conn = ConnetionBD.createConnetion();
			
			//CRIAR DE PREPAREDSTATEMENT - EXECUTAR SQL
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//TRAZENDO AS INFORMACOES DO BD
			rset = pstm.executeQuery();	
			if(rset.next()) {
				JOptionPane.showMessageDialog(null, "ID Localizado");							
			}else {
				JOptionPane.showMessageDialog(null, "ID N�o Localizado");
				System.exit(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//FINALIZAR CONEXOES ATIVAS
				if (rset!=null) {
					rset.close();
				}
				if (pstm!= null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}
	
	//ALTERAR DADOS NO BANCO
	public void update(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, telefone = ?, dataCadastro= ?" + " where id ="+ contato.getIdValida() ;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//CRIANDO CONEXAO DA INSERCAO
			conn = ConnetionBD.createConnetion();
					
			//CRIAR O PREPAREDSTATEMENT - EXECUTAR SQL
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//BUSCANDO VALORES ESPERADOS
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setString(3, contato.getTelefone());
			pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));
						
			//EXECUTAR  A QUERY
			pstm.execute();
			JOptionPane.showMessageDialog(null,"Dados Alterados Com sucesso!");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//FINALIZAR CONEXOES ATIVAS
				if (pstm!= null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	//EXCLUIR DADOS DO BANCO
	public void delete(int id) {
		String sql = "DELETE FROM contatos WHERE id= ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//CRIANDO CONEXAO DA INSERCAO
			conn = ConnetionBD.createConnetion();
					
			//CRIAR O PREPAREDSTATEMENT - EXECUTAR SQL
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//COMPARAR DADOS
			pstm.setInt(1, id);
			//EXECUTAR  A QUERY
			pstm.execute();
			JOptionPane.showMessageDialog(null, "Contato Excluido!");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					//FINALIZAR CONEXOES ATIVAS
					if (pstm!= null) {
						pstm.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}catch (Exception e){
					e.printStackTrace();
			}
		}
	}
}
