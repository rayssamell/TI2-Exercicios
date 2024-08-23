package classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	protected Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "LocalHost";
		String mydatabase = "Exercicio02";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "1234";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	public boolean insert(Formulario formulario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO formulario (id, animal, sexo, idade, cidade, telefone, email, mora, cientes, apliberado, teveanimal, aondefica, animalsozinho, permissao, nome) "
				       + "VALUES ("+formulario.getId()+ ", '" + formulario.getAnimal() + "', '"  
				       + formulario.getIdade() + "', '" + formulario.getSexo() + "', '" + formulario.getCidade() + "', '"
				       + formulario.getTelefone() + "', '" + formulario.getEmail() + "', '"  + formulario.getMora() + "', '"
				       + formulario.getCientes() + "', '" + formulario.getApLiberado() + "', '"  + formulario.getTeveAnimal() + "', '"
				       + formulario.getAondeFica() + "', '" + formulario.getAnimalSozinho() + "', '"  + formulario.getPermissao() + "', '"
				       + formulario.getNome() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Formulario get(int id) {
	    Formulario formulario = null;
	    
	    try {
	        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        String sql = "SELECT * FROM formulario WHERE id=" + id;
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);    
	        if (rs.next()) {            
	            formulario = new Formulario(
	                rs.getInt("id"),
	                rs.getString("animal"),
	                rs.getString("sexo").charAt(0),
	                rs.getInt("idade"),
	                rs.getString("cidade"),
	                rs.getString("telefone"),
	                rs.getString("email"),
	                rs.getString("mora"),
	                rs.getString("cientes"),
	                rs.getString("apliberado"),
	                rs.getString("teveanimal"),
	                rs.getString("aondefica"),
	                rs.getString("animalsozinho"),
	                rs.getString("permissao"),
	                rs.getString("nome")
	            );
	        }
	        st.close();
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	    return formulario;
	}

	
	
	public List<Formulario> get() {
		return get("");
	}

	
	public List<Formulario> getOrderById() {
	    return get("id");
	}

	public List<Formulario> getOrderByAnimal() {
	    return get("animal");
	}

	public List<Formulario> getOrderBySexo() {
	    return get("sexo");
	}

	public List<Formulario> getOrderByIdade() {
	    return get("idade");
	}

	public List<Formulario> getOrderByCidade() {
	    return get("cidade");
	}

	public List<Formulario> getOrderByTelefone() {
	    return get("telefone");
	}

	public List<Formulario> getOrderByEmail() {
	    return get("email");
	}

	public List<Formulario> getOrderByMora() {
	    return get("mora");
	}

	public List<Formulario> getOrderByCientes() {
	    return get("cientes");
	}

	public List<Formulario> getOrderByApliberado() {
	    return get("apliberado");
	}

	public List<Formulario> getOrderByTeveAnimal() {
	    return get("teveanimal");
	}

	public List<Formulario> getOrderByAondefica() {
	    return get("aondefica");
	}

	public List<Formulario> getOrderByAnimalsozinho() {
	    return get("animalsozinho");
	}

	public List<Formulario> getOrderByPermissao() {
	    return get("permissao");
	}

	public List<Formulario> getOrderByNome() {
	    return get("nome");
	}

	
	
	private List<Formulario> get(String orderBy) {	
	
		List<Formulario> formularios = new ArrayList<Formulario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM formulario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Formulario f = new Formulario(rs.getInt("id"),
	        		    rs.getString("animal"),
	        		    rs.getString("sexo").charAt(0),
	        		    rs.getInt("idade"),
	        		    rs.getString("cidade"),
	        		    rs.getString("telefone"),
	        		    rs.getString("email"),
	        		    rs.getString("mora"),
	        		    rs.getString("cientes"),
	        		    rs.getString("apliberado"),
	        		    rs.getString("teveanimal"),
	        		    rs.getString("aondefica"),
	        		    rs.getString("animalsozinho"),
	        		    rs.getString("permissao"),
	        		    rs.getString("nome"));
	           formularios.add(f);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return formularios;
	}


	public List<Formulario> getSexoMasculino() {
		List<Formulario> formularios = new ArrayList<Formulario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM formulario WHERE formulario.sexo LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Formulario f = new Formulario(rs.getInt("id"),
	        		    rs.getString("animal"),
	        		    rs.getString("sexo").charAt(0),
	        		    rs.getInt("idade"),
	        		    rs.getString("cidade"),
	        		    rs.getString("telefone"),
	        		    rs.getString("email"),
	        		    rs.getString("mora"),
	        		    rs.getString("cientes"),
	        		    rs.getString("apliberado"),
	        		    rs.getString("teveanimal"),
	        		    rs.getString("aondefica"),
	        		    rs.getString("animalsozinho"),
	        		    rs.getString("permissao"),
	        		    rs.getString("nome"));
	        	formularios.add(f);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return formularios;
	}
	
	
	public boolean update(Formulario formulario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE formulario SET " +
				    "animal = '" + formulario.getAnimal() + "', " +
				    "sexo = '" + formulario.getSexo() + "', " +
				    "idade = " + formulario.getIdade() + ", " +
				    "cidade = '" + formulario.getCidade() + "', " +
				    "telefone = '" + formulario.getTelefone() + "', " +
				    "email = '" + formulario.getEmail() + "', " +
				    "mora = '" + formulario.getMora() + "', " +
				    "cientes = '" + formulario.getCientes() + "', " +
				    "apliberado = '" + formulario.getApLiberado() + "', " +
				    "teveanimal = '" + formulario.getTeveAnimal() + "', " +
				    "aondefica = '" + formulario.getAondeFica() + "', " +
				    "animalsozinho = '" + formulario.getAnimalSozinho() + "', " +
				    "permissao = '" + formulario.getPermissao() + "', " +
				    "nome = '" + formulario.getNome() + "' " +
				    "WHERE id = " + formulario.getId();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM formulario WHERE id = " + id;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}