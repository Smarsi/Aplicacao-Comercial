package com.example.luisgfoliveira1.lojinhob;

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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Carrinho extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView idFun, idPro, nomeProduto, preco, precoTotal;
    EditText quantidade;
    SeekBar sb;
    double calculoTotal=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        idFun = findViewById(R.id.carIdFun);
        idPro = findViewById(R.id.carIdProduto);
        nomeProduto = findViewById(R.id.carNomeProduto);
        preco = findViewById(R.id.carPreco);
        precoTotal = findViewById(R.id.carPrecoTotal);
        quantidade = findViewById(R.id.carQuantidade);
        /*
        Vamos capturar o id do usuario que esta nas preferencias do
        telefone. Portanto iremos fazer  uma instância da classe
         sharedPrefences
         */
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        idFun.setText("");
        idFun.append("Id do funcionario");
        idFun.append(sp.getString("kid", ""));

        idPro.setText("");
        idPro.append("Código do Produto");
        idPro.append(getIntent().getExtras().get("carIdPro").toString());

        nomeProduto.setText("");
        nomeProduto.append("Nome do Produto");
        nomeProduto.append(getIntent().getExtras().get("carNome").toString());

        preco.setText("");

        preco.append(getIntent().getExtras().get("carPreco").toString());

        quantidade.setText("1");

        calculoTotal = Double.parseDouble(quantidade.getText().toString()) *
                Double.parseDouble(preco.getText().toString());

        precoTotal.setText("");
        precoTotal.setText("R$ " + String.valueOf(calculoTotal));


        sb = findViewById(R.id.seekBar);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                quantidade.setText(String.valueOf(progress));
                calculoTotal = Double.parseDouble(quantidade.getText().toString()) *
                        Double.parseDouble(preco.getText().toString());
                precoTotal.setText("R$ "+String.valueOf(calculoTotal));
                precoTotal.append(String.valueOf(calculoTotal));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
/*

        drawerLayout = findViewById(R.id.telaCarrinho);

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
                        Intent per = new Intent(Carrinho.this, Perfil.class);
                        startActivity(per);
                        break;
                    }
                    case R.id.categorias:{
                        Intent cat = new Intent(Carrinho.this,Categoria.class);
                        startActivity(cat);
                        break;
                    }

                    case R.id.regulamento: {
                        Intent reg = new Intent(Carrinho.this, Regulamento.class);
                        startActivity(reg);
                        break;
                    }
                    case R.id.privacidade: {
                        Intent pol = new Intent(Carrinho.this, PoliticaPrivacidade.class);
                        startActivity(pol);
                        break;
                    }
                    case R.id.carrinho: {
                        Intent car = new Intent(Carrinho.this, MainActivity.class);
                        startActivity(car);
                        break;
                    }
                    case R.id.cadastro:{
                        Intent cad = new Intent(Carrinho.this, CadastroCliente.class);
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

*/



    }
}