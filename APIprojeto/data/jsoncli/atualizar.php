<?php
//Este é o arquivo que armazena os dados relacionados à Atualização de clientes.

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
include_once "../../objects/clientes.php";

//instancia da classe Database
$database = new Database();
$db = $database->getConnection();

//Criar instância da classe de funcionários.
$cliente = new Clientes($db);

//Vamos pegar os dados enviados pelo usuário.
$data = json_decode(file_get_contents("php://input"));

//Vamos igualar os dados enviados com os que vamos cadastrar.
$cliente->idcliente = $data->idcliente;
$cliente->endereco = $data->endereco;
$cliente->bairro = $data->bairro;
$cliente->numero = $data->numero;
$cliente->complemento = $data->complemento;
$cliente->cep = $data->cep;
$cliente->email = $data->email;
$cliente->telefone = $data->telefone;
$cliente->celular = $data->celular;
$cliente->nome = $data->nome;
$cliente->cpf = $data->cpf;

//Abaixo iremos tentar executar a atualização no banco de dados.
if($cliente->Atualizar()){
    //Eremos retornar o código e uma mensagem de sucesso.
    header('HTTP/1.0 200');
    echo json_encode(array("Mensagem"=>"Atualização realizada com sucesso!"));
}else{
    //Caso nao seja possível iremos retornar o código e uma mansagem de erro.
    header('HTTP/1.0 503');
    echo json_encode(array("Mensagem"=>"Não foi possível realizar a atualização"));
}

?>