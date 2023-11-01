package com.example.jdbc.Produto;


import com.sun.jdi.connect.Connector;

import java.sql.*;

public class ProdutoConexao implements ProdutoInterface{
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

    //Método desconectar
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
    public boolean inserir(Produto produto) {
        try  {
            conectar();
            // Instanciando o objeto preparedStatement (pstmt)

            pstmt = conn.prepareStatement("INSERT INTO PRODUTO (nome, preco, descricao, data_validade, id, quant, imagem, fk_administrador_cpf, categoria, ativado) VALUES (?,?,?,?,?,?,?,?,?,?)");

            // Setando o valor aos parâmetros
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setString(3, produto.getDescricao());
            pstmt.setDate(4, produto.getData_validade());
            pstmt.setInt(5, produto.getId());
            pstmt.setInt(6, produto.getQuantidade());
            pstmt.setString(7, produto.getImagem());
            pstmt.setString(8, produto.fk_administrador_cpf());
            pstmt.setString(9, produto.getCategoria());
            pstmt.setBoolean(10, true);

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
        public boolean alterar(Produto produto) {
        conectar();
        try {
            pstmt = conn.prepareStatement("UPDATE PRODUTO SET nome = ?, quant = ?, preco = ?, data_validade = ?, descricao = ? WHERE id = ?");

            // Setando o valor aos parâmetros
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidade());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setDate(4, produto.getData_validade());
            pstmt.setString(5, produto.getDescricao());
            pstmt.setInt(6, produto.getId());

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
    // SOFT DELETE

    public boolean softDelete(int produto_id) {
        conectar();
        //VERIFICA SE O REGISTRO NÃO EXISTE
        try {
            String remover = "UPDATE PRODUTO SET ATIVADO = ? WHERE ID= ?";

            //INSTANCIANDO O OBJETO PREPAREDSTATAMENT (PSTMT)
            pstmt = conn.prepareStatement(remover);

            //SETANDO OS PARAMETROS
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, produto_id);

            //EXECUTA O COMANDO SQL DO PREPARESTATAMENT O UPDATE MOSTRA A QUANTIDADE DE LINHAS afetadas linhas que foram
            //mexidas
            pstmt.executeUpdate();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    //REMOVE PRODUTO
       public boolean remover(int produto_id) {
        conectar();
        //VERIFICA SE O REGISTRO NÃO EXISTE
        try {
            String remover = "DELETE FROM PRODUTO WHERE ID= ?";

            //INSTANCIANDO O OBJETO PREPAREDSTATAMENT (PSTMT)
            pstmt = conn.prepareStatement(remover);

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, produto_id);

            //EXECUTA O COMANDO SQL DO PREPARESTATAMENT O UPDATE MOSTRA A QUANTIDADE DE LINHAS afetadas linhas que foram
            //mexidas
            pstmt.executeUpdate();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    //MOSTRA OS PRODUTOS PELO ID ESCOLHIDO
    public ResultSet buscarPorID(int id) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM PRODUTO WHERE ID = ?");

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

    //MOSTRA OS PRODUTOS
    public ResultSet pesquisar() {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM PRODUTO");

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

    public ResultSet pesquisar(String nome) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement( "SELECT * FROM produto WHERE " +
                        "UPPER(nome) LIKE UPPER(CONCAT('%', ?, '%')) OR " +
                        "UPPER(categoria) LIKE UPPER(CONCAT('%', ?, '%')) OR " +
                        "UPPER(descricao) LIKE UPPER(CONCAT('%', ?, '%')) ");

            pstmt.setString(1, nome);
            pstmt.setString(2, nome);
            pstmt.setString(3, nome);

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
    public ResultSet pesquisar(Date data) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement( "SELECT * FROM produto WHERE" +
                    "UPPER(nome) LIKE UPPER (CONCAT('%', ?, '%')) OR " +
                    "UPPER(categoria) LIKE UPPER (CONCAT('%', ?, '%')) OR " +
                    "UPPER(descricao) LIKE UPPER (CONCAT('%', ?, '%')) AND " +
                    "DATA_VALIDADE = ?");

            pstmt.setDate(1, data);

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
    public ResultSet pesquisar(String nome, Date data) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement( "SELECT * FROM produto WHERE " +
                    "UPPER(nome) LIKE UPPER (CONCAT('%', ?, '%')) OR " +
                    "UPPER(categoria) LIKE UPPER (CONCAT('%', ?, '%')) OR " +
                    "UPPER(descricao) LIKE UPPER (CONCAT('%', ?, '%')) AND " +
                    "DATA_VALIDADE = ?");

            pstmt.setString(1, nome);
            pstmt.setString(2, nome);
            pstmt.setString(3, nome);
            pstmt.setDate(4, data);

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
