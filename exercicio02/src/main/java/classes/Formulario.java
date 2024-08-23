package classes;

public class Formulario {
    private int id;
    private String animal;
    private char sexo;
    private int idade;
    private String cidade;
    private String telefone;
    private String email;
    private String mora;
    private String cientes;
    private String apliberado;
    private String teveanimal;
    private String aondefica;
    private String animalsozinho;
    private String permissao;
    private String nome;

    public Formulario() {
        this.id = -1;
        this.animal = "";
        this.sexo = '*';
        this.idade = 0;
        this.cidade = "";
        this.telefone = "";
        this.email = "";
        this.mora = "";
        this.cientes = "";
        this.apliberado = "";
        this.teveanimal = "";
        this.aondefica = "";
        this.animalsozinho = "";
        this.permissao = "";
        this.nome = "";
    }

    public Formulario(int id, String animal, char sexo, int idade, String cidade, String telefone, String email,
                      String mora, String cientes, String apliberado, String teveanimal, String aondefica,
                      String animalsozinho, String permissao, String nome) {
        this.id = id;
        this.animal = animal;
        this.sexo = sexo;
        this.idade = idade;
        this.cidade = cidade;
        this.telefone = telefone;
        this.email = email;
        this.mora = mora;
        this.cientes = cientes;
        this.apliberado = apliberado;
        this.teveanimal = teveanimal;
        this.aondefica = aondefica;
        this.animalsozinho = animalsozinho;
        this.permissao = permissao;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMora() {
        return mora;
    }

    public void setMora(String mora) {
        this.mora = mora;
    }

    public String getCientes() {
        return cientes;
    }

    public void setCientes(String cientes) {
        this.cientes = cientes;
    }

    public String getApLiberado() {
        return apliberado;
    }

    public void setApLiberado(String apliberado) {
        this.apliberado = apliberado;
    }

    public String getTeveAnimal() {
        return teveanimal;
    }

    public void setTeveAnimal(String teveanimal) {
        this.teveanimal = teveanimal;
    }

    public String getAondeFica() {
        return aondefica;
    }

    public void setAondeFica(String aondefica) {
        this.aondefica = aondefica;
    }

    public String getAnimalSozinho() {
        return animalsozinho;
    }

    public void setAnimalSozinho(String animalsozinho) {
        this.animalsozinho = animalsozinho;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Formulario [id=" + id + ", animal=" + animal + ", sexo=" + sexo + ", idade=" + idade + 
               ", cidade=" + cidade + ", telefone=" + telefone + ", email=" + email + ", mora=" + mora + 
               ", cientes=" + cientes + ", apliberado=" + apliberado + ", teveanimal=" + teveanimal + 
               ", aondefica=" + aondefica + ", animalsozinho=" + animalsozinho + ", permissao=" + permissao + 
               ", nome=" + nome + "]";
    }
}