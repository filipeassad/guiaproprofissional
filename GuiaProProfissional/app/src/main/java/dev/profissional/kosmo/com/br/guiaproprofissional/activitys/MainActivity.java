package dev.profissional.kosmo.com.br.guiaproprofissional.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import dev.profissional.kosmo.com.br.guiaproprofissional.R;
import dev.profissional.kosmo.com.br.guiaproprofissional.dao.AutenticacaoManager;
import dev.profissional.kosmo.com.br.guiaproprofissional.dao.DataBaseHelper;
import dev.profissional.kosmo.com.br.guiaproprofissional.dialogs.InformacaoDialog;
import dev.profissional.kosmo.com.br.guiaproprofissional.interfaces.PostLoginInterface;
import dev.profissional.kosmo.com.br.guiaproprofissional.model.Autenticacao;
import dev.profissional.kosmo.com.br.guiaproprofissional.task.PostLoginAsyncTask;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.FerramentasBasicas;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.PushNotificationService;
import dev.profissional.kosmo.com.br.guiaproprofissional.utils.VariaveisEstaticas;

public class MainActivity extends Activity implements PostLoginInterface{

    private Button btnEntrar;
    private TextView tvCadastre;
    private TextView tvSenha;
    private EditText edtLogin;
    private EditText edtSenha;
    private Autenticacao autenticacao;
    private DataBaseHelper dataBaseHelper;
    private AutenticacaoManager autenticacaoManager;
    private PostLoginInterface postLoginInterface = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        tvCadastre = (TextView) findViewById(R.id.tvCadastre);
        tvSenha = (TextView) findViewById(R.id.tvSenha);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        dataBaseHelper = new DataBaseHelper(this);
        autenticacaoManager = new AutenticacaoManager(dataBaseHelper.getWritableDatabase());

        verificaTolken();

        Intent serviceIntent = new Intent(this,PushNotificationService.class);
        this.startService(serviceIntent);

        acoes();

    }

    private void acoes(){

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtLogin.getText().toString().trim().equals("")){
                    edtLogin.setError("Digite o login.");
                    return;
                }

                if(edtSenha.getText().toString().trim().equals("")){
                    edtSenha.setError("Digite a senha.");
                    return;
                }

                if(!FerramentasBasicas.isOnline(view.getContext())){
                    InformacaoDialog informacaoDialog = new InformacaoDialog(view.getContext());
                    informacaoDialog.gerarDialog("Sem acesso a internet!\n Tente novamente.");
                    return;
                }

                autenticacao = new Autenticacao();
                autenticacao.setUsuario(edtLogin.getText().toString());
                autenticacao.setSenha(edtSenha.getText().toString());

                PostLoginAsyncTask postLoginAsyncTask = new PostLoginAsyncTask(view.getContext(), autenticacao, postLoginInterface);
                postLoginAsyncTask.execute(FerramentasBasicas.getURL() + "login");

            }
        });

        tvCadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), CadastroActivity.class);
                //startActivity(intent);
            }
        });

        tvSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), SenhaActivity.class);
                //startActivity(intent);
            }
        });

    }

    private void verificaTolken(){

        List<Autenticacao> lista = autenticacaoManager.getAutenticacaoAtivo();
        if(!lista.isEmpty()){
            VariaveisEstaticas.setAutenticacao(lista.get(0));
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void postLogin(HashMap<String, String> hash) {

        if(hash.get("erro") != null){
            InformacaoDialog informacaoDialog = new InformacaoDialog(this);
            informacaoDialog.gerarDialog("Não Foi possível logar!\n Tente novamente.");
        }else{
            List<Autenticacao> lista = autenticacaoManager.getAutenticacaobyUsuario(autenticacao.getUsuario());

            if(lista.isEmpty()){
                autenticacao.setHash(hash.get("token"));
                autenticacao.setAtivo(true);
                autenticacaoManager.insertAutenticacao(autenticacao);
            }else{
                autenticacao = lista.get(0);
                autenticacao.setHash(hash.get("token"));
                autenticacao.setAtivo(true);
                autenticacaoManager.updateAutenticacao(autenticacao);
            }

            VariaveisEstaticas.setAutenticacao(autenticacaoManager.getAutenticacaoAtivo().get(0));

            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
