package com.example.luisgfoliveira1.lojinhob.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Produto {
    @SerializedName("idproduto")
    @Expose
    private int idProduto;

    @SerializedName("nomeproduto")
    @Expose
    private String nomeProduto;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("categoria")
    @Expose
    private String categoria;


    @SerializedName("preco")
    @Expose
    private double preco;

    @SerializedName("img1")
    @Expose
    private String img1;

    @SerializedName("img2")
    @Expose
    private String img2;

    @SerializedName("img3")
    @Expose
    private String img3;

    @SerializedName("img4")
    @Expose
    private String img4;

    public Produto (){

    }
    public Produto(int idProduto, String nomeProduto, String descricao, String categoria, double preco, String img1, String img2, String img3, String img4) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {return  categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getImg1() {
        return "http://www.edilsonsilva.net/apcomercial/data/jsonpr/"+img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return "http://www.edilsonsilva.net/apcomercial/data/jsonpr/"+img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return "http://www.edilsonsilva.net/apcomercial/data/jsonpr/"+img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return "http://www.edilsonsilva.net/apcomercial/data/jsonpr/"+img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }
}