package com.example.luisgfoliveira1.lojinhob.chamadaRetrofit;

import com.example.luisgfoliveira1.lojinhob.servico.ServicoCadFuncionarios;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoClientes;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoFuncionarios;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoPedidos;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoProduto;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoProdutos;


public class Utilitario {
        public Utilitario(){}

        /*
        Abaixo foi criado uma propriedade que
        guarda o caminho do servidor que contém o json.
        Essa propriedade foi setada como final(constante)
        e tipada como static(assim é possível acessá-la
        sem a necessidade de instanciar a classe Utilitario
         */
        public static final  String caminhoURL = "http://diogodiniz.com.br/Lojinho/APIprojeto/data/";

        /*
        Abaixo criamos o método obterUsuario que passa
        a url base para a ChamadaRetrofit e criar a
        url completa para a api
         */
        public static ServicoFuncionarios obterFuncionario(){
            return ChamadaRetrofit.getClient(caminhoURL).create(ServicoFuncionarios.class);
        }

        public static ServicoProduto obterProdutos(){
            return ChamadaRetrofit.getClient(caminhoURL).create(ServicoProduto.class);
        }
        public static ServicoPedidos obterPedidos(){
            return ChamadaRetrofit.getClient(caminhoURL).create(ServicoPedidos.class);
        }
        public static ServicoClientes obterClientes(){
            return  ChamadaRetrofit.getClient(caminhoURL).create(ServicoClientes.class);
        }
        public static ServicoProdutos obeterCadProdutos(){
            return ChamadaRetrofit.getClient(caminhoURL).create(ServicoProdutos.class);
        }
        public static ServicoCadFuncionarios obterCadFuncionarios(){
            return  ChamadaRetrofit.getClient(caminhoURL).create(ServicoCadFuncionarios.class);
        }

    }

