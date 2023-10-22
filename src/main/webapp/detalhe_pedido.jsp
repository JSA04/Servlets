<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

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
    <link rel="stylesheet" href="./css/detalhe_pedido.css">

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
        <div id="pedido">
            <div id="div_pedido">
                <div id="titulo_pedido">
                    <h1>#${id_pedido} - ${cliente}</h1>
                </div>
                <div id="desc_pedido">
                    <% String items = (String) request.getAttribute("listaItems");
                       String[] lista_items = items.split("///"); %>
                    <% for (String item: lista_items) { %>
                        <% String[] i = item.split("%%"); %>
                        <div class="desc">
                            <p>Produto: <%= i[0] %></p>
                            <p>Quantidade: <%= i[1] %></p>
                            <p>Valor Unitário: <%= i[2] %></p>
                        </div>

                    <% } %>
                </div>
                <div id="valor_final">
                    <p> ${total} </p>
                </div>
            </div>
        </div>
    </main>
    <footer></footer>
</body>
</html>