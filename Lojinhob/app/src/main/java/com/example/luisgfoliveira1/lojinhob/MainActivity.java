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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ListView listView;
    ArrayList<Produtos> produtosLista;
    ProdutosAdapter adapter;

    String idProduto, nomeProduto, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
        produtosLista = new ArrayList<>();


        exibirDados();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalhes = new Intent(MainActivity.this, Detalhes.class);
                detalhes.putExtra("idProduto",produtosLista.get(position).getIdProduto());
                detalhes.putExtra("nomeProduto",produtosLista.get(position).getNomeProduto());
                detalhes.putExtra("descricaoProduto",produtosLista.get(position).getDescricao());
                detalhes
                detalhes.putExtra("precoProduto",produtosLista.get(position).getPreco());
                detalhes.putExtra("imagem1",produtosLista.get(position).getImg1());
                detalhes.putExtra("imagem2",produtosLista.get(position).getImg2());
                detalhes.putExtra("imagem3",produtosLista.get(position).getImg3());
                detalhes.putExtra("imagem4",produtosLista.get(position).getImg4());

                startActivity(detalhes);



            }
        });

        drawerLayout = findViewById(R.id.telaInicial);

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
                        Intent per = new Intent(MainActivity.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias:{
                        Intent cat = new Intent(MainActivity.this,Categoria.class);
                        startActivity(cat);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(MainActivity.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(MainActivity.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(MainActivity.this, Carrinho.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro:{
                        Intent cad = new Intent(MainActivity.this, CadastroCliente.class);
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
