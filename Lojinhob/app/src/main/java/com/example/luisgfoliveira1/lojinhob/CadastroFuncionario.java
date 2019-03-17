package com.example.luisgfoliveira1.lojinhob;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luisgfoliveira1.lojinhob.chamadaRetrofit.Utilitario;
import com.example.luisgfoliveira1.lojinhob.models.CadFuncionarios;
import com.example.luisgfoliveira1.lojinhob.models.Funcionarios;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoCadFuncionarios;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroFuncionario extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ServicoCadFuncionarios sf;
    EditText  funEmail;
    EditText  funSenha;
    EditText  funNome;
    EditText  funcpf;
    EditText  funTelefone;
    EditText  funCelular;
    EditText  funEndereco;
    EditText  funNumero;
    EditText  funCep;
    EditText  funBairro;
    EditText  funComplemento;
    EditText funFoto;
    Button btncadFunCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.telaCadastroFun);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                        R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.perfil: {
                        Intent per = new Intent(CadastroFuncionario.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias: {
                        Intent cat = new Intent(CadastroFuncionario.this, Categoria.class);
                        startActivity(cat);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(CadastroFuncionario.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(CadastroFuncionario.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(CadastroFuncionario.this, CarrinhoProduto.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro: {
                        Intent cad = new Intent(CadastroFuncionario.this, CadastroCliente.class);
                        startActivity(cad);
                        break;
                    }
                    case R.id.cadastrofun: {
                        Intent cadfun = new Intent(CadastroFuncionario.this, MainActivity.class);
                        startActivity(cadfun);
                        break;
                    }
                    case  R.id.cadastroPro: {
                        Intent cadpro = new Intent(CadastroFuncionario.this, CadastroProdutos.class);
                        startActivity(cadpro);
                    }




                    break;
                    default:
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });
        /*
        Fazer o vinculo dos elementos XML com os controles em java

       */
        funEmail = findViewById(R.id.funEmail);
        funSenha = findViewById(R.id.funSenha);
        funNome = findViewById(R.id.funNome);
        funcpf = findViewById(R.id.funCpf);
        funTelefone = findViewById(R.id.funTelefone);
        funCelular = findViewById(R.id.funCelular);
        funEndereco = findViewById(R.id.funEndereco);
        funBairro = findViewById(R.id.funBairro);
        funComplemento = findViewById(R.id.funComplemento);
        funCep = findViewById(R.id.funCep);
        funNumero = findViewById(R.id.funNumero);
        funFoto = findViewById(R.id.funFoto);
        btncadFunCadastrar = findViewById(R.id.btncadFunCadastrar);


        sf = Utilitario.obterCadFuncionarios();

        btncadFunCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CadFuncionarios fun = new CadFuncionarios();
                    fun.setEmail(funEmail.getText().toString());
                    fun.setSenha(funSenha.getText().toString());
                    fun.setNome(funNome.getText().toString());
                    fun.setCpf(funcpf.getText().toString());
                    fun.setTelefone(funTelefone.getText().toString());
                    fun.setCelular(funCelular.getText().toString());
                    fun.setEndereco(funEndereco.getText().toString());
                    fun.setBairro(funBairro.getText().toString());
                    fun.setComplemento(funComplemento.getText().toString());
                    fun.setCep(funCep.getText().toString());
                    fun.setNumero(funNumero.getText().toString());
                    fun.setFoto(funFoto.getText().toString());
                    cadastroFuncionarios(fun);

                    //Limpar as caixas
                    funEmail.setText("");
                    funSenha.setText("");
                    funNome.setText("");
                    funcpf.setText("");
                    funTelefone.setText("");
                    funCelular.setText("");
                    funEndereco.setText("");
                    funBairro.setText("");
                    funComplemento.setText("");
                    funCep.setText("");
                    funNumero.setText("");
                    funFoto.setText("");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void cadastroFuncionarios (CadFuncionarios fun){
        Call<CadFuncionarios> call = sf.addFuncionarios(fun);
        call.enqueue(new Callback<CadFuncionarios>() {
            @Override
            public void onResponse(Call<CadFuncionarios> call, Response<CadFuncionarios> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroFuncionario.this, "Cadastrado", Toast.LENGTH_LONG).show();

                } else{
                    Log.e("Erro: ", "Não foi possivel cadastrar. " + response.message());

                }
            }

            @Override
            public void onFailure(Call<CadFuncionarios> call, Throwable t) {
                    Log.e("Erro: ", t.getMessage());

            }
        });




    }
}