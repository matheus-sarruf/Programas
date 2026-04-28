
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

abstract class Veiculo {

    protected String chassi, marca, modelo, cor, classe;
    protected double preco;
    protected int ano;

    public Veiculo(String chassi, String marca, String modelo, String cor, double preco, int ano, String classe) {
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.classe = classe;
        this.preco = preco;
        this.ano = ano;
    }

    public String getChassi() {
        return this.chassi;
    }

    public String getMarca() {
        return this.marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getCor() {
        return this.cor;
    }

    public double getPreco() {
        return this.preco;
    }

    public int getAno() {
        return this.ano;
    }

    public String getClasse() {
        return this.classe;
    }
}

class Carro extends Veiculo {

    private int numPortas;
    private String tipoCombustivel, potenciaMotor;

    public Carro(String chassi, String marca, String modelo, String cor, double preco, int ano, int numPortas, String tipoCombustivel, String potenciaMotor, String classe) {
        super(chassi, marca, modelo, cor, preco, ano, classe);
        this.numPortas = numPortas;
        this.tipoCombustivel = tipoCombustivel;
        this.potenciaMotor = potenciaMotor;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public int getNumPortas() {
        return numPortas;
    }

    public String getPotenciaMotor() {
        return potenciaMotor;
    }
}

class Cliente {

    private String nome, telefone, CPF;

    public Cliente(String nome, String telefone, String CPF) {
        this.nome = nome;
        this.telefone = telefone;
        this.CPF = CPF;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getCPF() {
        return this.CPF;
    }
}

class Vendedor {

    private String nome, matricula;

    public Vendedor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMatricula() {
        return this.matricula;
    }
}

class GerenciadorLoja {

    private ArrayList<Veiculo> estoque = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Vendedor> vendedores = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo) {
        estoque.add(veiculo);
    }

    public ArrayList<Veiculo> getEstoque() {
        return estoque;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }
}

public class Estudo1 {

    private GerenciadorLoja gerenciadorLoja;
    private JFrame frame;
    private JTable veiculoTable, clienteTable, vendedorTable;

    public Estudo1() {
        gerenciadorLoja = new GerenciadorLoja();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Gerenciamento de Veículos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Veículos", createVeiculoPanel());
        tabbedPane.add("Clientes", createClientePanel());
        tabbedPane.add("Vendedores", createVendedorPanel());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createVeiculoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tabela de veículos
        veiculoTable = new JTable(new DefaultTableModel(new Object[]{"Chassi", "Marca", "Modelo", "Cor", "Preço", "Ano"}, 0));
        panel.add(new JScrollPane(veiculoTable), BorderLayout.CENTER);

        // Painel de entrada de dados para veículos
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        JTextField chassiField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField modeloField = new JTextField();
        JTextField corField = new JTextField();
        JTextField precoField = new JTextField();
        JTextField anoField = new JTextField();

        inputPanel.add(new JLabel("Chassi:"));
        inputPanel.add(chassiField);
        inputPanel.add(new JLabel("Marca:"));
        inputPanel.add(marcaField);
        inputPanel.add(new JLabel("Modelo:"));
        inputPanel.add(modeloField);
        inputPanel.add(new JLabel("Cor:"));
        inputPanel.add(corField);
        inputPanel.add(new JLabel("Preço:"));
        inputPanel.add(precoField);
        inputPanel.add(new JLabel("Ano:"));
        inputPanel.add(anoField);

        JButton addButton = new JButton("Adicionar Veículo");
        inputPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String chassi = chassiField.getText();
                    String marca = marcaField.getText();
                    String modelo = modeloField.getText();
                    String cor = corField.getText();
                    double preco = Double.parseDouble(precoField.getText());
                    int ano = Integer.parseInt(anoField.getText());

                    Veiculo veiculo = new Carro(chassi, marca, modelo, cor, preco, ano, 4, "Gasolina", "150CV", "Carro");
                    gerenciadorLoja.adicionarVeiculo(veiculo);
                    updateVeiculoTable();
                    clearFields(chassiField, marcaField, modeloField, corField, precoField, anoField);
                    JOptionPane.showMessageDialog(frame, "Veículo adicionado com sucesso!");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira valores válidos para preço e ano.");
                }
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        return panel;
    }

    private void updateVeiculoTable() {
        DefaultTableModel model = (DefaultTableModel) veiculoTable.getModel();
        model.setRowCount(0); // Limpa a tabela
        for (Veiculo v : gerenciadorLoja.getEstoque()) {
            model.addRow(new Object[]{v.getChassi(), v.getMarca(), v.getModelo(), v.getCor(), v.getPreco(), v.getAno()});
        }
    }

    private JPanel createClientePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tabela de Clientes
        clienteTable = new JTable(new DefaultTableModel(new Object[]{"Nome", "Telefone", "CPF"}, 0));
        panel.add(new JScrollPane(clienteTable), BorderLayout.CENTER);

        // Painel de entrada de dados para Clientes
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JTextField nomeField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField cpfField = new JTextField();

        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Telefone:"));
        inputPanel.add(telefoneField);
        inputPanel.add(new JLabel("CPF:"));
        inputPanel.add(cpfField);

        JButton addButton = new JButton("Adicionar Cliente");
        inputPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                String cpf = cpfField.getText();

                Cliente cliente = new Cliente(nome, telefone, cpf);
                gerenciadorLoja.adicionarCliente(cliente);
                updateClienteTable();
                clearFields(nomeField, telefoneField, cpfField);
                JOptionPane.showMessageDialog(frame, "Cliente adicionado com sucesso!");
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        return panel;
    }

    private void updateClienteTable() {
        DefaultTableModel model = (DefaultTableModel) clienteTable.getModel();
        model.setRowCount(0); // Limpa a tabela
        for (Cliente c : gerenciadorLoja.getClientes()) {
            model.addRow(new Object[]{c.getNome(), c.getTelefone(), c.getCPF()});
        }
    }

    private JPanel createVendedorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tabela de Vendedores
        vendedorTable = new JTable(new DefaultTableModel(new Object[]{"Nome", "Matrícula"}, 0));
        panel.add(new JScrollPane(vendedorTable), BorderLayout.CENTER);

        // Painel de entrada de dados para Vendedores
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JTextField nomeField = new JTextField();
        JTextField matriculaField = new JTextField();

        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Matrícula:"));
        inputPanel.add(matriculaField);

        JButton addButton = new JButton("Adicionar Vendedor");
        inputPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String matricula = matriculaField.getText();

                Vendedor vendedor = new Vendedor(nome, matricula);
                gerenciadorLoja.adicionarVendedor(vendedor);
                updateVendedorTable();
                clearFields(nomeField, matriculaField);
                JOptionPane.showMessageDialog(frame, "Vendedor adicionado com sucesso!");
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        return panel;
    }

    private void updateVendedorTable() {
        DefaultTableModel model = (DefaultTableModel) vendedorTable.getModel();
        model.setRowCount(0); // Limpa a tabela
        for (Vendedor v : gerenciadorLoja.getVendedores()) {
            model.addRow(new Object[]{v.getNome(), v.getMatricula()});
        }
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Estudo1::new);
    }
}
