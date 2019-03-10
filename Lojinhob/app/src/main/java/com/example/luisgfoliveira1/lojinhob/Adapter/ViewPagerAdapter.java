package com.example.luisgfoliveira1.lojinhob.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {


    private Context context;

    /*
    Abaixo temos um atributo que representa o caminho de
    cada imagem que dever ser apresentada.
    Como temos mais de uma imagem, foi criado um array(vetor)
    com os caminhos das imagens
     */
    private String[] imageUrls;


    /*
    O construtor da classe requisita a tela que contém
    o viewpager e também o array de imagens contendo o
    caminho das imagens
     */
    public ViewPagerAdapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    /*
    Este método retorna a quantidade imagens no array
     */
    @Override
    public int getCount() {
        return imageUrls.length;
    }

    /*
    O método abaixo verifica se há imagens na origem para
    ser exibida, ou seja, verifica se há imagens no
    array e retorna verdadeiro(true) e se não há
    imagens retorna falso(false)
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /*
    Esse método trata do momento que uma imagem será
    exibida.
    O ViewGroup é responsável por conter as imagens
    que foram passada por array, todas elas com suas
    respectivas posições.
    Para exibir uma imagem usamos o parâmetro position
    e, assim temos a exibição de uma imagem no instante
    em que ela é passada.
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        /*
        Para carregar a imagem da api e exibir no viewpager,
         estamos usando a biblioteca(library) chamda
         Picasso.
         Usamos o método get() para carregar(load) as
         imagens(imageUrls) uma por vez com suas
         respectivas posições, pois o Picasso só exibie
         uma imagem por vez.
         O fit()->ajuste: redimensiona a imagem para
         caber na viewpager.
         O centerCrop-> alinha a imagem ao centro.
         O comando into->carrega a imagem

         */
        Picasso.get()
                .load(imageUrls[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        /*
        Retornar o conjunto de imagens no imageView
        para o viewpager
         */
        return imageView;
    }

    /*
    Elimina a imagem do viewpager quando sai da tela
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

