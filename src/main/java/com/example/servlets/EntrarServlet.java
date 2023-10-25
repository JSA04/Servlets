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
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        boolean encontrado = false;
        int id = 0;
        AdmConexao admConexao = new AdmConexao();
        String user = request.getParameter("user");
        String senha = request.getParameter("senha");

        //VERIFICA SE TEM ALGO NO BANCO
        try {
            ResultSet adms = admConexao.buscar();
            //retornar false se o cursor não estiver antes do primeiro registro ou se não tiver linhas no
            //ResultSet, ou true caso contrário.
            //WHILE QUE VAI PASSAR DE LINHA EM LINHA NO BANCO VENDO SE A SENHA, EMAIL OU USER EXISTEM NO BANCO
            while (adms.next()) {
                //VERIFICA SE USUARIO OU EMAIL EXISTEM NO BANCO
                if (adms.getString("usuario").equals(user) || adms.getString("email").equals(user)) {
                    if (adms.getString("senha").equals(senha)) {
                        encontrado = true;
                        id = adms.getInt("id");
                        break;
                    } else {
                        encontrado = false;
                    }
                }
            }

        } catch(SQLException sqle){
            sqle.printStackTrace();
        }

        if (encontrado){
            autenticar(request, user, id);
            request.getRequestDispatcher("/dashboard").forward(request, response);
        } else {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
        }
    }
    protected void autenticar(HttpServletRequest request, String user, int id) {
        HttpSession session = request.getSession();

        session.setAttribute("usuario", user);
        session.setAttribute("idUsuario", id);
    }

    public static boolean verificaAutenticacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") == null) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return false;
        } else return true;

    }
}