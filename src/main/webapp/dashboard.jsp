<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <a href="entrar.jsp" class="header_button" id="sair_button">Sair</a>
                <a href="cadastrar_produto.jsp" class="header_button" id="add_produto_button">Adicionar Produto</a>
            </div>
        </div>
    </header>
    <main>
        <div id="produtos">

            <% String produtos = String.valueOf(request.getAttribute("listaProdutos")); %>
            <% String[] listaPedidos = produtos.split("///"); %>
            <% for (String produto: listaPedidos) { %>
            <% String[] pInfo = produto.split("%%"); %>

            <div class="produto" id="produto_<%= pInfo[0] %>">
                <a href="pedidos?id_produto=<%= pInfo[0] %>">
                    <% if (pInfo[1] == null) { %>
                    <div class="produto_img" style="background-image: url('img/produto.svg')"></div>
                    <% } else { %>
                    <div class="produto_img" style="background-image: url('<%= pInfo[2] %>')"></div>
                    <% } %>
                </a>
                <button class="produto_button">Remover Produto</button>
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