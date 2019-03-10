package com.example.luisgfoliveira1.lojinhob.servico;

import com.example.luisgfoliveira1.lojinhob.models.Pedidos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServicoPedidos {
    @GET("jsonped/listar.php")
    Call<List<Pedidos>> getPedidos();

    @POST("jsonped/cadastrar.php")
    Call<Pedidos> addPedidos(@Body Pedidos pedidos);
}
