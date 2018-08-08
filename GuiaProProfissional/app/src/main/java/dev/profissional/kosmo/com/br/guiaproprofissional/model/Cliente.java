package dev.profissional.kosmo.com.br.guiaproprofissional.model;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by 0118431 on 19/04/2018.
 */

public class Cliente {

    private Integer id;
    private String nome;
    private String email;
    private String celular;
    private Bitmap img;

    public Cliente(Integer id, String nome, String email, String celular, Bitmap img) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.img = img;
    }

    public Cliente() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public byte[] imgBlob(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }
}
