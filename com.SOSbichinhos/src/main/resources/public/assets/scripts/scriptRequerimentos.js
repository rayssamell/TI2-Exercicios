document.addEventListener("DOMContentLoaded", () => {
    const apiUrl_Formulario = "/formulario";  //retorna todos os formulários

    // Funcao para buscar todos os formularios submetidos
    function getFormularios() {
        fetch(apiUrl_Formulario)
            .then((res) => res.json())
            .then(data => {
                exibirFormularios(data);
            })
            .catch(error => {
                console.error('Erro ao carregar formulários:', error);
                alert("Erro ao carregar os formulários.");
            });
    }

    // Funcao para exibir os formulários no HTML
    function exibirFormularios(formularios) {
        const tela = document.getElementById("tela");
        tela.innerHTML = "";  // Limpa o conteúdo atual

        if (formularios.length === 0) {
            tela.innerHTML = "<p>Nenhum formulário submetido.</p>";
            return;
        }

        formularios.forEach(formulario => {
            const divFormulario = document.createElement("div");
            divFormulario.classList.add("card", "mb-3");
            divFormulario.innerHTML = `
                <div class="card-body">
                    <h5 class="card-title">Nome do Requerente: ${formulario.nome || 'N/A'}</h5>
                    <p class="card-text">Telefone: ${formulario.telefone || 'N/A'}</p>
                    <p class="card-text">E-mail: ${formulario.email || 'N/A'}</p>
                    <p class="card-text">Nome do Animal: ${formulario.nomeAnimal || 'N/A'}</p>
                    <button class="btn btn-danger" data-id="${formulario.id}">Deletar</button>
                </div>
            `;
            tela.appendChild(divFormulario);
        });
    }

    // Carrega os formulários
    getFormularios();

    //evento para deletar formulario
    document.getElementById("tela").addEventListener("click", function(event) {
        if (event.target.classList.contains("btn-danger")) {
            const id = event.target.getAttribute("data-id");
            deleteFormulario(id);
        }
    });

    // Funcao para deletar um formulario
    function deleteFormulario(id) {
        fetch(`${apiUrl_Formulario}/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                alert("Formulário deletado com sucesso.");
                getFormularios();  // Recarrega a lista de formulários após a exclusão
            } else {
                alert("Erro ao deletar o formulário.");
            }
        })
        .catch(error => {
            console.error('Erro ao deletar formulário:', error);
            alert("Erro ao deletar o formulário.");
        });
    }
});
