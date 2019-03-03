<?php
//Esta é a tela que armazena as informações a respeito dos Detalhes dos pedidos.
//Aqui encontram-se todas as funções principais para o CRUD
// Produtos -> Contém CRUD completo (Create, Retriever, Update, Delete).

//Vamos criar as variáveis que serão necessárias no nosso código.
class Detalhes{
    public $idpedido;
    public $idproduto;
    public $quantidade;

   // $detalhes = array(
   //    "idpedido"=>$idped,
   //    "idproduto"=>$idproduto,
   //    "quantidade"=>$quantidade
   // );




 // Abaixo é apresentado o construtor da classe Detalhes.
 // Ele é reponsável por iniciar a classe. Em php usamos __construct
 // para declarar um construtor.
 public function __construct($db){
    $this->conexao = $db;
 }

 // Abaixo iremos criar o primeiros Elemento ligado ao CRUD.

 // ------------------- Cadastro -------------------

 public function cadastrar(){
     
    //Criando variável para guardar o comando SQL
    $queryped = "insert into pedidos set
                    idfuncionario=:if,
                    idcliente=:ic";

    // Vamos preparar a execução
    $stmt = $this->conexao->prepare($queryped);

    // Vamos tirar qualquer caractere especial.
    $this->idfuncionario = htmlspecialchars(strip_tags($this->idfuncionario));
    $this->idcliente = htmlspecialchars(strip_tags($this->idcliente));
  
    // Vamos fazer a ligação dos parâmetros dos dados enviados com o banco.
    $stmt->bindParam(":if",$this->idfuncionario);
    $stmt->bindParam(":ic",$this->idcliente);


    //Vamos verificar se o pedido foi cadastrado com sucesso 

    $stmt->execute();
    //Vamos armazenar o IdProduto gerado em uma variável.
    $idped = $this->conexao->lastInsertId(); 

    //-------------------------------------------------------------------------

    $querydet = "insert into detalhespedido (idpedido,idproduto,quantidade) values";
    $valores = "";
    $dados ="";

   // for($i=0, $i < )

   // }


}
}
?>