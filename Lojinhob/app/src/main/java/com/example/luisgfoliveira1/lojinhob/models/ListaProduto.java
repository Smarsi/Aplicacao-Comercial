package com.example.luisgfoliveira1.lojinhob.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListaProduto {


    @SerializedName("dados")
    @Expose
    private ArrayList<Produto> lstProduto;

    public ArrayList<Produto> getLstProduto() {
        return lstProduto;
    }

    public void setLstProdutos(ArrayList<Produto> lstProduto) {
        this.lstProduto = lstProduto;
    }
}

