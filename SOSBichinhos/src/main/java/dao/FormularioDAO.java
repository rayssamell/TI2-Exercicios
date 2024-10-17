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

    private int maxId = 0;

    public int getMaxId() {
    String sql = "SELECT MAX(id) as maxId FROM formulario";
    try {
        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = st.executeQuery(sql); 
        if (rs.next()) {
            maxId = rs.getInt("maxId");
        }
        rs.close(); 
        st.close(); 
    } catch (SQLException u) {
        u.printStackTrace();
    }
    
    return maxId;
    }

    //inserir um formulario no banco de dados
    public boolean insert(Formulario formulario) {
        boolean status = false;
        try {  
            
            this.maxId = (formulario.getIdFormulario() > this.maxId) ? formulario.getIdFormulario() : this.maxId;
            String sql = "INSERT INTO formulario (id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, ap_liberado) VALUES(?,?,?,?,?,?,?,?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, formulario.getIdFormulario());
                stmt.setString(2, formulario.getAnimalSozinho());
                stmt.setBoolean(3, formulario.isFamiliaCiente());  
                stmt.setBoolean(4, formulario.isPermissao());  
                stmt.setBoolean(5, formulario.isTeveAnimal());  
                stmt.setInt(6, formulario.getPessoa().getId());  
                stmt.setInt(7, formulario.getAnimal().getId());  
                stmt.setBoolean(8, formulario.isApLiberado());  
                stmt.executeUpdate();
            }
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }


    //obter o formulario pelo if
    public Formulario get(int id) {
        Formulario formulario = null;
        
        try { 
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Formulario f " +
                         "JOIN Pessoa p ON f.id_pessoa = p.id " +
                         "JOIN Animal a ON f.id_animal = a.id " +
                         "WHERE f.id = ?";
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                Pessoa pessoa = new Pessoa(
                    rs.getInt("p.id_pessoa"),
                    rs.getString("p.nome"),
                    rs.getString("p.email"),
                    rs.getString("p.senha"),
                    rs.getString("p.moradia"),
                    rs.getString("p.imagem"),
                    rs.getInt("p.idade"),
                    rs.getString("p.sexo")
                );

                // Montar o objeto Animal
                Animal animal = new Animal(
                    rs.getInt("a.id_animal"), 
                    rs.getString("a.imagem"), 
                    rs.getString("a.nome"), 
                    rs.getString("a.sexo").charAt(0), 
                    rs.getString("a.idade"), 
                    rs.getString("a.raca"), 
                    rs.getString("a.vacinas"), 
                    rs.getBoolean("a.castrado"), 
                    rs.getString("a.historia"), 
                    rs.getString("a.porte").charAt(0), 
                    rs.getString("a.especie")
                );
                formulario = new Formulario(
                    rs.getInt("f.id_formulario"),
                    rs.getString("f.animal_sozinho"),
                    rs.getBoolean("f.familia_ciente"),
                    rs.getBoolean("f.permissao"),
                    rs.getBoolean("f.teve_animal"),
                    animal, // Objeto Animal já criado
                    pessoa, // Objeto Pessoa já criado
                    rs.getBoolean("f.ap_liberado")
                );
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formulario;
    }

    //obter todos os formularios
    public Formulario[] getForms() {
        Formulario[] form = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM formulario ");

            if (rs.next()) {
                rs.last();
                form = new Formulario[rs.getRow()];
                rs.beforeFirst();
            }

            for (int i = 0; rs.next(); i++) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                AnimalDAO animalDAO = new AnimalDAO();
    
                // Recuperando os objetos Pessoa e Animal relacionados ao formulário
                Pessoa pessoa = pessoaDAO.get(rs.getInt("id_pessoa"));
                Animal animal = animalDAO.get(rs.getInt("id_animal"));
    
                form[i++] = new Formulario(
                    rs.getInt("id_formulario"),
                    rs.getString("animal_sozinho"),
                    rs.getBoolean("familia_ciente"),
                    rs.getBoolean("permissao"),
                    rs.getBoolean("teve_animal"),
                    animal, // Objeto Animal já criado
                    pessoa, // Objeto Pessoa já criado
                    rs.getBoolean("ap_liberado")
                );
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return form;
    }

    public List<Formulario> get(String orderBy) {
        List<Formulario> formularios = new ArrayList<Formulario>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Usando JOIN para obter dados de Pessoa e Animal
            String sql = "SELECT * FROM Formulario f " +
                         "JOIN Pessoa p ON f.id_pessoa = p.id " +
                         "JOIN Animal a ON f.id_animal = a.id " +
                         "WHERE f.id = ?"
                        + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));

            ResultSet rs = st.executeQuery(sql);	         
            while(rs.next()) {
                // Criar objetos Pessoa e Animal a partir do ResultSet
                Pessoa pessoa = new Pessoa(
                    rs.getInt("p.id_pessoa"),
                    rs.getString("p.nome"),
                    rs.getString("p.email"),
                    rs.getString("p.senha"),
                    rs.getString("p.moradia"),
                    rs.getString("p.imagem"),
                    rs.getInt("p.idade"),
                    rs.getString("p.sexo")
                );

                Animal animal = new Animal(
                    rs.getInt("a.id_animal"), 
                    rs.getString("a.imagem"), 
                    rs.getString("a.nome"), 
                    rs.getString("a.sexo").charAt(0), 
                    rs.getString("a.idade"), 
                    rs.getString("a.raca"), 
                    rs.getString("a.vacinas"), 
                    rs.getBoolean("a.castrado"), 
                    rs.getString("a.historia"), 
                    rs.getString("a.porte").charAt(0), 
                    rs.getString("a.especie")
                );
                
                Formulario f = new Formulario(
                    rs.getInt("f.id_formulario"),
                    rs.getString("f.animal_sozinho"),
                    rs.getBoolean("f.familia_ciente"),
                    rs.getBoolean("f.permissao"),
                    rs.getBoolean("f.teve_animal"),
                    animal, // Objeto Animal já criado
                    pessoa, // Objeto Pessoa já criado
                    rs.getBoolean("f.ap_liberado")
                );

                formularios.add(f);
            }
            
            rs.close(); // Fechar ResultSet
            st.close(); // Fechar Statement
        } catch (SQLException e) {
            System.err.println("Erro ao buscar formularios: " + e.getMessage());
            e.printStackTrace(); // Melhor para depuração
        }
        return formularios;
    }

    //atualizar informacoes do formulario
    public boolean update(Formulario formulario) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE formulario SET " +
                    "familia_ciente = '" + formulario.isFamiliaCiente() + "', " +
                    "teve_animal = '" + formulario.isTeveAnimal() + "', " +
                    "permissao = '" + formulario.isPermissao() + "', " +
                    "animal_sozinho = '" + formulario.getAnimalSozinho() + "', " +
                    "id_pessoa = '" + formulario.getPessoa().getId() + "', " +
                    "id_animal = " + formulario.getAnimal().getId() + ", " +
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

    //deletar formulario
    public boolean delete(int id) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM formulario WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

}