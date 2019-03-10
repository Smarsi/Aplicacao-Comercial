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
    EditText cadEmail, cadNome, cadcpf, cadTelefone, cadCelular,
            cadEndereco, cadNumeroEnd, cadCep, cadBairro, cadCompplemento;
    Button btnCadCadastro;


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
                        Intent car = new Intent(CadastroCliente.this, Carrinho.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro: {
                        Intent cad = new Intent(CadastroCliente.this, MainActivity.class);
                        startActivity(cad);
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
        cadEmail = findViewById(R.id.cadEmail);
        cadcpf = findViewById(R.id.cadCpf);
        cadTelefone = findViewById(R.id.cadTelefone);
        cadCelular = findViewById(R.id.cadCelular);
        cadEndereco = findViewById(R.id.cadEndereco);
        cadNumeroEnd = findViewById(R.id.cadNumeroEnd);
        cadBairro = findViewById(R.id.cadBairro);
        cadCompplemento = findViewById(R.id.cadComplemento);
        btnCadCadastro = findViewById(R.id.btncadCadastrar);

        sc = Utilitario.obterClientes();

        btnCadCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Clientes us = new Clientes();
                    us.setEmail(cadEmail.getText().toString());
                    us.setNome(cadNome.getText().toString());
                    us.setCpf(cadcpf.getText().toString());
                    us.setTelefone(cadTelefone.getText().toString());
                    us.setCelular(cadCelular.getText().toString());
                    us.setEndereco(cadEndereco.getText().toString());
                    us.setNumeroEndereco(cadNumeroEnd.getText().toString());
                    us.setCep(cadCep.getText().toString());
                    us.setBairro(cadBairro.getText().toString());
                    us.setComplemento(cadCompplemento.getText().toString());
                    cadastrarCliente(us);
                    //Limpar as caixas
                    cadEmail.setText("");
                    cadNome.setText("");
                    cadcpf.setText("");
                    cadTelefone.setText("");
                    cadCelular.setText("");
                    cadEndereco.setText("");
                    cadNumeroEnd.setText("");
                    cadBairro.setText("");
                    cadCep.setText("");
                    cadCompplemento.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void cadastrarCliente(Clientes us) {
        Call<Clientes> call = sc.addClientes(us);
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