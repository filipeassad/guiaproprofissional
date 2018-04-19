package dev.profissional.kosmo.com.br.guiaproprofissional.fragments;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Cliente;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Notificacao;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.VariaveisEstaticas;

/**
 * Created by 0118431 on 19/04/2018.
 */

public class NotificacaoFragment extends Fragment {

    private LinearLayout llNotificacao;
    private boolean primeiro = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notificacoes, container, false);

        llNotificacao = (LinearLayout) view.findViewById(R.id.llNotificacao);

        VariaveisEstaticas.getFragmentInterface().visibilidadeMenu(true);

        carregaNotificacoes();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void carregaNotificacoes(){

        List<Notificacao> lista = new ArrayList<>();

        lista.add(new Notificacao(1,"Kratos","Me ligue!", new Date(), new Cliente(1,"Kratos", "kratos@gmail.com","999999999999", BitmapFactory.decodeResource(getContext().getResources(), R.drawable.kratos))));
        lista.add(new Notificacao(1,"Finn","Tentei te ligar!", new Date(), new Cliente(1,"Finn", "finn@gmail.com","999999999999", BitmapFactory.decodeResource(getContext().getResources(), R.drawable.finn))));
        lista.add(new Notificacao(1,"Dath Vader","Te mandei uma mensagem no whats!", new Date(), new Cliente(1,"Dath Vader", "vader@gmail.com","999999999999", BitmapFactory.decodeResource(getContext().getResources(), R.drawable.darthvader))));

        primeiro = true;

        for(Notificacao aux :lista){

            if(primeiro){
                primeiro = false;
            }else{
                LinearLayout linha = new LinearLayout(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(30,0,30,0);
                linha.setLayoutParams(params);
                linha.setBackgroundColor(Color.parseColor("#FF2B2B2B"));
                llNotificacao.addView(linha);
            }

            LinearLayout notificacao = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.adapter_notificacao,null);
            ImageView img = (ImageView) notificacao.findViewById(R.id.ivNotificacao);
            TextView nome = (TextView) notificacao.findViewById(R.id.tvNomeCliente);
            TextView descricao = (TextView) notificacao.findViewById(R.id.tvDescricao);
            TextView data = (TextView) notificacao.findViewById(R.id.tvData);

            img.setImageBitmap(aux.getCliente().getImg());
            nome.setText(aux.getNome());
            descricao.setText(aux.getDescricao());
            data.setText(aux.dataExtenso());

            llNotificacao.addView(notificacao);
        }


    }

}
