package com.example.jdbc.Item;

public class Item {
    private int quantidade;
    private double preco;
    private int fk_produto_id;
    private int fk_pedido_id;
    private int id;

    //CONSTRUTOR
    public Item(int quantidade, double preco, int fk_produto_id, int fk_pedido_id, int id) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.fk_produto_id = fk_produto_id;
        this.fk_pedido_id = fk_pedido_id;
        this.id = id;
    }

    //GETTERS

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public int getFk_produto_id() {
        return fk_produto_id;
    }

    public int getFk_pedido_id() {
        return fk_pedido_id;
    }

    public int getId() {
        return id;
    }


    //SETTERS

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setFk_produto_id(int fk_produto_id) {
        this.fk_produto_id = fk_produto_id;
    }

    public void setFk_pedido_id(int fk_pedido_id) {
        this.fk_pedido_id = fk_pedido_id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //TOSTRING

    public String toString() {
        return "Item{" +
                "quantidade=" + quantidade +
                ", preco=" + preco +
                ", fk_produto_id=" + fk_produto_id +
                ", fk_pedido_id=" + fk_pedido_id +
                ", id=" + id +
                '}';
    }
}
