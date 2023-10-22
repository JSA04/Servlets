package com.example.jdbc.Administrador;

import java.sql.*;

public class AdmConexao implements AdmInterface{
    //ATRIBUTOS
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    //FAZ A CONEXÃO COM O BANCO DE DADOS
    public void conectar() {
        try {
            // Informando qual driver de conexão será utilizado pelo DriveManager
            Class.forName("org.postgresql.Driver");
            // Criando a conexão com o BD
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://isabelle.db.elephantsql.com:5432/wsgdbdbe", "wsgdbdbe", "Z91LZsvXz1sdt4tOZA-aNh_Yziw8Vba5");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //DESCONECTA COM O BANCO DE DADOS
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                // Desconectando do BD
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    //INSERI NO BANCO DE DADOS
    public boolean inserir(Administracao adm) {
        try  {
            conectar();
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("INSERT INTO ADMINISTRADOR (USUARIO,EMAIL,SENHA,ID) VALUES (?,?,?,?)");

            // Setando o valor aos parâmetros
            pstmt.setString(1, adm.getUsuario());
            pstmt.setString(2, adm.getEmail());
            pstmt.setString(3, adm.getSenha());
            pstmt.setInt(4, adm.getId());


            //EXECUTA O COMANDO SQL DO PREPARESTATAMENT
            pstmt.execute();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;

        } finally {
            desconectar();
        }
    }

    //ALTERA AS INFORMAÇÕES
    public boolean alterar(Administracao adm) {
        conectar();
        try {
            pstmt = conn.prepareStatement("UPDATE ADMINISTRADOR SET USUARIO=?, EMAIL=?, SENHA=? WHERE ID=?");

            //SETANDO OS PARAMETROS
            pstmt.setString(1, adm.getUsuario());
            pstmt.setString(2, adm.getEmail());
            pstmt.setString(3, adm.getSenha());
            pstmt.setInt(4, adm.getId());

            //EXECUTA O COMANDO SQL DO PREPARESTATAMENT O UPDATE RETORNA AS LINHAS QUE TEM
            pstmt.executeUpdate();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;

        } finally {
            desconectar();
        }
    }
    //REMOVE O ADM
    public boolean remover(Administracao adm) {
        conectar();
        //VERIFICA SE O REGISTRO NÃO EXISTE
        try {
            String remover = "DELETE FROM ADMINISTRADOR WHERE ID = ?";
            //INSTANCIANDO O OBJETO PREPAREDSTATAMENT (PSTMT)
            pstmt = conn.prepareStatement(remover);

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, adm.getId());

            //EXECUTA O COMANDO SQL DO PREPARESTATAMENT O UPDATE RETORNA AS LINHAS QUE TEM
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }
    //MOSTRA OS ADMs PELO ID ESCOLIDO
    public ResultSet buscarPorID(int id) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
                pstmt = conn.prepareStatement("SELECT * FROM ADMINISTRADOR WHERE ID=?");

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, id);

            // Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            // Retornando o ResultSet
            return rs;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } finally {
            desconectar();
        }
    }
    //MOSTRA OS ADMs
    public ResultSet buscar() {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM ADMINISTRADOR");

            // Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            // Retornando o ResultSet
            return rs;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } finally {
            desconectar();
        }
    }
}

