package com.example.luisgfoliveira1.lojinhob.servico;

import com.example.luisgfoliveira1.lojinhob.Produtos;
import com.example.luisgfoliveira1.lojinhob.models.ListaProduto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServicoProduto {

    @GET("jsonpr/listar.php")
    Call<List<Produtos>> getProdutos();

    @GET("jsonpr/listar.php")
    Call<ListaProduto> getTodosProdutos();

    @POST("jsonpr/cadastrar.php")
    Call<Produtos> addProdutos(@Body Produtos produtos);
}
