<?php
//Este é o arquivo que armazena todos os dados referentes ao apagar de produtos.

#Vamos definir os cabeçalhos para receber as informações
#no formato de json de diversas origens

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: DELETE");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type,Access-Control-Allow-Headers, Authorization,X-Requested-With");


// Vamos importar a conexao com o banco de dados (nosso arquivo database).
include_once "../../conexao/database.php";

// Vamos importar a classe produtos
include_once "../../objects/produtos.php";

//Instância da classe database
$database = new Database();
$db = $database->getConnection();

//Instância da classe de produtos
$produto = new Produtos($db);

//Vamos pegar os dados postados
//Para pegarmos os dados que o usuário envia
//usamos o file_get_contents no PHP.
$data = json_decode(file_get_contents("php://input"));

$produto->idproduto = $data->idproduto;
$produto->idestoque = $data->idestoque;

//Vamos tentar executar o cadastro.
if($produto->Apagar()){
    //iremos retornar ao internalta
        //a mensagem de cadastro realizado
        //e o codigo de status 201 de criado
        //header('HTTP/1.0 201');
        header('HTTP/1.0 200');
        echo json_encode(array("mensagem"=>"Produto Apagado com sucesso!"));
}else{
     //header('HTTP/1.0 503');//erro interno do servidor
     header('HTTP/1.0 503');
     echo json_encode(array("mensagem"=>"Não foi possível apagar o produto!"));
}
?>