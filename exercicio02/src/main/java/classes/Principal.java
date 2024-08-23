package classes;

import java.util.List;

public class Principal {
	
	public static void main(String[] args) throws Exception {
		
		DAO dao = new DAO();
		
		System.out.println("\n\n==== Inserir Registro === ");
		Formulario form = new Formulario(6, "Charlie", 'M', 20, "Belo Horizonte", "31-98395-5587", "Isadora@gmail.com", "casa",
		"sim", "nao moro em apartamento", "sim", "com meus amigos", "nao", "sim", "Isadora Campos");
		if(dao.insert(form) == true) {
			System.out.println("Inserção com sucesso -> " + form.toString());
		}
		
		System.out.println("\n\n==== Mostrar registros de animais do sexo masculino === ");
		List<Formulario> formularios = dao.getSexoMasculino();
		for (Formulario f: formularios) {
			System.out.println(f.toString());
		}

		System.out.println("\n\n==== Atualizar nome (código (" + form.getId() + ") === ");
		form.setNome("Isadora Soares");
		dao.update(form);

		System.out.println("\n\n==== Mostrar registros ordenados por código === ");
		formularios = dao.getOrderById();
		for (Formulario f: formularios) {
			System.out.println(f.toString());
		}
		
		System.out.println("\n\n==== Excluir registro (código " + form.getId() + ") === ");
		dao.delete(form.getId());
		
		System.out.println("\n\n==== Mostrar registros ordenados por nome === ");
		formularios = dao.getOrderByNome();
		for (Formulario f: formularios) {
			System.out.println(f.toString());
		}
	}
}