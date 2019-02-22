<?php
// Vamos usar o formato de json para selecionar os dados 
// vindos do banco de dados.
// A requisição a essa página pode ser feita por meio
// de vários protocolos diferentes, como:
    //-http; https; file; ftp
// Precisamos permitir o acesso. Para isso vamos 
// usar cabeçalhos de permissão e controle.

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; Charsert=UTF-8");

// Vamos importar as informações de conexão com o banco de dados
// por meio do comando include_once.
include_once "../../conexao/database.php";

// Agora vamos importar a classe de produtos que contém 
//todas as funções relacionadas ao CRUD.
include_once "../../objects/produtos.php";

//Instância do arquivo database que contém os dados de
// conexao com o banco de dados.
$database = new Database();
$db = $database->getConnection();

// Iniciando o objeto da classe produtos.
$produto = new Produtos($db);

// Chamando a função de listar presente dentro 
//da classe produtos
$stat = $produto->listar();

//Contar a quantidade de linhas que retornam da consulta
$num = $stat->rowCount();

/*
Fazemos a contagem da quantidade de linhas para podermos 
verificar se é maior que 0(zero), então nós exibiremos na
tela em formato de json. Caso contrário nós iremos exibir 
uma mensagem dizendo que nn foi encontrado nada(nao localizado).
*/

if($num > 0){
        //Organizar os dados em formato de array.
        $produto_arr = array();
        $produto_arr["dados"]=array();
        while($linha=$stat->fetch(PDO::FETCH_ASSOC)){
            //Extrair o conteúdo que está retornando da 
            // linha e montar o array com todos os dados.
            extract($linha);
            $array_item = array(
                "nomeproduto"=>$nomeproduto,
                "descricao"=>$descricao,
                "preco"=>$preco,
                "categoria"=>$categoria,
                "img1"=>$img1,
                "img2"=>$img2,
                "img3"=>$img3,
                "img4"=>$img4,
                "idfornecedor"=>$idfornecedor
            );
            //Vamos criar uma lista de array chamada dados e colocar 
            //dentro todos os dados retornado para preparar para saída.
            array_push($produto_arr["dados"], $array_item);
        } // Fim do laço while.
        //Responder com o código de status positivo (200)
        http_response_code(200); //Sucesso.
        //Exibir os dados em formato de json.
        echo json_encode($produto_arr);
}
else{
    //Responder que não foi encontrado.
    //Código 404 - Not Found.
    http_response_code(404);
    echo json_encode(array("mensagem"=>"Não foi possível localizar nenhum produto!"));
}
?>