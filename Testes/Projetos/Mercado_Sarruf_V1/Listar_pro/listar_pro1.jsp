<!-- jsp é um arquivo html que suporta codigo java dentro -->
<%@page import="java.sql.*"%>
<!-- importando todos as classes dessa subclass -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Listagem de Produtos </title>
        <link rel="stylesheet" href="listar_pro_jsp.css">
    </head>
    <body>
        <!-- começo do script em java -->
        <main>
            <div class="backcolor">
                <%
                    try {
                        int i = 0;
                        Connection conecta;
                        // Declara uma variável para a conexão com o banco de dados.
                        PreparedStatement sql;
                        // Declara uma variável para executar comandos SQL.
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // Carrega o driver JDBC do MySQL.
                        conecta = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mercado_sarruf?allowMultiQueries=true&useSSL=false&serverTimezone=UTC", "Sarruf", "12345677");
                        // Estabelece a conexão com o banco de dados usando a URL, usuário e senha.
                        // conexão com banco de dados
                        sql = conecta.prepareStatement("SELECT * FROM produto ORDER BY nome ASC;");
                        // Prepara uma consulta SQL para selecionar todos os registros da tabela
                        ResultSet tabela = sql.executeQuery();
                        // Executa a consulta e armazena o resultado em tabela
                %>
                <table>
                    <tr>
                        <th> ID </th>
                        <th> Codigo </th>
                        <th> Nome </th>
                        <th> Preço </th>
                        <th> Quantidade </th>
                        <!-- th cabeça da tabela ou titulo da tabela -->
                    </tr>
                    <%
                        while (tabela.next()) {
                            i = 1;
                    %>
                    <tr>
                        <td id = "primario"> <%= tabela.getString("id")%> </td>
                        <td id = "primario"> <%= tabela.getString("codigo")%> </td>
                        <td> <%= tabela.getString("nome")%> </td>
                        <td> <%= tabela.getString("preco")%> </td>
                        <td> <%= tabela.getString("quantidade")%> </td>
                        <!-- a % e um fragmento do codigo java que vira html -->
                        <!-- td dados da tabela ou conteudo da tabela -->
                    </tr>
                    <%
                        }
                    %>
                </table>
                <h1>
                    <%
                            if (i == 0) {
                                out.print("O Banco de Dados Esta Vazio.");
                            }
                        } catch (Exception erro) {
                            out.print("Mensagem de Erro: " + erro.getMessage());
                            // Captura e exibe qualquer erro que ocorra durante a execução do código.
                        }
                    %>
                </h1>
                <!-- fim do script em java -->
            </div>
            <button onclick="document.location = '../Listar_pro/listpro.html'">
                Voltar </button>
        </main>
    </body>
</html>
