package service;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import dao.FormularioDAO;
import model.Formulario;
import spark.Request;
import spark.Response;


public class FormularioService {

	private FormularioDAO formularioDAO = new FormularioDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_IDADE = 3;
	private final int FORM_ORDERBY_SEXO = 4;
	private final int FORM_ORDERBY_CIDADE = 5;
	private final int FORM_ORDERBY_CIENTE = 6;
	private final int FORM_ORDERBY_TEVEANIMAL = 7;
	private final int FORM_ORDERBY_PERMISSAO = 8;
	private final int FORM_ORDERBY_ANIMALSOZINHO = 9;
	private final int FORM_ORDERBY_APLIBERADO = 10;
	private final int FORM_ORDERBY_AONDEFICA = 11;
	private final int FORM_ORDERBY_TELEFONE = 12;
	private final int FORM_ORDERBY_EMAIL = 13;
	private final int FORM_ORDERBY_NOMEANIMAL = 14;
	private final int FORM_ORDERBY_IMAGEMANIMAL = 15;
	private final int FORM_ORDERBY_MORADIA = 17;
  
	public FormularioService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Formulario(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Formulario(), orderBy);
	}

	
	public void makeForm(int tipo, Formulario formulario, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umFormulario = "";
		if(tipo != FORM_INSERT) {
			umFormulario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/Formulario/list/1\">Novo Registro</a></b></font></td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t</table>";
			umFormulario += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/formulario/";
			String name, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Formulario";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + formulario.getId();
				name = "Atualizar Registro (ID " + formulario.getId() + ")";
				buttonLabel = "Atualizar";
			}
			umFormulario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umFormulario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ formulario.getNome() +"\"></td>";
			umFormulario += "\t\t\t<td>Idade: <input class=\"input--register\" type=\"text\" name=\"idade\" value=\""+ formulario.getIdade() +"\"></td>";
			umFormulario += "\t\t\t<td>Sexo: <input class=\"input--register\" type=\"text\" name=\"sexo\" value=\""+ formulario.getSexo() +"\"></td>";
			umFormulario += "\t\t\t<td>Cidade: <input class=\"input--register\" type=\"text\" name=\"cidade\" value=\""+ formulario.getCidade() +"\"></td>";
			umFormulario += "\t\t\t<td>Ciente: <input class=\"input--register\" type=\"text\" name=\"ciente\" value=\""+ formulario.getCiente() +"\"></td>";
			umFormulario += "\t\t\t<td>Teve animal: <input class=\"input--register\" type=\"text\" name=\"teveAnimal\" value=\""+ formulario.getTeveAnimal() +"\"></td>";
			umFormulario += "\t\t\t<td>Permissao: <input class=\"input--register\" type=\"text\" name=\"permissao\" value=\""+ formulario.getPermissao() +"\"></td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td>Ap Liberado: <input class=\"input--register\" type=\"text\" name=\"apLiberado\" value=\""+ formulario.getApLiberado() +"\"></td>";
			umFormulario += "\t\t\t<td>Aonde Fica: <input class=\"input--register\" type=\"text\" name=\"aondeFica\" value=\""+ formulario.getAondeFica() +"\"></td>";
			umFormulario += "\t\t\t<td>Telefone: <input class=\"input--register\" type=\"text\" name=\"telefone\" value=\""+ formulario.getTelefone() +"\"></td>";
			umFormulario += "\t\t\t<td>Email: <input class=\"input--register\" type=\"text\" name=\"email\" value=\""+ formulario.getEmail() +"\"></td>";
			umFormulario += "\t\t\t<td>Nome animal: <input class=\"input--register\" type=\"text\" name=\"nomeAnimal\" value=\""+ formulario.getNomeAnimal() +"\"></td>";
			umFormulario += "\t\t\t<td>Imagem Animal: <input class=\"input--register\" type=\"text\" name=\"imagemAnimal\" value=\""+ formulario.getUrlImagem() +"\"></td>";
			umFormulario += "\t\t\t<td>Moradia: <input class=\"input--register\" type=\"text\" name=\"moradia\" value=\""+ formulario.getMoradia() +"\"></td>";
			umFormulario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t</table>";
			umFormulario += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umFormulario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Formulario (ID " + formulario.getId() + ")</b></font></td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td>&nbsp;Nome: "+ formulario.getNome() +"</td>";
			umFormulario += "\t\t\t<td>Idade: "+ formulario.getIdade() +"</td>";
			umFormulario += "\t\t\t<td>Sexo: "+ formulario.getSexo() +"</td>";
			umFormulario += "\t\t\t<td>Cidade: "+ formulario.getCidade() +"</td>";
			umFormulario += "\t\t\t<td>Ciente: "+ formulario.getCiente() +"</td>";
			umFormulario += "\t\t\t<td>Teve animal: "+ formulario.getTeveAnimal() +"</td>";
			umFormulario += "\t\t\t<td>Permissao: "+ formulario.getPermissao() +"</td>";
			umFormulario += "\t\t\t<td>Animal Sozinho: "+ formulario.getAnimalSozinho() +"</td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t\t<tr>";
			umFormulario += "\t\t\t<td>&nbsp;Ap Liberado: "+ formulario.getApLiberado() + "</td>";
			umFormulario += "\t\t\t<td>Aonde Fica: "+ formulario.getAondeFica() + "</td>";
			umFormulario += "\t\t\t<td>Telefone: "+ formulario.getTelefone() +"</td>";
			umFormulario += "\t\t\t<td>Email: "+ formulario.getEmail() +"</td>";
			umFormulario += "\t\t\t<td>Nome Animal: "+ formulario.getNomeAnimal() +"</td>";
			umFormulario += "\t\t\t<td>Imagem Animal: "+ formulario.getUrlImagem() +"</td>";
			umFormulario += "\t\t\t<td>Moradia: "+ formulario.getMoradia() +"</td>";
			umFormulario += "\t\t\t<td>&nbsp;</td>";
			umFormulario += "\t\t</tr>";
			umFormulario += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-Formulario>", umFormulario);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Formularios</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_IDADE + "\"><b>Idade</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_SEXO + "\"><b>Sexo</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_CIDADE + "\"><b>Cidade</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_CIENTE + "\"><b>Ciente</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_TEVEANIMAL + "\"><b>TeveAnimal</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_PERMISSAO + "\"><b>Permissao</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_ANIMALSOZINHO + "\"><b>Animal Sozinho</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_APLIBERADO + "\"><b>ApLiberado</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_AONDEFICA + "\"><b>AondeFIca</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_TELEFONE + "\"><b>Telefone</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_EMAIL + "\"><b>Email</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_NOMEANIMAL + "\"><b>NomeAnimall</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_IMAGEMANIMAL + "\"><b>ImagemAnimal</b></a></td>\n" +
				"\t<td><a href=\"/formulario/list/" +FORM_ORDERBY_MORADIA + "\"><b>Moradia</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Formulario> Formularios;
		if (orderBy == FORM_ORDERBY_ID) {                 	
			Formularios = formularioDAO.getOrderById();
		} else if (orderBy == FORM_ORDERBY_NOME) {		
			Formularios = formularioDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_IDADE) {		
			Formularios = formularioDAO.getOrderByIdade();
		} else {											
			Formularios = formularioDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Formulario p : Formularios) {
		    bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
		    list += "\n<tr bgcolor=\"" + bgcolor + "\">\n" + 
		            "\t<td>" + p.getId() + "</td>\n" +
		            "\t<td>" + p.getNome() + "</td>\n" +
		            "\t<td>" + p.getIdade() + "</td>\n" +
		            "\t<td>" + p.getSexo() + "</td>\n" +  
		            "\t<td>" + p.getCidade() + "</td>\n" +  
		            "\t<td>" + p.getCiente() + "</td>\n" +  
		            "\t<td>" + p.getTeveAnimal() + "</td>\n" +  
		            "\t<td>" + p.getPermissao() + "</td>\n" +  
		            "\t<td>" + p.getAnimalSozinho() + "</td>\n" +  
		            "\t<td>" + p.getApLiberado() + "</td>\n" +  
		            "\t<td>" + p.getAondeFica() + "</td>\n" +  
		            "\t<td>" + p.getTelefone() + "</td>\n" +  
		            "\t<td>" + p.getEmail() + "</td>\n" +  
		            "\t<td>" + p.getNomeAnimal() + "</td>\n" +  
		            "\t<td>" + p.getUrlImagem() + "</td>\n" +  
		            "\t<td>" + p.getMoradia() + "</td>\n" +  
		            "\t<td align=\"center\" valign=\"middle\"><a href=\"/formulario/" + p.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
		            "\t<td align=\"center\" valign=\"middle\"><a href=\"/formulario/update/" + p.getId() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
		            "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteFormulario('" + p.getId() + "', '" + p.getNome() + "', '" + p.getIdade() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
		            "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-FORMULARIO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		int idade = Integer.parseInt(request.queryParams("idade"));
		String sexoStr = request.queryParams("sexo");
		char sexo = (sexoStr != null && !sexoStr.isEmpty()) ? sexoStr.charAt(0) : ' '; 
		String cidade = request.queryParams("cidade");
		String ciente = request.queryParams("ciente");
		String teve_animal = request.queryParams("teveAnimal");
		String permissao= request.queryParams("permissao");
		String ap_liberado = request.queryParams("apLiberado");
		String animal_sozinho= request.queryParams("animalSozinho");
		String aonde_fica= request.queryParams("aondeFica");
		String telefone= request.queryParams("telefone");
		String email= request.queryParams("email");
		String nome_animal= request.queryParams("nomeAnimal");
		String imagem_animal= request.queryParams("imagemAnimal");
		String moradia = request.queryParams("moradia");

		String resp = "";
		
		Formulario formulario = new Formulario(-1, nome, idade, sexo, cidade, ciente, teve_animal, permissao, 
		ap_liberado, animal_sozinho, aonde_fica, telefone, email, nome_animal, imagem_animal, moradia);
		
		if(formularioDAO.insert(formulario) == true) {
            resp = "Formulario (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Formulario (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Formulario formulario = (Formulario) formularioDAO.get(id);
		
		if (formulario != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, formulario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Formulario " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Formulario formulario = (Formulario) formularioDAO.get(id);
		
		if (formulario != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, formulario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Formulario " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Formulario formulario = formularioDAO.get(id);
        String resp;       

        if (formulario != null) {
			formulario.setNome(request.queryParams("descricao"));
			formulario.setIdade(Integer.parseInt(request.queryParams("idade")));
			formulario.setSexo(request.queryParams("sexo").charAt(0));
			formulario.setCidade(request.queryParams("cidade"));
			formulario.setCiente(request.queryParams("ciente"));
			formulario.setTeveAnimal(request.queryParams("teveAnimal"));
			formulario.setPermissao(request.queryParams("permissao"));
			formulario.setApLiberado(request.queryParams("apLiberado"));
			formulario.setAnimalSozinho(request.queryParams("animalSozinho"));
			formulario.setAondeFica(request.queryParams("aondeFica"));
			formulario.setTelefone(request.queryParams("telefone"));
			formulario.setEmail(request.queryParams("email"));
			formulario.setNomeAnimal(request.queryParams("nomeAnimal"));
			formulario.setUrlImagem(request.queryParams("imagemAnimal"));
			formulario.setMoradia(request.queryParams("moradia"));
        	formularioDAO.update(formulario);
            resp = "Registro (ID " + formulario.getId() + ") atualizado!";
			response.status(200); // success
		} else {
            response.status(404); // 404 Not found
            resp = "Registro (ID \" + Formulario.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Formulario formulario = formularioDAO.get(id);
        String resp;       

        if (formulario != null) {
            formularioDAO.delete(id);
            response.status(200); // success
            resp = "Registro (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Registro (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}