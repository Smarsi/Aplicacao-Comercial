<?php
#Este é o arquivo de configuração da conexão com o nosso banco de dados.


#Vamos cirar uma variável para representar a conexão com o banco de dados.
class Database{
    public $conexao;

    //Agora vamos criar uma função para estabelecer a conexão com o banco.
    //Para fazermos isso precisamos passar alguns dados como : usuário do
    //banco, nome ou ip do servidor de banco de dados, porta de comunicação e
    //a senha do banco.
    function getConnection(){
        //Vamos fazer um try catch para tentarmos estabelecer a conexão.
        //Tente estabelecer uma conexao com os seguintes dados, caso não seja possível
        //exiba a mensagem abaixo e mostre o erro.
        try{
        $conexao = new PDO("mysql:host=localhost;port=3307; dbname=lojinho","root","");
        }
        catch(PDOException $ex){
            echo "Erro ao tentar estabelecer a conexão com o banco".$ex->getMessage();
        }
        return $conexao;
    } 
}
?>