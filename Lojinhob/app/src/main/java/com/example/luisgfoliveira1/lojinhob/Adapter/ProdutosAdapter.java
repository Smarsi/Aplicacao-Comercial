package com.example.luisgfoliveira1.lojinhob.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.luisgfoliveira1.lojinhob.models.Produto;
import com.example.luisgfoliveira1.lojinhob.R;
import com.example.luisgfoliveira1.lojinhob.models.ListaProduto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdutosAdapter extends ArrayAdapter<Produto> {

    List<Produto> produtosList;
    Context contexto;
    private LayoutInflater mInflater;




    public ProdutosAdapter(Context contexto, List<Produto> objetos){
        super(contexto, 0,objetos);
        this.contexto = contexto;
        this.mInflater = LayoutInflater.from(contexto);
        produtosList = objetos;
    }

    @Override
    public Produto getItem(int posicao){
        return  produtosList.get(posicao);


    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent){

        final ViewHolder vh;

        /*
        Verificar se o layout com convertView está com dados ou não.
        Caso não esteja ele será inflado(preenchido) com dados que virão do json
         */
        if(convertView == null){
            View view = mInflater.inflate(R.layout.layout_row_view,parent,false);
            /*
            O método create foi chamada para construir e preencher os dados no controles de
            xml da tela layout_row_view.
             */
            vh = ViewHolder.create((RelativeLayout)view);
            view.setTag(vh);
        }
        else{
            vh = (ViewHolder)convertView.getTag();
        }

        /*
        Quando um produto for tocado, retornará os dados daquela posição específica com os
        seus respectivos dados.
         */
        Produto item = getItem(posicao);
        vh.textViewNome.setText(item.getNomeProduto());
        vh.textViewPreco.setText(String.valueOf(item.getPreco()));


        /*
        Usamos a biblioteca Picasso para exibir as imagens na lista de produtos.
        Foi passo os seguinte itens: Caso o produto não possua imagem, então ser substituido por
        uma imagem padrão com o comando placeholder. Caso a imagem apresente algum error para
        ser carregada será exibida uma imagem de erro com o comando error. Neste caso usamos
        a mesma imagem.
        Caso a imagem exista e não apresente nenhum problema ela será exibida com o comando
        into
         */



        Picasso.get().load(item.getImg1())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(vh.imageView);
        /*Picasso.get().load(item.getImg1())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(vh.imageView);*/
        return vh.rootView;
    }

    private static class ViewHolder{

        private final RelativeLayout rootView;
        private final ImageView imageView;
        private final TextView textViewNome;
        private final TextView textViewPreco;



        public ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewNome, TextView textViewPreco) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewNome = textViewNome;
            this.textViewPreco = textViewPreco;


        }
        public static ViewHolder create(RelativeLayout rootView){
            /*
            O método create é responsável por vincular os dados dos controles
            xml de exibição das imagens com imageView e textos com ViewNome e ViewPreco.
            os dados que preenchem estes campos são oriundos da api json
             */
            ImageView imageView = rootView.findViewById(R.id.imageView);
            TextView textViewNome = rootView.findViewById(R.id.textViewNome);
            TextView textViewPreco = rootView.findViewById(R.id.textViewPreco);
            return new ViewHolder(rootView,imageView,textViewNome,textViewPreco);
        }
    }
}
