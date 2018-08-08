package dev.profissional.kosmo.com.br.guiaproprofissional.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.profissional.kosmo.com.br.guiaproprofissional.model.Atendimento;

public class AtendimentoManager {

    private SQLiteDatabase db;

    public AtendimentoManager(SQLiteDatabase db) {
        this.db = db;
    }

    public List<Atendimento> getAllAtendimento(){

        List<Atendimento> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM atendimento", new String[]{});
        ClienteManager clienteManager = new ClienteManager(db);

        while(cursor.moveToNext()){

            Atendimento atendimento = new Atendimento();
            atendimento.setId(cursor.getInt(0));
            atendimento.setDataStr(cursor.getString(1));
            atendimento.setIdCliente(cursor.getInt(2));
            atendimento.setTipoAtendimento(cursor.getString(3));
            atendimento.setDescricaoAtendimento(cursor.getString(4));
            atendimento.setSituacaoAtendimento(cursor.getString(5));

            atendimento.setClienteObj(clienteManager.getClienteById(atendimento.getIdCliente()+""));
            lista.add(atendimento);

        }

        cursor.close();

        return lista;

    }

    public Atendimento getClienteById(String id){

        Cursor cursor = db.rawQuery("SELECT * FROM atendimento WHERE id = ?", new String[]{id});
        Atendimento atendimento = new Atendimento();
        ClienteManager clienteManager = new ClienteManager(db);

        if(cursor.moveToNext()){
            atendimento.setId(cursor.getInt(0));
            atendimento.setDataStr(cursor.getString(1));
            atendimento.setIdCliente(cursor.getInt(2));
            atendimento.setTipoAtendimento(cursor.getString(3));
            atendimento.setDescricaoAtendimento(cursor.getString(4));
            atendimento.setSituacaoAtendimento(cursor.getString(5));
            atendimento.setClienteObj(clienteManager.getClienteById(atendimento.getIdCliente()+""));
        }

        cursor.close();

        return atendimento;

    }

    public boolean insertCliente(Atendimento atendimento){

        ContentValues args = new ContentValues();

        args.put("id", atendimento.getId());
        args.put("dataAtendimento", atendimento.dataExtenso());
        args.put("idCliente", atendimento.getIdCliente());
        args.put("tipoAtendimento", atendimento.getTipoAtendimento());
        args.put("descricaoAtendimento", atendimento.getDescricaoAtendimento());
        args.put("situacaoAtendimento", atendimento.getSituacaoAtendimento());

        long resultado = db.insert("atendimento",null, args);

        if(resultado > 0){
            return true;
        }

        return false;
    }

    public boolean updateCliente(Atendimento atendimento){

        ContentValues args = new ContentValues();

        args.put("id", atendimento.getId());
        args.put("dataAtendimento", atendimento.dataExtenso());
        args.put("idCliente", atendimento.getIdCliente());
        args.put("tipoAtendimento", atendimento.getTipoAtendimento());
        args.put("descricaoAtendimento", atendimento.getDescricaoAtendimento());
        args.put("situacaoAtendimento", atendimento.getSituacaoAtendimento());

        long resultado = db.update("atendimento", args, "id=" + atendimento.getId(), null);

        if(resultado > 0){
            return true;
        }

        return false;
    }
}
