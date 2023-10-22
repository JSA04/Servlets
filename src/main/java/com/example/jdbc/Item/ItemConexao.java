package com.example.jdbc.Item;

import java.sql.*;

public class ItemConexao implements ItemInterface {
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
    public boolean inserir(Item item) {
        try  {
            conectar();
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("INSERT INTO ITEM (QUANTIDADE,PRECO,ID,FK_PRODUTO_ID,FK_PEDIDO_ID) VALUES (?,?,?,?,?)");

            // Setando o valor aos parâmetros
            pstmt.setInt(1, item.getQuantidade());
            pstmt.setDouble(2, item.getPreco());
            pstmt.setInt(3, item.getId());
            pstmt.setInt(4, item.getFk_produto_id());
            pstmt.setInt(5, item.getFk_pedido_id());

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
    public boolean alterar(Item item) {
        conectar();
        try {
            pstmt = conn.prepareStatement("UPDATE ITEM SET QUANTIDADE=?, PRECO=?, ID=?, FK_PRODUTO_ID=?, FK_PEDIDO_ID=? WHERE ID=?");

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, item.getQuantidade());
            pstmt.setDouble(2, item.getPreco());
            pstmt.setInt(3, item.getId());
            pstmt.setInt(4, item.getFk_produto_id());
            pstmt.setInt(5, item.getFk_pedido_id());

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
    //REMOVE O ITEM
    public boolean remover(Item item) {
        conectar();
        //VERIFICA SE O REGISTRO NÃO EXISTE
        try {
            String remover = "DELETE FROM ITEM WHERE ID = ?";
            //INSTANCIANDO O OBJETO PREPAREDSTATAMENT (PSTMT)
            pstmt = conn.prepareStatement(remover);

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, item.getId());

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

    //MOSTRA OS ITEMS PELO ID ESCOLIDO
    public ResultSet buscarPorID(int id) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
                pstmt = conn.prepareStatement("SELECT * FROM ITEM WHERE ID=?");

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
    //MOSTRA OS ITEMS
    public ResultSet buscar() {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM ITEM");

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

