package service;

import java.util.List;
import spark.Request;
import spark.Response;
import com.google.gson.*;
import dao.FormularioDAO;
import model.Formulario;

public class FormularioService {

    private FormularioDAO formularioDAO;
    private Gson gson;

    public FormularioService() {
        try {
            this.formularioDAO = new FormularioDAO();
            this.gson = new Gson();
        } catch (Exception e) {
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    // Inserir Novo Formulario 
    public boolean insert(Request request, Response response) {
        Formulario form = gson.fromJson(request.body(), Formulario.class);
        // pessoa logada -> int id_pessoa =  obterIdPessoaLogada(); ;
        // animal clicado -> int id_animal =  obterIdAnimalSelecionado(); ;
        try {
            //form.setIdPessoa();
            //form.setIdAnimal();
            formularioDAO.insert(form);
            response.status(201); // 201 Created
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir no service: " + e.getMessage());
            return false;
        }
    }

    //Buscar Formulario
    public Object get(Request request, Response response) {
        try {
            int id = Integer.parseInt(request.params(":id_formulario"));
            Formulario formulario = formularioDAO.get(id);

            if (formulario != null) {
                response.status(200); // success
                response.header("Content-Type", "application/json");
                response.header("Content-Encoding", "UTF-8");
                return gson.toJson(formulario);
            } else {
                response.status(404); // 404 Not found
                return "Formulario " + id + " não encontrado.";
            }
        } catch (NumberFormatException e) {
            response.status(400); // Bad request
            return "ID inválido.";
        } catch (Exception e) {
            response.status(500); // Server error
            return "Erro ao buscar o formulário: " + e.getMessage();
        }
    }

    //Buscar todos os formularios submetidos
    public String getAll(Request request, Response response) {
        List<Formulario> formularios = formularioDAO.getAll();
        response.status(200); // Success
        return gson.toJson(formularios);
    }

    //Atualizar Formulario
    public boolean update(Request request, Response response) {
        Formulario formulario = gson.fromJson(request.body(), Formulario.class);

        if (formulario != null) {
            response.status(200); // success
            return formularioDAO.update(formulario);
        } else {
            response.status(404); // 404 Not found
            return false;
        }
    }

    //Deletar Formulario
    public boolean delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
       
        try{
            return formularioDAO.delete(id);
        } catch (Exception e){
            System.out.println("Erro ao excluir formulario");
            return false;
        }
    }
}
