package com.example.jdbc.Pedido;
import java.sql.*;

public class PedidoConexao implements PedidoInterface{
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
    public boolean inserir(Pedido pedido) {
        //FAZ A CONEXÃO COM O BANCO
        conectar();
        try  {

            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("INSERT INTO PEDIDO (PRECOTOTAL,STATUS,DATA,HORA,ID,FK_CLIENTE_CPF,FK_ADMINISTRADOR_ID) VALUES (?,?,?,?,?,?,?)");

            // Setando o valor aos parâmetros
            pstmt.setDouble(1,pedido.getPrecoTotal());
            pstmt.setString(2,pedido.getStatus());
            pstmt.setDate(3,pedido.getData_pedido());
            pstmt.setTime(4,pedido.getHora());
            pstmt.setInt(5,pedido.getId());
            pstmt.setString(6,pedido.getFk_cliente_cpf());
            pstmt.setInt(7,pedido.getFk_administrador_id());


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
        public boolean alterar(Pedido pedido) {
        conectar();
        try {
            pstmt = conn.prepareStatement("UPDATE PEDIDO SET DATA=?, FK_CLIENTE_CPF=?, FK_ADMINISTRADOR_ID=?, PRECOTOTAL=?, STATUS=?, HORA=?, DATA=? WHERE ID=?");

            //SETANDO OS PARAMETROS
            pstmt.setDate(1,pedido.getData_pedido());
            pstmt.setString(2,pedido.getFk_cliente_cpf());
            pstmt.setInt(3,pedido.getFk_administrador_id());
            pstmt.setDouble(4,pedido.getPrecoTotal());
            pstmt.setString(5,pedido.getStatus());
            pstmt.setDate(6,pedido.getData_pedido());
            pstmt.setTime(7,pedido.getHora());
            pstmt.setInt(8,pedido.getId());

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
    //REMOVE PEDIDO
    public boolean remover(Pedido pedido) {
        conectar();

        //VERIFICA SE O REGISTRO NÃO EXISTE
        try {
            String remover = "DELETE FROM PEDIDO WHERE ID = ?";

            //INSTANCIANDO O OBJETO PREPAREDSTATAMENT (PSTMT)
            pstmt = conn.prepareStatement(remover);

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, pedido.getId());

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
    // MOSTRA OS PEDIDOS QUE CONTÉM UM PRODUTO ESPECIFICO: usado pelo PedidosServlet
    public ResultSet buscarPedidosPorProduto(int id_produto) {
        try {
            this.conectar();
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(
                    "SELECT " +
                            "PE.ID \"ID_PEDIDO\"," +
                            "CL.NOME_COMPLETO \"CLIENTE\"," +
                            "PE.PRECOTOTAL \"TOTAL\"," +
                            "PR.NOME \"PRODUTO\"," +
                            "PE.STATUS \"STATUS\" " +
                            "FROM ITEM IT " +
                            "JOIN PEDIDO PE ON PE.ID = IT.FK_PEDIDO_ID " +
                            "JOIN PRODUTO PR ON IT.FK_PRODUTO_ID = PR.ID " +
                            "JOIN CLIENTE CL ON PE.FK_CLIENTE_CPF = CL.CPF " +
                            "WHERE PR.ID= ?");

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, id_produto);

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

    // MOSTRA OS ITENS DO PEDIDO: usado pelo DetalhesPedidoServlet
    public ResultSet buscarItensPorIDPedido(int id_pedido) {
        try {
            this.conectar();
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(
                    "SELECT " +
                        "CL.NOME_COMPLETO \"CLIENTE\", PR.NOME \"DESC\", " +
                        "IT.QUANTIDADE \"QTD\", PR.PRECO \"VALOR_UNI\", PE.PRECOTOTAL \"TOTAL\"" +
                        "FROM ITEM IT " +
                        "JOIN PEDIDO PE ON PE.ID = IT.FK_PEDIDO_ID " +
                        "JOIN PRODUTO PR ON IT.FK_PRODUTO_ID = PR.ID " +
                        "JOIN CLIENTE CL ON PE.FK_CLIENTE_CPF = CL.CPF " +
                        "WHERE PE.ID= ?");

            //SETANDO OS PARAMETROS
            pstmt.setInt(1, id_pedido);

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

    //MOSTRA OS PEDIDOS PELO ID ESCOLHIDO
    public ResultSet buscarPorID(int id) {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM PEDIDO WHERE ID= ?");

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

    //MOSTRA OS PEDIDOS
    public ResultSet buscar() {
        conectar();
        try {
            // Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM PEDIDO");

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
