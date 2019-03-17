package com.example.luisgfoliveira1.lojinhob.servico;

import com.example.luisgfoliveira1.lojinhob.models.Clientes;


        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.POST;


public interface ServicoClientes {

    @POST("jsoncli/cadastrar.php")
    Call<Clientes> addClientes(@Body Clientes clientes);


}
