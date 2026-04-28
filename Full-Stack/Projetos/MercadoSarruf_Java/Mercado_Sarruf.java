import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;

public class Mercado_Sarruf {

    public static void main(String[] args) {
        System.out.println("=== Mercado Sarruf - Sistema de Gestão ===");
        System.out.println("Versão 1.0 - Desenvolvido por @matheus-sarruf");
        System.out.println("Conectando ao MySQL...");

        try {
            Configuracao configuracao = new Configuracao();
            configuracao.carregarConfiguracoes();

            BancoDados bancoDados = new BancoDados(configuracao);

            Sistema sistema = new Sistema(configuracao, bancoDados);

            if (!bancoDados.testarConexao()) {
                int resposta = JOptionPane.showConfirmDialog(null,
                        "Não foi possível conectar ao MySQL.\n" +
                                "Verifique se:\n" +
                                "1. MySQL está rodando\n" +
                                "2. Banco 'mercado_sarruf' existe\n" +
                                "3. Credenciais em config.properties estão corretas\n\n" +
                                "Deseja continuar sem banco de dados?",
                        "Erro de Conexão",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);

                if (resposta != JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            } else {
                System.out.println("Conexão com MySQL estabelecida com sucesso!");
            }

            if (configuracao.isInterfaceGrafica()) {
                SwingUtilities.invokeLater(() -> {
                    InterfaceGrafica gui = new InterfaceGrafica(sistema);
                    gui.iniciar();
                });
            } else {
                InterfaceConsole console = new InterfaceConsole(sistema);
                console.iniciar();
            }

        } catch (Exception e) {
            System.err.println("Erro fatal ao iniciar sistema: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao iniciar sistema:\n" + e.getMessage(),
                    "Erro Fatal",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

class Configuracao {
    private String tema = "Tema_Escuro";
    private String temaBotoes = "azul";
    private double volumeMusica = 0.5;
    private double volumeEfeitos = 0.7;
    private boolean musicaAtiva = true;
    private boolean efeitosAtivos = true;
    private boolean interfaceGrafica = true;
    private String dbUrl = "jdbc:mysql://localhost:3306/mercado_sarruf";
    private String dbUsuario = "root";
    private String dbSenha = "root";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    private int zoom = 100;

    private static final Logger LOGGER = Logger.getLogger(Configuracao.class.getName());

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTemaBotoes() {
        return temaBotoes;
    }

    public void setTemaBotoes(String temaBotoes) {
        this.temaBotoes = temaBotoes;
    }

    public double getVolumeMusica() {
        return volumeMusica;
    }

    public void setVolumeMusica(double volume) {
        this.volumeMusica = Math.max(0, Math.min(1, volume));
    }

    public double getVolumeEfeitos() {
        return volumeEfeitos;
    }

    public void setVolumeEfeitos(double volume) {
        this.volumeEfeitos = Math.max(0, Math.min(1, volume));
    }

    public boolean isMusicaAtiva() {
        return musicaAtiva;
    }

    public void setMusicaAtiva(boolean ativa) {
        this.musicaAtiva = ativa;
    }

    public boolean isEfeitosAtivos() {
        return efeitosAtivos;
    }

    public void setEfeitosAtivos(boolean ativos) {
        this.efeitosAtivos = ativos;
    }

    public boolean isInterfaceGrafica() {
        return interfaceGrafica;
    }

    public void setInterfaceGrafica(boolean grafica) {
        this.interfaceGrafica = grafica;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsuario() {
        return dbUsuario;
    }

    public void setDbUsuario(String dbUsuario) {
        this.dbUsuario = dbUsuario;
    }

    public String getDbSenha() {
        return dbSenha;
    }

    public void setDbSenha(String dbSenha) {
        this.dbSenha = dbSenha;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = Math.max(10, Math.min(300, zoom));
    }

    public void carregarConfiguracoes() {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(fis);

            tema = props.getProperty("tema", "Tema_Escuro");
            temaBotoes = props.getProperty("temaBotoes", "azul");
            volumeMusica = Double.parseDouble(props.getProperty("volumeMusica", "0.5"));
            volumeEfeitos = Double.parseDouble(props.getProperty("volumeEfeitos", "0.7"));
            musicaAtiva = Boolean.parseBoolean(props.getProperty("musicaAtiva", "true"));
            efeitosAtivos = Boolean.parseBoolean(props.getProperty("efeitosAtivos", "true"));
            interfaceGrafica = Boolean.parseBoolean(props.getProperty("interfaceGrafica", "true"));

            dbUrl = props.getProperty("db.url", "jdbc:mysql://localhost:3306/mercado_sarruf");
            dbUsuario = props.getProperty("db.usuario", "root");
            dbSenha = props.getProperty("db.senha", "root");
            dbDriver = props.getProperty("db.driver", "com.mysql.cj.jdbc.Driver");
            zoom = Integer.parseInt(props.getProperty("zoom", "100"));

            LOGGER.info("Configurações carregadas");
        } catch (IOException e) {
            LOGGER.warning("Configurações não encontradas, usando padrões");
            salvarConfiguracoes();
        } catch (NumberFormatException e) {
            LOGGER.warning("Erro ao converter número nas configurações, usando padrões");
        }
    }

    public void salvarConfiguracoes() {
        try (FileOutputStream fos = new FileOutputStream("config.properties")) {
            Properties props = new Properties();

            props.setProperty("tema", tema);
            props.setProperty("temaBotoes", temaBotoes);
            props.setProperty("volumeMusica", String.valueOf(volumeMusica));
            props.setProperty("volumeEfeitos", String.valueOf(volumeEfeitos));
            props.setProperty("musicaAtiva", String.valueOf(musicaAtiva));
            props.setProperty("efeitosAtivos", String.valueOf(efeitosAtivos));
            props.setProperty("interfaceGrafica", String.valueOf(interfaceGrafica));

            props.setProperty("db.url", dbUrl);
            props.setProperty("db.usuario", dbUsuario);
            props.setProperty("db.senha", dbSenha);
            props.setProperty("db.driver", dbDriver);
            props.setProperty("zoom", String.valueOf(zoom));

            props.store(fos, "Configurações do Mercado Sarruf");
            LOGGER.info("Configurações salvas");
        } catch (IOException e) {
            LOGGER.severe("Erro ao salvar configurações: " + e.getMessage());
        }
    }
}

class BancoDados {
    private Connection conexao;
    private static final Logger LOGGER = Logger.getLogger(BancoDados.class.getName());
    private Configuracao config;

    public BancoDados(Configuracao config) {
        this.config = config;
        conectar();
    }

    private void conectar() {
        try {
            Class.forName(config.getDbDriver());
            String url = config.getDbUrl();
            String usuario = config.getDbUsuario();
            String senha = config.getDbSenha();

            conexao = DriverManager.getConnection(url, usuario, senha);
            LOGGER.info("Conectado ao MySQL");
            criarTabelas();
        } catch (ClassNotFoundException e) {
            LOGGER.severe("Driver do MySQL não encontrado: " + e.getMessage());
            System.err.println("Erro: Driver MySQL não encontrado. Baixe o mysql-connector-j-9.4.0.jar");
        } catch (SQLException e) {
            LOGGER.severe("Erro ao conectar ao banco: " + e.getMessage());
            System.err.println("Erro de conexão MySQL: " + e.getMessage());
            System.err.println(
                    "Verifique: 1) MySQL está rodando 2) Banco 'mercado_sarruf' existe 3) Credenciais corretas");
        }
    }

    private void criarTabelas() {
        String sqlProdutos = """
                CREATE TABLE IF NOT EXISTS produtos (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nome VARCHAR(100) NOT NULL,
                    codigo VARCHAR(20) UNIQUE NOT NULL,
                    preco DECIMAL(10,2) NOT NULL,
                    quantidade INT NOT NULL,
                    marca VARCHAR(50) NOT NULL,
                    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
                """;

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlProdutos);
            LOGGER.info("Tabela 'produtos' criada/verificada");
        } catch (SQLException e) {
            LOGGER.severe("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                LOGGER.info("Conexão fechada");
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    public boolean testarConexao() {
        try {
            return conexao != null && !conexao.isClosed() && conexao.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
}

class Sistema {
    private Configuracao configuracao;
    private BancoDados bancoDados;
    private TemaManager temaManager;
    private SomManager somManager;
    private static final Logger LOGGER = Logger.getLogger(Sistema.class.getName());

    public Sistema(Configuracao configuracao, BancoDados bancoDados) {
        this.configuracao = configuracao;
        this.bancoDados = bancoDados;
        this.temaManager = new TemaManager();
        this.somManager = new SomManager();
        LOGGER.info("Sistema inicializado");
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public BancoDados getBancoDados() {
        return bancoDados;
    }

    public TemaManager getTemaManager() {
        return temaManager;
    }

    public SomManager getSomManager() {
        return somManager;
    }

    public Logger getLogger() {
        return LOGGER;
    }

    public void salvarConfiguracoes() {
        configuracao.salvarConfiguracoes();
    }

    public void encerrar() {
        salvarConfiguracoes();
        bancoDados.fecharConexao();
        LOGGER.info("Sistema encerrado");
        System.exit(0);
    }
}

class Produto {
    private int id;
    private String nome;
    private String codigo;
    private double preco;
    private int quantidade;
    private String marca;
    private LocalDateTime dataCadastro;

    public Produto() {
        this.dataCadastro = LocalDateTime.now();
    }

    public Produto(String nome, String codigo, double preco, int quantidade, String marca) {
        this();
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || !codigo.matches("\\d+")) {
            throw new IllegalArgumentException("Código deve conter apenas números");
        }
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.quantidade = quantidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser vazia");
        }
        this.marca = marca.trim();
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime data) {
        this.dataCadastro = data;
    }

    @Override
    public String toString() {
        return String.format("Produto [ID: %d, Nome: %s, Código: %s, Preço: R$%.2f, Quantidade: %d, Marca: %s]",
                id, nome, codigo, preco, quantidade, marca);
    }
}

class ProdutoDAO {
    private Connection conexao;
    private static final Logger LOGGER = Logger.getLogger(ProdutoDAO.class.getName());

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean cadastrar(Produto produto) {
        return inserir(produto);
    }

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produtos(nome, codigo, preco, quantidade, marca) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getCodigo());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setInt(4, produto.getQuantidade());
            pstmt.setString(5, produto.getMarca());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        produto.setId(generatedKeys.getInt(1));
                    }
                }
                LOGGER.info("Produto inserido: " + produto.getNome());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro ao inserir produto: " + e.getMessage());
            if (e.getErrorCode() == 1062) {
                throw new RuntimeException("Código de produto já existe!");
            }
        }
        return false;
    }

    public boolean alterar(Produto produto) {
        return atualizar(produto);
    }

    public boolean atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ?, marca = ? WHERE codigo = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setInt(3, produto.getQuantidade());
            pstmt.setString(4, produto.getMarca());
            pstmt.setString(5, produto.getCodigo());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                LOGGER.info("Produto atualizado: " + produto.getNome());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro ao atualizar produto: " + e.getMessage());
        }
        return false;
    }

    public boolean excluirTodos() {
        String sql = "DELETE FROM produtos";

        try (Statement stmt = conexao.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            LOGGER.info("Todos os produtos excluídos: " + rows + " registros");
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.severe("Erro ao excluir todos produtos: " + e.getMessage());
        }
        return false;
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos ORDER BY nome";

        try (Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setMarca(rs.getString("marca"));
                produto.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                produtos.add(produto);
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    public Produto buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM produtos WHERE codigo = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setMarca(rs.getString("marca"));
                return produto;
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    public boolean excluir(String codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, codigo);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                LOGGER.info("Produto excluído: código " + codigo);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro ao excluir produto: " + e.getMessage());
        }
        return false;
    }

    public List<Produto> pesquisar(String nome, String marca, String codigo,
            Double precoMin, Double precoMax,
            Integer quantidadeMin, Integer quantidadeMax) {
        List<Produto> produtos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM produtos WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            sql.append(" AND nome LIKE ?");
            parametros.add("%" + nome + "%");
        }

        if (marca != null && !marca.isEmpty()) {
            sql.append(" AND marca LIKE ?");
            parametros.add("%" + marca + "%");
        }

        if (codigo != null && !codigo.isEmpty()) {
            sql.append(" AND codigo = ?");
            parametros.add(codigo);
        }

        if (precoMin != null) {
            sql.append(" AND preco >= ?");
            parametros.add(precoMin);
        }

        if (precoMax != null) {
            sql.append(" AND preco <= ?");
            parametros.add(precoMax);
        }

        if (quantidadeMin != null) {
            sql.append(" AND quantidade >= ?");
            parametros.add(quantidadeMin);
        }

        if (quantidadeMax != null) {
            sql.append(" AND quantidade <= ?");
            parametros.add(quantidadeMax);
        }

        sql.append(" ORDER BY nome");

        try (PreparedStatement stmt = conexao.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setCodigo(rs.getString("codigo"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setMarca(rs.getString("marca"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro na pesquisa: " + e.getMessage());
        }
        return produtos;
    }
}

class TemaManager {
    private Map<String, Color[]> temas;
    private String temaAtual;

    public TemaManager() {
        temas = new HashMap<>();
        inicializarTemas();
        temaAtual = "Tema_Escuro";
    }

    private void inicializarTemas() {
        temas.put("Tema_Escuro", new Color[] {
                new Color(30, 30, 46),
                new Color(89, 194, 230),
                new Color(64, 160, 194),
                new Color(180, 210, 255),
                new Color(255, 121, 198),
                new Color(255, 85, 85),
                new Color(205, 214, 244)
        });
        temas.put("Tema_Claro", new Color[] {
                new Color(239, 241, 245),
                new Color(136, 192, 208),
                new Color(116, 172, 188),
                new Color(180, 210, 255),
                new Color(245, 194, 231),
                new Color(210, 15, 57),
                new Color(76, 79, 105)
        });
        temas.put("Tema_Preto", new Color[] {
                Color.BLACK,
                new Color(50, 50, 50),
                new Color(80, 80, 80),
                new Color(120, 120, 120),
                new Color(0, 200, 200),
                new Color(255, 50, 50),
                Color.WHITE
        });
        temas.put("Tema_Branco", new Color[] {
                Color.WHITE,
                new Color(240, 240, 240),
                new Color(220, 220, 220),
                new Color(180, 180, 180),
                new Color(0, 150, 150),
                new Color(200, 50, 50),
                Color.BLACK
        });
    }

    public void aplicarTema(Component componente, String nomeTema) {
        if (!temas.containsKey(nomeTema)) {
            nomeTema = "Tema_Escuro";
        }
        temaAtual = nomeTema;
        if (componente instanceof Container) {
            aplicarTemaRecursivo((Container) componente, temas.get(nomeTema));
        }
    }

    private void aplicarTemaRecursivo(Container container, Color[] cores) {
        container.setBackground(cores[0]);
        container.setForeground(cores[6]);
        for (Component comp : container.getComponents()) {
            if (comp instanceof Container) {
                aplicarTemaRecursivo((Container) comp, cores);
            }
            if (comp instanceof JButton) {
                comp.setBackground(cores[1]);
                comp.setForeground(cores[6]);
            } else if (comp instanceof JLabel || comp instanceof JTextArea) {
                comp.setForeground(cores[6]);
            }
        }
    }

    public String getTemaAtual() {
        return temaAtual;
    }

    public void setTemaAtual(String tema) {
        this.temaAtual = tema;
    }
}

class SomManager {
    private static final Logger LOGGER = Logger.getLogger(SomManager.class.getName());

    public void tocarEfeito(String tipo) {
        LOGGER.info("Tocando efeito: " + tipo);
        switch (tipo) {
            case "sucesso":
                System.out.println("[SOM] Sucesso!");
                break;
            case "erro":
                System.out.println("[SOM] Erro!");
                break;
            case "clique":
                System.out.println("[SOM] Clique");
                break;
            case "hover":
                System.out.println("[SOM] Hover");
                break;
            case "exclusao":
                System.out.println("[SOM] Exclusão");
                break;
            case "alteracao":
                System.out.println("[SOM] Alteração");
                break;
            case "listagem":
                System.out.println("[SOM] Listagem");
                break;
            case "pesquisa":
                System.out.println("[SOM] Pesquisa");
                break;
            default:
                System.out.println("[SOM] Efeito: " + tipo);
        }
    }

    public void tocarMusica(String nome) {
        LOGGER.info("Tocando música: " + nome);
        System.out.println("[MÚSICA] " + nome);
    }

    public void pararMusica() {
        LOGGER.info("Música parada");
        System.out.println("[MÚSICA] Parada");
    }
}

class InterfaceConsole {
    private Sistema sistema;
    private ProdutoDAO produtoDAO;
    private Scanner scanner;
    private boolean executando;

    public InterfaceConsole(Sistema sistema) {
        this.sistema = sistema;
        this.produtoDAO = new ProdutoDAO(sistema.getBancoDados().getConexao());
        this.scanner = new Scanner(System.in);
        this.executando = true;
    }

    public void iniciar() {
        sistema.getLogger().info("Interface console iniciada");
        sistema.getSomManager().tocarMusica("inicio");
        exibirCabecalho();
        while (executando) {
            exibirMenu();
            String opcao = scanner.nextLine().trim();
            try {
                processarOpcao(opcao);
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                sistema.getLogger().warning("Erro no console: " + e.getMessage());
            }
        }
    }

    private void exibirCabecalho() {
        System.out.println("=".repeat(60));
        System.out.println("            MERCADO SARRUF - SISTEMA DE GESTÃO");
        System.out.println("=".repeat(60));
        System.out.println("Versão 1.0 | Desenvolvido por @matheus-sarruf");
        System.out.println("-".repeat(60));
    }

    private void exibirMenu() {
        System.out.println("\nMENU PRINCIPAL:");
        System.out.println("1. Listar Produtos");
        System.out.println("2. Cadastrar Produto");
        System.out.println("3. Excluir Produto");
        System.out.println("4. Alterar Produto");
        System.out.println("5. Pesquisar Produtos");
        System.out.println("6. Configurações");
        System.out.println("7. Informações do Sistema");
        System.out.println("8. Excluir Todos os Produtos");
        System.out.println("9. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private void processarOpcao(String opcao) {
        switch (opcao) {
            case "1" -> listarProdutos();
            case "2" -> cadastrarProduto();
            case "3" -> excluirProduto();
            case "4" -> alterarProduto();
            case "5" -> pesquisarProdutos();
            case "6" -> menuConfiguracoes();
            case "7" -> mostrarInformacoes();
            case "8" -> excluirTodosProdutos();
            case "9" -> sair();
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private void listarProdutos() {
        System.out.println("\n=== LISTA DE PRODUTOS ===");
        List<Produto> produtos = produtoDAO.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            System.out.printf("%-4s %-20s %-10s %-10s %-8s %-15s %-20s%n",
                    "ID", "Nome", "Código", "Preço", "Qtd", "Marca", "Cadastro");
            System.out.println("-".repeat(90));
            for (Produto p : produtos) {
                System.out.printf("%-4d %-20s %-10s R$%-9.2f %-8d %-15s %-20s%n",
                        p.getId(),
                        p.getNome().length() > 20 ? p.getNome().substring(0, 17) + "..." : p.getNome(),
                        p.getCodigo(),
                        p.getPreco(),
                        p.getQuantidade(),
                        p.getMarca().length() > 15 ? p.getMarca().substring(0, 12) + "..." : p.getMarca(),
                        p.getDataCadastro().format(formatter));
            }
            double valorTotal = produtos.stream()
                    .mapToDouble(p -> p.getPreco() * p.getQuantidade())
                    .sum();
            System.out.println("\nTotal de produtos: " + produtos.size());
            System.out.printf("Valor total em estoque: R$ %.2f%n", valorTotal);
        }
        sistema.getSomManager().tocarEfeito("listagem");
    }

    private void cadastrarProduto() {
        System.out.println("\n=== CADASTRO DE PRODUTO ===");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Código: ");
            String codigo = scanner.nextLine().trim();
            System.out.print("Preço: ");
            double preco = Double.parseDouble(scanner.nextLine().replace(",", "."));
            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            System.out.print("Marca: ");
            String marca = scanner.nextLine().trim();
            Produto produto = new Produto(nome, codigo, preco, quantidade, marca);
            if (produtoDAO.cadastrar(produto)) {
                System.out.println("✅ Produto cadastrado com sucesso!");
                sistema.getSomManager().tocarEfeito("sucesso");
            } else {
                System.out.println("❌ Erro ao cadastrar produto!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Formato numérico inválido!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void excluirProduto() {
        System.out.println("\n=== EXCLUSÃO DE PRODUTO ===");
        System.out.print("Código do produto a excluir: ");
        String codigo = scanner.nextLine().trim();
        Produto produto = produtoDAO.buscarPorCodigo(codigo);
        if (produto == null) {
            System.out.println("❌ Produto não encontrado!");
            return;
        }
        System.out.println("\nProduto encontrado:");
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Código: " + produto.getCodigo());
        System.out.println("Preço: R$ " + produto.getPreco());
        System.out.println("Quantidade: " + produto.getQuantidade());
        System.out.println("Marca: " + produto.getMarca());
        System.out.print("\nConfirma a exclusão? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
            if (produtoDAO.excluir(codigo)) {
                System.out.println("✅ Produto excluído com sucesso!");
                sistema.getSomManager().tocarEfeito("exclusao");
            } else {
                System.out.println("❌ Erro ao excluir produto!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private void excluirTodosProdutos() {
        System.out.println("\n=== EXCLUSÃO DE TODOS OS PRODUTOS ===");
        System.out.println("ATENÇÃO: Esta ação é irreversível!");
        System.out.print("Tem certeza que deseja excluir TODOS os produtos? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
            if (produtoDAO.excluirTodos()) {
                System.out.println("✅ Todos os produtos foram excluídos!");
                sistema.getSomManager().tocarEfeito("exclusao");
            } else {
                System.out.println("❌ Erro ao excluir produtos!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private void alterarProduto() {
        System.out.println("\n=== ALTERAÇÃO DE PRODUTO ===");
        System.out.print("Código do produto a alterar: ");
        String codigo = scanner.nextLine().trim();
        Produto produto = produtoDAO.buscarPorCodigo(codigo);
        if (produto == null) {
            System.out.println("❌ Produto não encontrado!");
            return;
        }
        System.out.println("\nProduto atual:");
        System.out.println("1. Nome: " + produto.getNome());
        System.out.println("2. Preço: R$ " + produto.getPreco());
        System.out.println("3. Quantidade: " + produto.getQuantidade());
        System.out.println("4. Marca: " + produto.getMarca());
        System.out.println("\nDeixe em branco para manter o valor atual.");
        try {
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine().trim();
            if (!novoNome.isEmpty())
                produto.setNome(novoNome);
            System.out.print("Novo preço: ");
            String novoPreco = scanner.nextLine().trim();
            if (!novoPreco.isEmpty())
                produto.setPreco(Double.parseDouble(novoPreco.replace(",", ".")));
            System.out.print("Nova quantidade: ");
            String novaQuant = scanner.nextLine().trim();
            if (!novaQuant.isEmpty())
                produto.setQuantidade(Integer.parseInt(novaQuant));
            System.out.print("Nova marca: ");
            String novaMarca = scanner.nextLine().trim();
            if (!novaMarca.isEmpty())
                produto.setMarca(novaMarca);
            if (produtoDAO.alterar(produto)) {
                System.out.println("✅ Produto alterado com sucesso!");
                sistema.getSomManager().tocarEfeito("alteracao");
            } else {
                System.out.println("❌ Erro ao alterar produto!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Formato numérico inválido!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void pesquisarProdutos() {
        System.out.println("\n=== PESQUISA DE PRODUTOS ===");
        System.out.println("Deixe em branco para ignorar o filtro.");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Marca: ");
            String marca = scanner.nextLine().trim();
            System.out.print("Código: ");
            String codigo = scanner.nextLine().trim();
            System.out.print("Preço mínimo: ");
            String precoMinStr = scanner.nextLine().trim();
            Double precoMin = precoMinStr.isEmpty() ? null : Double.parseDouble(precoMinStr.replace(",", "."));
            System.out.print("Preço máximo: ");
            String precoMaxStr = scanner.nextLine().trim();
            Double precoMax = precoMaxStr.isEmpty() ? null : Double.parseDouble(precoMaxStr.replace(",", "."));
            System.out.print("Quantidade mínima: ");
            String quantMinStr = scanner.nextLine().trim();
            Integer quantMin = quantMinStr.isEmpty() ? null : Integer.parseInt(quantMinStr);
            System.out.print("Quantidade máxima: ");
            String quantMaxStr = scanner.nextLine().trim();
            Integer quantMax = quantMaxStr.isEmpty() ? null : Integer.parseInt(quantMaxStr);
            List<Produto> resultados = produtoDAO.pesquisar(
                    nome.isEmpty() ? null : nome,
                    marca.isEmpty() ? null : marca,
                    codigo.isEmpty() ? null : codigo,
                    precoMin, precoMax,
                    quantMin, quantMax);
            System.out.println("\n=== RESULTADOS ===");
            if (resultados.isEmpty()) {
                System.out.println("Nenhum produto encontrado.");
            } else {
                System.out.println("Encontrados " + resultados.size() + " produtos:");
                for (Produto p : resultados) {
                    System.out.println("- " + p.getNome() + " (Código: " + p.getCodigo() +
                            ", Preço: R$" + p.getPreco() + ", Qtd: " + p.getQuantidade() + ")");
                }
            }
            sistema.getSomManager().tocarEfeito("pesquisa");
        } catch (NumberFormatException e) {
            System.out.println("❌ Formato numérico inválido!");
        }
    }

    private void menuConfiguracoes() {
        System.out.println("\n=== CONFIGURAÇÕES ===");
        Configuracao config = sistema.getConfiguracao();
        TemaManager tema = sistema.getTemaManager();
        System.out.println("1. Volume Música: " + (int) (config.getVolumeMusica() * 100) + "%");
        System.out.println("2. Volume Efeitos: " + (int) (config.getVolumeEfeitos() * 100) + "%");
        System.out.println("3. Música Ativa: " + (config.isMusicaAtiva() ? "Sim" : "Não"));
        System.out.println("4. Efeitos Ativos: " + (config.isEfeitosAtivos() ? "Sim" : "Não"));
        System.out.println("5. Tema Atual: " + config.getTema());
        System.out.println("6. Tema Botões: " + config.getTemaBotoes());
        System.out.println("7. Salvar Configurações");
        System.out.println("8. Voltar");
        System.out.print("\nEscolha: ");
        String opcao = scanner.nextLine().trim();
        switch (opcao) {
            case "1" -> {
                System.out.print("Novo volume música (0-100): ");
                try {
                    int vol = Integer.parseInt(scanner.nextLine());
                    config.setVolumeMusica(vol / 100.0);
                    System.out.println("Volume alterado!");
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido!");
                }
            }
            case "2" -> {
                System.out.print("Novo volume efeitos (0-100): ");
                try {
                    int vol = Integer.parseInt(scanner.nextLine());
                    config.setVolumeEfeitos(vol / 100.0);
                    System.out.println("Volume alterado!");
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido!");
                }
            }
            case "3" -> {
                config.setMusicaAtiva(!config.isMusicaAtiva());
                System.out.println("Música " + (config.isMusicaAtiva() ? "ativada" : "desativada"));
            }
            case "4" -> {
                config.setEfeitosAtivos(!config.isEfeitosAtivos());
                System.out.println("Efeitos " + (config.isEfeitosAtivos() ? "ativados" : "desativados"));
            }
            case "5" -> {
                System.out.println("Temas disponíveis: Escuro, Claro, Preto, Branco");
                System.out.print("Novo tema: ");
                String novoTema = scanner.nextLine().trim().replace(" ", "_");
                config.setTema("Tema_" + novoTema);
                tema.setTemaAtual("Tema_" + novoTema);
                System.out.println("Tema alterado!");
            }
            case "6" -> {
                System.out.println("Temas disponíveis: verde, azul, misto1, misto2, moderno");
                System.out.print("Novo tema botões: ");
                String novoTema = scanner.nextLine().trim().toLowerCase();
                config.setTemaBotoes(novoTema);
                System.out.println("Tema dos botões alterado!");
            }
            case "7" -> {
                config.salvarConfiguracoes();
                System.out.println("Configurações salvas!");
            }
        }
    }

    private void mostrarInformacoes() {
        System.out.println("\n=== INFORMAÇÕES DO SISTEMA ===");
        System.out.println("Data/Hora: " + java.time.LocalDateTime.now());
        System.out.println("Sistema: " + System.getProperty("os.name"));
        System.out.println("Java: " + System.getProperty("java.version"));
        System.out.println("Tema: " + sistema.getConfiguracao().getTema());
        System.out.println("Volume Música: " + (int) (sistema.getConfiguracao().getVolumeMusica() * 100) + "%");
        System.out.println("Volume Efeitos: " + (int) (sistema.getConfiguracao().getVolumeEfeitos() * 100) + "%");
        System.out.println("BD: " + (sistema.getBancoDados().getConexao() != null ? "Conectado" : "Desconectado"));
        List<Produto> produtos = produtoDAO.listarTodos();
        System.out.println("Total Produtos: " + produtos.size());
        double valorTotal = produtos.stream()
                .mapToDouble(p -> p.getPreco() * p.getQuantidade())
                .sum();
        System.out.printf("Valor Estoque: R$ %.2f%n", valorTotal);
    }

    private void sair() {
        System.out.print("\nDeseja realmente sair? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        if (resposta.equals("S") || resposta.equals("SIM")) {
            System.out.println("Encerrando sistema...");
            executando = false;
            sistema.encerrar();
        }
    }
}

class InterfaceGrafica extends JFrame {
    private Sistema sistema;
    private ProdutoDAO produtoDAO;
    private JTextArea areaTexto;

    public InterfaceGrafica(Sistema sistema) {
        this.sistema = sistema;
        this.produtoDAO = new ProdutoDAO(sistema.getBancoDados().getConexao());
        configurarJanela();
        criarComponentes();
        aplicarTema();
    }

    private void configurarJanela() {
        setTitle("Mercado Sarruf - Sistema de Gestão");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void criarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();

        JMenu menuProdutos = new JMenu("Produtos");
        JMenuItem itemCadastrar = new JMenuItem("Cadastrar");
        JMenuItem itemListar = new JMenuItem("Listar Todos");
        JMenuItem itemPesquisar = new JMenuItem("Pesquisar");
        JMenuItem itemExcluir = new JMenuItem("Excluir");
        JMenuItem itemAlterar = new JMenuItem("Alterar");
        JMenuItem itemSair = new JMenuItem("Sair");

        itemCadastrar.addActionListener(e -> mostrarDialogoCadastro());
        itemListar.addActionListener(e -> listarProdutos());
        itemPesquisar.addActionListener(e -> mostrarDialogoPesquisa());
        itemExcluir.addActionListener(e -> mostrarDialogoExclusao());
        itemAlterar.addActionListener(e -> mostrarDialogoAlteracao());
        itemSair.addActionListener(e -> sistema.encerrar());

        menuProdutos.add(itemCadastrar);
        menuProdutos.add(itemListar);
        menuProdutos.add(itemPesquisar);
        menuProdutos.addSeparator();
        menuProdutos.add(itemExcluir);
        menuProdutos.add(itemAlterar);
        menuProdutos.addSeparator();
        menuProdutos.add(itemSair);

        JMenu menuConfig = new JMenu("Configurações");
        JMenuItem itemTemas = new JMenuItem("Temas");
        itemTemas.addActionListener(e -> mostrarDialogoTemas());
        menuConfig.add(itemTemas);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Mercado Sarruf v1.0\nDesenvolvido por @matheus-sarruf"));
        menuAjuda.add(itemSobre);

        menuBar.add(menuProdutos);
        menuBar.add(menuConfig);
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar);

        JPanel painelTopo = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("MERCADO SARRUF", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        painelTopo.add(lblTitulo, BorderLayout.CENTER);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 5, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnListar = new JButton("Listar");
        JButton btnPesquisar = new JButton("Pesquisar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnSair = new JButton("Sair");

        btnCadastrar.addActionListener(e -> mostrarDialogoCadastro());
        btnListar.addActionListener(e -> listarProdutos());
        btnPesquisar.addActionListener(e -> mostrarDialogoPesquisa());
        btnExcluir.addActionListener(e -> mostrarDialogoExclusao());
        btnSair.addActionListener(e -> sistema.encerrar());

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnPesquisar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnSair);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void aplicarTema() {
        sistema.getTemaManager().aplicarTema(this, sistema.getConfiguracao().getTema());
    }

    private void mostrarDialogoCadastro() {
        JDialog dialog = new JDialog(this, "Cadastrar Produto", true);
        dialog.setSize(400, 350);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNome = new JTextField(20);
        JTextField txtCodigo = new JTextField(20);
        JTextField txtPreco = new JTextField(20);
        JTextField txtQuantidade = new JTextField(20);
        JTextField txtMarca = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("Preço:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtPreco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(new JLabel("Quantidade:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtQuantidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtMarca, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            try {
                if (txtNome.getText().trim().isEmpty() ||
                        txtCodigo.getText().trim().isEmpty() ||
                        txtPreco.getText().trim().isEmpty() ||
                        txtQuantidade.getText().trim().isEmpty() ||
                        txtMarca.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Preencha todos os campos!",
                            "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Produto produto = new Produto(
                        txtNome.getText().trim(),
                        txtCodigo.getText().trim(),
                        Double.parseDouble(txtPreco.getText().replace(",", ".")),
                        Integer.parseInt(txtQuantidade.getText()),
                        txtMarca.getText().trim());
                if (produtoDAO.cadastrar(produto)) {
                    JOptionPane.showMessageDialog(dialog, "Produto cadastrado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    sistema.getSomManager().tocarEfeito("sucesso");
                    dialog.dispose();
                    listarProdutos();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erro ao cadastrar produto!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Valores numéricos inválidos!\nPreço e quantidade devem ser números.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        dialog.add(painelBotoes, gbc);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarTodos();
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE PRODUTOS ===\n\n");
        if (produtos.isEmpty()) {
            sb.append("Nenhum produto cadastrado.\n");
        } else {
            sb.append(String.format("%-5s %-20s %-10s %-10s %-8s %-15s\n",
                    "ID", "Nome", "Código", "Preço", "Qtd", "Marca"));
            sb.append("-".repeat(78)).append("\n");
            for (Produto p : produtos) {
                sb.append(String.format("%-5d %-20s %-10s R$%-9.2f %-8d %-15s\n",
                        p.getId(),
                        p.getNome().length() > 20 ? p.getNome().substring(0, 17) + "..." : p.getNome(),
                        p.getCodigo(),
                        p.getPreco(),
                        p.getQuantidade(),
                        p.getMarca().length() > 15 ? p.getMarca().substring(0, 12) + "..." : p.getMarca()));
            }
            double valorTotal = produtos.stream()
                    .mapToDouble(p -> p.getPreco() * p.getQuantidade())
                    .sum();
            sb.append("\nTotal de produtos: ").append(produtos.size());
            sb.append(String.format("\nValor total em estoque: R$ %.2f", valorTotal));
        }
        areaTexto.setText(sb.toString());
    }

    private void mostrarDialogoPesquisa() {
        JDialog dialog = new JDialog(this, "Pesquisar Produtos", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new GridLayout(9, 2, 10, 10));
        dialog.setLocationRelativeTo(this);

        JTextField txtNome = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtCodigo = new JTextField();
        JTextField txtPrecoMin = new JTextField();
        JTextField txtPrecoMax = new JTextField();
        JTextField txtQuantMin = new JTextField();
        JTextField txtQuantMax = new JTextField();

        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("Marca:"));
        dialog.add(txtMarca);
        dialog.add(new JLabel("Código:"));
        dialog.add(txtCodigo);
        dialog.add(new JLabel("Preço Mínimo:"));
        dialog.add(txtPrecoMin);
        dialog.add(new JLabel("Preço Máximo:"));
        dialog.add(txtPrecoMax);
        dialog.add(new JLabel("Quantidade Mínima:"));
        dialog.add(txtQuantMin);
        dialog.add(new JLabel("Quantidade Máxima:"));
        dialog.add(txtQuantMax);

        JButton btnPesquisar = new JButton("Pesquisar");
        JButton btnCancelar = new JButton("Cancelar");

        btnPesquisar.addActionListener(e -> {
            try {
                Double precoMin = txtPrecoMin.getText().isEmpty() ? null
                        : Double.parseDouble(txtPrecoMin.getText().replace(",", "."));
                Double precoMax = txtPrecoMax.getText().isEmpty() ? null
                        : Double.parseDouble(txtPrecoMax.getText().replace(",", "."));
                Integer quantMin = txtQuantMin.getText().isEmpty() ? null
                        : Integer.parseInt(txtQuantMin.getText());
                Integer quantMax = txtQuantMax.getText().isEmpty() ? null
                        : Integer.parseInt(txtQuantMax.getText());
                List<Produto> resultados = produtoDAO.pesquisar(
                        txtNome.getText().isEmpty() ? null : txtNome.getText(),
                        txtMarca.getText().isEmpty() ? null : txtMarca.getText(),
                        txtCodigo.getText().isEmpty() ? null : txtCodigo.getText(),
                        precoMin, precoMax, quantMin, quantMax);
                mostrarResultadosPesquisa(resultados);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Valores numéricos inválidos!");
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnPesquisar);
        dialog.add(btnCancelar);
        dialog.setVisible(true);
    }

    private void mostrarResultadosPesquisa(List<Produto> resultados) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADOS DA PESQUISA ===\n\n");
        if (resultados.isEmpty()) {
            sb.append("Nenhum produto encontrado.\n");
        } else {
            sb.append(String.format("%-5s %-20s %-10s %-10s %-8s %-15s\n",
                    "ID", "Nome", "Código", "Preço", "Qtd", "Marca"));
            sb.append("-".repeat(78)).append("\n");
            for (Produto p : resultados) {
                sb.append(String.format("%-5d %-20s %-10s R$%-9.2f %-8d %-15s\n",
                        p.getId(),
                        p.getNome().length() > 20 ? p.getNome().substring(0, 17) + "..." : p.getNome(),
                        p.getCodigo(),
                        p.getPreco(),
                        p.getQuantidade(),
                        p.getMarca().length() > 15 ? p.getMarca().substring(0, 12) + "..." : p.getMarca()));
            }
            sb.append("\nTotal encontrado: ").append(resultados.size()).append(" produtos");
        }
        areaTexto.setText(sb.toString());
    }

    private void mostrarDialogoExclusao() {
        String codigo = JOptionPane.showInputDialog(this, "Código do produto a excluir:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Produto produto = produtoDAO.buscarPorCodigo(codigo.trim());
            if (produto == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Excluir produto:\n"
                            + "Nome: " + produto.getNome() + "\n"
                            + "Código: " + produto.getCodigo() + "\n"
                            + "Preço: R$ " + produto.getPreco() + "\n"
                            + "Quantidade: " + produto.getQuantidade() + "\n\n"
                            + "Confirmar exclusão?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (produtoDAO.excluir(codigo.trim())) {
                    JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                    sistema.getSomManager().tocarEfeito("exclusao");
                    listarProdutos();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir produto!");
                }
            }
        }
    }

    private void mostrarDialogoAlteracao() {
        String codigo = JOptionPane.showInputDialog(this, "Código do produto a alterar:");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Produto produto = produtoDAO.buscarPorCodigo(codigo.trim());
            if (produto == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado!");
                return;
            }
            JDialog dialog = new JDialog(this, "Alterar Produto", true);
            dialog.setSize(400, 350);
            dialog.setLayout(new GridLayout(7, 2, 10, 10));
            dialog.setLocationRelativeTo(this);
            JTextField txtNome = new JTextField(produto.getNome());
            JTextField txtPreco = new JTextField(String.valueOf(produto.getPreco()));
            JTextField txtQuantidade = new JTextField(String.valueOf(produto.getQuantidade()));
            JTextField txtMarca = new JTextField(produto.getMarca());
            dialog.add(new JLabel("Código (não alterável):"));
            dialog.add(new JLabel(codigo));
            dialog.add(new JLabel("Nome:"));
            dialog.add(txtNome);
            dialog.add(new JLabel("Preço (R$):"));
            dialog.add(txtPreco);
            dialog.add(new JLabel("Quantidade:"));
            dialog.add(txtQuantidade);
            dialog.add(new JLabel("Marca:"));
            dialog.add(txtMarca);
            JButton btnSalvar = new JButton("Salvar Alterações");
            JButton btnCancelar = new JButton("Cancelar");
            btnSalvar.addActionListener(e -> {
                try {
                    produto.setNome(txtNome.getText());
                    produto.setPreco(Double.parseDouble(txtPreco.getText().replace(",", ".")));
                    produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                    produto.setMarca(txtMarca.getText());
                    if (produtoDAO.alterar(produto)) {
                        JOptionPane.showMessageDialog(dialog, "Produto alterado com sucesso!");
                        sistema.getSomManager().tocarEfeito("alteracao");
                        dialog.dispose();
                        listarProdutos();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Erro ao alterar produto!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Valores numéricos inválidos!");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, ex.getMessage());
                }
            });
            btnCancelar.addActionListener(e -> dialog.dispose());
            dialog.add(btnSalvar);
            dialog.add(btnCancelar);
            dialog.setVisible(true);
        }
    }

    private void mostrarDialogoTemas() {
        String[] temas = { "Tema_Escuro", "Tema_Claro", "Tema_Preto", "Tema_Branco" };
        String escolha = (String) JOptionPane.showInputDialog(
                this,
                "Escolha um tema:",
                "Alterar Tema",
                JOptionPane.QUESTION_MESSAGE,
                null,
                temas,
                sistema.getConfiguracao().getTema());
        if (escolha != null) {
            sistema.getConfiguracao().setTema(escolha);
            sistema.getTemaManager().setTemaAtual(escolha);
            aplicarTema();
            JOptionPane.showMessageDialog(this, "Tema alterado para: " + escolha);
        }
    }

    public void iniciar() {
        setVisible(true);
        listarProdutos();
        if (produtoDAO.listarTodos().isEmpty()) {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Nenhum produto cadastrado. Deseja cadastrar produtos de exemplo?",
                    "Primeira Execução",
                    JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                cadastrarProdutosExemplo();
            }
        }
    }

    private void cadastrarProdutosExemplo() {
        Produto[] exemplos = {
                new Produto("Café Premium 500g", "1001", 25.90, 50, "Café do Ponto"),
                new Produto("Ovo Branco 30un", "1002", 12.50, 200, "Granja Feliz"),
                new Produto("Açúcar Refinado 1kg", "1003", 8.90, 80, "Doce Brasil"),
                new Produto("Arroz Integral 5kg", "1004", 22.75, 60, "Camil"),
                new Produto("Feijão Carioca 1kg", "1005", 9.99, 100, "Kicaldo")
        };
        int sucessos = 0;
        for (Produto p : exemplos) {
            if (produtoDAO.inserir(p)) {
                sucessos++;
            }
        }
        JOptionPane.showMessageDialog(this,
                String.format("%d produtos de exemplo cadastrados com sucesso!", sucessos),
                "Cadastro Exemplo",
                JOptionPane.INFORMATION_MESSAGE);
        listarProdutos();
    }
}