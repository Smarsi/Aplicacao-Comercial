package com.example.luisgfoliveira1.lojinhob.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pedidos {
    @SerializedName("idpedido")
    @Expose
    private int idPedido;

    @SerializedName("idFuncionarios")
    @Expose
    private int idFuncionarios;

    @SerializedName("datapedido")
    @Expose
    private Date dataPedido;


    public Pedidos() {
    }

    public Pedidos(int idPedido, int idFuncionarios, Date dataPedido) {
        this.idPedido = idPedido;
        this.idFuncionarios = idFuncionarios;
        this.dataPedido = dataPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdFuncionarios() {
        return idFuncionarios;
    }

    public void setIdFuncionarios(int idFuncionarios) {
        this.idFuncionarios = idFuncionarios;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }
}
