<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../comun.css">
    <title>Listar produtos</title>
    <style>
        div#MinMax {
            display: flex;
            flex-direction: row;
            width: 100%;
            gap: 25px;
        }

        @media (max-width: 850px) {
            .alinha {
                gap: 10px 0px;
                margin: 5px;
            }

            div#MinMax {
                display: flex;
                flex-direction: row;
                width: 100%;
                gap: 15px;
            }
        }
    </style>
</head>

<body>
    <header>
        <h1>Listagem de produtos</h1>
    </header>
    <main>
        <form id="listar_produtos1" method="post" action="listar.jsp">
            <div id="alinha_pai">
                <div class="alinha">
                    <label for="nome">Nome:</label>
                    <label for="marca">Marca:</label>
                    <label for="codigo">Código:</label>
                    <label for="preco">Preço:</label>
                    <label for="quantidade">Quantidade:</label>
                </div>
                <div id="inputs" class="alinha">
                    <input type="text" id="nome" name="nome" placeholder="   digite o nome aqui">
                    <input type="text" id="marca" name="marca" placeholder="   digite o marca aqui">
                    <input type="text" id="codigo" name="codigo" placeholder="   digite o código aqui"
                        pattern="[0-9]+([0-9]+)?">
                    <div id="MinMax">
                        <input type="text" id="precoMin" name="precoMin" placeholder="   digite o mimimo preço aqui"
                            pattern="[0-9]+([,\.][0-9]+)?">
                        <input type="text" id="precoMax" name="precoMax" placeholder="   digite o maximo preço aqui"
                            pattern="[0-9]+([,\.][0-9]+)?">
                    </div>
                    <div id="MinMax">
                        <input type="text" id="quantidadeMin" name="quantidadeMin"
                            placeholder="   digite o mimimo quantidade aqui" pattern="[0-9]+([,\.][0-9]+)?">
                        <input type="text" id="quantidadeMax" name="quantidadeMax"
                            placeholder="   digite o maximo quantidade aqui" pattern="[0-9]+([,\.][0-9]+)?">
                    </div>
                </div>
            </div>
            <p><input type="submit" id="Pesquisar" value="Pesquisar Produtos"></p>
        </form>
        <hr>
        <form id="listar_produtos2" method="post" action="listar.jsp">
            <input type="hidden" id="variavel" name="variavel" value="12345677">
            <input type="submit" id="vertudo" value="Ver Produtos">
            <p>
                O botão acima ira exibir todos os produtos cadastrados no banco de dados.
            </p>
        </form>
    </main>
    <script src="../comum.js"></script>
    <script>
        if (document.documentElement.clientWidth < 850) {
            document.getElementById('alinha_pai').innerHTML = `
                <div class="alinha">
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome" placeholder="   digite o nome aqui">
                    <label for="marca">Marca:</label>
                    <input type="text" id="marca" name="marca" placeholder="   digite o marca aqui">
                    <label for="codigo">Código:</label>
                    <input type="text" id="codigo" name="codigo" placeholder="   digite o código aqui"
                        pattern="[0-9]+([0-9]+)?">
                    <label for="preco">Preço:</label>
                    <div id="MinMax">
                        <input type="text" id="precoMin" name="precoMin" placeholder="   digite o mimimo preço aqui"
                            pattern="[0-9]+([,\.][0-9]+)?">
                        <input type="text" id="precoMax" name="precoMax" placeholder="   digite o maximo preço aqui"
                            pattern="[0-9]+([,\.][0-9]+)?">
                    </div>
                    <label for="quantidade">Quantidade:</label>
                    <div id="MinMax">
                        <input type="text" id="quantidadeMin" name="quantidadeMin"
                            placeholder="   digite o mimimo quantidade aqui" pattern="[0-9]+([,\.][0-9]+)?">
                        <input type="text" id="quantidadeMax" name="quantidadeMax"
                            placeholder="   digite o maximo quantidade aqui" pattern="[0-9]+([,\.][0-9]+)?">
                    </div>
                </div>
                `
        }
    </script>
</body>

</html>