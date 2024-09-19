package app;

import static spark.Spark.*;
import service.FormularioService;


public class Aplicacao {
	
	private static FormularioService formularioService = new FormularioService();
	
    public static void main(String[] args) {
        port(8080);
        
        staticFiles.location("/public");
        
        post("/formulario/insert", (request, response) -> formularioService.insert(request, response));

        get("/formulario/:id", (request, response) -> formularioService.get(request, response));
        
        get("/formulario/list/:orderby", (request, response) -> formularioService.getAll(request, response));
        
        get("/formulario/update/:id", (request, response) -> formularioService.getToUpdate(request, response));
        
        post("/formulario/update/:id", (request, response) -> formularioService.update(request, response));
           
        get("/formulario/delete/:id", (request, response) -> formularioService.delete(request, response));

             
    }
}