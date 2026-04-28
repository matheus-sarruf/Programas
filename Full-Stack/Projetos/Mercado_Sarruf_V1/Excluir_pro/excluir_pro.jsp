<%@page import="java.util.*"%>
<!-- jsp é um arquivo html que suporta codigo java dentro -->

<%@page import="java.sql.*"%>
<!-- importando todos as classes dessa subclass -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Cadastro de Produtos </title>
        <link rel="stylesheet" href="excluir_pro_jsp.css">
    </head>
    <body>
        <main>
            <div class="org">
                <div class="c_org">
                    <h2>
                        <%
                            Connection conecta = null;
                            PreparedStatement sql = null;
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                conecta = DriverManager.getConnection(
                                        "jdbc:mysql://127.0.0.1:3300/mercado_sarruf?useSSL=false&serverTimezone=UTC",
                                        "Sarruf",
                                        "12345677");
                                String rami = request.getParameter("rami");
                                if (rami == null) {
                                    String id = request.getParameter("id");
                                    String codigo = request.getParameter("codigo");

                                    if ((id == null || id.trim().isEmpty()) && (codigo == null || codigo.trim().isEmpty())) {
                                        out.print("Informe o ID ou Código do produto ou ambos");
                                    }

                                    StringBuilder pedacoCodi = new StringBuilder("DELETE FROM produto WHERE 1=1");

                                    if (id != null && !id.trim().isEmpty() && codigo != null && !codigo.trim().isEmpty()) {
                                        PreparedStatement verificaEx = conecta.prepareStatement("SELECT COUNT(*) FROM produto WHERE id = ?");
                                        verificaEx.setInt(1, Integer.parseInt(id.trim()));
                                        ResultSet rs = verificaEx.executeQuery();
                                        rs.next();
                                        int existe = rs.getInt(1);
                                        if (existe == 0) {
                                            out.print("Não Existe Nenhum Produto com Esse ID.");
                                            return;
                                        }
                                        verificaEx = conecta.prepareStatement("SELECT COUNT(*) FROM produto WHERE id = ?");
                                        verificaEx.setInt(1, Integer.parseInt(id.trim()));
                                        ResultSet rs = verificaEx.executeQuery();
                                        rs.next();
                                        int existe = rs.getInt(1);
                                        if (existe == 0) {
                                            out.print("Não Existe Nenhum Produto com Esse ID.");
                                            return;
                                        } else {
                                            pedacoCodi.append("AND id = " + id);

                                        }

                                        if (id != null && !id.trim().isEmpty() && codigo != null && !codigo.trim().isEmpty()) {
                                            pedacoCodi.append(" AND codigo = " + codigo + " AND id = " + id);
                                            int exclusao = sql.executeUpdate();
                                            if (exclusao > 0) {
                                                out.print("Produto Excluído com Sucesso!");
                                            } else {
                                                out.print("Ocorreu um Erro ao Excluir o Produto");
                                            }
                                        }

                                        if (id != null && !id.trim().isEmpty()) {
                                            pedacoCodi.append(" AND id = " + id);
                                            int exclusao = sql.executeUpdate();
                                            if (exclusao > 0) {
                                                out.print("Produto Excluído com Sucesso!");
                                            } else {
                                                out.print("Ocorreu um Erro ao Excluir o Produto");
                                            }
                                        }

                                        if (codigo != null && !codigo.trim().isEmpty()) {
                                            pedacoCodi.append(" AND codigo = " + codigo);
                                            int exclusao = sql.executeUpdate();
                                            if (exclusao > 0) {
                                                out.print("Produto Excluído com Sucesso!");
                                            } else {
                                                out.print("Ocorreu um Erro ao Excluir o Produto");
                                            }
                                        }

                                        // Executar a exclusão
                                        sql = conecta.prepareStatement(pedacoCodi.toString());

                                        // VERIFICAÇÃO DA EXCLUSÃO
                                        int linhasAfetadas = sql.executeUpdate();

                                        if (linhasAfetadas > 0) {

                                            // Verificação adicional (opcional)
                                        }
                                    } else {
                                    }
                                }
                            } catch (Exception e) {
                                out.print("<div class='error'>Erro: " + e.getMessage() + "</div>");
                            }

                        %>
                        %>
                    </h2>
                    <!-- fim do script em java -->
                    <button onclick="document.location = '../Cadastro_pro/cadpro.html'">
                        Voltar </button>
                </div>
            </div>
        </main>
    </body>
</html>
