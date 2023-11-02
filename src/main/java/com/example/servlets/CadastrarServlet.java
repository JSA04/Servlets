package com.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.example.jdbc.Administrador.AdmConexao;
import com.example.jdbc.Administrador.Administracao;

@WebServlet(name = "cadastrar", value = "/cadastrar")
public class CadastrarServlet extends HttpServlet {
    @Override
    //solicitar que o servidor web aceite os dados anexados no corpo da mensagem de requisição para armazenamento.
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //se o login não for null ele vai para a tela de deshboard
        if (EntrarServlet.verificaAutenticacao(request)) {
            request.getRequestDispatcher("/dashboard").forward(request, response);
            return;
        }

        //cria uma sessão se um já não existe para este usuário
        HttpSession session = request.getSession();

        try {
            //Obtendo as informações
            String nome = request.getParameter("nome_completo");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String confirmarSenha = request.getParameter("confirmar_senha");

            //Criando objeto AdmConexão
            AdmConexao admConexao = new AdmConexao();

            //Criando objeto administrador com suas informações
            Administracao administrador = new Administracao(nome, email, senha, cpf);

            //Verificando se o cpf tem 11 digitos
            if (cpf.length() != 11) {
                session.setAttribute("classMsg", "erro_msg");
                session.setAttribute("msg", "O CPF deve conter 11 digítos! ");
                request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
            //Verificando se senhas são iguais
            } else if (!Objects.equals(senha, confirmarSenha)) {
                session.setAttribute("classMsg", "erro_msg");
                session.setAttribute("msg", "As senhas não conferem! ");
                request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
            } else {
                if (!(admConexao.verificaExistencia(administrador))){
                    //Adicionando no banco de dados
                    admConexao.inserir(administrador);
                    request.setAttribute("user", nome);
                    request.setAttribute("senha", senha);
                    request.getRequestDispatcher("/entrar").forward(request, response);
                } else {
                    session.setAttribute("classMsg", "erro_msg");
                    session.setAttribute("msg", "O Nome de Úsuario, E-mail ou CPF já está em uso! ");
                    request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
