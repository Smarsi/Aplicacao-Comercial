package com.example.luisgfoliveira1.lojinhob.chamadaRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChamadaRetrofit {

    /*
    Criação de uma propriedade para estabelecer a comunicação com o
    json via http. Será utilizada para fazer a chamada da url do
    servidor onde está o json
    Usamos o comando static para que não seja necessário instanciar
    a classe ChamadaRetrofit para utilizá-lo
     */
    public static Retrofit retrofit = null;

    /*
    Abaixo é apresentada a construção do método
    que faz a chamada da url base adicionando
    os sulfixos dos métodos get, post, update
    e delete de cada classe específica(
    ServicoUsuarios, ServicoPagamentos,
    ServicoPedidos, ServicoDetalhes e
    ServicoProduto
     */
    public static Retrofit getClient(String url){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

