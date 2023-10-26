<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
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
            <div>
                <h1 class="titulo">Pedidos</h1>
            </div>
            <div class="nav_button">
                <a href="sair" class="header_button" id="sair_button">Sair</a>
                <a href="dashboard" class="header_button" id="voltar_button">Voltar</a>
            </div>
        </div>
    </header>
    <main>
        <div id="produto">
            <div id="detalhes_produto">
                <div id="produto_img"></div>
                <div id="detalhes">
                    <p id="produto_nome">Carne</p>
                    <div class="dados">
                        <p>Quantidade: 22</p>
                        <p>R$ 19,99</p>
                    </div>
                    <div class="dados">
                        <p>Categoria: Bovina</p>
                        <p>Validade: 04/04/2024</p>
                    </div>
                    <p style="font-weight: bold;">Descrição</p>
                    <p id="produto_desc">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Laborum harum delectus corporis incidunt nemo earum, soluta excepturi suscipit dolor cumque perferendis odio rerum illo quaerat vitae neque. Ea, obcaecati veritatis!</p>
                    <a href="excluir">
                        <button id="excluir_button">Excluir</button>
                    </a>
                </div>
            </div>
            <div id="pedidos">
                <% List<Map<String, String>> pedidos = (ArrayList) (request.getAttribute("listaPedidos")); %>
                <% for (Map<String, String> pedido: pedidos) { %>

                <a href="detalhe?id_pedido=<%= pedido.get("id_pedido") %>" class="pedido">
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