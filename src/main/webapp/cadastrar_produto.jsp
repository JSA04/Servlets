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
            <div>
                <h1 class="titulo"></h1>
            </div>

            <div class="nav_button">
                <a href="dashboard" class="header_button" id="voltar_button">Voltar</a>
            </div>

        </div>
    </header>
    <main>
        <div class="div_cadastrar_produto">
            <form action="cadastrar_produto">
                <h1>Novo produto</h1>
                <div class="produto_infos">
                    <div class="info">
                        <label for="nome_id">Nome</label>
                        <input type="text" class="input" name="nome" id="nome_id">
                    </div>
                    <div class="info">
                        <label for="tipo_id">Tipo</label>
                        <input type="text" class="input" name="tipo" id="tipo_id">
                    </div>
                    <div class="info">
                        <label for="quantidade_id">Quantidade</label>
                        <input type="number" class="input" name="quantidade" id="quantidade_id">
                    </div>
                    <div class="info">
                        <label for="preco_id">Preço</label>
                        <input type="number" class="input" name="preco" id="preco_id" step="0.50">
                    </div>
                </div>
                <label for="escolher_img" id="img">Insira a URL</label>

            </form>
        </div>
    </main>
    <footer></footer>
</body>
</html>