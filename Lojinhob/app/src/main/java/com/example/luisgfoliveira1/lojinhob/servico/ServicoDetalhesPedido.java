package com.example.luisgfoliveira1.lojinhob.servico;


import com.example.luisgfoliveira1.lojinhob.models.DetalhesPedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServicoDetalhesPedido {
    @GET("jsondet/listar.php")
    Call<List<DetalhesPedido>> getDetalhesPedido();

    @POST("jsondet/cadastrar.php")
    Call<DetalhesPedido> addDetalhesPedido(@Body DetalhesPedido detalhesPedido);
}

