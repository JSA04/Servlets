<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt_br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EqualityFood</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/static/header.css">
    <link rel="stylesheet" type="text/css" href="./css/static/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/log_in_out.css">
    <link rel="stylesheet" type="text/css" href="./css/static/styles.css">
</head>
<body>
    <header>
        <div class="nav">
            <div></div>
            <div class="nav_button">
                <a href="entrar.jsp" class="header_button" id="entrar_button">Entrar</a>
                <a href="cadastrar.jsp" class="header_button" id="cadastrar_button">Cadastrar</a>
            </div>
        </div>
    </header>
    <main>
        <div class="div_log_in_out">
            <form action="entrar" id="form_log_in_out" method="POST">
                <div class="form_head">
                    <img src="img/logo.svg" alt="Logo EqualityFood" id="logo">
                    <h1>Conecte-se</h1>
                </div>
                <div class="campos">
                    <div class="form_campo">
                        <label for="user_id">Usuário</label>
                        <input type="text" class="input" name="user" id="user_id" 
                        placeholder="Usuário ou E-mail" required>
                    </div>
                    <div class="form_campo">
                        <label for="senha_id">Senha</label>
                        <input type="password" class="input" name="senha" id="senha_id"
                        placeholder="Senha" required>
                    </div>
                </div>
                <p class="${classMsg}"> ${msg} </p>
                <% request.getSession().setAttribute("msg", ""); %>
                <input type="submit" id="confirmar_button" value="Entrar">
            </form>
        </div>
        <div class="div_background background">
            <div class="background_img background"></div>
        </div>
    </main>
</body>
</html>