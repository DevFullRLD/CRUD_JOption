package application;

import java.util.Date;

import javax.swing.JOptionPane;

import DAO.ContatoDAO;
import utilities.Contato;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int op = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Digite o valor correspondente para a operacao desejada: " + "\n"+
						"1 - Incluir Contato"             +"\n"+
						"2 - Ver Todos os Contatos"       +"\n"+
						"3 - Alterar Contato"             +"\n"+
						"4 - Incluir Contato"             +"\n"));

		//INSTANCIA DAS CLASSES
		ContatoDAO contatoDAO = new ContatoDAO();
		Contato contato = new Contato();
		
		switch(op) {
			//INCLUIR DADOS
			case 1:{

				//ENTRADA DE DADOS
				String nome  = JOptionPane.showInputDialog("Digite Seu nome:");
				int idade    = Integer.parseInt(JOptionPane.showInputDialog("Digite sua Idade:"));
				String telefone = JOptionPane.showInputDialog("Digite seu Telefone com DDD:");
				
				//ATRIBUINDO OS VALORES NAS VARIAVEIS
				contato.setNome(nome);
				contato.setIdade(idade);
				contato.setTelefone(telefone);
				contato.setDataCadastro(new Date());
				
				//INSERINDO NO BANCO OS VALORES DIGITADOS
				contatoDAO.insert(contato);						
				break;
			}
			
			//VISUALIZAR TODOS OS DADOS DO BANCO (SELECT)
			case 2:{
				contatoDAO.exibirSelect();
				break;
			}
			
			//ATUALIZAR DADOS DO CONTATO
			case 3:{
				
				int id  = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu ID para a altera��o:"));
				contato.setIdValida(id);
				//VALIDANDO SE O ID E VALIDO
				contatoDAO.validaDados(contato);
				String nome  = JOptionPane.showInputDialog("Digite Seu nome:");
				int idade    = Integer.parseInt(JOptionPane.showInputDialog("Digite sua Idade:"));
				String telefone = JOptionPane.showInputDialog("Digite seu Telefone com DDD:");
				
				//ATRIBUINDO OS VALORES NAS VARIAVEIS
				contato.setNome(nome);
				contato.setIdade(idade);
				contato.setTelefone(telefone);
				contato.setDataCadastro(new Date());
				
				//ALTERANDO NO BANCO OS VALORES DIGITADOS
				contatoDAO.update(contato);
				break;
			}
			
			//DELETE DOS DADOS
			case 4:{
				//VALIDANDO SE O ID E VALIDO
				int id  = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu ID para a altera��o:"));
				contato.setIdValida(id);
				contatoDAO.validaDados(contato);
				//DELETANDO CONTATO DO BANCO
				contatoDAO.delete(id);
				break;
			}
			default:{
				JOptionPane.showMessageDialog(null, "Op��o Digitada � Invalida ");
			break;
			} 
		}
	}

}
