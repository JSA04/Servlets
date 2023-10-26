<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt_br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EqualityFood</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/static/header.css">
    <link rel="stylesheet" type="text/css" href="./css/static/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/log_in_out.css">
</head>
<body>
    <header>
        <div class="nav">
            <div>
                <h1 class="titulo"></h1>
            </div>
            <div class="nav_button">
                <a href="entrar.jsp" class="header_button" id="entrar_button">Entrar</a>
                <a href="cadastrar.jsp" class="header_button" id="cadastrar_button">Cadastrar</a>
            </div>
        </div>
    </header>
    <main>
        <div class="div_log_in_out div_cadastro">
            <form action="cadastrar" method="POST" id="form_log_in_out">
                <div class="form_head">
                    <img src="img/logo.svg" alt="Logo EqualityFood" id="logo">
                    <h1>Cadastre-se</h1>
                </div>
                <div class="campos">
                    <div class="form_campo">
                        <label for="nome_completo_id">Usuário</label>
                        <input type="text" class="input" name="nome_completo" id="nome_completo_id" 
                        placeholder="Usuário" required>
                    </div>
                    <div class="form_campo">
                        <label for="email_id">E-mail</label>
                        <input type="email" class="input" name="email" id="email_id" 
                        placeholder="E-mail" required>
                    </div>
                    <div class="form_campo">
                        <label for="senha_id">Criar Senha</label>
                        <input type="password" class="input" name="senha" id="senha_id"
                        placeholder="Senha" required>
                    </div>
                    <div class="form_campo">
                        <label for="confirmar_senha_id">Confirmar Senha</label>
                        <input type="password" class="input" name="confirmar_senha" id="confirmar_senha_id" 
                        placeholder="Confirmar Senha" required>
                    </div>
                </div>
                <div class="form_checkbox">
                    <input type="checkbox" name="termos" id="termos_id" required>
                    <p>Li e concordo com os Termos e Condições de Uso. Os termos estão disponíveis para consulta no app.</p>
                </div>
                <p class="${classMsg}"> ${msg} </p>
                <% request.getSession().setAttribute("msg", ""); %>
                <input type="submit" id="confirmar_button" value="Cadastrar">
            </form>
        </div>
        <div class="div_background background">
            <div class="background_img background"></div>
        </div>
    </main>
</body>
</html>