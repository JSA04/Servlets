package com.example.jdbc.Produto;

import java.sql.Date;

public class Produto {
    private String nome;
    private double preco;
    private String descricao;
    private Date data_validade;
    private int id;
    private int quantidade;
    private int fk_Adiministrador_id;
    private String imagem;
    private String categoria;

    //CONSTRUTOR
    public Produto(String nome, double preco, String descricao, Date data_validade, int id, int quantidade, String imagem, int fk_Adiministrador_id, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.data_validade = data_validade;
        this.id = id;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.fk_Adiministrador_id = fk_Adiministrador_id;
        this.categoria = categoria;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getFk_Adiministrador_id() {
        return fk_Adiministrador_id;
    }

    public String getImagem() {
        return imagem;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public String getDescricao() {
        return descricao;
    }

    //SETTERS

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
