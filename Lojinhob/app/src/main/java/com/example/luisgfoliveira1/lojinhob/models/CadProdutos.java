package com.example.luisgfoliveira1.lojinhob.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CadProdutos {

    @SerializedName("nomeproduto")
    @Expose
    private String nomeproduto;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("categoria")
    @Expose
    private String categoria;

    @SerializedName("quantidade")
    @Expose
    private  String quantidade;

    @SerializedName("preco")
    @Expose
    private String preco;

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

    @SerializedName("idfornecedor")
    @Expose
    private String idfornecedor;

    public CadProdutos(String idfornecedor, String nomeproduto, String quantidade,
                       String descricao, String categoria, String preco, String img1,
                       String img2, String img3, String img4){

        this.idfornecedor = idfornecedor;
        this.nomeproduto = nomeproduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.preco = preco;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
    }
    public CadProdutos(){}


    public String getIdfornecedor() { return idfornecedor; }

    public void  setIdfornecedor(String idfornecedor) { this.idfornecedor = idfornecedor; }

    public String getNomeProduto() {
        return nomeproduto;
    }

    public void setNomeProduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidade() { return quantidade; }

    public void setQuantidade(String quantidade) { this.quantidade = quantidade; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getImg1() {
        return "http://diogodiniz.com.br/Lojinho/APIprojeto/data/" +img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return "http://diogodiniz.com.br/Lojinho/APIprojeto/data/" +img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return "http://diogodiniz.com.br/Lojinho/APIprojeto/data/" +img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return "http://diogodiniz.com.br/Lojinho/APIprojeto/data/" +img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }
}

