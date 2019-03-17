package com.example.luisgfoliveira1.lojinhob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.luisgfoliveira1.lojinhob.chamadaRetrofit.Utilitario;
import com.example.luisgfoliveira1.lojinhob.models.Clientes;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoClientes;


import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroCliente extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ServicoClientes sc;
    EditText cadEmail;
    EditText cadNome;
    EditText cadcpf;
    EditText cadTelefone;
    EditText cadCelular;
    EditText cadEndereco;
    EditText cadNumeroEnd;
    EditText cadCep;
    EditText cadBairro;
    EditText cadCompplemento;
    Button btncadCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.telaCadastro);
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
                        Intent per = new Intent(CadastroCliente.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias: {
                        Intent cat = new Intent(CadastroCliente.this, Categoria.class);
                        startActivity(cat);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(CadastroCliente.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(CadastroCliente.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(CadastroCliente.this, CarrinhoProduto.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro: {
                        Intent cad = new Intent(CadastroCliente.this, MainActivity.class);
                        startActivity(cad);
                    }
                    case R.id.cadastrofun: {
                        Intent cadfun = new Intent(CadastroCliente.this, CadastroFuncionario.class);
                        startActivity(cadfun);
                        break;
                    }
                    case  R.id.cadastroPro: {
                        Intent cadpro = new Intent(CadastroCliente.this, CadastroProdutos.class);
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
        cadNome = findViewById(R.id.cadNome);
        cadcpf = findViewById(R.id.cadCpf);
        cadEmail = findViewById(R.id.cadEmail);
        cadTelefone = findViewById(R.id.cadTelefone);
        cadCelular = findViewById(R.id.cadCelular);
        cadEndereco = findViewById(R.id.cadEndereco);
        cadBairro = findViewById(R.id.cadBairro);
        cadNumeroEnd = findViewById(R.id.cadNumeroEnd);
        cadCep = findViewById(R.id.cadCep);
        cadCompplemento = findViewById(R.id.cadComplemento);
        btncadCadastrar = findViewById(R.id.btncadCadastrar);

        sc = Utilitario.obterClientes();

        btncadCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Clientes cli = new Clientes();
                    cli.setNome(cadNome.getText().toString());
                    cli.setCpf(cadcpf.getText().toString());
                    cli.setEmail(cadEmail.getText().toString());
                    cli.setTelefone(cadTelefone.getText().toString());
                    cli.setCelular(cadCelular.getText().toString());
                    cli.setEndereco(cadEndereco.getText().toString());
                    cli.setBairro(cadBairro.getText().toString());
                    cli.setCep(cadCep.getText().toString());
                    cli.setNumero(cadNumeroEnd.getText().toString());
                    cli.setComplemento(cadCompplemento.getText().toString());
                    cadastrarClientes(cli);
                    System.out.print(cli);

                    //Limpar as caixas
                    cadEmail.setText("");
                    cadcpf.setText("");
                    cadNome.setText("");
                    cadTelefone.setText("");
                    cadCelular.setText("");
                    cadEndereco.setText("");
                    cadBairro.setText("");
                    cadCep.setText("");
                    cadNumeroEnd.setText("");
                    cadCompplemento.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void cadastrarClientes (Clientes cli) {
        Call<Clientes> call = sc.addClientes(cli);
        call.enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroCliente.this, "Cadastrou", Toast.LENGTH_LONG).show();
                } else {
                    Log.e("Erro: ", "Não foi possível cadastrar. " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Clientes> call, Throwable t) {
                Log.e("Erro: ", t.getMessage());
            }
        });




    }
}