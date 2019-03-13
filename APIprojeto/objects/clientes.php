<?php
//Este é o arquivo que armazena os dados relacionados ao 
// CRUD dos funcionários.

class clientes{
 //abaixo criaremos as variáveis necessárias de acordo com
 //os campos da tabela de clientes no banco de dados.
public $idcliente;
public $nome;
public $cpf;
public $idcontato;
public $idendereco;

 //Abaixo criaremos as variáveis necessárias de acordo com os campos
 //da tabela de contato.
 public $email;
 public $telefone;
 public $celular;

 //Abaixo criaremos as variáveis necessárias de acordo com os campos 
 //da tabela de endereço.
public $endereco;
public $bairro;
public $numero;
public $complemento;
public $cep;
 
 //Abaixo está sendo apresentado o construtor da classe de funcionários
 //Em php usamos o __construct.
public function __construct($db){
    $this->conexao=$db;
}

// Criando o primeiro elemento do CRUD de clientes (Listar).

// ------------------- LISTAR -------------------

public function listar(){
    //Vamos criar a variável que contém o comando de SQL.
    $query = "select * from clientes";

    //Vamos preparar e executar efetivamente a consulta nas 
    //linhas abaixo.
    //Criando a variável stmt(irá guardar o resultado da consulta).
$stmt = $this->conexao->prepare($query);
   
    $stmt->execute();
    
    return $stmt;
}

// ------------------- LISTAR pelo ID-------------------

public function listarCompleto(){
    //Criando variável para guardar o comando se SQL.
    $query = "select f.*,c.*,e.*
    from clientes as f
    inner join contato as c on c.idcontato=f.idcontato
    inner join endereco as e on f.idendereco=e.idendereco
    where idcliente>0";

    //preparar a execução
    $stmt=$this->conexao->prepare($query);

    //Executar a consulta
    $stmt->execute();

    return $stmt;
}

// ------------------- Cadastrar -------------------

public function cadastrar(){

    //Criando a variável que irá guardar o comando de SQL.
    $queryend = "Insert into endereco set
                    endereco=:en,
                    bairro=:ba,
                    numero=:nm,
                    complemento=:com,
                    cep=:cep";

    //Preparar para executar
    $stmtend = $this->conexao->prepare($queryend);

    /*Por questões de segurança para evitar comandos de 
    SQLinject, iremos remover qualquer caractere especial dos campos
    que possam estar vindos de qualquer página html ou aplicação.
    */
    $this->endereco = htmlspecialchars(strip_tags($this->endereco));
    $this->bairro = htmlspecialchars(strip_tags($this->bairro));
    $this->numero = htmlspecialchars(strip_tags($this->numero));
    $this->complemento = htmlspecialchars(strip_tags($this->complemento));
    $this->cep = htmlspecialchars(strip_tags($this->cep));

    //vamos fazer a ligação dos parâmetros enviados com os campos do banco.
    $stmtend->bindParam(":en",$this->endereco);
    $stmtend->bindParam(":ba",$this->bairro);
    $stmtend->bindParam(":nm",$this->numero);
    $stmtend->bindParam(":com",$this->complemento);
    $stmtend->bindParam(":cep",$this->cep);

    $stmtend->execute();
    $idend=$this->conexao->lastInsertId(); //PDO::

    //------------------------------------------------------------------------------------------------


    $querycont = "Insert into contato set
                    email=:em,
                    telefone=:tel,
                    celular=:cel";

    $stmtcont = $this->conexao->prepare($querycont);

    /*Por questões de segurança para evitar comandos de 
    SQLinject, iremos remover qualquer caractere especial dos campos
    que possam estar vindos de qualquer página html ou aplicação.
    */

    $this->email = htmlspecialchars(strip_tags($this->email));
    $this->telefone = htmlspecialchars(strip_tags($this->telefone));
    $this->celular = htmlspecialchars(strip_tags($this->celular));

    //Fazendo o ligamento dos parâmetros
    $stmtcont->bindParam(":em",$this->email);
    $stmtcont->bindParam(":tel",$this->telefone);
    $stmtcont->bindParam(":cel",$this->celular);
    
    $stmtcont->execute();
    $idcont = $this->conexao->lastInsertId();
    
   
    //------------------------------------------------------------------------------------------------
    
    $query = "Insert into clientes set
                    nome=:nm,
                    cpf=:cpf,
                    idcontato=:ic,
                    idendereco=:ie";


    //Vamos preparar para executar
    $stmt = $this->conexao->prepare($query);


    /*Por questões de segurança para evitar comandos de 
    SQLinject, iremos remover qualquer caractere especial dos campos
    que possam estar vindos de qualquer página html ou aplicação.
    */

    $this->nome = htmlspecialchars(strip_tags($this->nome));
    $this->cpf = htmlspecialchars(strip_tags($this->cpf));
    
    /*
    As linhas abaixo foram eliminadas do código pois estavam gerando um erro,
    e impedindo o cadastro do funcionário de acontecer, tanto o contato quanto o
    endereco eram cadastrados normalmente, porém o mesmo não acontecia com o funcionário.
    */
    
     //$this->idcontato = htmlspecialchars(strip_tags($this->$idcont));
     //$this->idendereco = htmlspecialchars(strip_tags($this->$idend));

    //Vamos fazer a ligação dos parâmetros entre o que foi enviado com o que 
    //está no banco.

    $stmt->bindParam(":nm",$this->nome);
    $stmt->bindParam(":cpf",$this->cpf);
    $stmt->bindParam(":ic",$idcont);
    $stmt->bindParam(":ie",$idend);

    echo json_encode($this);

    //Iremos fazer um if para executar a consulta e verificar se foi cadastrado com sucesso.
    if($stmt->execute()){
        return true;
    }
    return false;
}


// ------------------- ATUALIZAR -------------------

public function Atualizar(){
//Vamos criar a variável que armazena o comando de SQL.
$query = "update endereco as e 
inner join clientes as f on f.idendereco = e.idendereco
inner join contato as c on f.idcontato = c.idcontato
                   set 
                   e.endereco=:en,
                   e.bairro=:ba,
                   e.numero=:nu,
                   e.complemento=:com,
                   e.cep=:cep,
                   c.email=:em,
                   c.telefone=:tel,
                   c.celular=:cel,
                   f.nome=:se,
                   f.cpf=:no
                   where f.idcliente=:idcliente";

//Vamos preparar para a execução do comando
$stmt = $this->conexao->prepare($query);

//Vamos usar uma função para retirar 
//todos os caracteres especiais vindos de 
//uma página html.
//Isso fará com que você evite a execução
//de comandos maliciosos no banco de dados
//comandos de sqlinject
$this->endereco = htmlspecialchars(strip_tags($this->endereco));
$this->bairro = htmlspecialchars(strip_tags($this->bairro));
$this->numero = htmlspecialchars(strip_tags($this->numero));
$this->complemento = htmlspecialchars(strip_tags($this->complemento));
$this->cep = htmlspecialchars(strip_tags($this->cep));
$this->email = htmlspecialchars(strip_tags($this->email));
$this->telefone = htmlspecialchars(strip_tags($this->telefone));
$this->celular = htmlspecialchars(strip_tags($this->celular));
$this->nome = htmlspecialchars(strip_tags($this->nome));
$this->cpf = htmlspecialchars(strip_tags($this->cpf));

//Vamos fazer um bindParam(ligção de parâmetros) entre os dados
        //enviados pelo usuario no navegado ou smartphone para o banco
        //de dados
        $stmt->bindParam(":en",$this->endereco);
        $stmt->bindParam(":ba",$this->bairro);
        $stmt->bindParam(":nu",$this->numero);
        $stmt->bindParam(":com",$this->complemento);
        $stmt->bindParam(":cep",$this->cep);
        $stmt->bindParam(":em",$this->email);
        $stmt->bindParam(":tel",$this->telefone);
        $stmt->bindParam(":cel",$this->celular);
        $stmt->bindParam(":se",$this->nome);
        $stmt->bindParam(":no",$this->cpf);
        $stmt->bindParam(":idcliente",$this->idcliente);

        echo json_encode($this);

//Executar a consulta e verificar se cadastrou
if($stmt->execute()){
    return true;
    }
    return false;

}

}
?>