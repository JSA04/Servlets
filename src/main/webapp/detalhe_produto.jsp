<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt_br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EqualityFood - ${titulo}</title>
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="./css/static/header.css">
    <link rel="stylesheet" href="./css/static/footer.css">
    <link rel="stylesheet" href="./css/static/styles.css">
    <link rel="stylesheet" href="css/detalhe_produto.css">

</head>
<body>
    <header>
        <div class="nav">
            <div class="perfil">
                <div class="perfil_img"></div>
                <div class="perfil_info">
                    <a href="alterar_admin">
                        <h1><%= request.getSession().getAttribute("usuario") %></h1>
                        <p><%= request.getSession().getAttribute("email") %></p>
                    </a>
                </div>
            </div>
            <div class="nav_button">
                <a href="sair" class="header_button" id="sair_button">Sair</a>
                <a href="dashboard" class="header_button" id="voltar_button">Voltar</a>
            </div>
        </div>
    </header>
    <main>
        <div id="produto">

            <% HashMap produto = (HashMap) request.getAttribute("produto"); %>

            <div id="detalhes_produto">

                <% if (produto.get("imagem") == null || produto.get("imagem").equals("")) { %>
                    <div id="produto_img" style="background-image: url('img/produto.svg')"></div>
                <% } else { %>
                    <div id="produto_img" style="background-image: url('<%= produto.get("imagem") %>')"></div>
                <% } %>

                <div id="detalhes">
                    <p id="produto_nome"><%= produto.get("nome") %></p>
                    <div class="dados">
                        <p>Quantidade: <%= produto.get("quantidade") %></p>
                        <p><%= produto.get("preco") %></p>
                    </div>
                    <div class="dados">
                        <p>Categoria: <%= produto.get("categoria") %></p>
                        <p>Validade: <%= produto.get("validade") %></p>
                    </div>
                    <% if (!produto.get("descricao").equals("")) { %>
                    <p style="font-weight: bold;">Descrição</p>
                    <p id="produto_desc"><%= produto.get("descricao") %></p>
                    <% } %>
                    <a href="excluir_produto?id_produto=${id_produto}">
                        <button id="excluir_button">Excluir</button>
                    </a>
                    <a href="alterar_produto?id_produto=${id_produto}">
                        <button id="alterar_button">Alterar</button>
                    </a>
                </div>
            </div>
            <div id="pedidos">
                <% List<Map<String, String>> pedidos = (ArrayList) (request.getAttribute("listaPedidos")); %>

                <% if (pedidos.isEmpty()) { %>
                <p>Não há pedidos desse produto. </p>
                <% } %>


                <% for (Map<String, String> pedido: pedidos) { %>

                <a href="detalhe_pedido?id_pedido=<%= pedido.get("id_pedido") %>" class="pedido">
                    <h1 class="nome_cliente"><%= pedido.get("cliente") %></h1>
                    <div class="pedido_info">
                        <p>Total: <%= pedido.get("total") %></p>
                        <% if (pedido.get("status").equalsIgnoreCase("ENTREGUE")) { %>
                            <div class="status_entrega pedido_entregue"></div>
                        <% } else if (pedido.get("status").equalsIgnoreCase("EM ANDAMENTO")) { %>
                            <div class="status_entrega pedido_andamento"></div>
                        <% } else { %>
                            <div class="status_entrega pedido_cancelado"></div>
                        <% } %>
                    </div>
                </a>
                <% } %>
            </div>
        </div>
    </main>
    <footer></footer>
</body>
</html>