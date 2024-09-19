document.addEventListener("DOMContentLoaded", () => {

    const apiUrl_Formulario = "/formulario";

    // Função para salvar formulário submetido
    function saveForm(dado) {
        fetch(apiUrl_Formulario, { 
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dado)
        }).then(response => response.json())
          .then(dado => {
            console.log(dado);
            alert("Formulário submetido com sucesso!");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao submeter formulário.");
          });
    }

    // Função para deletar formulários
    function deleteForm(id) {
        fetch(`${apiUrl_Formulario}/delete/` + id, {
            method: 'DELETE',
        }).then(response => response.json())
          .then(id => {
            console.log(id);
            alert("Formulário " + id + " removido com sucesso");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao remover o formulário");
          });
    }

    // Função para inserir novo formulário
    function insertForm() {
        const inputNome = document.querySelector("#nome").value;
        const inputIdade = document.querySelector("#idade").value;
        const inputSexo = document.querySelector('input[name="sexo"]:checked').value;
        const inputCidade = document.querySelector("#cidade").value;
        const inputCiente = document.querySelector("#ciente").value;
        const inputTeveAnimal = document.querySelector("#teveAnimal").value;
        const inputPermissao = document.querySelector("#permissao").value;
        const inputAnimalSozinho = document.querySelector("#animalSozinho").value;
        const inputAondeFica = document.querySelector("#aondeFica").value;
        const inputMoradia = document.querySelector("#moradia").value;
        const inputTelefone = document.querySelector("#telefone").value;
        const inputEmail = document.querySelector("#email").value;
        const inputNomeAnimal = document.querySelector("#nomeAnimal").value;
        const inputImagemAnimal = document.querySelector("#url_imagem").value;

        let formulario = {
            nome: inputNome,
            idade: inputIdade,
            sexo: inputSexo,
            cidade: inputCidade,
            ciente: inputCiente,
            teveAnimal: inputTeveAnimal,
            permissao: inputPermissao,
            animalSozinho: inputAnimalSozinho,
            aondeFica: inputAondeFica,
            moradia: inputMoradia,
            telefone: inputTelefone,
            email: inputEmail,
            nomeAnimal: inputNomeAnimal,
            imagemAnimal: inputImagemAnimal
        };

        saveForm(formulario);
        console.log(formulario);
    }

    // Eventos - Botões
    const SubmeterFormBtn = document.querySelector(".botao_adocao");
    const RemoverBtn = document.querySelector(".botao_remover");
    const remover_id = document.querySelector("#remover_registro");

    SubmeterFormBtn.addEventListener("click", function(event) {
        event.preventDefault();
        insertForm();
    });

    RemoverBtn.addEventListener("click", function() {
        const id = remover_id.value;
        deleteForm(id); 
    });

    // Detalhar formulário (implemente a função detailForm)
    const detailBtn = document.querySelector(".botao-detalhar");
    detailBtn.addEventListener("click", function() {
        const id = remover_id.value;
        detailForm(id);  // Função `detailForm` precisa ser implementada
    });

});
