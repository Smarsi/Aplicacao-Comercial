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
 public $idcontato;
 public $email;
 public $telefone;
 public $celular;

 //Abaixo criaremos as variáveis necessárias de acordo com os campos 
 //da tabela de endereço.
public $idendereco;
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
    $query = "Select * from funcionarios";

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
    $query = "select * from funcionarios where idfuncionario=?";

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
    $querycont = "Insert into endereco set
                    endereco=:en,
                    bairro=:ba,
                    numero=:nm,
                    complemento=:com,
                    cep=:cep";

    //Preparar para executar
    $stmt = $this->conexao->prepare($queryend);

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
    $stmt->bindParam(":en",$this->endereco);
    $stmt->binParam(":ba",$this->bairro);
    $stmt->bindParam(":nm",$this->numero);
    $stmt->bindParam(":com",$this->complemento);
    $stmt->binParam(":cep",$this->cep);

    //Vamos executar a consulta e verificar se o cadatro foi efetuado,
    //para isso ermos usar um if.
    if($stmt->execute()){
        return true;
        //Criar a variável para armazenar o último id gerado.
        $idcont = $this->conexao->last_insert_id();
    }else{
        return false;
    }


    $queryend = "Insert into contato set
                    email=:em,
                    telefone=:tel,
                    celular=:cel";

    /*Por questões de segurança para evitar comandos de 
    SQLinject, iremos remover qualquer caractere especial dos campos
    que possam estar vindos de qualquer página html ou aplicação.
    */

    $this->email = htmlspecialchars(strip_tags($this->email));
    $this->telefone = htmlspecialchars(strip_tags($this->telefone));
    $this->celular = htmlspecialchars(strip_tags($this->celular));

    //Fazendo o ligamento dos parâmetros
    $stmt->bindParam(":em",$this->email);
    $stmt->binParam(":tel",$this->telefone);
    $stmt->bindParam(":cel",$this->celular);

    //Vamos executar a consulta e verificar se o cadastro foi efetuado,
    //para isso usaremos um if.
    if($stmt->is_execute()){
        return true;
        //Criar a variável para armazenar o último id gerado.
        $idend = $this->conexao->last_insert_id();

    }else{
        return false;
    }

    
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
    $this->idcontato = htmlspecialchars(strip_tags($this->idcontato));
    $this->idendereco = htmlspecialchars(strip_tags($this->idendereco));

    //Vamos fazer a ligação dos parâmetros entre oque foi enviado com o que 
    //está no banco.

    $stmt->bindParam(":se",$this->senha);
    $stmt->bindParam(":no",$this->nome);
    $stmt->bindParam(":cpf",$this->cpf);
    $stmt->bindParam(":fo",$this->foto);
    $stmt->bindParam(":ic",$this->$idcontato);
    $stmt->bindParam(":ie",$this->$idend);

    //Iremos fazer if para executar a consulta e verificar se foi cadastrado com sucesso.
    if($stmt->execute()){
        return true;
    } 
        return false;  
}
}
?>