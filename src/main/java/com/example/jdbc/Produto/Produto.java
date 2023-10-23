package com.example.jdbc.Produto;

import java.sql.Date;

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;
    private int id;
    private int fk_Adiministrador_id;
    private String imagem;
    private Date data_validade;
    private String descricao;

    //CONSTRUTOR
    public Produto(String nome, int fk_Adiministrador_id, String imagem, int quantidade, double preco, Date data_validade, String descricao) {
        this.nome = nome;
        this.imagem = imagem;
        this.fk_Adiministrador_id = fk_Adiministrador_id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.data_validade=data_validade;
        this.descricao = descricao;
    }

    //GETTERS
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

    public int getId() {
        return id;
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
