package com.example.luisgfoliveira1.lojinhob;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.luisgfoliveira1.lojinhob.Adapter.ProdutosAdapter;
import com.example.luisgfoliveira1.lojinhob.chamadaRetrofit.Utilitario;
import com.example.luisgfoliveira1.lojinhob.models.ListaProduto;
import com.example.luisgfoliveira1.lojinhob.models.Produto;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoProduto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    ListView listView;
    ArrayList<Produto> produtosLista;
    ProdutosAdapter adapter;

    String idProduto, nomeProduto, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView =  findViewById(R.id.listView);
        produtosLista = new ArrayList<>();

        exibirDados();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalhes = new Intent(MainActivity.this, Detalhes.class);
                detalhes.putExtra("idProduto", produtosLista.get(position).getIdProduto());
                detalhes.putExtra("nomeProduto",produtosLista.get(position).getNomeProduto());
                detalhes.putExtra("descricaoProduto",produtosLista.get(position).getDescricao());
                detalhes.putExtra("categoria",produtosLista.get(position).getCategoria());
                detalhes.putExtra("precoProduto",produtosLista.get(position).getPreco());
                detalhes.putExtra("imagem1",produtosLista.get(position).getImg1());
                detalhes.putExtra("imagem2",produtosLista.get(position).getImg2());
                detalhes.putExtra("imagem3",produtosLista.get(position).getImg3());
                detalhes.putExtra("imagem4",produtosLista.get(position).getImg4());


                startActivity(detalhes);
            }
        });

        drawerLayout = findViewById(R.id.telaInicial);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,drawerLayout,toolbar,
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
                        Intent car = new Intent(MainActivity.this, CarrinhoProduto.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro:{
                        Intent cad = new Intent(MainActivity.this, CadastroCliente.class);
                        startActivity(cad);
                    }
                    case R.id.cadastrofun: {
                        Intent cadfun = new Intent(MainActivity.this, CadastroFuncionario.class);
                        startActivity(cadfun);
                        break;
                    }
                    case  R.id.cadastroPro: {
                        Intent cadpro = new Intent(MainActivity.this, CadastroProdutos.class);
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

    private void exibirDados() {
        final ProgressDialog dialog;

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Buscando Dados");
        dialog.setMessage("Exibição de dados do json");
        dialog.show();

        ServicoProduto sp = Utilitario.obterProdutos();
        Call<ListaProduto> call = sp.getTodosProdutos();

        call.enqueue(new Callback<ListaProduto>() {
            @Override
            public void onResponse(Call<ListaProduto> call, Response<ListaProduto> response) {

                dialog.dismiss();//Efeito carregando

                if (response.isSuccessful()) {
                    produtosLista = response.body().getLstProduto();
                    adapter = new ProdutosAdapter(getApplicationContext(), produtosLista);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Erro -> " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListaProduto> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erro ->" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
