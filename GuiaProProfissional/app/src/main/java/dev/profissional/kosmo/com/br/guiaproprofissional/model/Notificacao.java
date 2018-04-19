package dev.profissional.kosmo.com.br.guiaproprofissional.model;

import java.util.Date;
import java.util.Locale;

/**
 * Created by 0118431 on 19/04/2018.
 */

public class Notificacao {

    private Integer id;
    private String nome;
    private String descricao;
    private Date data;

    private Cliente cliente;

    public Notificacao(Integer id, String nome, String descricao, Date data, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.cliente = cliente;
    }

    public Notificacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String dataExtenso(){
        return this.data != null ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).format(this.data) : "";
    }

}
