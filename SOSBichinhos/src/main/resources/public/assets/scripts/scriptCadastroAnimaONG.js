document.addEventListener("DOMContentLoaded", () => {

    const apiUrl_CRUD_Animal = "/animal";

    //--------------------------------FUNÇÕES saveData-----------------------------------//

    /**
     * Manda para o JSON qualquer objeto
     * @param {object} dado objeto a ser salvado no JSON server
     */
    function saveDataAnimal(dado) {
        fetch(apiUrl_CRUD_Animal, { // URL base do servidor
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dado)
        }).then(response => response.json())
          .then(dado => {
            console.log(dado);
            alert("Animal adicionado com sucesso");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao adicionar o animal");
          });
    }

    /**
     * Apaga do server os objetos
     * @param {string} id ID do objeto a ser excluído
     */
    function deleteData(id) {
        fetch(`${apiUrl_CRUD_Animal}/delete/` + id, { // URL base do servidor
            method: 'POST',
        }).then(response => response.json())
          .then(id => {
            console.log(id);
            alert("Animal " + id + " removido com sucesso");
          })
          .catch(error => {
              console.error('Erro:', error);
              alert("Erro ao remover o animal ou animal inexistente");
          });
    }

    function getAnimal(FunctionCallBack, id) {
        fetch(`${apiUrl_CRUD_Animal}/${id}`)
          .then((res) => res.json())
          .then(data => {
            FunctionCallBack(data);
            return data;
          })
          .catch(error => {
            alert("Animal não encontrado ou inexistente");
            console.error('Erro:', error);
          });
      }

      function getTodosAnimais(FunctionCallBack) {
        fetch(`${apiUrl_CRUD_Animal}`)
          .then((res) => res.json())
          .then(data => {
            FunctionCallBack(data);
            return data;
          })
          .catch(error => {
            alert("Animal não encontrado ou inexistente");
            console.error('Erro:', error);
          });
      }

    //-------------------------------- END - FUNÇÕES SaveData -----------------------------------//

    function GraverAnimal() {

        //const inputImagemAnimal = document.querySelector("#imagem-input-animal").value; //Concertar input imagem
        const inputNomeAnimal = document.querySelector("#nome-input-animal").value;
        const inputSexoAnimal= document.querySelector('#sexo-input-animal').value;
        const inputIdadeAnimal = document.querySelector("#idade-input-animal").value;
        const inputRacaAnimal = document.querySelector("#raca-input-animal").value;
        const inputVacinasAnimal = document.querySelector("#vacinas-input-animal").value;
        const inputCastradoAnimal = document.querySelector("#castrado-input-animal").value;
        const inputHistoriaAnimal = document.querySelector("#historia-input-animal").value;
        const inputPorteAnimal = document.querySelector('#porte-input-animal').value;
        const inputEspecieAnimal = document.querySelector('#especie-input-animal').value;
        
        //const tagsSelecionadas = Array.from(document.querySelectorAll('input[name="tags"]:checked')).map(checkbox => checkbox.value);

        // let tags = {
          
        // }

        let castradoBoolean;

        if (inputCastradoAnimal == "true"){
          castradoBoolean = true;
        }else{
          castradoBoolean = false;
        }
        
        let Animal = {
            imagem: "inputImagemAnimal", //Concertar input imagem
            nome: inputNomeAnimal,
            sexo: inputSexoAnimal,
            idade: inputIdadeAnimal,
            raca: inputRacaAnimal,
            vacinas: inputVacinasAnimal,
            castrado: castradoBoolean,
            historia: inputHistoriaAnimal,
            porte: inputPorteAnimal,
            especie: inputEspecieAnimal
        };
        console.log(Animal);
        
        saveDataAnimal(Animal);
    }

    //--------------------------------EventListener - Botões-----------------------------------//

    const CadastrarAnimalBtn = document.querySelector(".botao-cadastra-animal");
    const RemoveAnimalBtn = document.querySelector(".botao-remove-animal");
    const inputIdRemoveAnimal = document.querySelector("#id-remove-animal");

    CadastrarAnimalBtn.addEventListener("click", GraverAnimal);

    RemoveAnimalBtn.addEventListener("click", function() {
        const idRemoveAnimal = inputIdRemoveAnimal.value;
        deleteData(idRemoveAnimal);
    });


//----------------Mostra animal solicitado em JSON -----------------//

    const DetalharAnimalJSONBtn = document.querySelector(".botao-detalha-animal-JSON"); 

    DetalharAnimalJSONBtn.addEventListener("click", function() {

        const inputIdAnimal= document.querySelector("#id-detalha-animal").value;

        getAnimal(MostraAnimalVisualizavel_EmJSON, inputIdAnimal)
        
    });

    function MostraAnimalVisualizavel_EmJSON(animal) {

        window.location.href = "http://localhost:8081/animal/" + animal.id_animal;
    }



    //----------------Mostra animal solicitado no html -----------------//


    const DetalharAnimalBtn = document.querySelector(".botao-detalha-animal");

    

    DetalharAnimalBtn.addEventListener("click", function(){

        const inputIdAnimal= document.querySelector("#id-detalha-animal").value;

        getAnimal(MostraAnimalVisualizavel, inputIdAnimal)
    });

    function MostraAnimalVisualizavel(animal){

        const divAnimalVisualizavel = document.querySelector(".pagina-container-Animal-Visualizavel");

        divAnimalVisualizavel.style.display = "block"

        divAnimalVisualizavel.innerHTML = `<h1 class="titulo">Animal Visualizavel</h1>

                <section class="flexPerfilJP">

          <div>
            <img id="avatarPrincipalJP" src="${animal.imagem}" alt="animal">
          </div>
          <div class="informacoesJP">
            <div id="nomeAutorJP">
              <p>Nome: ${animal.nome}</p>
            </div>
            <p><strong>id do animal:</strong> ${animal.id_animal}</p>
            <p><strong>Idade:</strong>${animal.idade}</p>
            <p><strong>Sexo:</strong> ${animal.sexo}</p>
            <p><strong>Raça:</strong> ${animal.raca}</p>
            <p><strong>Vacinação:</strong> ${animal.vacinas}</p>
            <p><strong>Castrado:</strong> ${animal.castrado}</p>
            <p><strong>Porte:</strong> ${animal.porte}</p>
            <p><strong>Espécie:</strong> ${animal.especie}</p>
            <p><strong>Tags:</strong> ${animal.tags}</p>
            <div id="descricaoJP">
              <h4><strong>História:</strong></h4>
              <p>
                ${animal.historia}
              </p>
            </div>
          </div>
        </section>`;

    }



    //----------------Mostra TODOS os animal solicitado no html -----------------//


    const botaolistaAnimaisBtn = document.querySelector(".botao-lista-animais");

    

    botaolistaAnimaisBtn.addEventListener("click", function(){

        getTodosAnimais(ListaTodosAnimais)
    });

    function ListaTodosAnimais(animais){

        const divAnimalVisualizavel = document.querySelector(".pagina-container-Animal-Visualizavel");

        divAnimalVisualizavel.style.display = "block"

         divAnimalVisualizavel.innerHTML = `<h1 class="titulo">Animal Visualizavel</h1>`

         animais.forEach(animal => {
             
             divAnimalVisualizavel.innerHTML += `
     
                     <section class="flexPerfilJP">
     
               <div>
                 <img id="avatarPrincipalJP" src="${animal.imagem}" alt="animal">
               </div>
               <div class="informacoesJP">
                 <div id="nomeAutorJP">
                   <p>Nome: ${animal.nome}</p>
                 </div>
                 <p><strong>id do animal:</strong> ${animal.id_animal}</p>
                 <p><strong>Idade:</strong>${animal.idade}</p>
                 <p><strong>Sexo:</strong> ${animal.sexo}</p>
                 <p><strong>Raça:</strong> ${animal.raca}</p>
                 <p><strong>Vacinação:</strong> ${animal.vacinas}</p>
                 <p><strong>Castrado:</strong> ${animal.castrado}</p>
                 <p><strong>Porte:</strong> ${animal.porte}</p>
                 <p><strong>Espécie:</strong> ${animal.especie}</p>
                 <p><strong>Tags:</strong> ${animal.tags}</p>
                 <div id="descricaoJP">
                   <h4><strong>História:</strong></h4>
                   <p>
                     ${animal.historia}
                   </p>
                 </div>
               </div>
             </section>`;
         });

    }
    

        //--------------------------------End - EventListener Botões-----------------------------------//




}); // <-- Aqui está a chave de fechamento do primeiro bloco DOMContentLoaded

