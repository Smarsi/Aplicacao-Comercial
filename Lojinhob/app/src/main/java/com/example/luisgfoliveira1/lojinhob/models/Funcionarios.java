package com.example.luisgfoliveira1.lojinhob.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Funcionarios {


    @SerializedName("idFuncionario")
    @Expose
    private int idFuncionario;

    @SerializedName("email")
    @Expose
    private  String email;

    @SerializedName("cpf")
    @Expose
    private String cpf;

    @SerializedName("senha")
    @Expose
    private String senha;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("telefone")
    @Expose
    private String telefone;

    @SerializedName("endereco")
    @Expose
    private String endereco;

    @SerializedName("numeroEndereco")
    @Expose
    private String numeroEndereco;

    @SerializedName("cep")
    @Expose
    private String cep;

    @SerializedName("bairro")
    @Expose
    private String bairro;

    @SerializedName("complemento")
    @Expose
    private String complemento;

    @SerializedName("foto")
    @Expose
    private String foto;

    public Funcionarios (int idUsuario, String senha, String nome, String cpf, String telefone, String foto, String endereco,
                         String numeroEndereco, String cep, String bairro, String complemento, String email){
        this.idFuncionario = idFuncionario;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.foto = foto;
        this.endereco = endereco;
        this.numeroEndereco = numeroEndereco;
        this.cep = cep;
        this.bairro = bairro;
        this.complemento = complemento;
    }
    public Funcionarios(){}


    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getEndereco(String endereco) { return endereco; }

    public void setNumeroEndereco(String numeroEndereco) { this.numeroEndereco = numeroEndereco; }

    public String getNumeroEndereco(String numeroEndereco) { return numeroEndereco; }

    public void   setCep(String cep) { this.cep = cep; }

    public String getCep(String cep) { return cep; }

    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getBairro(String bairro) { return bairro; }

    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getComplemento(String complemento) { return complemento; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail(String email) { return email; }
}
