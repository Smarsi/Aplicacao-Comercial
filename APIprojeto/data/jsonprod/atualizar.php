<?php
//Este é o arquivo que armazena os dados referentes à atualização de produtos.

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
include_once "../../objects/produtos.php";

//instancia da classe Database
$database = new Database();
$db = $database->getConnection();

//Criar instância da classe de funcionários.
$produto = new Produtos($db);

//Vamos pegar os dados enviados pelo usuário.
$data = json_decode(file_get_contents("php://input"));

//Vamos igualar os dados enviados com os que vamos cadastrar.
$produto->idproduto = $data->idproduto;
$produto->quantidade = $data->quantidade;
$produto->idfornecedor = $data->idfornecedor;
$produto->nomeproduto = $data->nomeproduto;
$produto->descricao = $data->descricao;
$produto->preco = $data->preco;
$produto->categoria = $data->categoria;
$produto->img1 = $data->img1;
$produto->img2 = $data->img2;
$produto->img3 = $data->img3;
$produto->img4 = $data->img4;

//Abaixo iremos tentar executar a atualização no banco de dados.
if($produto->Atualizar()){
    //Eremos retornar o código e uma mensagem de sucesso.
    header('HTTP/1.0 200');
    echo json_encode(array("Mensagem"=>"Atualização realizada com sucesso!"));
}else{
    //Caso nao seja possível iremos retornar o código e uma mansagem de erro.
    header('HTTP/1.0 503');
    echo json_encode(array("Mensagem"=>"Não foi possível realizar a atualização"));
}

?>