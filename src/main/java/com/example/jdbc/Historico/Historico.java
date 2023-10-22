package com.example.jdbc.Historico;

public class Historico {
    private String descricao;
    private int id;
    private int fk_Administrador_id;

    //CONSTRUTOR
    public Historico(String descricao, int id, int fk_Adiministrador_id) {
        this.descricao = descricao;
        this.id = id;
        this.fk_Administrador_id = fk_Adiministrador_id;
    }

    //GETTERS
    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public int getFk_Administrador_id() {
        return fk_Administrador_id;
    }

    //SETTERS
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Alteracao{" +
                "descricao='" + descricao + '\'' +
                ", id=" + id +
                ", fk_Administrador_id=" + fk_Administrador_id +
                '}';
    }
}
