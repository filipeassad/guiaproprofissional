package dev.profissional.kosmo.com.br.guiaproprofissional.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NANEM = "guiaproprofidb";
    private static final int VERSAO = 1;
    private final Context myContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NANEM, null, VERSAO);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tabela cliente
        db.execSQL("CREATE TABLE cliente(" +
                "id INTEGER PRIMARY KEY ," +
                "nome TEXT," +
                "email TEXT," +
                "celular TEXT," +
                "img BLOB" +
                ");");

        //Tabela atendimento
        db.execSQL("CREATE TABLE atendimento(" +
                "id INTEGER PRIMARY KEY ," +
                "dataAtendimento TEXT," +
                "idCliente INTEGER," +
                "tipoAtendimento TEXT," +
                "descricaoAtendimento TEXT," +
                "situacaoAtendimento TEXT" +
                ");");

        db.execSQL("CREATE TABLE autenticacao (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hash TEXT, " +
                "usuario TEXT, " +
                "ativo TEXT," +
                "userid INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
