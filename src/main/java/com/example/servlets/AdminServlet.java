package com.example.servlets;

import com.example.jdbc.Administrador.AdmConexao;
import com.example.jdbc.Administrador.Administracao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "alterar-admin", value = "/alterar_admin")
public class AdminServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        String cpf = (String) session.getAttribute("idUsuario");
        ResultSet usuario_info = new AdmConexao().buscarPorCPF(cpf);

        String adm = null, email = null, senha = null;

        try {
            if (usuario_info != null){
                while (usuario_info.next()){
                    adm = usuario_info.getString("usuario");
                    email = usuario_info.getString("email");
                    senha = usuario_info.getString("senha");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("adm", adm);
        request.setAttribute("email", email);
        request.setAttribute("senha", senha);
        request.setAttribute("cpf", cpf);

        request.getRequestDispatcher("alterar_admin.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (!(EntrarServlet.verificaAutenticacao(request))) {
            request.getRequestDispatcher("entrar.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        String usuario = request.getParameter("usuario");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = (String) session.getAttribute("idUsuario");

        Administracao admAlterado = new Administracao(usuario, email, senha, cpf);
        new AdmConexao().alterar(admAlterado);

        session.setAttribute("usuario", admAlterado.getUsuario());
        session.setAttribute("email", admAlterado.getEmail());

        request.getRequestDispatcher("/dashboard").forward(request, response);
    }
}
