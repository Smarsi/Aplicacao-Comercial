package com.example.luisgfoliveira1.lojinhob.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalhesPedido {
    @SerializedName("iddetalhespedido")
    @Expose
    private int idDetalhesPedido;

    @SerializedName("idpedido")
    @Expose
    private int idPedido;

    @SerializedName("idproduto")
    @Expose
    private int idProduto;

    @SerializedName("quantidade")
    @Expose
    private int quantidade;

    @SerializedName("categoria")
    @Expose
    private int categoria;

    public DetalhesPedido() {
    }

    public DetalhesPedido(int idDetalhesPedido, int idPedido, int idProduto, int quantidade, int categoria) {
        this.idDetalhesPedido = idDetalhesPedido;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public int getIdDetalhesPedido() {
        return idDetalhesPedido;
    }

    public void setIdDetalhesPedido(int idDetalhesPedido) {
        this.idDetalhesPedido = idDetalhesPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    

}
