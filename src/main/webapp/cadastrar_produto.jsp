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
            <form action="cadastrar_produto" method="POST">
                <h1>Novo produto</h1>
                <div class="produto_infos">
                    <div class="info">
                        <label for="nome_id">Nome</label>
                        <input type="text" class="input" name="nome" id="nome_id" required>
                    </div>
                    <div class="info">
                        <label for="categoria_id">Categoria</label>
                        <select required id="categoria_id" class="input">
                            <option>Não Perecíveis</option>
                            <option>Congelados</option>
                            <option>Legumes</option>
                            <option>Carne Suína</option>
                            <option>Frango</option>
                            <option>Carne Bovina</option>
                        </select>
                    </div>
                    <div class="info">
                        <label for="quantidade_id">Quantidade</label>
                        <input type="number" min="1" class="input" name="quantidade" id="quantidade_id" required>
                    </div>
                    <div class="info">
                        <label for="preco_id">Preço</label>
                        <input type="number" min="0" class="input" name="preco" id="preco_id" step="0.01" required>
                    </div>
                </div>
                <label for="data_validade_id">Data de validade: </label>
                <input class="input" type="date" name="validade" id="data_validade_id" required>
                <label for="escolher_img">Insira a Imagem (URL): </label>
                <input class="input" type="text" name="imagem_url" id="escolher_img">
                <textarea class="input" name="descricao" id="descricao_id" cols="30" rows="3" placeholder="Descrição"></textarea>
                <input class="input" type="submit" id="confirmar_button" value="Cadastrar">
            </form>
        </div>
    </main>
    <footer></footer>
</body>
</html>