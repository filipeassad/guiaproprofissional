package dev.profissional.kosmo.com.br.guiaproprofissional.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.fragments.DetalheAtendimentoFragment;
import dev.profissional.kosmo.com.br.guiaproprofissional.fragments.TelaInicialFragment;


/**
 * Created by 0118431 on 08/03/2018.
 */

public class GerenciadorFragment {

    private Animacao animacao = new Animacao();

    public void mudarTela(FragmentManager fm, LinearLayout llNavDraw, String nomeTela, LinearLayout llBgCinza){

        FragmentTransaction ft = fm.beginTransaction();

        if(nomeTela.equals("Telainicial")){
            TelaInicialFragment telaInicialFragment = new TelaInicialFragment();
            ft.replace(R.id.contFragments, telaInicialFragment, nomeTela);
        }else if(nomeTela.equals("DetalheAtendimento")){
            DetalheAtendimentoFragment detalheAtendimentoFragment = new DetalheAtendimentoFragment();
            ft.replace(R.id.contFragments, detalheAtendimentoFragment, nomeTela);
        }else{
            return;
        }

        if(fm.findFragmentByTag(nomeTela) != null){
            fm.popBackStack(nomeTela,1);
        }

        ft.addToBackStack(nomeTela);
        ft.commit();
        animacao.animaSaida(llNavDraw);

        if(llBgCinza.getVisibility() == View.VISIBLE)
            animacao.fadeOutAnimation(llBgCinza);

    }

}
