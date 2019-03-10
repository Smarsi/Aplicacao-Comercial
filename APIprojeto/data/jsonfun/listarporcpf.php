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
$funcionarios = new funcionarios($db);

//Abaixo vamos pegar os dados enviados pelo usuário
$data = json_decode(file_get_contents("php://input"));

//Vamos igualar os dados enviados com os campos do banco de dados
$funcionarios->cpf = $data->cpf;

//Vamos executar a função de pesquisa:
$funcionarios->listarPorCpf();

return $funcionarios;


?>