package com.example.jdbc.Pedido;

import java.sql.Date;
import java.sql.Time;

public class Pedido {
    private int id;
    private String fk_cliente_cpf;
    private int fk_administrador_id;
    private double precoTotal;
    private Date data_pedido;
    private Time hora;
    private String status;

    //CONSTRUTOR
    public Pedido(int id, int fk_administrador_id, String fk_cliente_cpf, Date data_pedido, String status,Time hora) {
        this.id = id;
        if(fk_cliente_cpf.length()==11) {
            this.fk_cliente_cpf = fk_cliente_cpf;
        }
        this.fk_administrador_id=fk_administrador_id;
        this.data_pedido = data_pedido;
        this.status = status;

    }

    //GETTERS

    public int getId() {
        return id;
    }

    public Time getHora() {
        return hora;
    }

    public int getFk_administrador_id(){return fk_administrador_id;}

    public String getFk_cliente_cpf(){return fk_cliente_cpf;}

    public double getPrecoTotal() {
        return precoTotal;
    }

    public String getStatus() {
        return status;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    //SETTERS
    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }

}
