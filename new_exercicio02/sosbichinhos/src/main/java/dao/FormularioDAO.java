package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Formulario;

public class FormularioDAO extends DAO {
	
	public FormularioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Formulario formulario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
	        String sql = "INSERT INTO \"Formulario\" (id, nome, idade, sexo, cidade, ap_liberado, ciente, teve_animal, permissao, animal_sozinho, aonde_fica, telefone, email, nome_animal, imagem_animal, moradia) "
	                   + "VALUES (" + formulario.getId() + ", '" + formulario.getNome() + "', "  
	                   + formulario.getIdade() + ", '" + formulario.getSexo() + "', '" + formulario.getCidade() + "', '"
	                   + formulario.getApLiberado() + "', '" + formulario.getCiente() + "', '"  + formulario.getTeveAnimal() + "', '"
	                   + formulario.getPermissao() + "', '" + formulario.getAnimalSozinho() + "', '"  + formulario.getAondeFica() + "', '"
	                   + formulario.getTelefone() + "', '" + formulario.getEmail() + "', '"  + formulario.getNomeAnimal() + "', '"
	                   + formulario.getUrlImagem() + "', '" + formulario.getMoradia() + "');";
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
	        String sql = "SELECT * FROM \"Formulario\" WHERE id=" + id;
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);    
	        if (rs.next()) {            
	        	formulario = new Formulario(
	                    rs.getInt("id"),
	                    rs.getString("nome"),
	                    rs.getInt("idade"),
	                    rs.getString("sexo").charAt(0),
	                    rs.getString("cidade"),
	                    rs.getString("ap_liberado"),
	                    rs.getString("ciente"),
	                    rs.getString("teve_animal"),
	                    rs.getString("permissao"),
	                    rs.getString("animal_sozinho"),
	                    rs.getString("aonde_fica"),
	                    rs.getString("telefone"),
	                    rs.getString("email"),
	                    rs.getString("nome_animal"),
	                    rs.getString("imagem_animal"),
	                    rs.getString("moradia")
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
	
	public List<Formulario> getOrderByNome() {
	    return get("nome");
	}

	public List<Formulario> getOrderByIdade() {
	    return get("idade");
	}
	
	public List<Formulario> getOrderBySexo() {
	    return get("sexo");
	}

	public List<Formulario> getOrderByCidade() {
	    return get("cidade");
	}

	public List<Formulario> getOrderByApLiberado() {
	    return get("ap_liberado");
	}
	
	public List<Formulario> getOrderByCiente() {
	    return get("ciente");
	}
	
	public List<Formulario> getOrderByTeveAnimal() {
	    return get("teve_animal");
	}

	public List<Formulario> getOrderByPermissao() {
	    return get("permissao");
	}

	public List<Formulario> getOrderByAnimalSozinho() {
	    return get("animal_sozinho");
	}
	
	public List<Formulario> getOrderByAondefica() {
	    return get("aonde_fica");
	}

	public List<Formulario> getOrderByTelefone() {
	    return get("telefone");
	}

	public List<Formulario> getOrderByEmail() {
	    return get("email");
	}
	
	public List<Formulario> getOrderByNomeAnimal() {
	    return get("nome_animal");
	}
	
	public List<Formulario> getOrderByUrlImagem() {
	    return get("imagem_animal");
	}
	
	public List<Formulario> getOrderByMoradia() {
	    return get("moradia");
	}
	
	private List<Formulario> get(String orderBy) {	
	
		List<Formulario> formularios = new ArrayList<Formulario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"Formulario\"" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Formulario f = new Formulario(
	        			rs.getInt("id"),
	                    rs.getString("nome"),
	                    rs.getInt("idade"),
	                    rs.getString("sexo").charAt(0), 
	                    rs.getString("cidade"),
	                    rs.getString("ap_liberado"),
	                    rs.getString("ciente"),
	                    rs.getString("teve_animal"),
	                    rs.getString("permissao"),
	                    rs.getString("animal_sozinho"),
	                    rs.getString("aonde_fica"),
	                    rs.getString("telefone"),
	                    rs.getString("email"),
	                    rs.getString("nome_animal"),
	                    rs.getString("imagem_animal"),
	                    rs.getString("moradia")
	        	);
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
			String sql = "SELECT * FROM \"Formulario\" WHERE sexo = 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Formulario f = new Formulario(
	        			rs.getInt("id"),
		                rs.getString("nome"),
		                rs.getInt("idade"),
		                rs.getString("sexo").charAt(0),
		                rs.getString("cidade"),
		                rs.getString("ap_liberado"),
		                rs.getString("ciente"),
		                rs.getString("teve_animal"),
		                rs.getString("permissao"),
		                rs.getString("animal_sozinho"),
		                rs.getString("aonde_fica"),
		                rs.getString("telefone"),
		                rs.getString("email"),
		                rs.getString("nome_animal"),
		                rs.getString("imagem_animal"),
		                rs.getString("moradia")
	            );
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
	        String sql = "UPDATE \"Formulario\" SET " +
	                "nome = '" + formulario.getNome() + "', " +
	                "idade = " + formulario.getIdade() + ", " +
	                "sexo = '" + formulario.getSexo() + "', " +
	                "cidade = '" + formulario.getCidade() + "', " +
	                "ap_liberado = '" + formulario.getApLiberado() + "', " + // Corrigido
	                "ciente = '" + formulario.getCiente() + "', " +
	                "teve_animal = '" + formulario.getTeveAnimal() + "', " +
	                "permissao = '" + formulario.getPermissao() + "', " +
	                "animal_sozinho = '" + formulario.getAnimalSozinho() + "', " +
	                "aonde_fica = '" + formulario.getAondeFica() + "', " +
	                "telefone = '" + formulario.getTelefone() + "', " +
	                "email = '" + formulario.getEmail() + "', " +
	                "nome_animal = '" + formulario.getNomeAnimal() + "', " + // Corrigido
	                "imagem_animal = '" + formulario.getUrlImagem() + "', " +
	                "moradia = '" + formulario.getMoradia() + "' " + // Corrigido
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
			String sql = "DELETE FROM \"Formulario\" WHERE id = " + id;
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