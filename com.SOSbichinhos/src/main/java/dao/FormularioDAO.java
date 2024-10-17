package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class FormularioDAO extends DAO {

    public FormularioDAO() {
        this.Conectar();
    }

    @Override
    public void finalize() {
        this.close();
    }

    // Inserir Formulario para adotar animal
    public boolean insert(Formulario formulario) {
        boolean status = false;
        try {  
            String sql = "INSERT INTO formulario (id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, ap_liberado) VALUES(?,?,?,?,?,?,?,?)";
            // para evitar SQL Injection
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, formulario.getIdFormulario());
                stmt.setString(2, formulario.getAnimalSozinho());
                stmt.setBoolean(3, formulario.isFamiliaCiente());  
                stmt.setBoolean(4, formulario.isPermissao());  
                stmt.setBoolean(5, formulario.isTeveAnimal());  
                stmt.setInt(6, formulario.getIdPessoa());  
                stmt.setInt(7, formulario.getIdAnimal());  
                stmt.setBoolean(8, formulario.isApLiberado());  
                
                stmt.executeUpdate();
            }
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    // Obter Formulario pelo ID
    public Formulario get(int id) {
        Formulario formulario = null;
        String sql = "SELECT id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, ap_liberado FROM formulario WHERE id = ?";

        try { 
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id); // Definindo o parâmetro da consulta
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                formulario = new Formulario(
                    rs.getInt("id_formulario"),
                    rs.getString("animal_sozinho"),
                    rs.getBoolean("familia_ciente"),
                    rs.getBoolean("permissao"),
                    rs.getBoolean("teve_animal"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa"),
                    rs.getBoolean("ap_liberado")
                );
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formulario;
    }

    //Listar todos os formularios submetidos
    public List<Formulario> getAll() {
        List<Formulario> formularios = new ArrayList<Formulario>();
        
        try {
            String sql = "SELECT id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, ap_liberado FROM formulario";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
                  
            while(rs.next()) {
                Formulario f = new Formulario(
                    rs.getInt("id_formulario"),
                    rs.getString("animal_sozinho"),
                    rs.getBoolean("familia_ciente"),
                    rs.getBoolean("permissao"),
                    rs.getBoolean("teve_animal"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa"),
                    rs.getBoolean("ap_liberado")
                );

                formularios.add(f);
            }
            
            rs.close(); // Fechar ResultSet
            stmt.close(); // Fechar Statement
        } catch (SQLException e) {
            System.err.println("Erro ao buscar formularios: " + e.getMessage());
            e.printStackTrace();
        }
        return formularios;
    }

    //Atualizar informacoes do formulario
    public boolean update(Formulario formulario) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE formulario SET " +
                    "familia_ciente = '" + formulario.isFamiliaCiente() + "', " +
                    "teve_animal = '" + formulario.isTeveAnimal() + "', " +
                    "permissao = '" + formulario.isPermissao() + "', " +
                    "animal_sozinho = '" + formulario.getAnimalSozinho() + "', " +
                    "id_pessoa = '" + formulario.getIdPessoa() + "', " +
                    "id_animal = " + formulario.getIdAnimal() + ", " +
                    "ap_liberado = " + formulario.isApLiberado() + ", " +
                    "WHERE id = " + formulario.getIdFormulario();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    //Deletar formulario
    public boolean delete(int id) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM formulario WHERE id_formulario = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

}