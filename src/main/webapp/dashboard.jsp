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
        <form id="busca" method="GET" action="dashboard">
            <div>
                <label for="busca_input_id">Buscar: </label>
                <input type="text" name="busca_input" id="busca_input_id" class="input" placeholder="Palavra-Chave">
            </div>
            <div>
                <label for="data_input_id">Filtrar por data: </label>
                <input type="date" name="data_input" id="data_input_id" class="input">
                <input type="submit" value="Buscar" id="buscar_button">
            </div>
        </form>
        <div id="produtos">

            <% List<Map<String, String>> produtos = (ArrayList) (request.getAttribute("listaProdutos")); %>
            <% for (Map<String, String> produto: produtos) { %>

            <div class="produto" id="produto_<%= produto.get("id") %>">
                <p class="produto_titulo"><%= produto.get("nome") %></p>
                <a href="detalhe_produto?id_produto=<%= produto.get("id") %>">
                    <% if (produto.get("imagem") == null || produto.get("imagem").isEmpty()) { %>
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

            <% if (produtos.isEmpty()) { %>
                <p>Não há pedidos desse produto. </p>
            <% } %>

            <% if (!(boolean) (request.getAttribute("pesquisa"))) { %>
                <div class="produto">
                    <a href="cadastrar_produto.jsp">
                        <div id="add_button"></div>
                    </a>
                </div>
            <% } %>
        </div>
    </main>
    <footer></footer>
</body>
</html>