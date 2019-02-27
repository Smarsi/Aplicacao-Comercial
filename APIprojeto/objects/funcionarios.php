<?php
//Este é o arquivo que armazena os dados relacionados ao 
// CRUD dos funcionários.

class funcionarios{
 //abaixo criaremos as variáveis necessárias de acordo com
 //os campos da tabela de funcionários no banco de dados.
 public $idfuncionario;
 public $senha;
 public $nome;
 public $cpf;
 public $foto;
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

// Criando o primeiro elemento do CRUD de funcionários (Listar).

// ------------------- LISTAR -------------------

public function listar(){
    //Vamos criar a variável que contém o comando de SQL.
    $query = "select idfuncionario,nome,cpf,email,endereco
    from funcionarios as f
    inner join contato as c on c.idcontato=f.idcontato
    inner join endereco as e on f.idendereco=e.idendereco
    where idfuncionario>0";

    //Vamos preparar e executar efetivamente a consulta nas 
    //linhas abaixo.
    //Criando a variável stmt(irá guardar o resultado da consulta).
$stmt = $this->conexao->prepare($query);
   
    $stmt->execute();
    
    return $stmt;
}

// ------------------- LISTAR Completo -------------------

public function listarCompleto(){
    //Vamos criar a variável que contém o comando de SQL.
    $query = "select f.*,c.*,e.*
    from funcionarios as f
    inner join contato as c on c.idcontato=f.idcontato
    inner join endereco as e on f.idendereco=e.idendereco
    where idfuncionario>0";

    //Vamos preparar e executar efetivamente a consulta nas 
    //linhas abaixo.
    //Criando a variável stmt(irá guardar o resultado da consulta).
    $stmt = $this->conexao->prepare($query);
   
    $stmt->execute();
    
    return $stmt;
}


// ------------------- LISTAR pelo ID-------------------

public function listarPorId(){
    //Criando variável para guardar o comando se SQL.
    $query = "";

    //preparar a execução
    $stmt=$this->conexao->prepare($query);

    //Fazendo a ligação dos parâmetros.
    $stmt->bindParam(1,$this->idfuncionario);

    //Executar a consulta
    $stmt->execute();

    //Vamos organizar os dados retornados em formato de Json, para isso
    // usaremos uma variável array.
    $linha = $stmt->fetch(PDO::FETCH_ASSOC);

    //Organiar os dados retornados do banco.
    $this->senha = $linha['senha'];
    $this->nome = $linha['nome'];
    $this->cpf = $linha['cpf'];
    $this->foto = $linha['foto'];
    $this->idcontato = $linha['idcontato'];
    $this->idendereco = $linha['idendereco'];
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
    
    $query = "Insert into funcionarios set
                    senha=:se,
                    nome=:no,
                    cpf=:cpf,
                    foto=:fo,
                    idcontato=:ic,
                    idendereco=:ie";


    //Vamos preparar para executar
    $stmt = $this->conexao->prepare($query);


    /*Por questões de segurança para evitar comandos de 
    SQLinject, iremos remover qualquer caractere especial dos campos
    que possam estar vindos de qualquer página html ou aplicação.
    */

    $this->senha = htmlspecialchars(strip_tags($this->senha));
    $this->nome = htmlspecialchars(strip_tags($this->nome));
    $this->cpf = htmlspecialchars(strip_tags($this->cpf));
    $this->foto = htmlspecialchars(strip_tags($this->foto));
    
    /*
    As linhas abaixo foram eliminadas do código pois estavam gerando um erro,
    e impedindo o cadastro do funcionário de acontecer, tanto o contato quanto o
    endereco eram cadastrados normalmente, porém o mesmo não acontecia com o funcionário.
    */
    
    // $this->idcontato = htmlspecialchars(strip_tags($this->$idcont));
    // $this->idendereco = htmlspecialchars(strip_tags($this->$idend));

    //Vamos fazer a ligação dos parâmetros entre o que foi enviado com o que 
    //está no banco.

    $stmt->bindParam(":se",$this->senha);
    $stmt->bindParam(":no",$this->nome);
    $stmt->bindParam(":cpf",$this->cpf);
    $stmt->bindParam(":fo",$this->foto);
    $stmt->bindParam(":ic",$idcont);
    $stmt->bindParam(":ie",$idend);

    //Iremos fazer um if para executar a consulta e verificar se foi cadastrado com sucesso.
    if($stmt->execute()){
        return true;
    }
    return false;
}
}
?>