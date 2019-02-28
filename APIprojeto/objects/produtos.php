<?php
//Esta é a tela que armazena as informações a respeito dos produtos.
//Aqui encontram-se todas as funções principais para o CRUD
// Produtos -> Contém CRUD completo (Create, Retriever, Update, Delete).

//Vamos criar as variáveis que serão necessárias no nosso código.
class Produtos{
    public $idproduto;
    public $nomeproduto;
    public $descricao;
    public $preco;
    public $categoria;
    public $img1;
    public $img2;
    public $img3;
    public $img4;
    public $idfornecedor;

 // Abaixo é apresentado o construtor da classe produtos.
 // Ele é reponsável por iniciar a classe. Em php usamos __construct
 // para declarar um construtor.
 public function __construct($db){
    $this->conexao = $db;
 }

 // Abaixo iremos criar o primeiros Elemento ligado ao CRUD.

 // ------------------- LISTAR -------------------
 
 public function listar(){
     // Vamos criar uma variável para armazenar o texto de consulta no banco.
     $query = "Select * from produtos";

    // Abaixo vamos criar uma variável chamada stat que irá guardar o 
    // resultado da execução da consulta.
    // Vamos preparar a execução da consulta.

    $stat = $this->conexao->prepare($query);

    // Vamos executar a consulta efetivamente.
    $stat->execute();

    // Vamos retornar os dados que foram selecionados.
    return $stat;
 }

 // ------------------- Cadastro -------------------

 public function cadastrar(){
     // vamos criar uma variável para armazenar os dados da consulta 
     $query = "insert into produtos set 
                nomeproduto=:n,
                descricao=:d,
                preco=:p,
                categoria=:c,
                img1=:i1,
                img2=:i2,
                img3=:i3,
                img4=:i4,
                idfornecedor=:if";

            
    // Vamos preparar a execução
    $stat = $this->conexao->prepare($query);

    // Vamos tirar qualquer caractere especial.
    $this->nomeproduto = htmlspecialchars(strip_tags($this->nomeproduto));
    $this->descricao = htmlspecialchars(strip_tags($this->descricao));
    $this->preco = htmlspecialchars(strip_tags($this->preco));
    $this->categoria = htmlspecialchars(strip_tags($this->categoria));
    $this->img1 = htmlspecialchars(strip_tags($this->img1));
    $this->img2 = htmlspecialchars(strip_tags($this->img2));
    $this->img3 = htmlspecialchars(strip_tags($this->img3));
    $this->img4 = htmlspecialchars(strip_tags($this->img4));
    $this->idfornecedor = htmlspecialchars(strip_tags($this->idfornecedor));

    // Vamos fazer a ligação dos parâmetros dos dados enviados com o banco.
    $stat->bindParam(":n",$this->nomeproduto);
    $stat->bindParam(":d",$this->descricao);
    $stat->bindParam(":p",$this->preco);
    $stat->bindParam(":c",$this->categoria);
    $stat->bindParam(":i1",$this->img1);
    $stat->bindParam(":i2",$this->img2);
    $stat->bindParam(":i3",$this->img3);
    $stat->bindParam(":i4",$this->img4);
    $stat->bindParam(":if",$this->idfornecedor);

    //Vamos verificar se o produto foi cadastrado com sucesso 

    $stat->execute();
    //Vamos armazenar o IdProduto gerado em uma variável.
    $idprod = $this->conexao->lastInsertId(); 

    //-------------------------------------------------------------------------------------------------------------

    // Agora iremos usar o idProduto gerado e armazenado na variável acima para efetuarmos o cadastro
    // de um estoque.

    $queryest = "Insert into estoque set
                        idproduto=:ip,
                        quantidade=:qt,
                        idfornecedor=:f";

    // Vamos preparar a execução
    $stmtest = $this->conexao->prepare($queryest);

     // Vamos tirar qualquer caractere especial.
     $this->quantidade = htmlspecialchars(strip_tags($this->quantidade));

     // Vamos fazer a ligação dos parâmetros dos dados enviados com o banco.
     $stmtest->bindParam(":ip",$idprod);
     $stmtest->bindParam(":qt",$this->quantidade);
     $stmtest->bindParam(":f",$this->idfornecedor);

     //Vamos efetuar efetivamente a consulta
    if($stmtest->execute()){
        return true;
    } 
    return false;

}
}
?>