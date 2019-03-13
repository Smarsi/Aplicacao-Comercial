<?php
//Este é o arquivo que armazena os dados relacionados à atualização de fornecedores.
#Vamos definir os cabeçalhos para receber as informações
#no formato de json de diversas origens

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: PUT");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization,X-Requested-With");

//Vamos importar a classe database.
include_once "../../conexao/database.php";

//Vamos importar a classe de funcionarios.
include_once "../../objects/fornecedor.php";

//instancia da classe Database
$database = new Database();
$db = $database->getConnection();

//Criar instância da classe de funcionários.
$fornecedor = new Fornecedor($db);

//Vamos pegar os dados enviados pelo usuário.
$data = json_decode(file_get_contents("php://input"));

//echo json_encode($data);

//Vamos igualar os dados enviados com os que vamos cadastrar.
$fornecedor->idfornecedor = $data->idfornecedor;
$fornecedor->endereco = $data->endereco;
$fornecedor->bairro = $data->bairro;
$fornecedor->numero = $data->numero;
$fornecedor->complemento = $data->complemento;
$fornecedor->cep = $data->cep;
$fornecedor->email = $data->email;
$fornecedor->telefone = $data->telefone;
$fornecedor->celular = $data->celular;
$fornecedor->razaosocial = $data->razaosocial;
$fornecedor->cnpj = $data->cnpj;


//Abaixo iremos tentar executar a atualização no banco de dados.
if($fornecedor->Atualizar()){
    //Eremos retornar o código e uma mensagem de sucesso.
    header('HTTP/1.0 200');
    echo json_encode(array("Mensagem"=>"Atualização realizada com sucesso!"));
}else{
    //Caso nao seja possível iremos retornar o código e uma mansagem de erro.
    header('HTTP/1.0 503');
    echo json_encode(array("Mensagem"=>"Não foi possível realizar a atualização"));
}

?>