<?php
//Vamos definir os cabeçalhos para podermos receber 
//as informações no formato de json de diversas origens.
//(Para que vários tipos de protocolos ou aplicações se comuniquem).

header("Access-Coontrol-Allow-Origin:*");
header("Content-Type: application/json; charset=UTF-8;");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");

// Abaixo o "Authorization" serve para bloquear algumas páginas da aplicação, isso depende de login
// e nível de acesso. Caso você queira limitar o acesso a algumas funcionalidades 
// usuario : Authorization: digite aqui o que será autorizado.
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

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

// Abaixo iremos efetuar uma verificação para saber se 
// o usuário do sistema preencheu todos os dados necessários.
// Caso ele tenha preenchido todos os campos vamos cadastrar,
// caso contrário iremos exibir uma mensagem de erro.

if(
    !empty($data->nomeproduto)&&
    !empty($data->descricao)&&
    !empty($data->preco)&&
    !empty($data->categoria)&&
    !empty($data->img1)&&
    !empty($data->img2)&&
    !empty($data->img3)&&
    !empty($data->img4)&&
    !empty($data->idfornecedor)
    
){
    //Se os dados estão preenchidos entao iremos passar para API
    //efeturar o cadastro no banco.
    $produto->nomeproduto = $data->nomeproduto;
    $produto->descricao = $data->descricao;
    $produto->preco = $data->preco;
    $produto->categoria = $data->categoria;
    $produto->img1 = $data->img1;
    $produto->img2 = $data->img2;
    $produto->img3 = $data->img3;
    $produto->img4 = $data->img4;
    $produto->idfornecedor = $data->idfornecedor;
    //Depois de passarmos os dados vamos tentar executar o cadastro
    if($produto->cadastrar()){
        //Iremos retornar ao usuário a mensagem de cadastro realizado
        // com sucesso e o código de status 201 (criado, success).
        http_response_code(201);
        echo json_encode(array("mensagem"=>"Cadastro realizado com sucesso!"));
    }
    else{
        //Se não for possível realizar o cadastro:
        http_response_code(400); // Código 400 é Bad request.
        echo json_encode(array("mansagem"=>"Não foi possível realizar o cadastro!"));
    }
} 
else{
    // Mensagem para o usuário caso não tenha preenchido todos os campos:
    http_response_code(400); //Bad request.
    echo json_encode(array("mensagem"=>"Preencha todos os campos para efetuar o cadastro!"));
}

?>