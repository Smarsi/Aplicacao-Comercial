package com.example.luisgfoliveira1.lojinhob;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luisgfoliveira1.lojinhob.Adapter.ViewPagerAdapter;

public class Detalhes extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView txtDetalhesNomeProduto;
    TextView txtDetalhesDescricao;
    TextView txtDetalhesPreco;
    TextView txtDetalhesIdProduto;
    ImageView imageViewProduto;
    Button btnCarrinho;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtDetalhesNomeProduto = findViewById(R.id.textDetalheNomeProduto);
        txtDetalhesDescricao = findViewById(R.id.textDetalheDescricao);
        txtDetalhesPreco = findViewById(R.id.textDetalhePreco);
        txtDetalhesIdProduto = findViewById(R.id.textDetalheIdProduto);

        txtDetalhesNomeProduto.setText(getIntent().getExtras().get("nomeProduto").toString());
        txtDetalhesDescricao.setText(getIntent().getExtras().get("descricaoProduto").toString());
        txtDetalhesPreco.setText(getIntent().getExtras().get("precoProduto").toString());
        txtDetalhesIdProduto.setText(getIntent().getExtras().get("idProduto").toString());


        btnCarrinho = findViewById(R.id.btnDetCarrinho);
        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent car = new Intent(Detalhes.this, CarrinhoProduto.class);
                car.putExtra("carNome", txtDetalhesNomeProduto.getText());
                car.putExtra("carPreco", txtDetalhesPreco.getText());
                car.putExtra("carIdPro", txtDetalhesIdProduto.getText());
                startActivity(car);
            }
        });

        String img1 = getIntent().getExtras().get("imagem1").toString();
        String img2 = getIntent().getExtras().get("imagem2").toString();
        String img3 = getIntent().getExtras().get("imagem3").toString();
        String img4 = getIntent().getExtras().get("imagem4").toString();

        String[] imagens = new String[]{
                img1,
                img2,
                img3,
                img4
        };
/*
        Ligação entre o viewpager que está em xml do
        layout com o objeto viewPager do java
        para escrever comandos de execução.
         */
        final ViewPager viewPager = findViewById(R.id.view_pager);
        /*
        Instância da classe ViewPagerAdapter e passagem
        do context(tela que abre o pageviewer) e o
        array de imagens que será exibido
         */
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imagens);

        /*
        Vincular a adaptação das imagens vindas da api
        com o viewpager da tela detalhes.
         */
        viewPager.setAdapter(adapter);


          }

    }