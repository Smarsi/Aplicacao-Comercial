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
import android.widget.Toast;

import com.example.luisgfoliveira1.lojinhob.chamadaRetrofit.Utilitario;
import com.example.luisgfoliveira1.lojinhob.models.CadProdutos;
import com.example.luisgfoliveira1.lojinhob.models.Produto;
import com.example.luisgfoliveira1.lojinhob.servico.ServicoProdutos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroProdutos extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ServicoProdutos sp;
    EditText cadProdNome;
    EditText cadProdDesc;
    EditText cadProdPreco;
    EditText cadProdCategoria;
    EditText cadProdQuantidade;
    EditText cadProdImg1;
    EditText cadProdImg2;
    EditText cadProdImg3;
    EditText cadProdImg4;
    EditText cadProdIdFornecedor;
    Button btnCadProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.telaCadastroPro);
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
                        Intent per = new Intent(CadastroProdutos.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias: {
                        Intent cat = new Intent(CadastroProdutos.this, Categoria.class);
                        startActivity(cat);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(CadastroProdutos.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(CadastroProdutos.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(CadastroProdutos.this, CarrinhoProduto.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro: {
                        Intent cad = new Intent(CadastroProdutos.this, CadastroCliente.class);
                        startActivity(cad);
                        break;
                    }
                    case R.id.cadastrofun: {
                        Intent cadfun = new Intent(CadastroProdutos.this, CadastroFuncionario.class);
                        startActivity(cadfun);
                        break;
                    }
                    case  R.id.cadastroPro: {
                        Intent cadpro = new Intent(CadastroProdutos.this, MainActivity.class);
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
          cadProdNome = findViewById(R.id.cadProdNome);
          cadProdCategoria = findViewById(R.id.cadProdCategoria);
          cadProdDesc = findViewById(R.id.cadProdDesc);
          cadProdQuantidade = findViewById(R.id.cadProdQuantidade);
          cadProdIdFornecedor = findViewById(R.id.cadProdIdFornecedor);
          cadProdPreco = findViewById(R.id.cadProdPreco);
          cadProdImg1 = findViewById(R.id.cadProdImg1);
          cadProdImg2 = findViewById(R.id.cadProdImg2);
          cadProdImg3 = findViewById(R.id.cadProdImg3);
          cadProdImg4 = findViewById(R.id.cadProdImg4);
          btnCadProdutos = findViewById(R.id.btnCadastrarProduto);

          sp = Utilitario.obeterCadProdutos();

          btnCadProdutos.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  try {
                      CadProdutos pro = new CadProdutos();
                      pro.setNomeProduto(cadProdNome.getText().toString());
                      pro.setCategoria(cadProdCategoria.getText().toString());
                      pro.setDescricao(cadProdDesc.getText().toString());
                      pro.setQuantidade(cadProdQuantidade.getText().toString());
                      pro.setIdfornecedor(cadProdIdFornecedor.getText().toString());
                      pro.setPreco(cadProdPreco.getText().toString());
                      pro.setImg1(cadProdImg1.getText().toString());
                      pro.setImg2(cadProdImg2.getText().toString());
                      pro.setImg3(cadProdImg3.getText().toString());
                      pro.setImg4(cadProdImg4.getText().toString());
                      cadastrarPodutos(pro);


                      //Limpar as caixas
                      cadProdIdFornecedor.setText("");
                      cadProdQuantidade.setText("");
                      cadProdNome.setText("");
                      cadProdCategoria.setText("");
                      cadProdDesc.setText("");
                      cadProdIdFornecedor.setText("");
                      cadProdImg1.setText("");
                      cadProdImg2.setText("");
                      cadProdImg3.setText("");
                      cadProdImg4.setText("");


                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
    });
          }
        public void cadastrarPodutos(CadProdutos prod) {
        Call<CadProdutos> call = sp.addCadProdutos(prod);
        call.enqueue(new Callback<CadProdutos>() {
            @Override
            public void onResponse(Call<CadProdutos> call, Response<CadProdutos> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CadastroProdutos.this,"Cadastrou",Toast.LENGTH_LONG).show();

                }
                else
                    Log.e("Erro: ","Não foi possivel cadastrar. "+response.message());

            }

            @Override
            public void onFailure(Call<CadProdutos> call, Throwable t) {
                Log.e("Erro: ", t.getMessage());

            }
        });


    }
}