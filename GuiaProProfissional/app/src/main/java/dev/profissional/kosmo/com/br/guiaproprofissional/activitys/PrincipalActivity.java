package dev.profissional.kosmo.com.br.guiaproprofissional.activitys;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.adapter.MenuNavAdapter;
import dev.profissional.kosmo.com.br.guiaproprofissional.fragments.TelaInicialFragment;
import dev.profissional.kosmo.com.br.guiaproprofissional.interfaces.FragmentInterface;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.ItemMenuNav;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.Animacao;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.GerenciadorFragment;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.VariaveisEstaticas;

/**
 * Created by 0118431 on 08/03/2018.
 */

public class PrincipalActivity extends FragmentActivity implements FragmentInterface {

    private Bundle savedInstanceState;
    private FragmentManager fm = getSupportFragmentManager();

    private LinearLayout llNavDraw;
    private LinearLayout llBgCinza;
    private ImageView ivMenu;
    private ImageView ivNotificacao;

    private LinearLayout btnAberto;
    private LinearLayout btnFechado;

    private GerenciadorFragment gerenciadorFragment  = new GerenciadorFragment();
    private Animacao animacao = new Animacao();
    private boolean statusProfissional = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        llNavDraw = (LinearLayout) findViewById(R.id.llNavDraw);
        llBgCinza = (LinearLayout) findViewById(R.id.llBgCinza);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        ivNotificacao = (ImageView) findViewById(R.id.ivNotificacao);
        btnAberto = (LinearLayout) findViewById(R.id.btnAberto);
        btnFechado = (LinearLayout) findViewById(R.id.btnFechado);

        VariaveisEstaticas.setFragmentInterface(this);

        insertFirstFragment();
        carregaNav();

        acoes();
    }

    private void carregaNav(){

        View nav_layout = getLayoutInflater().inflate(R.layout.nav_menu, llNavDraw);
        ListView lvNav = (ListView) nav_layout.findViewById(R.id.lvNav);

        List<ItemMenuNav> lista = new ArrayList<>();
        lista.add(new ItemMenuNav("Informações Pessoais", BitmapFactory.decodeResource(this.getResources(), R.drawable.manuser)));
        lista.add(new ItemMenuNav("Notificações", BitmapFactory.decodeResource(this.getResources(), R.drawable.notification)));
        lista.add(new ItemMenuNav("Relate um Problema", BitmapFactory.decodeResource(this.getResources(), R.drawable.attention)));
        lista.add(new ItemMenuNav("Ajuda", BitmapFactory.decodeResource(this.getResources(), R.drawable.help)));
        lista.add(new ItemMenuNav("Sair", BitmapFactory.decodeResource(this.getResources(), R.drawable.sair)));

        MenuNavAdapter menuNavAdapter = new MenuNavAdapter(this, R.layout.adapter_menu_nav, lista);

        lvNav.setAdapter(menuNavAdapter);

        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(((ItemMenuNav)adapterView.getItemAtPosition(i)).getRotulo().equals("Informações Pessoais")){
                    mudaTela("Perfil");
                }else if (((ItemMenuNav)adapterView.getItemAtPosition(i)).getRotulo().equals("Telainicial")){
                    mudaTela("Telainicial");
                }
            }
        });

    }

    private void insertFirstFragment(){

        if(savedInstanceState == null){
            TelaInicialFragment telaInicialFragment = new TelaInicialFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.contFragments, telaInicialFragment, "Notificações");
            ft.commit();
        }

    }

    private void acoes(){

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llNavDraw.getVisibility() == View.VISIBLE){
                    animacao.animaSaida(llNavDraw);
                    animacao.fadeOutAnimation(llBgCinza);
                }else{
                    llNavDraw.setVisibility(View.VISIBLE);
                    llBgCinza.setVisibility(View.VISIBLE);
                    animacao.animaEntrada(llNavDraw);
                    animacao.fadeInAnimatio(llBgCinza);
                }
            }
        });

        llBgCinza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llNavDraw.getVisibility() == View.VISIBLE){
                    animacao.animaSaida(llNavDraw);
                    animacao.fadeOutAnimation(llBgCinza);
                }else{
                    llNavDraw.setVisibility(View.VISIBLE);
                    llBgCinza.setVisibility(View.VISIBLE);
                    animacao.animaEntrada(llNavDraw);
                    animacao.fadeInAnimatio(llBgCinza);
                }
            }
        });

        btnFechado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statusProfissional){
                    statusProfissional = false;
                    btnAberto.setBackgroundResource(R.drawable.shape_btn_status_cinza);
                    btnFechado.setBackgroundResource(R.drawable.shape_btn_status_vermelho);
                }
            }
        });

        btnAberto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!statusProfissional){
                    statusProfissional = true;
                    btnAberto.setBackgroundResource(R.drawable.shape_btn_status_verde);
                    btnFechado.setBackgroundResource(R.drawable.shape_btn_status_cinza);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(llNavDraw.getVisibility() == View.VISIBLE){
            animacao.animaSaida(llNavDraw);
            if(llBgCinza.getVisibility() == View.VISIBLE)
                animacao.fadeOutAnimation(llBgCinza);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void mudaTela(String nome) {
        gerenciadorFragment.mudarTela(fm,llNavDraw,nome,llBgCinza);
    }

    @Override
    public void voltar() {
        onBackPressed();
    }

    @Override
    public void visibilidadeMenu(boolean visivel) {

    }

    @Override
    public void mudaActivity(Intent intent) {
        startActivity(intent);
    }

}
