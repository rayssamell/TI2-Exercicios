package app;

import static spark.Spark.*;
import service.FormularioService;
import java.io.File;


public class AplicacaoFormulario {
	
	private static FormularioService formularioService = new FormularioService();

    public static void main(String[] args) {
        port(8081);
        
        staticFiles.location("/public");

        get("/requerimentos", (request, response) -> {
            response.type("text/html");
            return new File("src/main/resources/public/modules/requerimentos/requerimentos.html");
        });

        post("/formulario", (request, response) -> formularioService.insert(request, response));
        
        get("/formulario/:id", (request, response) -> formularioService.get(request, response));
        
        get("/formulario", (request, response) -> formularioService.getAll(request, response));
        
        post("/formulario/update/:id", (request, response) -> formularioService.update(request, response));
           
        delete("/formulario/:id", (request, response) -> formularioService.delete(request, response));
   
    }
}