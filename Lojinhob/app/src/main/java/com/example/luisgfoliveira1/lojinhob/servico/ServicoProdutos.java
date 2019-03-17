package com.example.luisgfoliveira1.lojinhob.servico;

import com.example.luisgfoliveira1.lojinhob.Produtos;
import com.example.luisgfoliveira1.lojinhob.models.CadProdutos;
import com.example.luisgfoliveira1.lojinhob.models.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServicoProdutos {

    @GET("jsonprod/listar.php")
    Call<List<Produtos>> getProdutos();

    @POST("jsonprod/cadastrar.php")
    Call<CadProdutos> addCadProdutos(@Body CadProdutos produto);

}
