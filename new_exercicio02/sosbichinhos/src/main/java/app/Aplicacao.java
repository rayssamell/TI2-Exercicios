package app;

import java.util.List;

import dao.FormularioDAO;
import model.Formulario;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		FormularioDAO dao = new FormularioDAO(); 
		
		System.out.println("\n\n==== Inserir Registro === ");
		Formulario form = new Formulario(
	    8, "Joao Pedro", 18, 'M', "Bicas","sim","sim", "sim", "sim", "nunca", "amigos" , 
	    "3199999990", "jptorres2009@gmail.com", "mittens", "https://www.petz.com.br/blog/wp-content/uploads/2023/01/tipos-de-gato-persa-3.jpg", 
	    "casa");
		
		if(dao.insert(form) == true) {
			System.out.println("Insercao com sucesso -> " + form.toString());
		}
	
		System.out.println("\n\n==== Mostrar registros do sexo masculino === ");
		List<Formulario> formularios = dao.getSexoMasculino();
		for (Formulario f: formularios) {
		    System.out.println(f.toString());
		}

		System.out.println("\n\n==== Atualizar nome (codigo (" + form.getId() + ") === ");
		form.setNome("Isadora Soares");
		dao.update(form);

		System.out.println("\n\n==== Mostrar registros ordenados por codigo === ");
		formularios = dao.getOrderById();
		for (Formulario f: formularios) {
			System.out.println(f.toString());
		}
		
		System.out.println("\n\n==== Excluir registro (codigo " + form.getId() + ") === ");
		dao.delete(form.getId());
		
		System.out.println("\n\n==== Mostrar registros ordenados por nome === ");
		formularios = dao.getOrderByNome();
		for (Formulario f: formularios) {
			System.out.println(f.toString());
		}
	}
}