<?php
//Este é o arquivo que armazena todos os dados referentes à listagem por cpf dos funcionarios.

#Vamos definir os cabeçalhos para receber as informações
#no formato de json de diversas origens
#Access-Control-Allow-Orgin:*; Permite a requisição desta api por diversos
#protocolos(http; https; file)

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization,X-Requested-With");

//vamos importar a conexão com o banco de dados
include_once "../../conexao/database.php";

//vamos importar a classe Funcionarios
include_once "../../objects/funcionarios.php";

//instancia da classe Database
$database = new Database();
$db = $database->getConnection();

//Instância da classe funcionarios 
$funcionarios = new Funcionarios($db);

//Abaixo vamos pegar os dados enviados pelo usuário
$data = json_decode(file_get_contents("php://input"));

//Vamos igualar os dados enviados com os campos do banco de dados
$funcionarios->cpf = $data->cpf;

//Vamos executar a função de pesquisa:
$stat = $funcionarios->listarPorCpf();

//Vamos criar uma variável para contar as linhas do retorno
$num = $stat->rowCount();

/*
Fazemos a contagem da quantidade de linhas para podermos 
verificar se é maior que 0(zero), então nós exibiremos na
tela em formato de json. Caso contrário nós iremos exibir 
uma mensagem dizendo que nn foi encontrado nada(nao localizado).
*/

if($num > 0){
    //Vamos criar um array para organizar os dados retornados para 
    //a apresentação para o usuário.
    $funcionarios_arr = array();
    $funcionarios_arr["dados"]=array();
    $linha=$stat->fetch(PDO::FETCH_ASSOC);  
    $funcionarios_arr["dados"]=array("banana","maca");
    while($linha=$stat->fetch(PDO::FETCH_ASSOC)){
        //Extrair o conteúdo que está retornando da 
        // linha e montar o array com todos os dados.
        extract($linha);
        $array_item = array(
            "idfuncionario"=>$idfuncionario,
            "senha"=>$senha,
            "nome"=>$nome,
            "cpf"=>$cpf,
            "foto"=>$foto,
            "idcontato"=>$idcontato,
            "idendereco"=>$idendereco,
            "email"=>$email,
            "telefone"=>$telefone,
            "celular"=>$celular,
            "endereco"=>$endereco,
            "bairro"=>$bairro,
            "numero"=>$numero,
            "complemento"=>$complemento,
            "cep"=>$cep
        );
        
        //Vamos criar uma lista de array chamada dados e colocar 
        //dentro todos os dados retornado para preparar para saída.
        array_push($funcionarios_arr, $array_item);
    } // Fim do laço while.
    //Responder com o código de status positivo (200)
    http_response_code(200); //Sucesso.
    //Exibir os dados em formato de json.
    echo json_encode($funcionarios_arr);
}
else{
//Responder que não foi encontrado.
//Código 404 - Not Found.
http_response_code(404);
echo json_encode(array("mensagem"=>"Não foi possível localizar nenhum funcionario!"));
}


?>