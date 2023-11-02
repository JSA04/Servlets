<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>

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
            <a href="alterar_admin.jsp">
                <div class="perfil">
                    <div class="perfil_img"></div>
                    <div class="perfil_info">
                        <a href="alterar_admin">
                            <h1><%= request.getSession().getAttribute("usuario") %></h1>
                            <p><%= request.getSession().getAttribute("email") %></p>
                        </a>
                    </div>
                </div>
            </a>
            <div class="nav_button">
                <a href="sair" class="header_button" id="sair_button">Sair</a>
                <a href="detalhe_produto?id_produto=${id_produto}" class="header_button" id="voltar_button">Voltar</a>
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
                    <% List<Map<String, String>> items = (ArrayList) (request.getAttribute("listaItems")); %>
                    <% for (Map<String, String> item: items) { %>
                        <div class="desc">
                            <p>Produto: <%= item.get("nome") %></p>
                            <p>Quantidade: <%= item.get("qtd") %></p>
                            <p>Valor Unitário: <%= item.get("valor_uni") %></p>
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