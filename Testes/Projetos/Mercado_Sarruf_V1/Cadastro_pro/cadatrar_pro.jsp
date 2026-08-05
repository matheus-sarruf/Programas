<!-- jsp é um arquivo html que suporta codigo java dentro -->

<%@page import="java.sql.*"%>
<!-- importando todos as classes dessa subclass -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Cadastro de Produtos </title>
        <link rel="stylesheet" href="cad_pro_jsp.css">
    </head>
    <body>
        <main>
            <div class="org">
                <div class="c_org">
                    <h2>
                        <!-- começo do script em java -->
                        <%
                            try {
                                String rami = request.getParameter("rami");
                                int codigo = 0;
                                String nome = " ";
                                float preco = 0;
                                int quantidade = 0;

                                if (rami == null) {
                                    // parametros tem que ser iguais ao banco de dados
                                    codigo = Integer.parseInt(request.getParameter("codigo"));
                                    // recebe o parametro do codigo do arquivo html "cadpro" que vem como caracter e transforma para um inteiro
                                    nome = request.getParameter("nome");
                                    // recebe o parametro do nome do arquivo html "cadpro" que vem como caracter
                                    preco = Float.parseFloat(request.getParameter("preco"));
                                    // recebe o parametro do preco do arquivo html "cadpro" que vem como caracter e transforma para um float
                                    quantidade = Integer.parseInt(request.getParameter("quantidade"));
                                    // recebe o parametro da quantidade do arquivo html "cadpro" que vem como caracter e transforma para um inteiro
                                }
                                // Declaração da variável 'conecta' para armazenar a conexão com o banco de dados
                                Connection conecta;

                                // Declaração da variável 'st' para armazenar o comando SQL preparado
                                PreparedStatement sql;

                                // Carrega o driver JDBC do MySQL para permitir a comunicação com o banco de dados
                                // O driver é necessário para conectar o Java ao MySQL
                                Class.forName("com.mysql.cj.jdbc.Driver");

                                // Estabelece a conexão com o banco de dados MySQL
                                // Parâmetros:
                                // 1. URL do banco de dados: "jdbc:mysql://127.0.0.1:3306/mercado_sarruf"
                                //    - 127.0.0.1:3306 é o endereço e porta do servidor MySQL (localhost e porta padrão)
                                //    - mercado_sarruf é o nome do banco de dados
                                // 2. Usuário do banco de dados: "Sarruf"
                                // 3. Senha do banco de dados: "12345677"
                                conecta = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mercado_sarruf?allowMultiQueries=true&useSSL=false&serverTimezone=UTC", "Sarruf", "12345677");
                                if (rami != null) {
                                    sql = conecta.prepareStatement(
                                            "DROP TABLE IF EXISTS produto; "
                                            + "CREATE TABLE produto ("
                                            + "id INT AUTO_INCREMENT PRIMARY KEY, "
                                            + "codigo INT UNIQUE NOT NULL, "
                                            + "nome VARCHAR(50) NOT NULL, "
                                            + "preco DECIMAL(10,2) NOT NULL, "
                                            + "quantidade INT NOT NULL"
                                            + "); "
                                            + "INSERT INTO produto (codigo, nome, preco, quantidade) VALUES "
                                            + "(147, 'Café', 43.99, 10), "
                                            + "(234, 'Ovo 12 unit', 33.99, 20), "
                                            + "(794, 'Ovo 6 unit', 25.99, 15), "
                                            + "(465, 'Arroz cristal', 43.99, 30), "
                                            + "(575, 'Açucar pila', 43.99, 25)"
                                    );
                                    sql.executeUpdate();
                                    out.print("Backup Restaurado com Sucesso!");
                                } else {
                                    // Prepara o comando SQL para inserir dados na tabela 'produto'
                                    // A query usa placeholders (?) para os valores que serão inseridos
                                    // A tabela 'produto' deve ter 4 colunas, correspondentes aos valores que serão inseridos
                                    sql = conecta.prepareStatement("INSERT INTO produto VALUES (DEFAULT, ?, ?, ?, ?);");

                                    // Define os valores dos placeholders na query
                                    // Parâmetros:
                                    // 1. Índice do placeholder (começa em 1)
                                    // 2. Valor a ser inserido
                                    sql.setInt(1, codigo);       // Insere o valor da variável 'codigo' no primeiro placeholder (provavelmente um ID)
                                    sql.setString(2, nome);      // Insere o valor da variável 'nome' no segundo placeholder (nome do produto)
                                    sql.setFloat(3, preco);      // Insere o valor da variável 'preco' no terceiro placeholder (preço do produto)
                                    sql.setInt(4, quantidade);   // Insere o valor da variável 'quantidade' no quarto placeholder (quantidade em estoque)

                                    // Executa o comando SQL para inserir os dados na tabela
                                    // 'executeUpdate()' é usado para operações que modificam o banco de dados (INSERT, UPDATE, DELETE)
                                    sql.executeUpdate();

                                    // Exibe uma mensagem de sucesso na página
                                    out.print("Produto Cadastrado com Sucesso!");

                                    // Captura e trata qualquer exceção que ocorra durante a execução do código
                                    // Exibe uma mensagem de erro na página, incluindo a mensagem da exceção
                                }
                            } catch (Exception erro) {
                                String msg_erro = erro.getMessage();
                                if (msg_erro.contains("Duplicate entry")) {
                                    out.print("O codigo do produto digitado já está em outro produto.");
                                } else {
                                    out.print("Mensagem de Erro: " + erro.getMessage());
                                }
                            }
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
