package com.example.jdbc.Administrador;

public class Administracao {
    private String usuario;
    private String email;
    private String senha;
    private int id;

    //CONSTRUTOR
    public Administracao(String usuario, String email, String senha, int id) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.id = id;
    }

    //GETTERS

    public String getUsuario() {
        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }


    //SETTERS


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //TOSTRING

    @Override
    public String toString() {
        return "Adiministrador.Adiministracao{" +
                "usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", id=" + id +
                '}';
    }
}
