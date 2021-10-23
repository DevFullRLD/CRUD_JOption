package ConnectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConnetionBD {
	//NOME DE USUARIO DO BD
	private static final String USERNAME= "root";
	
	//SENHA DO BD
	private static final String PASSWORD ="";
	
	// CAMINHO DO BD - PORTA 3306 - NOME DO BD
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	//CONEX�O COM O BD
	public static Connection createConnetion() throws Exception{
		//CARREGANDO CLASSE PELA JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//CRIANDO A CONEXAO COM O BD
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
	}
	public static void main(String[] args) throws Exception {
		//RECUPERANDO CONEXAO COM O BD CASO TENHA ALGUMA INICIADA
		Connection con = createConnetion();
		
		// TESTANDO CONEXAO
		if(con!=null) {
			JOptionPane.showMessageDialog(null, "Conex�o Efetuada com sucesso");
			con.close();
		}
	}
}
