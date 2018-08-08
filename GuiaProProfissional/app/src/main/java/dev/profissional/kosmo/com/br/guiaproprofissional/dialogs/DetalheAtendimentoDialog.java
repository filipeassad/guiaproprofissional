package dev.profissional.kosmo.com.br.guiaproprofissional.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Atendimento;

public class DetalheAtendimentoDialog {

    private Context context;

    private String TIPO_ATENDIMENTO_LIGAR = "Ligar";
    private String TIPO_ATENDIMENTO_CONVERSAR = "Conversar";
    private String COR_LARANJA = "#e9a11c";
    private String COR_VERDE = "#2daa0a";

    public DetalheAtendimentoDialog(Context context) {
        this.context = context;
    }

    public Dialog gerarDialog(Atendimento atendimento){

        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detalhe_atendimento);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView ivItem = (ImageView) dialog.findViewById(R.id.ivItem);
        TextView tvNomeCliente = (TextView) dialog.findViewById(R.id.tvNomeCliente);
        TextView tvDescricaoAtendimento = (TextView) dialog.findViewById(R.id.tvDescricaoAtendimento);
        LinearLayout llBotaoLateral = (LinearLayout) dialog.findViewById(R.id.llBotaoLateral);
        ImageView ivBotaoLateral = (ImageView) dialog.findViewById(R.id.ivBotaoLateral);
        TextView tvLateral = (TextView) dialog.findViewById(R.id.tvLateral);
        LinearLayout llUrgencia = (LinearLayout) dialog.findViewById(R.id.llUrgencia);
        TextView tvTipoContato = (TextView) dialog.findViewById(R.id.tvTipoContato);
        LinearLayout btnLigar = (LinearLayout) dialog.findViewById(R.id.btnLigar);
        LinearLayout btnWhats = (LinearLayout) dialog.findViewById(R.id.btnWhats);

        ivItem.setImageBitmap(atendimento.getClienteObj().getImg() != null ?
                atendimento.getClienteObj().getImg() :
                BitmapFactory.decodeResource(context.getResources(), R.drawable.usuario_laranja));

        tvNomeCliente.setText(atendimento.getClienteObj().getNome());
        tvDescricaoAtendimento.setText(atendimento.getDescricaoAtendimento());

        if(atendimento.getTipoAtendimento().equals(TIPO_ATENDIMENTO_LIGAR)){
            llBotaoLateral.setBackgroundResource(R.drawable.shape_btn_canto_laranja);
            ivBotaoLateral.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.smartphone));
            tvLateral.setText(TIPO_ATENDIMENTO_LIGAR);
            tvLateral.setTextColor(Color.parseColor(COR_LARANJA));
        }else{
            llBotaoLateral.setBackgroundResource(R.drawable.shape_btn_canto_verde);
            ivBotaoLateral.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.whatsapp));
            tvLateral.setText(TIPO_ATENDIMENTO_CONVERSAR);
            tvLateral.setTextColor(Color.parseColor(COR_VERDE));
        }

        llBotaoLateral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        llUrgencia.setVisibility(atendimento.getId() % 2 == 0 ? View.VISIBLE: View.GONE);

        dialog.show();

        return dialog;
    }
}
