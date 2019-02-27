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

// Vamos importar a classe funcionarios
include_once "../../objects/funcionarios.php";

//Instância da classe database
$database = new Database();
$db = $database->getConnection();

//Instância da classe de funcionários
$funcionario = new Funcionarios($db);

//Vamos pegar os dados postados
//Para pegarmos os dados que o usuário envia
//usamos o file_get_contents no PHP.
$data = json_decode(file_get_contents("php://input"));

//Abaixo vamos efetuar uma verificação para saber se todos os campos foram
//preenchidos.
  if(
    !empty($data->senha)&&
    !empty($data->nome)&&
    !empty($data->cpf)&&
    !empty($data->foto)&&
    !empty($data->email)&&
    !empty($data->telefone)&&
    !empty($data->celular)&&
    !empty($data->endereco)&&
    !empty($data->bairro)&&
    !empty($data->numero)&&
    !empty($data->complemento)&&
    !empty($data->cep)
    ){
    //Se os campos NÃO estiverem vazios então iremos passar para 
    //efetuar o cadastro no banco.
    $funcionario->senha = $data->senha;
    $funcionario->nome = $data->nome;
    $funcionario->cpf = $data->cpf;
    $funcionario->foto = $data->foto;
    $funcionario->email = $data->email;
    $funcionario->telefone = $data->telefone;
    $funcionario->celular = $data->celular;
    $funcionario->endereco = $data->endereco;
    $funcionario->bairro = $data->bairro;
    $funcionario->numero = $data->numero;
    $funcionario->complemento = $data->complemento;
    $funcionario->cep = $data->cep;
    
    $rs = $funcionario->cadastrar();
    //Depois de passarmos os dados vamos tentar executar o cadastro no banco.
    if($rs=="Cadastrou"){
        //Iremos retornar ao usuário a mensagem de cadastro realizado
        // com sucesso e o código de status 201 (criado, success).
        http_response_code(201);
        echo json_encode(array("mensagem"=>"Cadastro realizado com sucesso!"));
    }else{
        //Se não for possível realizar o cadastro:
        http_response_code(503); // 503 é o erro interno do servidor.
        echo json_encode(array("mensagem"=>"Não foi possível realizar o cadastro!"));
        //echo "Não foi possível realizar o cadastro!".$db->errorInfo();
    }
}else{
    // Mensagem para o usuário caso não tenha preenchido todos os campos:
    http_response_code(400); //Bad request.
    echo json_encode(array("mensagem"=>"Preencha todos os campos para efetuar o cadastro!")); 
}


?>