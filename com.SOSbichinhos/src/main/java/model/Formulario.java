package model;

public class Formulario {
	
    //---- Atributods Formulario ----//
    private int id_formulario;
    private int idPessoa; // fk Usuario
    private int idAnimal; // fk animal
    private String animalSozinho;
    private boolean familiaCiente;
    private boolean permissao;
    private boolean teveAnimal;
    private boolean ap_liberado;

    //---- Construtores ----//
    public Formulario() {
        this.id_formulario = -1;
        this.idPessoa = -1; // Inicializa como null
        this.idAnimal = -1; // Inicializa como null
        this.animalSozinho = "";
        this.familiaCiente = false; 
        this.permissao = false; 
        this.teveAnimal = false; 
        this.ap_liberado = false;
    }

    public Formulario(int id, String animalSozinho, boolean familiaCiente, boolean permissao, 
        boolean teveAnimal, int idAnimal, int idPessoa, boolean ap_liberado) {
        this.id_formulario = id;
        this.animalSozinho = animalSozinho;
        this.familiaCiente = familiaCiente;
        this.permissao = permissao;
        this.teveAnimal = teveAnimal;
        this.idAnimal = idAnimal;  // Objeto Animal
        this.idPessoa = idPessoa;  // Objeto Pessoa
        this.ap_liberado = ap_liberado;
    }

    //---- Metodos get e set ----//
    public int getIdFormulario() {
        return id_formulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.id_formulario = idFormulario;
    }

    public int getIdPessoa() {
        return idPessoa; 
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public boolean isFamiliaCiente() { 
        return familiaCiente;
    }

    public void setFamiliaCiente(boolean ciente) { 
        this.familiaCiente = ciente;
    }

    public boolean isTeveAnimal() { 
        return teveAnimal;
    }

    public void setTeveAnimal(boolean teveAnimal) { 
        this.teveAnimal = teveAnimal;
    }

    public String getAnimalSozinho() {
        return animalSozinho;
    }

    public void setAnimalSozinho(String animalSozinho) {
        this.animalSozinho = animalSozinho;
    }

    public boolean isPermissao() { 
        return permissao;
    }

    public void setPermissao(boolean permissao) { 
        this.permissao = permissao;
    }

    public boolean isApLiberado() { 
        return ap_liberado;
    }

    public void setApLiberado(boolean ap_liberado) { 
        this.ap_liberado = ap_liberado;
    }


    @Override
    public String toString() {
        return "Formulario [id=" + id_formulario + 
               ", pessoa=" + idPessoa + 
               ", animal=" + idAnimal +
               ", familia_ciente=" + familiaCiente + 
               ", teve_animal=" + teveAnimal + 
               ", permissao=" + permissao + 
               ", animal_sozinho=" + animalSozinho + 
               ", id_animal=" + idAnimal + 
               ", id_pessoa=" + idPessoa +
               ", ap_liberado=" + ap_liberado + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;
        
        Formulario other = (Formulario) obj;
        return this.getIdFormulario() == other.getIdFormulario();
    }
}