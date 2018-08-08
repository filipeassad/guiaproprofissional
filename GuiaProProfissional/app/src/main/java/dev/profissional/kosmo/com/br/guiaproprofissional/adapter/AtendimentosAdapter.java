package dev.profissional.kosmo.com.br.guiaproprofissional.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.interfaces.AtendimentoAdapterInterface;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Atendimento;

public class AtendimentosAdapter extends ArrayAdapter<Atendimento> {

    private Context myContext;
    private int myResource;
    private String TIPO_ATENDIMENTO_LIGAR = "Ligar";
    private String TIPO_ATENDIMENTO_CONVERSAR = "Conversar";
    private String COR_LARANJA = "#e9a11c";
    private String COR_VERDE = "#2daa0a";
    private AtendimentoAdapterInterface atendimentoAdapterInterface;

    public AtendimentosAdapter(@NonNull Context context, int resource, @NonNull List<Atendimento> objects, AtendimentoAdapterInterface atendimentoAdapterInterface) {
        super(context, resource, objects);
        myContext = context;
        myResource = resource;
        this.atendimentoAdapterInterface = atendimentoAdapterInterface;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = ((Activity) myContext).getLayoutInflater();
            convertView = inflater.inflate(myResource, parent, false);
        }

        ImageView ivItem = (ImageView) convertView.findViewById(R.id.ivItem);
        TextView tvNomeCliente = (TextView) convertView.findViewById(R.id.tvNomeCliente);
        TextView tvDescricaoAtendimento = (TextView) convertView.findViewById(R.id.tvDescricaoAtendimento);
        LinearLayout llBotaoLateral = (LinearLayout) convertView.findViewById(R.id.llBotaoLateral);
        ImageView ivBotaoLateral = (ImageView) convertView.findViewById(R.id.ivBotaoLateral);
        TextView tvLateral = (TextView) convertView.findViewById(R.id.tvLateral);
        LinearLayout llUrgencia = (LinearLayout) convertView.findViewById(R.id.llUrgencia);
        LinearLayout llContainer = (LinearLayout) convertView.findViewById(R.id.llContainer);

        final Atendimento atendimento = getItem(position);

        ivItem.setImageBitmap(atendimento.getClienteObj().getImg() != null ?
                atendimento.getClienteObj().getImg() :
                BitmapFactory.decodeResource(myContext.getResources(), R.drawable.usuario_laranja));

        tvNomeCliente.setText(atendimento.getClienteObj().getNome());
        tvDescricaoAtendimento.setText(atendimento.getDescricaoAtendimento());

        if(atendimento.getTipoAtendimento().equals(TIPO_ATENDIMENTO_LIGAR)){
            llBotaoLateral.setBackgroundResource(R.drawable.shape_btn_canto_laranja);
            ivBotaoLateral.setImageBitmap(BitmapFactory.decodeResource(myContext.getResources(),
                    R.drawable.smartphone));
            tvLateral.setText(TIPO_ATENDIMENTO_LIGAR);
            tvLateral.setTextColor(Color.parseColor(COR_LARANJA));
        }else{
            llBotaoLateral.setBackgroundResource(R.drawable.shape_btn_canto_verde);
            ivBotaoLateral.setImageBitmap(BitmapFactory.decodeResource(myContext.getResources(),
                    R.drawable.whatsapp));
            tvLateral.setText(TIPO_ATENDIMENTO_CONVERSAR);
            tvLateral.setTextColor(Color.parseColor(COR_VERDE));
        }

        llUrgencia.setVisibility(atendimento.getId() % 2 == 0 ? View.VISIBLE: View.GONE);

        llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atendimentoAdapterInterface.acessarDetalhe(atendimento);
            }
        });

        return convertView;
    }
}
