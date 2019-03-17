package com.example.luisgfoliveira1.lojinhob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CarrinhoProduto extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ListView list;
    LinearLayout linear;
    TextView nomeproduto, preco, quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_produto);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nomeproduto = findViewById(R.id.nomeProduto);
        preco = findViewById(R.id.precoProduto);
        quantidade = findViewById(R.id.quantidadeProduto);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        nomeproduto.setText("");
        nomeproduto.append(getIntent().getExtras().get("carNome").toString());

        preco.setText("");
        preco.append(getIntent().getExtras().get("carPreco").toString());




        drawerLayout = findViewById(R.id.telaCarrinhoPro);

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
                        Intent per = new Intent(CarrinhoProduto.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias:{
                        Intent cat = new Intent(CarrinhoProduto.this,Categoria.class);
                        startActivity(cat);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(CarrinhoProduto.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(CarrinhoProduto.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(CarrinhoProduto.this, MainActivity.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro:{
                        Intent cad = new Intent(CarrinhoProduto.this, CadastroCliente.class);
                        startActivity(cad);
                    }
                    case R.id.cadastrofun: {
                        Intent cadfun = new Intent(CarrinhoProduto.this, CadastroFuncionario.class);
                        startActivity(cadfun);
                        break;
                    }
                    case  R.id.cadastroPro: {
                        Intent cadpro = new Intent(CarrinhoProduto.this, MainActivity.class);
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



        }
    }

