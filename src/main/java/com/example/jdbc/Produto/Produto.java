package com.example.jdbc.Produto;

import java.sql.Date;

public class Produto {
    private String nome;
    private double preco;
    private String descricao;
    private Date data_validade;
    private int id;
    private int quantidade;
    private String fk_administrador_cpf;
    private String imagem;
    private String categoria;

    //CONSTRUTOR
    public Produto(String nome, double preco, String descricao, Date data_validade, int id, int quantidade, String imagem, String fk_administrador_cpf, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.data_validade = data_validade;
        this.id = id;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.fk_administrador_cpf = fk_administrador_cpf;
        this.categoria = categoria;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String fk_administrador_cpf() {
        return fk_administrador_cpf;
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
    public String getCategoria() { return categoria; }

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
