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

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Atendimento;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.VariaveisEstaticas;

public class DetalheAtendimentoFragment extends Fragment {

    private ImageView ivItem;
    private TextView tvNomeCliente;
    private TextView tvDescricaoAtendimento;
    private LinearLayout llBotaoLateral;
    private ImageView ivBotaoLateral;
    private TextView tvLateral;
    private LinearLayout llUrgencia;
    private TextView tvTipoContato;
    private LinearLayout btnLigar;
    private LinearLayout btnWhats;

    private String TIPO_ATENDIMENTO_LIGAR = "Ligar";
    private String TIPO_ATENDIMENTO_CONVERSAR = "Conversar";
    private String COR_LARANJA = "#e9a11c";
    private String COR_VERDE = "#2daa0a";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalhe_atendimento, container, false);

        ivItem = (ImageView) view.findViewById(R.id.ivItem);
        tvNomeCliente = (TextView) view.findViewById(R.id.tvNomeCliente);
        tvDescricaoAtendimento = (TextView) view.findViewById(R.id.tvDescricaoAtendimento);
        llBotaoLateral = (LinearLayout) view.findViewById(R.id.llBotaoLateral);
        ivBotaoLateral = (ImageView) view.findViewById(R.id.ivBotaoLateral);
        tvLateral = (TextView) view.findViewById(R.id.tvLateral);
        llUrgencia = (LinearLayout) view.findViewById(R.id.llUrgencia);
        tvTipoContato = (TextView) view.findViewById(R.id.tvTipoContato);
        btnLigar = (LinearLayout) view.findViewById(R.id.btnLigar);
        btnWhats = (LinearLayout) view.findViewById(R.id.btnWhats);

        carregaDadosAtendimento();

        return view;
    }

    private void carregaDadosAtendimento(){

        Atendimento atendimento = VariaveisEstaticas.getAtendimento();

        if(atendimento != null){
            ivItem.setImageBitmap(atendimento.getClienteObj().getImg() != null ?
                    atendimento.getClienteObj().getImg() :
                    BitmapFactory.decodeResource(getContext().getResources(), R.drawable.usuario_laranja));

            tvNomeCliente.setText(atendimento.getClienteObj().getNome());
            tvDescricaoAtendimento.setText(atendimento.getDescricaoAtendimento());

            if(atendimento.getTipoAtendimento().equals(TIPO_ATENDIMENTO_LIGAR)){
                llBotaoLateral.setBackgroundResource(R.drawable.shape_btn_canto_laranja);
                ivBotaoLateral.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.smartphone));
                tvLateral.setText(TIPO_ATENDIMENTO_LIGAR);
                tvLateral.setTextColor(Color.parseColor(COR_LARANJA));
            }else{
                llBotaoLateral.setBackgroundResource(R.drawable.shape_btn_canto_verde);
                ivBotaoLateral.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.whatsapp));
                tvLateral.setText(TIPO_ATENDIMENTO_CONVERSAR);
                tvLateral.setTextColor(Color.parseColor(COR_VERDE));
            }

            llBotaoLateral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            llUrgencia.setVisibility(atendimento.getId() % 2 == 0 ? View.VISIBLE: View.GONE);

        }else{
            VariaveisEstaticas.getFragmentInterface().voltar();
        }


    }

}
