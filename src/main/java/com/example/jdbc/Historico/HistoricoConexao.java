package com.example.jdbc.Historico;

import java.sql.*;

public class HistoricoConexao implements HistoricoInterface {
    //ATRIBUTOS
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    //FAZ A CONEXÃO COM O BANCO DE DADOS
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
    public boolean inserir(Historico historico) {
        try  {
            conectar();
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("INSERT INTO HISTORICO (DESCRICAO,ID,FK_ADMINISTRADOR_ID) VALUES (?,?,?)");

            // Setando o valor aos parâmetros
            pstmt.setString(1, historico.getDescricao());
            pstmt.setInt(2, historico.getId());
            pstmt.setInt(3, historico.getFk_Administrador_id());


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
    public boolean alterar(Historico historico) {
        conectar();
        try {
            pstmt = conn.prepareStatement("UPDATE HISTORICO SET DESCRICAO=? WHERE ID=?");

            //SETANDO OS PARAMETROS
            pstmt.setString(1, historico.getDescricao());
            pstmt.setInt(2, historico.getId());


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

    //REMOVE O HISTORICO
    public boolean remover(Historico alteracao) {
        conectar();
        //VERIFICA SE O REGISTRO NÃO EXISTE
        try {
            String remover = "DELETE FROM HISTORICO WHERE ID = ?";
            //INSTANCIANDO O OBJETO PREPAREDSTATAMENT (PSTMT)
            pstmt = conn.prepareStatement(remover);

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, alteracao.getId());

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

    //MOSTRA OS HISTORICOS PELO ID ESCOLIDO
    public ResultSet buscarPorID(int id) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM HISTORICO WHERE ID=?");

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

    //MOSTRA OS HISTORICOS
    public ResultSet buscar() {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM HISTORICO");

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
