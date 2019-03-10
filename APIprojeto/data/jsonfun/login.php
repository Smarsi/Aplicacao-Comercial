<?php
//Este é o arquivo que armazena os dados referentes ao login de funcionários.

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

//Vamos verificar abaixo se os dados necessários foram preenchidos.
if(
    !empty($data->cpf)&&
    !empty($data->senha)
){
    //Se os dados estão preenchidos iremos passar para
    //que a API possa cadastrar em banco 

    $funcionarios->cpf = $data->cpf;
    $funcionarios->senha = $data->senha;

    // vamos tentar executar o login 
    $funcionarios->Logar();

     /*
        Após ter feito o processo de login teremos como retorno o objeto
        funcionarios inteiro. Assim podemos verificar se o idfuncionario é maior 
        que 0(zero).
        Se for maior que zero deverá retornar o status code 201-> criado,
        ou seja, logado. E támbém a mensagem "Logado"
    */
    if($funcionarios->idfuncionario > 0){
        //Mensagem de logado e código de sucesso(201).
        header('HTTP/1.0 201');
        echo json_encode($funcionarios);
    }else{
        //Código e mensagem de erro.
        header('HTTP/1.0 503'); //Erro interno do servidor.
        echo json_encode(array("mensagem"=>"Usuário ou senha incorretos!"));
    }

}else{
    //Mensagem de que os campos estao vazios e codigo de erro 400(bad request).
    header('HTTP/1.0 400');
    echo json_encode(array("Mensagem"=>"Preencha todos os campos para efetuar o login!"));
}
?>