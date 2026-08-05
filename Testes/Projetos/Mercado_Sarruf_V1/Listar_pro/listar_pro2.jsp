<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Produtos</title>
        <link rel="stylesheet" href="listar_pro_jsp.css">
    </head>
    <body>
        <main>
            <%
                try {
                    // Captura os parâmetros de pesquisa
                    String codigo = request.getParameter("codigo");
                    String nome = request.getParameter("nome");
                    String preco = request.getParameter("preco");
                    String quantidade = request.getParameter("quantidade");
                    String id = request.getParameter("id");
                    int i = 0;

                    // Construção da consulta SQL dinâmica
                    String st = "SELECT * FROM produto WHERE 1=1"; // 1=1 para facilitar a adição de condições

                    if (codigo != null && !codigo.isEmpty()) {
                        st += " AND codigo = " + codigo + " ";
                    }
                    if (id != null && !id.isEmpty()) {
                        st += " AND id = " + id + " ";
                    }
                    if (nome != null && !nome.isEmpty()) {
                        st += " AND nome LIKE '" + nome + "%' ";
                    }
                    if (preco != null && !preco.isEmpty()) {
                        st += " AND preco LIKE '" + preco + "%' ";
                    }
                    if (quantidade != null && !quantidade.isEmpty()) {
                        st += " AND quantidade = " + quantidade + " ";
                    }
                    st += " ORDER BY nome ASC;"; // Ordena os resultados por nome
                    Connection conecta;
                    PreparedStatement sql;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conecta = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mercado_sarruf?allowMultiQueries=true&useSSL=false&serverTimezone=UTC", "Sarruf", "12345677");
                    sql = conecta.prepareStatement(st);
                    ResultSet tabela = sql.executeQuery();
            %>
            <table border="1">
                <tr>
                    <th> ID </th>
                    <th> Código </th>
                    <th> Nome </th>
                    <th> Preço </th>
                    <th> Quantidade </th>
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
                </tr>
                <%
                    }
                %>
            </table>
            <h1>
                <%
                        if (i == 0) {
                            out.print("Não foi Encontrado Nada na Pesquisa");
                        }
                    } catch (Exception erro) {
                        out.print("Mensagem de Erro: " + erro.getMessage());
                    }
                %>
            </h1>
            <button onclick="document.location = '../Listar_pro/listpro.html'"> Voltar </button>
        </main>
    </body>
</html>