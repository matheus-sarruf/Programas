import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import java.util.*;
import java.util.List;
import javax.swing.table.*;
import javax.swing.border.*;

public class MercadoSarruf extends JFrame {
    private JLabel titulo;
    private JPanel painelPrincipal;
    private JPanel painelCentral;
    private CardLayout trocarPainels;

    private String[] temas = { "Tema Escuro Azul", "Tema Claro", "Tema Escuro Verde", "Tema Escuro Vermelho" };
    private String temaAtual = "Tema Escuro Azul";

    private Connection connection;
    private String URL = "jdbc:mysql://localhost:3306/mercado_sarruf1";
    private String usuario = "root";
    private String senha = "Mm37843737@";

    private JPanel painelCadastro;
    private JPanel painelListagem;
    private JPanel painelExclusao;
    private JPanel painelAlteracao;

    private JTable tabelaProdutos;
    private JScrollPane scrolTabela;
    private DefaultTableModel tabela;

    private TitledBorder bordaAviso;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MercadoSarruf frame = new MercadoSarruf();
            frame.setVisible(true);
        });
    }

    public MercadoSarruf() {
        conectarBanco();

        setTitle("Mercado Sarruf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 900);
        setLocationRelativeTo(null);

        painelPrincipal = new JPanel();
        painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        painelPrincipal.setPreferredSize(new Dimension(1440, 650));
        painelPrincipal.setLayout(new BorderLayout(0, 0));

        setContentPane(painelPrincipal);

        aplicarTema(temaAtual);

        JPanel painelHeader = criarHeader();
        painelPrincipal.add(painelHeader, BorderLayout.NORTH);
        JPanel painelNav = criarNavegacao();
        painelPrincipal.add(painelNav, BorderLayout.CENTER);

        trocarPainels = new CardLayout();
        painelCentral = new JPanel(trocarPainels);

        painelCentral.add(criarPainelInicio(), "Inicio");
        painelCentral.add(criarPainelCadastrar(), "Cadastrar");
        painelCentral.add(criarPainelListar(), "Listar");
        painelCentral.add(criarPainelExcluir(), "Excluir");
        painelCentral.add(criarPainelAlterar(), "Alterar");
        trocarPainels.show(painelCentral, "Inicio");

        painelPrincipal.add(painelCentral, BorderLayout.SOUTH);
    }

    private void consertarBotao(JButton butao) {
        butao.setFocusPainted(false);
        butao.setRolloverEnabled(false);
        butao.setBackground(new Color(0, 155, 155));
        butao.setForeground(new Color(0, 255, 235));
    }

    private void conectarBanco() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, usuario, senha);
            System.out.println("Conectado ao banco de dados");
            criarTabelaProdutos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao conectar com o banco de dados: " + e.getMessage(),
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void opcoesTemas() {
        JDialog janelhinaMsgB = new JDialog(this, "Selecionar Tema", true);
        janelhinaMsgB.setLayout(new GridLayout(0, 1, 5, 5));
        janelhinaMsgB.setSize(400, 350);
        janelhinaMsgB.setLocationRelativeTo(this);
        janelhinaMsgB.getContentPane().setBackground(new Color(0, 15, 60));
        janelhinaMsgB.setResizable(false);

        for (String tema : temas) {
            JButton butao = new JButton(tema);
            butao.addActionListener(e -> {
                aplicarTema(tema);
                temaAtual = tema;
                janelhinaMsgB.dispose();
            });
            consertarBotao(butao);
            janelhinaMsgB.add(butao);
        }

        janelhinaMsgB.setVisible(true);
    }

    private void aplicarTema(String tema) {
        Color corFundo = null;
        Color corTexto = null;

        switch (tema) {
            case "Tema Escuro Azul":
                corFundo = new Color(0, 15, 60);
                break;
            case "Tema Claro":
                corFundo = new Color(100, 200, 255);
                corTexto = Color.BLUE;
                break;
            case "Tema Escuro Verde":
                corFundo = Color.BLACK;
                break;
            case "Tema Escuro Vermelho":
                corFundo = Color.RED;
                corTexto = Color.WHITE;
                break;
        }

        painelPrincipal.setBackground(corFundo);
    }

    private JPanel criarHeader() {
        JPanel painelHeader = new JPanel();
        painelHeader.setLayout(new BorderLayout(10, 10));
        painelHeader.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 25));
        painelHeader.setBackground(new Color(0, 255, 235, 100));

        titulo = new JLabel("Mercado Sarruf");
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(new Color(0, 255, 235, 230));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel promocao = new JLabel("Promoção de café e ovo");
        promocao.setFont(new Font("Arial", Font.PLAIN, 28));
        promocao.setForeground(new Color(0, 255, 235, 230));
        promocao.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.setOpaque(false);
        painelTextos.add(titulo);
        painelTextos.add(promocao);

        JButton butaoTema = new JButton("Tema");
        butaoTema.addActionListener(e -> opcoesTemas());
        consertarBotao(butaoTema);

        painelHeader.add(painelTextos, BorderLayout.CENTER);
        painelHeader.add(butaoTema, BorderLayout.EAST);

        return painelHeader;
    }

    private JPanel criarNavegacao() {
        JPanel painelNav = new JPanel();
        painelNav.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelNav.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255), 2));
        painelNav.setBackground(new Color(0, 255, 235, 100));
        painelNav.setPreferredSize(new Dimension(900, 60));

        String[] botoes = { "Inicio", "Listar", "Cadastrar", "Excluir", "Alterar" };

        for (String texto : botoes) {
            JButton butao = new JButton(texto);
            butao.setPreferredSize(new Dimension(120, 55));
            butao.setFont(new Font("Arial", Font.BOLD, 16));
            butao.addActionListener(e -> {
                trocarPainels.show(painelCentral, texto);
                scrollParaCentro();
            });
            butao.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255)));
            consertarBotao(butao);
            painelNav.add(butao);
        }

        return painelNav;
    }

    private void scrollParaCentro() {
        revalidate();
        repaint();
    }

    private JPanel criarPainelInicio() {
        JPanel painelInicio = new JPanel();
        painelInicio.setLayout(new BorderLayout());
        painelInicio.setBackground(new Color(0, 255, 235, 50));
        painelInicio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel texto = new JLabel("Bem-vindo ao Mercado Sarruf - Sistema de Gerenciamento de Produtos",
                SwingConstants.CENTER);
        texto.setFont(new Font("Arial", Font.BOLD, 26));
        texto.setForeground(new Color(0, 255, 235, 230));

        JLabel texto2 = new JLabel("Selecione uma opção no menu acima para começar", SwingConstants.CENTER);
        texto2.setFont(new Font("Arial", Font.PLAIN, 20));
        texto2.setForeground(new Color(0, 255, 235, 230));

        JPanel painelTexto = new JPanel();
        painelTexto.setLayout(new BoxLayout(painelTexto, BoxLayout.Y_AXIS));
        painelTexto.setOpaque(false);
        painelTexto.add(texto);
        painelTexto.add(Box.createRigidArea(new Dimension(0, 20)));
        painelTexto.add(texto2);

        painelInicio.add(painelTexto, BorderLayout.CENTER);
        return painelInicio;
    }

    private JPanel criarPainelCadastrar() {
        if (painelCadastro != null) {
            return painelCadastro;
        }

        painelCadastro = new JPanel();
        painelCadastro.setLayout(new BorderLayout(10, 10));
        painelCadastro.setBackground(new Color(0, 255, 235, 50));
        painelCadastro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de Produtos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(0, 255, 235, 230));
        painelCadastro.add(titulo, BorderLayout.NORTH);

        JPanel painelForm = new JPanel();
        painelForm.setLayout(new GridLayout(6, 2, 10, 10));
        painelForm.setBackground(new Color(0, 255, 235, 50));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        String[] campos = { "Nome:", "Código:", "Preço:", "Quantidade:", "Marca:" };
        JTextField[] inputsTexto = new JTextField[campos.length];

        for (int i = 0; i < campos.length; i++) {
            JLabel label = new JLabel(campos[i]);
            label.setForeground(new Color(0, 255, 235, 230));
            label.setFont(new Font("Arial", Font.BOLD, 20));
            painelForm.add(label);

            inputsTexto[i] = new JTextField();
            inputsTexto[i].setFont(new Font("Arial", Font.PLAIN, 20));
            inputsTexto[i].setForeground(new Color(0, 255, 255));
            inputsTexto[i].setBackground(new Color(0, 160, 200));
            painelForm.add(inputsTexto[i]);
        }

        painelForm.add(new JLabel());
        JButton cadastrarButao = new JButton("Cadastrar");
        cadastrarButao.addActionListener(e -> cadastrarProduto(inputsTexto));
        cadastrarButao.setFont(new Font("Arial", Font.BOLD, 20));
        consertarBotao(cadastrarButao);
        painelForm.add(cadastrarButao);
        painelCadastro.add(painelForm, BorderLayout.CENTER);

        JPanel backup = new JPanel();
        backup.setLayout(new BorderLayout(10, 10));
        backup.setBackground(new Color(0, 255, 235, 50));
        Border bordaAmarela = BorderFactory.createLineBorder(Color.YELLOW);
        bordaAviso = BorderFactory.createTitledBorder(bordaAmarela, "⚠️ ATENÇÃO ⚠️");
        bordaAviso.setTitleColor(Color.YELLOW);
        backup.setBorder(bordaAviso);

        JButton backupButao = new JButton("BACKUP");
        backupButao.setFont(new Font("Arial", Font.BOLD, 20));
        backupButao.setBackground(Color.YELLOW);
        backupButao.setForeground(Color.BLACK);
        backupButao.setActionCommand("BACKUP");
        backupButao.addActionListener(e -> confirmarAcao(e));
        backupButao.setPreferredSize(new Dimension(200, 40));

        JLabel aviso = new JLabel(
                "Ao clicar no botão BACKUP, todos os dados serao perdidos e os dados do backup serao restaurados.");
        aviso.setForeground(Color.ORANGE);
        aviso.setHorizontalAlignment(SwingConstants.CENTER);
        aviso.setFont(new Font("Arial", Font.PLAIN, 15));

        backup.add(backupButao, BorderLayout.CENTER);
        backup.add(aviso, BorderLayout.SOUTH);
        painelCadastro.add(backup, BorderLayout.SOUTH);

        return painelCadastro;
    }

    private void cadastrarProduto(JTextField[] campos) {
        try {
            String nome = campos[0].getText();
            String codigo = campos[1].getText();
            String preco = campos[2].getText();
            String quantidade = campos[3].getText();
            String marca = campos[4].getText();

            PreparedStatement stmtVerif = connection.prepareStatement("SELECT * FROM produtos WHERE codigo = ?");
            stmtVerif.setString(1, codigo);
            ResultSet rsVerif = stmtVerif.executeQuery();
            if (rsVerif.next()) {
                JOptionPane.showMessageDialog(this, "Código ja cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                rsVerif.close();
                stmtVerif.close();
                return;
            }
            rsVerif.close();
            stmtVerif.close();

            if (nome.isEmpty() || codigo.isEmpty() || preco.isEmpty() || quantidade.isEmpty() || marca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO produtos (nome, codigo, preco, quantidade, marca) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, codigo);
            stmt.setDouble(3, Double.parseDouble(preco.replace(",", ".")));
            stmt.setInt(4, Integer.parseInt(quantidade));
            stmt.setString(5, marca);

            int i = stmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                for (JTextField campo : campos) {
                    campo.setText("");
                }
            }
            stmt.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique os formatos dos campos numéricos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarAcao(ActionEvent e) {
        String chamouQ = e.getActionCommand();
        int confirma = 0;

        switch (chamouQ) {
            case "BACKUP":
                confirma = JOptionPane.showConfirmDialog(this,
                        "Tem certeza que deseja excluir TODOS os dados do banco e restaurar os dados do backup?\nEsta ação não pode ser desfeita!",
                        "Confirmação de Restauração do Backup",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (confirma == JOptionPane.YES_OPTION) {
                    restaurarBackup();
                }
                break;
            case "EXCLUIR_TUDO":
                confirma = JOptionPane.showConfirmDialog(this,
                        "Tem certeza que deseja excluir TODOS os dados do banco?\nEsta ação não pode ser desfeita!",
                        "Confirmação de Exclusão Total",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (confirma == JOptionPane.YES_OPTION) {
                    excluirTudo();
                }
                break;
        }
    }

    private void restaurarBackup() {
        try {
            String sql = "TRUNCATE TABLE produtos";
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, i + " produtos excluídos com sucesso!");
            stmt.close();

            String insertSQL = "INSERT INTO produtos (nome, codigo, preco, quantidade, marca) VALUES "
                    + "('Arroz', '123', 36.99, 30, 'Cristal'), "
                    + "('Feijão', '321', 40.99, 30, 'Macamarena'), "
                    + "('Café', '231', 30.59, 30, 'Mandela'), "
                    + "('Ovo', '312', 75.99, 30, 'Coamo'), "
                    + "('Vontade de viver', '111', 99999.99, 0, 'VIDA +')";

            stmt = connection.createStatement();
            int i2 = stmt.executeUpdate(insertSQL);
            JOptionPane.showMessageDialog(this, i2 + " produtos restaurados com sucesso!");
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao restaurar backup: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel criarPainelListar() {
        if (painelListagem != null) {
            return painelListagem;
        }

        painelListagem = new JPanel();
        painelListagem.setLayout(new BorderLayout(10, 10));
        painelListagem.setBackground(new Color(0, 255, 235, 50));
        painelListagem.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Listagem de Produtos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(0, 255, 235, 230));
        painelListagem.add(titulo, BorderLayout.NORTH);

        JButton listarButao = new JButton("Atualizar");
        consertarBotao(listarButao);
        listarButao.addActionListener(e -> listarProdutos());
        listarButao.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel painelCentral = new JPanel();
        painelCentral.setBackground(new Color(0, 255, 235, 50));
        painelCentral.add(listarButao);
        painelListagem.add(painelCentral, BorderLayout.CENTER);

        tabela = criarModeloTabela();
        tabelaProdutos = new JTable(tabela);
        consertarTabela();

        scrolTabela = new JScrollPane(tabelaProdutos);
        scrolTabela.setBorder(BorderFactory.createTitledBorder("Lista de Produtos"));
        scrolTabela.setPreferredSize(new Dimension(800, 450));
        painelListagem.add(scrolTabela, BorderLayout.SOUTH);

        return painelListagem;
    }

    private DefaultTableModel criarModeloTabela() {
        String[] colunas = { "ID", "Nome", "Código", "Preço", "Quantidade", "Marca" };
        return new DefaultTableModel(new Object[0][0], colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void consertarTabela() {
        JTableHeader header = tabelaProdutos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(0, 255, 255));
        header.setForeground(Color.BLACK);

        tabelaProdutos.setRowHeight(25);
        tabelaProdutos.setFont(new Font("Arial", Font.PLAIN, 16));
        tabelaProdutos.setGridColor(new Color(0, 255, 255));
        tabelaProdutos.setShowGrid(true);

        DefaultTableCellRenderer centroAlinha = new DefaultTableCellRenderer();
        centroAlinha.setHorizontalAlignment(JLabel.CENTER);
        tabelaProdutos.getColumnModel().getColumn(0).setCellRenderer(centroAlinha);
        tabelaProdutos.getColumnModel().getColumn(1).setCellRenderer(centroAlinha);
        tabelaProdutos.getColumnModel().getColumn(2).setCellRenderer(centroAlinha);
        tabelaProdutos.getColumnModel().getColumn(3).setCellRenderer(centroAlinha);
        tabelaProdutos.getColumnModel().getColumn(4).setCellRenderer(centroAlinha);
        tabelaProdutos.getColumnModel().getColumn(5).setCellRenderer(centroAlinha);
    }

    private void listarProdutos() {
        try {
            String sql = "SELECT id, nome, codigo, preco, quantidade, marca FROM produtos ORDER BY nome";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            tabela.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("marca")
                };
                tabela.addRow(row);
            }
            if (tabela.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this,
                        "Nenhum produto encontrado no banco de dados!",
                        "Informacão",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao listar produtos: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel criarPainelExcluir() {
        if (painelExclusao != null) {
            return painelExclusao;
        }

        painelExclusao = new JPanel();
        painelExclusao.setLayout(new BorderLayout(10, 10));
        painelExclusao.setBackground(new Color(0, 255, 235, 50));
        painelExclusao.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Exclusão de Produtos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(0, 255, 235, 230));
        painelExclusao.add(titulo, BorderLayout.NORTH);

        JPanel painelForm = new JPanel();
        painelForm.setLayout(new GridLayout(2, 2, 10, 10));
        painelForm.setBackground(new Color(0, 255, 235, 50));
        painelForm.setBorder(BorderFactory.createEmptyBorder(150, 100, 150, 100));

        JLabel codigoLabel = new JLabel("Código:");
        codigoLabel.setForeground(new Color(0, 255, 235, 230));
        codigoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        painelForm.add(codigoLabel);

        JTextField inputCodigo = new JTextField();
        inputCodigo.setFont(new Font("Arial", Font.PLAIN, 26));
        inputCodigo.setForeground(new Color(0, 255, 255));
        inputCodigo.setBackground(new Color(0, 160, 200));
        inputCodigo.setPreferredSize(new Dimension(200, 40));
        painelForm.add(inputCodigo);

        painelForm.add(new JLabel());
        JButton excluirButao = new JButton("Excluir");
        consertarBotao(excluirButao);
        excluirButao.addActionListener(e -> excluirProduto(inputCodigo.getText()));
        excluirButao.setPreferredSize(new Dimension(200, 40));
        painelForm.add(excluirButao);

        painelExclusao.add(painelForm, BorderLayout.CENTER);

        JPanel painelExcluirTudo = new JPanel();
        painelExcluirTudo.setLayout(new BorderLayout(10, 10));
        painelExcluirTudo.setBackground(new Color(0, 255, 235, 50));
        Border bordaAmarela = BorderFactory.createLineBorder(Color.YELLOW);
        bordaAviso = BorderFactory.createTitledBorder(bordaAmarela, "⚠️ ATENÇÃO ⚠️");
        bordaAviso.setTitleColor(Color.YELLOW);
        painelExcluirTudo.setBorder(bordaAviso);

        JButton excluirTudoButao = new JButton("EXCLUSÃO TOTAL");
        consertarBotao(excluirTudoButao);
        excluirTudoButao.setFont(new Font("Arial", Font.BOLD, 20));
        excluirTudoButao.setBackground(Color.RED);
        excluirTudoButao.setForeground(Color.BLACK);
        excluirTudoButao.setActionCommand("EXCLUIR_TUDO");
        excluirTudoButao.addActionListener(e -> confirmarAcao(e));
        excluirTudoButao.setPreferredSize(new Dimension(200, 40));

        JLabel aviso = new JLabel(
                "Ao clicar neste botão, TODOS os dados do banco serão excluídos permanentemente!");
        aviso.setForeground(Color.RED);
        aviso.setHorizontalAlignment(SwingConstants.CENTER);
        aviso.setFont(new Font("Arial", Font.PLAIN, 15));

        painelExcluirTudo.add(excluirTudoButao, BorderLayout.NORTH);
        painelExcluirTudo.add(aviso, BorderLayout.CENTER);
        painelExclusao.add(painelExcluirTudo, BorderLayout.SOUTH);

        return painelExclusao;
    }

    private void excluirProduto(String codigo) {
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um código para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String sql = "DELETE FROM produtos WHERE codigo = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            int i = stmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum produto encontrado com este código!", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirTudo() {
        try {
            String sql = "TRUNCATE TABLE produtos;";
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, i + " produtos excluídos com sucesso!");
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produtos: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel criarPainelAlterar() {
        if (painelAlteracao != null) {
            return painelAlteracao;
        }

        painelAlteracao = new JPanel();
        painelAlteracao.setLayout(new BorderLayout(10, 10));
        painelAlteracao.setBackground(new Color(0, 255, 235, 50));
        painelAlteracao.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Alteração de Produtos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(0, 255, 235, 230));
        painelAlteracao.add(titulo, BorderLayout.NORTH);

        JPanel painelII = new JPanel();
        painelII.setLayout(new GridLayout(2, 2, 10, 10));
        painelII.setBackground(new Color(0, 255, 235, 50));
        painelII.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JLabel codigoLabel = new JLabel("Codigo:");
        codigoLabel.setForeground(new Color(0, 255, 235, 230));
        codigoLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField inputCodigo = new JTextField();
        inputCodigo.setForeground(new Color(0, 255, 255));
        inputCodigo.setBackground(new Color(0, 160, 200));

        painelII.add(codigoLabel);
        painelII.add(inputCodigo);
        painelII.add(new JLabel());

        JButton procurarButao = new JButton("Procurar");
        consertarBotao(procurarButao);

        procurarButao.addActionListener(e -> {
            String codigoBusca = inputCodigo.getText().trim();
            if (codigoBusca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o código do produto para altera-lo!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String sql = "SELECT * FROM produtos WHERE codigo = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, codigoBusca);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    formInputsAlterar(rs);
                } else {
                    JOptionPane.showMessageDialog(this, "Nenhum produto encontrado com este código!", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
                rs.close();
                stmt.close();

            } catch (Exception ere) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar produto: " + ere.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        painelII.add(procurarButao);
        painelAlteracao.add(painelII, BorderLayout.NORTH);

        JPanel painelForm = new JPanel();
        painelForm.setLayout(new GridLayout(6, 2, 10, 10));
        painelForm.setBackground(new Color(0, 255, 235, 50));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        painelForm.setVisible(false);
        painelAlteracao.add(painelForm, BorderLayout.CENTER);

        return painelAlteracao;
    }

    private void formInputsAlterar(ResultSet rs) {
        try {
            JPanel painelForm = (JPanel) painelAlteracao.getComponent(2);
            painelForm.removeAll();
            painelForm.setVisible(true);

            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String codigo = rs.getString("codigo");
            double preco = rs.getDouble("preco");
            int quantidade = rs.getInt("quantidade");
            String marca = rs.getString("marca");

            String[] campos = { "Nome:", "Código:", "Preço:", "Quantidade:", "Marca:" };
            JTextField[] inputTexto = new JTextField[campos.length];

            for (int i = 0; i < campos.length; i++) {
                JLabel label = new JLabel(campos[i]);
                label.setForeground(new Color(0, 255, 235, 230));
                label.setFont(new Font("Arial", Font.BOLD, 20));
                painelForm.add(label);

                inputTexto[i] = new JTextField();
                inputTexto[i].setFont(new Font("Arial", Font.BOLD, 20));
                inputTexto[i].setForeground(new Color(0, 255, 255));
                inputTexto[i].setBackground(new Color(0, 160, 200));

                switch (i) {
                    case 0:
                        inputTexto[i].setText(nome);
                        break;
                    case 1:
                        inputTexto[i].setText(codigo);
                        break;
                    case 2:
                        inputTexto[i].setText(String.valueOf(preco));
                        break;
                    case 3:
                        inputTexto[i].setText(String.valueOf(quantidade));
                        break;
                    case 4:
                        inputTexto[i].setText(marca);
                        break;
                }
                painelForm.add(inputTexto[i]);
            }

            JButton alterarButao = new JButton("Alterar");
            consertarBotao(alterarButao);
            alterarButao.addActionListener(e -> alterarProduto(id, inputTexto));
            alterarButao.setBackground(new Color(0, 255, 235, 100));
            alterarButao.setForeground(new Color(0, 255, 235, 230));
            painelForm.add(new JLabel());
            painelForm.add(alterarButao);

            painelForm.revalidate();
            painelForm.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do produto: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarProduto(int id, JTextField[] campos) {
        try {
            String nome = campos[0].getText();
            String codigo = campos[1].getText();
            String preco = campos[2].getText();
            String quantidade = campos[3].getText();
            String marca = campos[4].getText();

            if (nome.isEmpty() || codigo.isEmpty() || preco.isEmpty() || quantidade.isEmpty() || marca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "UPDATE produtos SET nome = ?, codigo = ?, preco = ?, quantidade = ?, marca = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, codigo);
            stmt.setDouble(3, Double.parseDouble(preco.replace(",", ".")));
            stmt.setInt(4, Integer.parseInt(quantidade));
            stmt.setString(5, marca);
            stmt.setInt(6, id);
            int i = stmt.executeUpdate();

            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!");
                listarProdutos();
            }
            stmt.close();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique os formatos dos campos numéricos!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar produto: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarTabelaProdutos() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS produtos ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nome VARCHAR(100) NOT NULL, "
                    + "codigo VARCHAR(50) NOT NULL, "
                    + "preco DECIMAL(10,2) NOT NULL, "
                    + "quantidade INT NOT NULL, "
                    + "marca VARCHAR(100) NOT NULL)";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}