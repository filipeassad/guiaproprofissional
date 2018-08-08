package dev.profissional.kosmo.com.br.guiaproprofissional.utils;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Atendimento;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Cliente;

public class PopulaAtendimentos {

    private Context context;

    public PopulaAtendimentos(Context context) {
        this.context = context;
    }

    public List<Atendimento> criaAtendimentosNaoAtendidos(){

        List<Atendimento> listaAtendimento = new ArrayList<>();

        List<Cliente> clientes = criarListaClientes();

        int countId = 1;

        for(Cliente aux : clientes){

            Atendimento atendimento = new Atendimento(countId,
                    new Date(),
                    aux.getId(),
                    countId%2 == 0 ? "Ligar":"Whatsapp",
                    countId%2 == 0 ? "Preciso contruir uma casa.":"Preciso arrumar a minha banheira.",
                    "Não Atendido",
                    aux );

            listaAtendimento.add(atendimento);

            countId++;

        }

        return listaAtendimento;

    }

    public List<Cliente> criarListaClientes(){

        List<Cliente> listaCliente = new ArrayList<>();

        listaCliente.add(new Cliente(1, "Filipe Assad Baraúna", "filipeassad@gmail.com", "9999999999",
                BitmapFactory.decodeResource(this.context.getResources(), R.drawable.finn)));

        listaCliente.add(new Cliente(2, "Diego Henrique Franco", "diego@gmail.com", "9999999999",
                BitmapFactory.decodeResource(this.context.getResources(), R.drawable.darthvader)));

        listaCliente.add(new Cliente(3, "Wagner", "wagner@gmail.com", "9999999999",
                BitmapFactory.decodeResource(this.context.getResources(), R.drawable.agentsmith)));

        listaCliente.add(new Cliente(4, "Anthony", "anthony@gmail.com", "9999999999",
                BitmapFactory.decodeResource(this.context.getResources(), R.drawable.kratos)));

        return listaCliente;
    }
}
