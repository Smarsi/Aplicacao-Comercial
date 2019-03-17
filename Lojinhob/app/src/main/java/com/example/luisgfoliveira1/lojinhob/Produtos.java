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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luisgfoliveira1.lojinhob.Adapter.ProdutosAdapter;
import com.example.luisgfoliveira1.lojinhob.chamadaRetrofit.Utilitario;
import com.example.luisgfoliveira1.lojinhob.models.DetalhesPedido;
import com.example.luisgfoliveira1.lojinhob.models.ListaProduto;
import com.example.luisgfoliveira1.lojinhob.models.Produto;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoProduto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Produtos extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Button btnVoltar;

    ListView listView;
    ArrayList<Produto> produtosLista;
    ProdutosAdapter adapter;

    String idProduto, nomeProduto, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
        produtosLista = new ArrayList<>();

        exibirDados();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalhes = new Intent(Produtos.this, Detalhes.class);
                detalhes.putExtra("idProduto", produtosLista.get(position).getIdProduto());
                detalhes.putExtra("nomeProduto", produtosLista.get(position).getNomeProduto());
                detalhes.putExtra("descricaoProduto", produtosLista.get(position).getDescricao());
                detalhes.putExtra("categoria", produtosLista.get(position).getCategoria());
                detalhes.putExtra("precoProduto", produtosLista.get(position).getPreco());
                detalhes.putExtra("imagem1", produtosLista.get(position).getImg1());
                detalhes.putExtra("imagem2", produtosLista.get(position).getImg2());
                detalhes.putExtra("imagem3", produtosLista.get(position).getImg3());
                detalhes.putExtra("imagem4", produtosLista.get(position).getImg4());


                startActivity(detalhes);
            }
        });

        drawerLayout = findViewById(R.id.telaProdutos);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.perfil: {
                        Intent per = new Intent(Produtos.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias: {
                        Intent cat = new Intent(Produtos.this, Categoria.class);
                        startActivity(cat);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(Produtos.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(Produtos.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(Produtos.this, MainActivity.class);
                        startActivity(car);
                        break;

                    }
                    case R.id.cadastro: {
                        Intent cad = new Intent(Produtos.this, CadastroCliente.class);
                        startActivity(cad);
                    }
                    case R.id.cadastrofun: {
                        Intent cadfun = new Intent(Produtos.this, CadastroFuncionario.class);
                        startActivity(cadfun);
                        break;
                    }
                    case  R.id.cadastroPro: {
                        Intent cadpro = new Intent(Produtos.this, CadastroProdutos.class);
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

        btnVoltar = findViewById(R.id.btnVoltarProdutos);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Produtos.this, Categoria.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void exibirDados() {
        final ProgressDialog dialog;

        dialog = new ProgressDialog(Produtos.this);
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
                    adapter = new ProdutosAdapter(Produtos.this, produtosLista);
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





