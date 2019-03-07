package com.example.luisgfoliveira1.lojinhob;

import android.content.Intent;
import android.media.Image;
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
import android.widget.ImageView;

public class Categoria extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView imgHd, imgProcessador, imgMonitor, imgTeclados, imgPlacaVideo, imgFonte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.telaCategorias);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.perfil: {
                        Intent per = new Intent(Categoria.this, Perfil.class);
                        startActivity(per);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(Categoria.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(Categoria.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(Categoria.this, Carrinho.class);
                        startActivity(car);

                    }
                    case R.id.categorias:{
                        Intent cat = new Intent(Categoria.this,MainActivity.class);
                        startActivity(cat);
                        break;
                    }
                    case R.id.cadastro:{
                        Intent cad = new Intent(Categoria.this, CadastroCliente.class);
                        startActivity(cad);
                    }
                    default:
                        break;
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        imgHd = findViewById(R.id.imgCategoria);
        imgHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categoria.this, Produtos.class);
                startActivity(intent);
                finish();

            }
        });
        imgTeclados = findViewById(R.id.imgTeclados);
        imgTeclados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categoria.this, Produtos.class);
                startActivity(intent);
                finish();
            }
        });
        imgProcessador = findViewById(R.id.imgProcessador);
        imgProcessador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categoria.this, Produtos.class);
                startActivity(intent);
                finish();
            }
        });
        imgPlacaVideo = findViewById(R.id.imgPlacaVideo);
        imgPlacaVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categoria.this, Produtos.class);
                startActivity(intent);
                finish();
            }
        });
        imgMonitor = findViewById(R.id.imgMonitor);
        imgMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categoria.this, Produtos.class);
                startActivity(intent);
                finish();
            }
        });
        imgFonte = findViewById(R.id.imgFontes);
        imgFonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categoria.this, Produtos.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
