<?php
//Esta é a tela que armazena as informações a respeito dos Detalhes dos pedidos.
//Aqui encontram-se todas as funções principais para o CRUD
// Produtos -> Contém CRUD completo (Create, Retriever, Update, Delete).

//Vamos criar as variáveis que serão necessárias no nosso código.
class Detalhes{
    public $idpedido;
    public $idproduto;
    public $quantidade;

 // Abaixo é apresentado o construtor da classe Detalhes.
 // Ele é reponsável por iniciar a classe. Em php usamos __construct
 // para declarar um construtor.
 public function __construct($db){
    $this->conexao = $db;
 }

 // Abaixo iremos criar o primeiros Elemento ligado ao CRUD.

 // ------------------- Cadastro -------------------

 public function cadastrar($dados){
   //Acima estamos criando a função que irá realizar cadastro, estamos
   //esperando receber dados dentro dessa função, esses dados ficarão
   //armazenados dentro da variável $dados, que é do tipo array.
   //O arquivo de json está enviando todos os dados em formato bruto para
   //dentro desse array, assim teremos que entrar nos campos do array para
   //pegarmos os dados necessários para efetuar cada uma das ações da função
   //de cadastro.
 
   //Abaixo criamos variáveis para armazenar o id do cliente e do
   //funcionario para fazermos o cadastro do pedido no banco de dados.
   //Estamos entrando nos campos do array para podermos pegar os dados 
   //necessários e armazena-los em nossas variáveis.
   $idcli = $dados[0]->idfuncionario;
   $idfun = $dados[0]->idcliente;

    //echo json_encode($idcli); (Teste)
    //echo json_encode($idfun); (Teste)
    //Criando variável para guardar o comando SQL
    $queryped = "insert into pedidos(idfuncionario,idcliente) values ($idcli,$idfun)";

    // Vamos preparar a execução
    $stmt = $this->conexao->prepare($queryped);

    //Vamos verificar se o pedido foi cadastrado com sucesso 

     $stmt->execute();
    //Vamos armazenar o IdProduto gerado em uma variável.
    $idped = $this->conexao->lastInsertId(); 

   //-------------------------------------------------------------------------

    $idpro=0;
    $qtd = 0;
 
     for($i = 0; $i < sizeof($dados); $i++){
      $idpro = $dados[$i]->idproduto;
      $qtd = $dados[$i]->quantidade;
      $querydet = "insert into detalhespedido (idpedido,idproduto,quantidade) values ($idped,$idpro,$qtd)";
        
      $stmt = $this->conexao->prepare($querydet);

      $stmt->execute();
      //echo json_encode($dados[$i]->idproduto."-".$dados[$i]->quantidade); (Teste)
      //echo json_encode($dados[$i]->idfuncionario."-".$dados[$i]->idcliente); (Teste)
     
     } 

}
}
?>