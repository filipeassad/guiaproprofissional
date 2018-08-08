package dev.profissional.kosmo.com.br.guiaproprofissional.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Atendimento {

    private Integer id;
    private Date dataAtendimento;
    private Integer idCliente;
    private String tipoAtendimento;
    private String descricaoAtendimento;
    private String situacaoAtendimento;

    private Cliente clienteObj;

    public Atendimento() {
    }

    public Atendimento(Integer id, Date dataAtendimento, Integer idCliente, String tipoAtendimento, String descricaoAtendimento, String situacaoAtendimento, Cliente clienteObj) {
        this.id = id;
        this.dataAtendimento = dataAtendimento;
        this.idCliente = idCliente;
        this.tipoAtendimento = tipoAtendimento;
        this.descricaoAtendimento = descricaoAtendimento;
        this.situacaoAtendimento = situacaoAtendimento;
        this.clienteObj = clienteObj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public String getDescricaoAtendimento() {
        return descricaoAtendimento;
    }

    public void setDescricaoAtendimento(String descricaoAtendimento) {
        this.descricaoAtendimento = descricaoAtendimento;
    }

    public String getSituacaoAtendimento() {
        return this.situacaoAtendimento;
    }

    public void setSituacaoAtendimento(String situacaoAtendimento) {
        this.situacaoAtendimento = situacaoAtendimento;
    }

    public Cliente getClienteObj() {
        return clienteObj;
    }

    public void setClienteObj(Cliente clienteObj) {
        this.clienteObj = clienteObj;
    }

    public String dataExtenso(){
        return this.dataAtendimento != null ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).format(this.dataAtendimento) : "";
    }

    public void setDataStr(String valor){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dataAtendimento = new Date(simpleDateFormat.parse(valor).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
