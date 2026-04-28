
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

abstract class Veiculo {

    private String chassi;
    private String marca;
    private String modelo;
    private int ano;
    private double preco;
    private String cor;

    public Veiculo(String chassi, String marca, String modelo, int ano, double preco, String cor) {
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;

    }

    public String getChassi() {
        return this.chassi;
    }

    public void setMarca(String marca) {
        this.marca = marca;

    }

    public String getMarca() {
        return this.marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;

    }

    public String getModelo() {
        return this.modelo;
    }

    public void setAno(int ano) {
        this.ano = ano;

    }

    public int getAno() {
        return this.ano;
    }

    public void setPreco(double preco) {
        this.preco = preco;

    }

    public double getPreco() {
        return this.preco;
    }

    public void setCor(String cor) {
        this.cor = cor;

    }

    public String getCor() {
        return this.cor;
    }

    public abstract void exibirDetalhes();

}

interface Vendavel {

    public void calcularPrecoVenda(Veiculo veiculo, double desconto);

    public void registrarVenda(Veiculo carro, Cliente cliente, Vendedor vendedor, Date dataVenda);
}

class Carro extends Veiculo {

    private int numeroPortas;
    private String tipoCombustivel;
    private String potenciaMotor;

    public Carro(String chassi, String marca, String modelo, int ano, double preco, String cor, int numeroPortas, String tipoCombustivel, String potenciaMotor) {
        super(chassi, marca, modelo, ano, preco, cor);
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
        this.potenciaMotor = potenciaMotor;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public int getNumeroPortas() {
        return this.numeroPortas;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getTipoCombustivel() {
        return this.tipoCombustivel;
    }

    public void setPotenciaMotor(String potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public String getPotenciaMotor() {
        return this.potenciaMotor;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Chassi: " + this.getChassi() + " "
                + "Marca: " + this.getMarca() + " "
                + "Modelo: " + this.getModelo() + " "
                + "Ano: " + this.getAno() + " "
                + "Preço: " + this.getPreco() + " "
                + "Cor: " + this.getCor() + " "
                + "Numero Portas: " + this.getNumeroPortas() + " "
                + "Tipo Combustivel: " + this.getTipoCombustivel() + " "
                + "Potencia Motor: " + this.getPotenciaMotor());
    }
}

class Cliente {

    private String nome;
    private String cpf;
    private String telefone;

    public Cliente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}

class Vendedor {

    private String nome;
    private String matricula;

    public Vendedor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}

class GerenciadorLoja implements Vendavel {

    ArrayList<Veiculo> estoque = new ArrayList<>();

    public ArrayList<Veiculo> getEstoque() {
        return estoque;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        estoque.add(veiculo);
    }

    public void excluirVeiculo(String chassi) {
        for (Veiculo veiculo : estoque) {
            if (veiculo.getChassi().equals(chassi)) {
                estoque.remove(veiculo);
                System.out.println("carro removido");
            }
        }
    }

    public void buscarVeiculo(String chassi) {
        for (Veiculo veiculo : estoque) {
            if (veiculo.getChassi().equals(chassi)) {
                veiculo.exibirDetalhes();
            }
        }
    }

    public void listarEstoque() {
        for (Veiculo veiculo : estoque) {
            veiculo.exibirDetalhes();
        }
    }

    @Override
    public void calcularPrecoVenda(Veiculo veiculo, double desconto) {
        veiculo.setPreco(veiculo.getPreco() - (veiculo.getPreco() * desconto / 100));
        System.out.println("Preço com desconto: " + veiculo.getPreco());
    }

    @Override
    public void registrarVenda(Veiculo carro, Cliente cliente, Vendedor vendedor, Date dataVenda) {
        System.out.println("Venda registrada: Cliente " + cliente.getNome()
                + ", Vendedor: " + vendedor.getNome()
                + ", Veiculo: " + carro.getChassi()
                + ", Data: " + dataVenda);
    }
}

public class ClassePrincipal {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        boolean existe;
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        GerenciadorLoja loja = new GerenciadorLoja();
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("Digite 1 para entrar no sistema carro");
            System.out.println("Digite 2 para cadastrar carro");
            System.out.println("Digite 3 para cadastrar vendedor");
            System.out.println("Digite 4 para cadastrar cliente");
            System.out.println("Digite 5 para realizar venda");
            System.out.println("Digite 6 para sair");
            System.out.println("Opção escolhida:");
            opcao = ler.nextInt();

            if (opcao == 1) {
                while (opcao != 4) {
                    System.out.println("Digite 1 para buscar um carro");
                    System.out.println("Digite 2 para remover carro");
                    System.out.println("Digite 3 para exibir carro");
                    System.out.println("Digite 4 para sair");
                    System.out.println("Opção escolhida:");
                    opcao = ler.nextInt();

                    if (opcao == 1) {
                        System.out.println("Digite o chassi do carro que busca: ");
                        String acharChassi = ler.next();
                        loja.buscarVeiculo(acharChassi);
                    } else if (opcao == 2) {
                        System.out.println("Digite o chassi do carro que remove: ");
                        String removerChassi = ler.next();
                        loja.excluirVeiculo(removerChassi);
                    } else if (opcao == 3) {
                        loja.listarEstoque();
                    }
                }
            } else if (opcao == 2) {
                System.out.println("Digite o chassi do carro: ");
                String chassi = ler.next();
                System.out.println("Digite a marca do carro: ");
                String marca = ler.next();
                System.out.println("Digite o modelo do carro: ");
                String modelo = ler.next();
                System.out.println("Digite o ano do carro: ");
                int ano = ler.nextInt();
                System.out.println("Digite o preço do carro: ");
                double preco = ler.nextDouble();
                System.out.println("Digite a cor do carro: ");
                String cor = ler.next();
                System.out.println("Digite o número de portas do carro: ");
                int portas = ler.nextInt();
                System.out.println("Digite o tipo de combustivel do carro: ");
                String tipo = ler.next();
                System.out.println("Digite a potencia do motor do carro: ");
                String potencia = ler.next();

                Carro carro = new Carro(chassi, marca, modelo, ano, preco, cor, portas, tipo, potencia);
                carro.exibirDetalhes();

                loja.cadastrarVeiculo(carro);

            } else if (opcao == 3) {
                System.out.println("Digite o nome do vendedor: ");
                String nomeVendedor = ler.next();
                System.out.println("Digite a matricula do vendedor: ");
                String matricula = ler.next();
                Vendedor vendedor = new Vendedor(nomeVendedor, matricula);
                System.out.println("Informações do vendedor: " + vendedor.getNome() + " / " + vendedor.getMatricula());
                vendedores.add(vendedor);
            } else if (opcao == 4) {
                System.out.println("Digite o nome do cliente: ");
                String nomeCliente = ler.next();
                System.out.println("Digite o cpf do cliente: ");
                String cpfCliente = ler.next();
                System.out.println("Digite o telefone do cliente: ");
                String telefoneCliente = ler.next();

                Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente);
                System.out.println("Informações do cliente: " + cliente.getNome() + " / " + cliente.getCpf() + " / " + cliente.getTelefone());
                clientes.add(cliente);
            } else if (opcao == 5) {

                System.out.print("Digite o chassi do carro para registrar a venda: ");
                String chassi = ler.next();
                existe = false;
                int[] pegar_indice = new int[3];
                for (Veiculo carro : loja.getEstoque()) {
                    if (carro.getChassi().equals(chassi)) {
                        pegar_indice[0] = loja.getEstoque().indexOf(carro);
                        existe = true;
                        break;
                    }
                }

                if (!existe) {
                    System.out.println("Carro não encontrado.");
                } else {
                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = ler.next();
                    existe = false;
                    for (Cliente cliente : clientes) {
                        if (cliente.getCpf().equals(cpf)) {
                            existe = true;
                            pegar_indice[1] = clientes.indexOf(cliente);
                            break;
                        }
                    }
                    if (!existe) {
                        System.out.println("Cliente não encontrado.");
                    } else {
                        System.out.print("Digite a matricula do vendedor: ");
                        String matricula = ler.next();
                        existe = false;
                        for (Vendedor vendedor : vendedores) {
                            if (vendedor.getMatricula().equals(matricula)) {
                                pegar_indice[2] = vendedores.indexOf(vendedor);
                                existe = true;
                                break;
                            }
                        }

                        if (existe) {
                            Veiculo carro = loja.getEstoque().get(pegar_indice[0]);
                            Cliente cliente = clientes.get(pegar_indice[1]);
                            Vendedor vendedor = vendedores.get(pegar_indice[2]);
                            Date data = new Date();
                            loja.registrarVenda(carro, cliente, vendedor, data); // Simula a data
                            System.out.println("Venda registrada com sucesso!");
                            System.out.println("Carro vendido: ");
                            carro.exibirDetalhes();
                        } else {
                            System.out.println("Vendedor não encontrado.");
                        }
                    }
                }
            }
        }
    }
}
