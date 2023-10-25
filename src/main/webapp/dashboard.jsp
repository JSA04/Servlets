<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="pt_br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EqualityFood</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/static/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/static/header.css">
    <link rel="stylesheet" type="text/css" href="./css/static/footer.css">
    <link rel="stylesheet" type="text/css" href="css/dashboard.css">
</head>
<body>
    <header>
        <div class="nav">
            <div>
                <h1 class="titulo">Produtos</h1>
            </div>
            <div class="nav_button">
                <a href="sair" class="header_button" id="sair_button">Sair</a>
                <a href="cadastrar_produto.jsp" class="header_button" id="add_produto_button">Adicionar Produto</a>
            </div>
        </div>
    </header>
    <main>
        <div id="produtos">

            <% List<Map<String, String>> produtos = (ArrayList) (request.getAttribute("listaProdutos")); %>
            <% for (Map<String, String> produto: produtos) { %>

            <div class="produto" id="produto_<%= produto.get("id") %>">
                <p><%= produto.get("nome") %></p>
                <a href="pedidos?id_produto=<%= produto.get("id") %>">
                    <% if (produto.get("imagem") == null || produto.get("imagem").equals("")) { %>
                    <div class="produto_img" style="background-image: url('img/produto.svg')"></div>
                    <% } else { %>
                    <div class="produto_img" style="background-image: url('<%= produto.get("imagem") %>')"></div>
                    <% } %>
                </a>
                <a href="excluir?id_produto=<%= produto.get("id") %>">
                    <button class="produto_button"> Remover Produto </button>
                </a>
            </div>

            <% } %>

            <div class="produto">
                <a href="cadastrar_produto.jsp">
                    <div id="add_button"></div>
                </a>
            </div>
        </div>
    </main>
    <footer></footer>
</body>
</html>