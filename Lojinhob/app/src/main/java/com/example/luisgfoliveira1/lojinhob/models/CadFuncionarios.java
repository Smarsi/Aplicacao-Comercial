package com.example.luisgfoliveira1.lojinhob.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CadFuncionarios {


    @SerializedName("senha")
    @Expose
    private String senha;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("cpf")
        @Expose
        private String cpf;


        @SerializedName("nome")
        @Expose
        private String nome;

        @SerializedName("telefone")
        @Expose
        private String telefone;

        @SerializedName("celular")
        @Expose
        private String celular;

        @SerializedName("endereco")
        @Expose
        private String endereco;

        @SerializedName("numero")
        @Expose
        private String numero;

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

        public CadFuncionarios (String email, String senha, String nome, String cpf, String telefone, String celular,  String endereco,
                             String numero, String cep, String bairro,  String complemento, String foto){
            this.email = email;
            this.senha = senha;
            this.nome = nome;
            this.cpf = cpf;
            this.telefone = telefone;
            this.celular = celular;
            this.endereco = endereco;
            this.numero = numero;
            this.cep = cep;
            this.bairro = bairro;
            this.complemento = complemento;
            this.foto = foto;

            System.out.print(this);


        }
        public CadFuncionarios(){}


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

        public String getCelular() { return  celular; }

        public void setCelular(String celular) { this.celular = celular; }

        public String getEmail() { return  email; }

        public void setEmail(String email) { this.email = email; }

        public String getEndereco() { return  endereco;}

        public void setEndereco(String endereco) { this.endereco = endereco; }

        public String getNumero() { return numero; }

        public void setNumero(String numero) { this.numero = numero; }

        public String getCep() { return cep; }

        public void setCep(String cep) { this.cep = cep; }

        public String getBairro(){ return  bairro; }

        public void  setBairro(String bairro ) { this.bairro = bairro; }

        public String getComplemento() { return  complemento;}

        public void setComplemento(String complemento ) {this.complemento = complemento; }

        public String getFoto() { return foto;}

        public void  setFoto(String foto) {this.foto = foto;}

    }

