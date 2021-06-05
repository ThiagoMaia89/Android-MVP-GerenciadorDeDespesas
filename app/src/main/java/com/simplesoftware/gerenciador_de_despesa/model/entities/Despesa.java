package com.simplesoftware.gerenciador_de_despesa.model.entities;

import java.io.Serializable;

public class Despesa implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;
    private String local;
    private String data;

    public Despesa(){};

    public Despesa(Integer id, String nome, Double preco, String local, String data) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.local = local;
        this.data = data;
    }

    public Despesa(String nome, Double preco, String local, String data) {
        this.nome = nome;
        this.preco = preco;
        this.local = local;
        this.data = data;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", local='" + local + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
