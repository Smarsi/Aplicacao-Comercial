package com.example.luisgfoliveira1.lojinhob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luisgfoliveira1.lojinhob.chamadaRetrofit.Utilitario;
import com.example.luisgfoliveira1.lojinhob.models.Funcionarios;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoFuncionarios;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button btnLogin;

    EditText cpf, senha;

    ServicoFuncionarios sf;


    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean logado = app_preferences.contains("apLogado");
           /*
        Abaixo estamos verificado se a variável logado está
        com o seu valor true(verdadeiro)
        se logado for igual a true, então será criada uma
        intent para realizar a troca de tela. Isso faz
         com o usuário do app não passe pela tela de login
*/
        if(logado){
            Intent principal = new Intent(Login.this,MainActivity.class);
            startActivity(principal);
            finish();
        }

        cpf = findViewById(R.id.EditCpf);
        senha = findViewById(R.id.EditSenha);


        sf= Utilitario.obterFuncionario();

        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    /*
                    Abaixo a instância da classe usuário e passagem
                     dos dados da tela de login
                    para os objetos. Esses serão usados para logar.

                     */
                    Funcionarios fun = new Funcionarios();
                    fun.setCpf(cpf.getText().toString());
                    fun.setSenha(senha.getText().toString());

                    //Chamada do método efetuarLogin e a passagem dos
                    //dados do Funcionario
                    efetuarLogin(fun);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    void efetuarLogin(Funcionarios fun){
        Call<Funcionarios> call = sf.login(fun);
        call.enqueue(new Callback<Funcionarios>() {
            @Override
            public void onResponse(Call<Funcionarios> call, Response<Funcionarios> response) {
                /*
                Ao tentar logar, se a resposta for 200 ou 201(sucesso)
                então o usuário está logado e, portanto será criada
                a preferência de apLogado como verdadeiro e o
                usuário será redirecionado para a tela principal.
                 */
                if(response.isSuccessful()){
                    caixaDialogo("Validando dados");

                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Login.this);
                    sp.edit().putBoolean("apLogado", true).commit(); // islogin is a boolean value of your login statu

                    String nome = response.body().getNome();
                    String id=String.valueOf(response.body().getIdFuncionario());
                    String cpf = response.body().getCpf();
                    String tel = response.body().getTelefone();
                    String foto = response.body().getFoto();

                    sp.edit().putString("knome",nome).commit();
                    sp.edit().putString("kid",id).commit();
                    sp.edit().putString("kcpf",cpf).commit();
                    sp.edit().putString("ktel",tel).commit();
                    sp.edit().putString("kfoto",foto).commit();


                    Intent principal = new Intent(Login.this,MainActivity.class);
                    startActivity(principal);
                    finish();
                }
                else{
                    Log.e("Erro: ","Não foi possível logar. "+response.message()+"-"+response.code());
                }
            }

            /*
            Ao tentar chamar a api a aplicação pode não
            ser capaz de carregar os dados ou passá-los.
            Esse processo gera um erro que está sendo
            tratado abaixo.
             */
            @Override
            public void onFailure(Call<Funcionarios> call, Throwable t) {
                Log.e("Erro: ",t.getMessage());
            }
        });
    }


        /*btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

    private void caixaDialogo(String msg){
        pDialog = new ProgressDialog(Login.this);
        pDialog.setMessage(msg);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
}






