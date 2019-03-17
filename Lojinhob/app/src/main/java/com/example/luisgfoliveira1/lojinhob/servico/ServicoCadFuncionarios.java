package com.example.luisgfoliveira1.lojinhob.servico;

import com.example.luisgfoliveira1.lojinhob.models.CadFuncionarios;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicoCadFuncionarios {
    @POST("jsonfun/cadastrar.php")
    Call<CadFuncionarios> addFuncionarios(@Body CadFuncionarios cadFuncionarios);
}
