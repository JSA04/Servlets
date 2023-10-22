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
    <link rel="stylesheet" href="./css/pedido.css">

</head>
<body>
    <header>
        <div class="nav">
            <div>
                <h1 class="titulo">Pedidos</h1>
            </div>
            <div class="nav_button">
                <a href="entrar.jsp" class="header_button" id="sair_button">Sair</a>
                <a href="dashboard" class="header_button" id="voltar_button">Voltar</a>
            </div>
        </div>
    </header>
    <main>
        <div id="pedidos">
            <% String pedidos = String.valueOf(request.getAttribute("listaPedidos")); %>
            <% String[] listaPedidos = pedidos.split("///"); %>
            <% for (String pedido: listaPedidos) { %>
            <% String[] pInfo = pedido.split("%%"); %>

            <a href="detalhe-pedido?id_pedido=<%= pInfo[0] %>" class="pedido">
                <h1 class="nome_cliente"><%= pInfo[1] %></h1>
                <div class="pedido_info">
                    <p>Total: <%= pInfo[2] %></p>
                    <% if (pInfo[3].equalsIgnoreCase("ENTREGUE")) { %>
                        <div class="status_entrega pedido_entregue"></div>
                    <% } else if (pInfo[3].equalsIgnoreCase("EM ANDAMENTO")) { %>
                        <div class="status_entrega pedido_andamento"></div>
                    <% } else { %>
                        <div class="status_entrega pedido_cancelado"></div>
                    <% } %>
                </div>
            </a>
            <% } %>
        </div>
    </main>
    <footer></footer>
</body>
</html>