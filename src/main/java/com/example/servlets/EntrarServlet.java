package com.example.servlets;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.jdbc.Administrador.AdmConexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "entrar", value = "/entrar")
public class EntrarServlet extends HttpServlet {

    //solicitar que o servidor web aceite os dados anexados no corpo da mensagem de requisição para armazenamento.
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {

        if (verificaAutenticacao(request)) {
            request.getRequestDispatcher("/dashboard").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("classMsg", "erro_msg");

        boolean encontrado = false, senha_confere = false;
        String id = null;
        AdmConexao admConexao = new AdmConexao();
        String user = request.getParameter("user");
        if (user == null) user = (String) request.getAttribute("user");
        String senha = request.getParameter("senha");
        if (senha == null) senha = (String) request.getAttribute("senha");

        //VERIFICA SE TEM ALGO NO BANCO
        try {
            ResultSet adms = admConexao.buscar();
            //retornar false se o cursor não estiver antes do primeiro registro ou se não tiver linhas no
            //ResultSet, ou true caso contrário.
            //WHILE QUE VAI PASSAR DE LINHA EM LINHA NO BANCO VENDO SE A SENHA, EMAIL OU USER EXISTEM NO BANCO
            while (adms.next()) {
                //VERIFICA SE USUARIO OU EMAIL EXISTEM NO BANCO
                if (adms.getString("usuario").equals(user) || adms.getString("email").equals(user)) {
                    encontrado = true;
                    if (adms.getString("senha").equals(senha)) {
                        id = adms.getString("cpf");
                        senha_confere = true;
                        break;
                    }
                }
            }

        } catch(SQLException sqle){
            sqle.printStackTrace();
        }

        if (encontrado && senha_confere){
            autenticar(request, user, id);
            request.getRequestDispatcher("/dashboard").forward(request, response);
        } else if (encontrado) {
            session.setAttribute("msg", "Senha incorreta! ");
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
        } else {
            session.setAttribute("msg", "Usuário não encontrado! ");
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
        }
    }
    protected void autenticar(HttpServletRequest request, String user, String id) {
        HttpSession session = request.getSession();

        session.setAttribute("usuario", user);
        session.setAttribute("idUsuario", id);
    }

    //verifica se o usuario existe
    public static boolean verificaAutenticacao(HttpServletRequest request) {

        //cria uma sessão se um já não existe para este usuário
        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") == null) {
            session.setAttribute("classMsg", "erro_msg");
            session.setAttribute("msg", "Sua sessão expirou! ");
            return false;
        } else return true;

    }
}