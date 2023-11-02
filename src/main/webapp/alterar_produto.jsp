<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt_br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EqualityFood</title>
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="./css/static/header.css">
    <link rel="stylesheet" href="./css/static/footer.css">
    <link rel="stylesheet" href="./css/static/styles.css">
    <link rel="stylesheet" href="./css/cadastrar_produto.css">
</head>
<body>
<header>
    <div class="nav">
        <div></div>
        <div class="nav_button">
            <a href="detalhe_produto?id_produto=${id_produto}" class="header_button" id="voltar_button">Voltar</a>
        </div>
    </div>
</header>
<main>
    <div class="div_cadastrar_produto">
        <form action="alterar_produto?id_produto=${id_produto}" method="POST">
            <h1>Alterar produto</h1>
            <div class="produto_infos">
                <div class="info">
                    <label for="nome_id">Nome</label>
                    <input value="<%= (String) request.getAttribute("nome") %>" type="text" class="input" name="nome" id="nome_id" maxlength="50" required>
                </div>
                <div class="info">
                    <label for="categoria_id">Categoria</label>
                    <select required name="categoria" id="categoria_id" class="input">
                        <% String categoria = (String) request.getAttribute("categoria"); %>
                        <% String[] categorias = new String[]{"Não Perecíveis", "Congelados", "Legumes", "Carne Suína", "Frango", "Carne Bovina"}; %>
                        <% for (String c: categorias) { %>
                            <% if (c.equals(categoria)) { %>
                                <option value="<%= c %>" selected="selected"> <%= c %></option>
                            <% } else { %>
                                <option value="<%= c %>"> <%= c %></option>
                            <% } %>
                        <% } %>
                    </select>
                </div>
                <div class="info">
                    <label for="quantidade_id">Quantidade</label>
                    <input value="<%= (int) request.getAttribute("quantidade") %>" type="number" min="1" max="10000" class="input" name="quantidade" id="quantidade_id" required>
                </div>
                <div class="info">
                    <label for="preco_id">Preço</label>
                    <input value="<%= (double) request.getAttribute("preco") %>" type="number" min="0" max="10000" class="input" name="preco" id="preco_id" step="0.01" required>
                </div>
            </div>
            <label for="data_validade_id">Data de validade: </label>
            <p id="data_validade_id"><%= (String) request.getAttribute("validade") %></p>
            <label for="escolher_img">Insira a Imagem (URL): </label>
            <input value="<%= (String) request.getAttribute("imagem_url") %>" class="input" type="text" name="imagem_url" id="escolher_img" maxlength="200">
            <textarea class="input" name="descricao" id="descricao_id" cols="30" rows="3" maxlength="200" placeholder="Descrição"><%= (String) request.getAttribute("descricao") %></textarea>
            <input class="input" type="submit" id="confirmar_button" value="Cadastrar">
        </form>
    </div>
</main>
<footer></footer>
</body>
</html>