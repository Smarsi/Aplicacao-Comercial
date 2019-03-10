package com.example.luisgfoliveira1.lojinhob;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView nome,email,telefone,cpf;
    TextView idFun;
    Button btnUltimas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //-------- Pegando as informações que estão nas
        //-------- preferências do telefone

        nome = findViewById(R.id.PerNome);
        email = findViewById(R.id.PerEmail);
        cpf = findViewById(R.id.PerCpf);
        idFun = findViewById(R.id.PerIdFuncionario);

        /*
        Vamos fazer a instância da classe SharedPreferences
        para resgatar os dados que foram gravados nas
        preferências do telefone. Logo passaremos os dados
        para as caixas de texto(nome,email,telefone,cpf e id)
         */

        SharedPreferences sp =
                PreferenceManager.getDefaultSharedPreferences(this);

        nome.setText(sp.getString("knome",""));
        email.setText(sp.getString("kemail",""));
        cpf.setText(sp.getString("kcpf",""));
        idFun.setText(sp.getString("kid",""));


        drawerLayout = findViewById(R.id.telaPerfil);
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
                        Intent per = new Intent(Perfil.this, MainActivity.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias: {
                        Intent ult = new Intent(Perfil.this, UltimasCompras.class);
                        startActivity(ult);
                    }
                    break;

                    case R.id.regulamento: {
                        Intent desejos = new Intent(Perfil.this,Regulamento.class);
                        startActivity(desejos);
                    }
                    break;

                    case R.id.privacidade: {
                        Intent reg = new Intent(Perfil.this, PoliticaPrivacidade.class);
                        startActivity(reg);
                    }
                    break;
                    case R.id.carrinho: {
                        Intent pol = new Intent(Perfil.this, Carrinho.class);
                        startActivity(pol);
                    }
                    break;
                    case R.id.cadastro: {
                        Intent pol = new Intent(Perfil.this, CadastroCliente.class);
                        startActivity(pol);
                    }
                    break;
                    default:
                        break;
                }
                finish();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        btnUltimas = findViewById(R.id.btnUltimas);
        btnUltimas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, UltimasCompras.class);
                startActivity(intent);
                finish();

            }

        });
    }
}