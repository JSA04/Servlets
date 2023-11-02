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
    <link rel="stylesheet" href="./css/alterar_admin.css">
</head>
<body>
<header>
    <div class="nav">
        <div></div>
        <div class="nav_button">
            <a href="dashboard" class="header_button" id="voltar_button">Voltar</a>
        </div>
    </div>
</header>
<main>
    <div class="div_alterar_admin">
        <form action="alterar_admin" method="POST">
            <h1>Informações do Administrador</h1>
            <div class="adm_infos">
                <div class="info">
                    <label for="usuario_id">Nome</label>
                    <input value="<%= (String) request.getAttribute("adm") %>" type="text" class="input" name="usuario" id="usuario_id" required>
                </div>
                <div class="info">
                    <label for="email_id">E-mail</label>
                    <input value="<%= (String) request.getAttribute("email") %>" type="text" class="input" name="email" id="email_id" required>
                </div>
                <div class="info">
                    <label for="senha_id">Senha</label>
                    <input value="<%= (String) request.getAttribute("senha") %>" type="password" class="input" name="senha" id="senha_id" required>
                </div>
                <div class="info">
                    <label for="cpf">CPF</label>
                    <p id="cpf"><%= (String) request.getAttribute("cpf") %></p>
                </div>
            </div>
            <input class="input" type="submit" id="confirmar_button" value="Alterar">
        </form>
    </div>
</main>
<footer></footer>
</body>
</html>