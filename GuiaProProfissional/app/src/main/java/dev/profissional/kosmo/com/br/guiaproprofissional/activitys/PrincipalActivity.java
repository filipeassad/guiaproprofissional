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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.adapter.MenuNavAdapter;
import dev.profissional.kosmo.com.br.guiaproprofissional.fragments.NotificacaoFragment;
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
    private LinearLayout llFooterMenu;

    private LinearLayout llNotificacao;
    private ImageView ivNotificacao;
    private TextView tvNotificacao;

    private LinearLayout llTopRanked;
    private ImageView ivTopRanked;
    private TextView tvTopRanked;

    private LinearLayout llOfertas;
    private ImageView ivOfertas;
    private TextView tvOfertas;

    private LinearLayout llHistorico;
    private ImageView ivHistorico;
    private TextView tvHistorico;


    private GerenciadorFragment gerenciadorFragment  = new GerenciadorFragment();
    private Animacao animacao = new Animacao();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        llNavDraw = (LinearLayout) findViewById(R.id.llNavDraw);
        llBgCinza = (LinearLayout) findViewById(R.id.llBgCinza);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        llFooterMenu = (LinearLayout) findViewById(R.id.llFooterMenu);

        llNotificacao = (LinearLayout) findViewById(R.id.llNotificacao);
        ivNotificacao = (ImageView) findViewById(R.id.ivNotificacao);
        tvNotificacao = (TextView) findViewById(R.id.tvNotificacao);

        llTopRanked = (LinearLayout) findViewById(R.id.llTopRanked);
        ivTopRanked = (ImageView) findViewById(R.id.ivTopRanked);
        tvTopRanked = (TextView) findViewById(R.id.tvTopRanked);

        llOfertas = (LinearLayout) findViewById(R.id.llOfertas);
        ivOfertas = (ImageView) findViewById(R.id.ivOfertas);
        tvOfertas = (TextView) findViewById(R.id.tvOfertas);

        llHistorico = (LinearLayout) findViewById(R.id.llHistorico);
        ivHistorico = (ImageView) findViewById(R.id.ivHistorico);
        tvHistorico = (TextView) findViewById(R.id.tvHistorico);

        llNotificacao.setTag("Notificações");
        llTopRanked.setTag("TopRanked");
        llOfertas.setTag("Ofertas");
        llHistorico.setTag("Historico");

        llNotificacao.setOnClickListener(menuClickListener);
        llTopRanked.setOnClickListener(menuClickListener);
        llOfertas.setOnClickListener(menuClickListener);
        llHistorico.setOnClickListener(menuClickListener);

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
                }else if (((ItemMenuNav)adapterView.getItemAtPosition(i)).getRotulo().equals("Notificações")){
                    mudaTela("Notificacao");
                }
            }
        });

    }

    private void insertFirstFragment(){

        if(savedInstanceState == null){
            NotificacaoFragment notificacaoFragment = new NotificacaoFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.contFragments, notificacaoFragment, "Notificações");
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
        if(visivel){
            llFooterMenu.setVisibility(View.VISIBLE);
        }else{
            llFooterMenu.setVisibility(View.GONE);
        }
    }

    @Override
    public void mudaActivity(Intent intent) {
        startActivity(intent);
    }

    private View.OnClickListener menuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            limpaMenu();

            if(view.getTag().equals("Notificações")){
                ivNotificacao.setImageBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.notification));
                tvNotificacao.setTextColor(Color.parseColor("#e9a11c"));
                mudaTela("Notificações");
            }else if(view.getTag().equals("TopRanked")){
                ivTopRanked.setImageBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.star));
                tvTopRanked.setTextColor(Color.parseColor("#e9a11c"));
                mudaTela("TopRanked");
            }else if(view.getTag().equals("Ofertas")){
                ivOfertas.setImageBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.tags));
                tvOfertas.setTextColor(Color.parseColor("#e9a11c"));
                mudaTela("Ofertas");
            }else if(view.getTag().equals("Historico")){
                ivHistorico.setImageBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.list));
                tvHistorico.setTextColor(Color.parseColor("#e9a11c"));
                mudaTela("Historico");
            }

        }
    };

    private void limpaMenu(){

        ivNotificacao.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.notificationcinza));
        tvNotificacao.setTextColor(Color.parseColor("#868686"));

        ivTopRanked.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.starcinza));
        tvTopRanked.setTextColor(Color.parseColor("#868686"));

        ivOfertas.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.tagscinza));
        tvOfertas.setTextColor(Color.parseColor("#868686"));

        ivHistorico.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.listcinza));
        tvHistorico.setTextColor(Color.parseColor("#868686"));

    }


}
