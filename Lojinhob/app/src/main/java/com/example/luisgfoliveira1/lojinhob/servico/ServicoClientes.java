package com.example.luisgfoliveira1.lojinhob.servico;

import com.example.luisgfoliveira1.lojinhob.models.Clientes;
import com.example.luisgfoliveira1.lojinhob.models.Funcionarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicoClientes {

    @GET("jsoncli/listar.php")
    Call<List<Clientes>> getCliente();

    @POST("jsoncli/cadastrar.php")
    Call<Clientes> addClientes(@Body Clientes Cliente);

    @POST("jsoncli/login.php")
    Call<Clientes> login(@Body Clientes usuarios);


    @PUT("jsoncli/")
    Call<Clientes> upClientes(@Path("id") int id, @Body Clientes clientes);

}
