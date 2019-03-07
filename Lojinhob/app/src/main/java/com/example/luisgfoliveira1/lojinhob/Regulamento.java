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
import android.view.MenuItem;

public class Regulamento extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulamento);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.telaRegula);

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
                        Intent per = new Intent(Regulamento.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias:{
                        Intent cat = new Intent(Regulamento.this,Categoria.class);
                        startActivity(cat);
                        break;
                    }
                    case R.id.regulamento: {
                        Intent reg = new Intent(Regulamento.this, MainActivity.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(Regulamento.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(Regulamento.this, Carrinho.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro:{
                        Intent cad = new Intent(Regulamento.this, CadastroCliente.class);
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






    }
}
