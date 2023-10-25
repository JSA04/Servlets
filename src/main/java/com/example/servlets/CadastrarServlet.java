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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Obtendo as informações
            String nome = request.getParameter("nome_completo");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String confirmarSenha = request.getParameter("confirmar_senha");
            int novoId = 0;
            int maior = 0;

            //Criando objeto AdmConexão
            AdmConexao admConexao = new AdmConexao();

            //Método para pegar o id e adicionar mais 1
            ResultSet rs1= admConexao.buscar();
            if(rs1!=null){
                try{
                    if(!rs1.isBeforeFirst()) {
                        System.out.println("Não foi encontrado");
                    }
                    else {
                        while (rs1.next()) {
                            novoId = rs1.getInt("id");
                            if (novoId > maior) {
                                maior = novoId;
                            }
                        }
                        maior++;
                    }
                }catch (SQLException sqle){
                    sqle.printStackTrace();
                    System.out.println("Não foi encontrado");
                }
            }
            else {
                System.out.println("Erro ao tentar selecionar!!!");
            }

            //Criando objeto administrador com suas informações
            Administracao administrador = new Administracao(nome, email, senha, maior);

            //Verificando se senhas são iguais
            if (Objects.equals(senha, confirmarSenha)) {
                //Adicionando no banco de dados
                admConexao.inserir(administrador);
                request.getRequestDispatcher("/dashboard").forward(request, response);
            } else {
                request.getRequestDispatcher("entrar.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
