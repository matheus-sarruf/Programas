import java.util.*;
//importando todas as classes da blibioteca java util

abstract class Veiculo{
   
    protected String chassi, marca, modelo, cor, classe; 
    protected double preco;
    protected int ano;
    // encapisulameto usando o protect que e menos seguro que o private, apenas as classes do mesmo pacote pode ter acesso aos atibutos
    public Veiculo(String chassi, String marca, String modelo, String cor, double preco, int ano, String classe){
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.classe = classe;
        this.preco = preco;
        this.ano = ano;
    }
    // contrutor para facilitar a entrada de dados e obrigar tal entrada na classe que herdar(carro)

    public String getClasse(){return this.classe;}
    public void setClasse(String classe){this.classe = classe;}
    public String getChassi(){return this.chassi;}
    public void setChassi(String chassi){this.chassi = chassi;}
    public String getMarca(){return this.marca;}
    public void setMarca(String marca){this.marca = marca;}
    public int getAno(){return this.ano;}
    public void setAno(int ano){this.ano = ano;}
    public String getCor(){return this.cor;}
    public void setCor(String cor){this.cor = cor;}
    public String getModelo(){return this.modelo;}
    public void setModelo(String modelo){this.modelo = modelo;}
    public double getPreco(){return this.preco;}
    public void setPreco(double preco){this.preco = preco;}
    // finalizacao do encapsulamento usando get para leitura e set para reescrita(obvio)
    public void mostrarDados(){
        System.out.println("+---------------------------------------------+");
        System.out.println("Classe:                " + this.classe);
        System.out.println("Chassi:                " + this.chassi);
        System.out.println("Marca:                 " + this.marca);
        System.out.println("Modelo:                " + this.modelo);
        System.out.println("Cor:                   " + this.cor);
        System.out.println("Preço:                 " + this.preco);
        System.out.println("Ano:                   " + this.ano);

    }
    //metado para mostra atibutos da classe
    public int compareTo(Veiculo veiculos){return Double.compare(this.preco, veiculos.preco);}
    //metodo para comparar precos dos veiculos e ordenar no array
}
 // criando uma classe abstrata que nao pode ser instanciada
class Carro extends Veiculo{
    private int numPortas;
    private String tipoCombustivel, potenciaMotor;
    // encapisulando usando o private que e mais seguro que o protect 
    public Carro(String chassi, String marca, String modelo, String cor, double preco, int ano, int numPortas, String tipoCombustivel, String potenciaMotor, String classe){
        super(chassi, marca, modelo, cor, preco, ano, classe);
        //chama o construtor da classe mae
        this.tipoCombustivel = tipoCombustivel;
        this.potenciaMotor = potenciaMotor;
        this.numPortas = numPortas;
    }
    // construtor para facilitar a entrada de dados e obrigar a entrada 

    public String getPotenciaMotor(){return this.potenciaMotor;}
    public void setPotenciaMotor(String potenciaMotor){this.potenciaMotor = potenciaMotor;}
    public int getNumPortas(){return this.numPortas;}
    public void setNumPortas(int numPortas){this.numPortas = numPortas;}
    public String getTipoCombustivel(){return this.tipoCombustivel;}
    public void setTipoCombustivel(String tipoCombustivel){this.tipoCombustivel = tipoCombustivel;}
    // finalizando emcapisulamento
    public void mostrarDados(){
        super.mostrarDados();
        System.out.println("Tipo de Combustível:   " + this.tipoCombustivel);
        System.out.println("Potência do Motor:     " + this.potenciaMotor);
        System.out.println("Número de Portas:      " + this.numPortas);
        System.out.println("+---------------------------------------------+");
    }
    // metado para mostra atributos sendo um polimorfismo de reescrita
}
 // criando uma classe que herda de veiculo
interface Vendavel{
    default double calcularPrecoVenda(double desconto, Veiculo veiculo){
        return veiculo.getPreco() - (veiculo.getPreco() * desconto / 100);
    }
    // metodo padrao que calcula o preco de venda com desconto
    default String registrarVenda(Cliente cliente, Vendedor vendedor, Veiculo veiculo, Date dataVenda){
        return 
                "+_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_+" +
                "\ncliente.                         " + "Data da Venda: " + dataVenda.toString() +
                "\nnome:      " + cliente.getNome() +
                "\nCPF:       " + cliente.getCPF() +
                "+---------------------------------------------+" +
                "\nvendedor.  " +
                "\nmatricula: " + vendedor.getMatricula() +
                "\nnome:      " + vendedor.getNome() +
                "+---------------------------------------------+" +
                "\nveiculo.   " + 
                "\npreco:     " + veiculo.getPreco() +
                "\nchassi:    " + veiculo.getChassi() +
                "+_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_+"
                ;
    }
    // metodo padrao que registra a venda
}
// criando uma interface
class Cliente{
    private String nome, telefone, CPF;
    // encapisulando
    public Cliente(String nome, String telefone, String CPF){
        this.nome = nome;
        this.telefone = telefone;
        this.CPF = CPF;
    }
    // construtor
    public String getNome(){return this.nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getTelefone(){return this.telefone;}
    public void setTelefone(String telefone){this.telefone = telefone;}
    public String getCPF(){return this.CPF;}
    public void setCPF(String CPF){this.CPF = CPF;}
    // finalizando encapisulamento
    public void mostrarDados(){
        System.out.println("+---------------------------------------------+");
        System.out.println(" telefone:   " + this.telefone);
        System.out.println(" nome:       " + this.nome);
        System.out.println(" CPF:        " + this.CPF);
        System.out.println("+---------------------------------------------+");
    }
    // metodo para mostrar dados
}
// classe cliente
class Vendedor{
    private String nome, matricula;
    // encapisulando
    public Vendedor(String nome, String matricula){
        this.nome = nome;
        this.matricula = matricula;
    }
    // construtor
    public String getNome(){return this.nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getMatricula(){return this.matricula;}
    public void setMatricula(String matricula){this.matricula = matricula;}
    // finalizando encapssulamento
    public void mostrarDados(){
        System.out.println("+---------------------------------------------+");
        System.out.println("maticula:    " + this.matricula);
        System.out.println("nome:        " + this.nome);
        System.out.println("+---------------------------------------------+");
    }
    // nao usei so pra ver como uma ma progamacao afeta o codigo
}
// classe vendedor
class GerenciadorLoja implements Vendavel{

    String[] extrato = new String[10];
    // criando um vetor statico para quardar String 
    int tamanho = 0;
    // auxiliar so vetor statico

    private ArrayList<Veiculo> estoque = new ArrayList<>();
    // criando um vetor dinamico para querdar instacias do tipo veiculo
    private String senha;
    // encapisulando
    public GerenciadorLoja(String senha){
        this.senha = senha;
    }
    // construtor
    public boolean autenticar(String senha){
        return this.senha.equals(senha);
    }
    // metodo para autenticar gerenciador gerenciavel

    public ArrayList<Veiculo> getEstoque(){return estoque;}
    // get que retorna a lista que quarda veiculos
    // pergunta se nao tivesse no mesmo pacote eu teria que usar um set para modificar valores da lista?
    public String[] getExtrato(){return extrato;}
    // get que returna um vetor que quarda Strings

    public void cadastrarVeiculo(Veiculo veiculo){
        estoque.add(veiculo);
        veiculo.mostrarDados();
    }
    // metado para colocar veiculo na lista e organizar a lista por preco
    public void mostrarEstoque(){
        for (Veiculo veiculo : estoque){
            veiculo.mostrarDados();
        }
    }
    // metado para mostrar veiculos que estao na lista
    public void excluirVeiculo(String chassi, int num){
        Scanner input = new Scanner(System.in);
        int i = 0; String opcao;
        for (Veiculo veiculo : estoque){
            if (veiculo.getChassi().equals(chassi)){
                i = 1;
                if(num == 0){
                    System.out.println("+---------------------------------------------+");
                    System.out.println("            veiculo a ser removido:            ");
                    veiculo.mostrarDados();
                    for(;;){
                        System.out.print("voce tem certeza que quer remover esse veiculo?\nS/n: ");
                        opcao = input.next();
                        System.out.println("+---------------------------------------------+");
                        if (opcao.equalsIgnoreCase("n") || opcao.equals("N")){
                            break;
                        }
                        else if (opcao.equalsIgnoreCase("s") || opcao.equals("S")){
                            num = 51; break;
                        }
                        else{
                            System.out.println("        Opcao invalida. Tente novamente.       ");
                            System.out.println("+---------------------------------------------+");
                        }
                    }
                }
                if(num == 51 || num == 1){
                    estoque.remove(veiculo);
                    System.out.println("          veiculo removido do estoque.         ");
                    System.out.println("+---------------------------------------------+");
                    break;
                }
            }
        }
        if (i == 0){
            System.out.println("+---------------------------------------------+");
            System.out.println("            veiculo nao encontrado.            ");
            System.out.println("+---------------------------------------------+");
        }
    }
    // metado para exluir veiculo da lista
    public void extratoAdd(String valor){
        extrato[tamanho] = valor;
        tamanho++;
        System.out.println("tamanho maximo e quantos extratos salvos: (" + extrato.length + "/" + tamanho + ")");
    }
    /* adicionar valor que vai vim da interface la na classe principal
       metados da inteface que estao padra 
    */
}
// classe que implementa os metados (que estao padrao) da interface
public class Lista5{
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String NEGRITO = "\u001B[1m"; 
    public static final String CYANO = "\u001B[36m";
    public static final String AZUL = "\u001B[34m";
    // putaria que muda a cor da String
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        // para poder entar dados no sistema
        ArrayList<Cliente> clientes = new ArrayList<>();
        // lista dinamica que guarda instacias clientes
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        // lista dinamica que guarda obj clientes
        GerenciadorLoja[] gerenciadorloja = new GerenciadorLoja[2];
        // vetor que quarda dois gerenciador
        gerenciadorloja[0] = new GerenciadorLoja("4321");
        // instanciando gerenciador de loja na posicao 0
        int i = 0, senha, contagem = 0, matricula = -1, indice = 0;
        /* i a vareavel univerçau que salvou meu codigo e provavelmente a de outras pessoas. obs: pode aparecer com num,c,j e etc
         senha obrigar o usuario a digitar um valor inteiro e comparar
         contagem vai ser usado para verificar se o usuario ja tentou 3 vezes a senha errada
         matricula vai ser usado para colocar a matricula automatico no vendedor
         indice outra forma de i mas para vetores
        */
            System.out.print(VERDE + NEGRITO + "");
            //mudando a cor do caracter
            System.out.println("+---------------------------------------------+");
            System.out.println("digite a senha do sistema, 4 tentativas, dica '1234', so posso dizer isso.....");
            System.out.print("senha: ");
            senha = input.nextInt();
            // dando a senha e recebendo senha
            for(i = 0; senha != 1234 ; i ++){
                System.out.println("+---------------------------------------------+");
                if(i == 3){System.out.println("\n\n\n\n\nnumero maximo de tentativa. exit(0)......."); System.exit(0);}
                // caso erra 4 veses ban
                System.out.print("senha incorreta.\ntente novamente: ");
                senha = input.nextInt();
                // recebendo senha

            }

            System.out.println("+---------------------------------------------+");
            System.out.println("                 senha coreta.\n                   bem vindo.                  ");
            System.out.println("+---------------------------------------------+");
            // ben vindo a merd, chore, chore agora
            for(;;){
            // loop infinito
            System.out.print(CYANO  + "");
            // mudando corzinha
            System.out.println("digite o numero para acesar uma das opcoes abaixo:");
            System.out.println("+---------------------------------------------+");
            System.out.println("1 - cadastrar cliente.");
            System.out.println("2 - visualizar clientes.");
            System.out.println("3 - remover cliente.");
            System.out.println("4 - editar cliente.");
            System.out.println("5 - cadastrar vendedor.");
            System.out.println("6 - visualizar vendedores.");
            System.out.println("7 - remover vendedor.");
            System.out.println("8 - editar vendedor.");
            System.out.println("9 - logar Gerente.");
            System.out.println("10 - sair.");
            System.out.print("opcao: ");
            int opcao = input.nextInt();
            // mostrar e receber opcao
            System.out.println("+---------------------------------------------+");

                if(opcao == 10){
                    System.out.print(VERDE + NEGRITO + "");
                    System.out.print("\n\n\n\n\nsaindo......."); System.exit(0);
                }
            // caso seja 10, mudar corzinha e exit(0)
            else if(opcao == 1){
                // ai e foda
                System.out.print("digite o CPF do cliente para cadastrar.\nCPF: ");
                String CPF = input.next();
                // receber valor
                i = 0;
                // i salvando meu codgo
                for(Cliente cliente : clientes){
                    // for alexa akkkkkk, ele pega o primero indice e coloca no cliente que e do tipo Cliente e assim ate acabar
                    if(cliente.getCPF().equals(CPF)){
                    // compara pra ver se ja nao tem cpf igual
                        i = 1;
                        System.out.println("+---------------------------------------------+");
                        System.out.println("             cliente ja cadastrado:            ");
                        cliente.mostrarDados();
                        // nananan
                    }
                }
                if(i == 0){ 
                    // caso o meu i continuar 0 que quer dizer que nao tem outro cpf igual
                    System.out.print("digite o nome do cliente para cadastrar.\nnome: ");
                    String nome = input.next();
                    System.out.print("digite o telefone do cliente para cadastrar.\ntelefone: ");
                    String telefone = input.next();
                    Cliente cliente = new Cliente(nome, telefone, CPF);
                    clientes.add(cliente);
                    // pega valores instancia e guarda dentro da listas clientes
                    System.out.println("+---------------------------------------------+");
                    System.out.println("        cliente cadastrado com sucesso:        ");
                    cliente.mostrarDados();
                }
            }
            // faz cadastrar cliente
            else if(opcao == 2){
                for(;;){
                    // loop para novo sisteminha
                    System.out.println("digite uma opcao abaixo:");
                    System.out.println("+---------------------------------------------+");
                    System.out.println("1 - visualizar 'um' (especifico) cliente no sistema.");
                    System.out.println("2 - visualizar 'todos' os clientes no sistema.");
                    System.out.println("3 - voltar.");
                    System.out.print("opcao: ");
                    opcao = input.nextInt();
                    // recebe valor
                    System.out.println("+---------------------------------------------+");

                    if(opcao == 3){break;}
                    // voltar para o sistema principal
                    else if(opcao == 1){
                        System.out.print("digite o CPF do cliente.\nCPF: ");
                        String CPF = input.next();
                        i = 0;
                        // mato em prfessor te que explicar tudo, chatao
                        for(Cliente cliente : clientes){
                            if(cliente.getCPF().equals(CPF)){
                                i = 1;
                                System.out.println("+---------------------------------------------+");
                                System.out.println("              cliente encontrado:              ");
                                cliente.mostrarDados();
                            }
                        }
                        // for echo(alexa) para selecionar cliente por cliente e coparar com o CPF fornecido e mostrar dados
                        if(i == 0){
                            System.out.println("+---------------------------------------------+");
                            System.out.println("            cliente nao encontrado.            ");
                            System.out.println("+---------------------------------------------+");
                        }
                        // caso nao ache mostrar mensagem
                    }
                    // mostra os atributos de um cliente em especifico
                    else if(opcao == 2){
                        System.out.println("             clientes cadastrados:             ");
                        for(Cliente cliente : clientes){
                            cliente.mostrarDados();
                        }
                        System.out.println("o total de clientes cadastrados e: (" + clientes.size() + ").");
                        // size ve o total de elementos em uma lista
                        System.out.println("+---------------------------------------------+");
                    }
                    // mostar todos os cliente no sistema
                    else{
                        System.out.println("       algo deu errado, tente novamente.       ");
                        System.out.println("+---------------------------------------------+");
                    }
                    // se o imundo do usuario digita coisa q nao deve ele pode consertar seu erro
                }
            }
            // faz visualizar um cliente ou todos 
            else if(opcao == 3){
                System.out.print("digite o CPF do cliente para remover.\nCPF: ");
                String CPF = input.next();
                i = 0;
                for(Cliente cliente : clientes){
                    if(cliente.getCPF().equals(CPF)){
                        i = 1;
                        System.out.println("+---------------------------------------------+");
                        System.out.println("              cliente encontrado:              ");
                        indice = clientes.indexOf(cliente);
                        cliente.mostrarDados();
                    }
                }
                // verefica se tem cliente com esse cpf caso ache mostra dados e salva o indice
                if(i == 1){
                    for(;;){
                        // loop
                        System.out.print("voce tem certeza que quer deletar esse cliente?\ndigite S/n: ");
                        String string = input.next();
                        if(string.equals("s") || string.equals("S")){i = 50;break;}
                        else if(string.equals("n") || string.equals("N")){
                            System.out.println("+---------------------------------------------+");
                            break;
                        }
                        else{
                            System.out.println("+---------------------------------------------+");
                            System.out.println("       algo deu errado, tente novamente.       ");
                            System.out.println("+---------------------------------------------+");
                        }
                        // caso o usuario digita algo q nao seja s ou n ele repete    
                    }
                    if(i == 50){
                        clientes.remove(clientes.get(indice));
                        System.out.println("+---------------------------------------------+");
                        System.out.println("         cliente removido com sucesso.         ");
                        System.out.println("+---------------------------------------------+");
                    }
                    // remosao do cliente atraves do indisse e mensagem
                }
                if(i == 0){
                    System.out.println("+---------------------------------------------+");
                    System.out.println("            cliente nao encontrado.            ");
                    System.out.println("+---------------------------------------------+");
                }
                // caso nao ache mostrar mensagem
            }
            // remove um cliente da lista
            else if(opcao == 4){
                System.out.print("digite o CPF do cliente que e para editar.\nCPF: ");
                String CPF = input.next();
                i = 0;
                for(Cliente cliente : clientes){
                    if(cliente.getCPF().equals(CPF)){
                        i = 1; 
                        System.out.println("+---------------------------------------------+");
                        System.out.println("              cliente encontrado:              ");
                        cliente.mostrarDados();
                        indice = clientes.indexOf(cliente);
                        break;
                    }
                }
                if(i == 1){
                    for(;;){
                        // diferentes formas para denvolver. um explo e no alterar vendedor- e no alterar veiculo.
                        System.out.println("digite uma opcao abaixo:");
                        System.out.println("+---------------------------------------------+");
                        System.out.println("1 - telefone.");
                        System.out.println("2 - nome.");
                        System.out.println("3 - CPF.");
                        System.out.println("4 - voltar.");
                        System.out.print("opcao: ");
                        opcao = input.nextInt();
                        System.out.println("+---------------------------------------------+");
                        if(opcao == 4){
                            System.out.println("            dados atuais do cliente:           ");
                            clientes.get(indice).mostrarDados();
                            break;
                        }
                        else if(opcao == 2){
                            System.out.print("digite o novo nome do cliente.\nnome: ");
                            String nome = input.next();
                            
                            clientes.get(indice).setNome(nome);
                            clientes.get(indice).mostrarDados();
                        }
                        else if(opcao == 1){
                            System.out.print("digite o novo telefone do cliente.\ntelefone: ");
                            String telefone = input.next();
                            clientes.get(indice).setTelefone(telefone);
                            clientes.get(indice).mostrarDados();
                        }
                        else if(opcao == 3){
                            System.out.print("digite o novo CPF do cliente.\nCPF: ");
                            String cpf = input.next();
                            i = 0;
                            for(Cliente cliente : clientes){
                                if(cliente.getCPF().equals(cpf)){
                                    i = 1;
                                    System.out.println("+---------------------------------------------+");
                                    System.out.println("   ja tem um cliente com esse CPF cadastrado:  ");
                                    cliente.mostrarDados();
                                    break;  
                                }
                            }
                            if(i == 0){
                                clientes.get(indice).setCPF(cpf);
                                clientes.get(indice).mostrarDados();
                            }
                        }
                        else{
                            System.out.println("       algo deu errado, tente novamente.       ");
                            System.out.println("+---------------------------------------------+");
                        }
                    }
                }
                if(i == 0){
                    System.out.println("+---------------------------------------------+");
                    System.out.println("            cliente nao encontrado.            ");
                    System.out.println("+---------------------------------------------+");
                }
            }
            // edita atributor de um usuario
            else if(opcao == 5){
                System.out.print("digite o nome do vendedor para cadastrar.\nnome: ");
                String nome = input.next();
                i = 0;
                for(Vendedor vendedor : vendedores){
                    if(vendedor.getNome().equals(nome)){
                        i = 1;
                        System.out.println("+---------------------------------------------+");
                        System.out.println("  ja tem um vendedor com esse nome cadastrado: ");
                        System.out.println("+---------------------------------------------+");
                        System.out.println("matricula vendedor:   " + vendedor.getMatricula());
                        System.out.println("nome vendedor:        " + vendedor.getNome());
                        System.out.println("+---------------------------------------------+");
                        break;  
                    }
                }
                if(i == 0){
                    matricula ++;
                    String i_p_s = matricula + "";
                    Vendedor vendedor = new Vendedor(nome, i_p_s);
                    vendedores.add(vendedor);
                    System.out.println("+---------------------------------------------+");
                    System.out.println("        vendedor cadastrado com sucesso.       ");
                    System.out.println("+---------------------------------------------+");
                    System.out.println("matricula vendedor:   " + vendedor.getMatricula());
                    System.out.println("nome vendedor:        " + vendedor.getNome());          
                    System.out.println("+---------------------------------------------+");
                }
            }
            // faz cadastrar vendedor
            else if(opcao == 6){
                for(;;){
                    System.out.println("digite uma opcao abaixo:");
                    System.out.println("+---------------------------------------------+");
                    System.out.println("1 - visualizar 'um' (especifico) vendedor no sistema.");
                    System.out.println("2 - visualizar 'todos' vendedores no sistema.");
                    System.out.println("3 - voltar.");
                    System.out.print("opcao: ");
                    opcao = input.nextInt();
                    System.out.println("+---------------------------------------------+");
                    if(opcao == 3){break;}
                    else if(opcao == 1){
                        System.out.print("digite a matricula do vendedor.\nmatricula: ");
                        String matricul = input.next();
                        i = 0;
                        for(Vendedor vendedor : vendedores){
                            if(vendedor.getMatricula().equals(matricul)){
                                i = 1;
                                indice = vendedores.indexOf(vendedor);
                                System.out.println("+---------------------------------------------+");
                                System.out.println("              vendedor encontrado:             ");
                                System.out.println("+---------------------------------------------+");
                                System.out.println("matricula:   " + vendedor.getMatricula());          
                                System.out.println("nome:        " + vendedor.getNome());
                                System.out.println("+---------------------------------------------+");
                            }
                        }
                        if(i == 0){
                            System.out.println("+---------------------------------------------+");
                            System.out.println("            vendedor nao encontrado.           ");
                            System.out.println("+---------------------------------------------+");
                        }
                    }
                    else if(opcao == 2){
                        System.out.println("            vendedores cadastrados:            ");
                        for(Vendedor vendedor : vendedores){
                            System.out.println("+---------------------------------------------+");
                            System.out.println("matricula:   " + vendedor.getMatricula());
                            System.out.println("nome:        " + vendedor.getNome());
                        }
                        System.out.println("+---------------------------------------------+");
                        System.out.println("o total de vendedores cadastrados e de: (" + vendedores.size() + ").");
                        System.out.println("+---------------------------------------------+");
                    }
                    else{
                        System.out.println("       algo deu errado, tente novamente.       ");
                        System.out.println("+---------------------------------------------+");
                    }
                }
            }       
            // faz visualizar um vendedor ou todos
            else if(opcao == 7){
                System.out.print("digite a matricula do vendedor foi passar no RH.\nmatricula: ");
                String matricul = input.next();
                i = 0;
                for(Vendedor vendedor : vendedores){
                    if(vendedor.getMatricula().equals(matricul)){
                        i = 1;
                        indice = vendedores.indexOf(vendedor);
                        System.out.println("+---------------------------------------------+");
                        System.out.println("              vendedor encontrado:             ");
                        System.out.println("+---------------------------------------------+");
                        System.out.println("matricula:   " + vendedor.getMatricula());
                        System.out.println("nome:        " + vendedor.getNome());          
                        System.out.println("+---------------------------------------------+");
                    }
                }
                if(i == 1){
                    for(;;){
                        System.out.print("voce tem certeza que e esse vendedor a ser demitido?\ndigite S/n:");
                        String string = input.next();
                        if(string.equals("s") || string.equals("S")){i = 50;break;}
                        else if(string.equals("n") || string.equals("N")){
                            System.out.println("+---------------------------------------------+");
                            break;
                        }
                        else{
                            System.out.println("+---------------------------------------------+");
                            System.out.println("       algo deu errado, tente novamente.       ");
                            System.out.println("+---------------------------------------------+");
                        }   
                    }
                    if(i == 50){
                        vendedores.remove(vendedores.get(indice));
                        System.out.println("+---------------------------------------------+");
                        System.out.println("         vendedor demitido com sucesso.        ");
                        System.out.println("+---------------------------------------------+");
                    }
                }
                if(i == 0){
                    System.out.println("+---------------------------------------------+");
                    System.out.println("            vendedor nao encontrado.           ");
                    System.out.println("+---------------------------------------------+");
                }
            }
            // remove um vendedor que passou no RH
            else if(opcao == 8){
                System.out.print("digite a matricula para editar o nome do vendedor.\nmatricula: ");
                String matricul = input.next();
                i = 0;
                for(Vendedor vendedor : vendedores){
                    if(vendedor.getMatricula().equals(matricul)){
                        i = 1; 
                        System.out.println("+---------------------------------------------+");
                        System.out.println("matricula vendedor:   " + vendedor.getMatricula());
                        System.out.println("nome vendedor:        " + vendedor.getNome());          
                        System.out.println("+---------------------------------------------+");
                        indice = vendedores.indexOf(vendedor);
                        break;
                    }
                }
                if(i == 1){
                    for(;;){
                        Vendedor vendedor = vendedores.get(indice);
                        System.out.println("digite uma opcao abaixo:");
                        System.out.println("+---------------------------------------------+");
                        System.out.println("1 - digite o nome do vendedor.");
                        System.out.println("2 - voltar.");
                        System.out.print("opcao: ");
                        opcao = input.nextInt();
                        System.out.println("+---------------------------------------------+");
                        if(opcao == 2){
                            System.out.println("           dados atuais do vendedor:           ");
                            System.out.println("+---------------------------------------------+");
                            System.out.println("matricula vendedor:   " + vendedor.getMatricula());
                            System.out.println("nome vendedor:        " + vendedor.getNome());          
                            System.out.println("+---------------------------------------------+");
                            vendedores.set(indice, vendedor);
                            break;
                        }
                        else if(opcao == 1){
                            System.out.print("digite o novo nome do funcionario.\nnome: ");
                            String nome = input.next();
                            i = 0;
                            for(Vendedor vendedo : vendedores){
                                if(vendedo.getNome().equals(nome)){
                                    i = 1;
                                    System.out.println("+---------------------------------------------+");
                                    System.out.println("ja existe um vendedor com esse nome no sistema.");
                                    System.out.println("+---------------------------------------------+");
                                    System.out.println("matricula:   " + vendedo.getMatricula());
                                    System.out.println("nome:        " + vendedo.getNome());
                                    System.out.println("+---------------------------------------------+");
                                    break;
                                }
                            }
                            if(i == 0){
                                vendedor.setNome(nome);
                                System.out.println("+---------------------------------------------+");
                                System.out.println("matricula vendedor:   " + vendedor.getMatricula());
                                System.out.println("nome vendedor:        " + vendedor.getNome());          
                                System.out.println("+---------------------------------------------+");
                            }
                        }
                        else{
                            System.out.println("       algo deu errado, tente novamente.       ");
                            System.out.println("+---------------------------------------------+");
                        }
                    
                    }
                }
                if(i == 0){
                    System.out.println("+---------------------------------------------+");
                    System.out.println("            vendedor nao encontrado.           ");
                    System.out.println("+---------------------------------------------+");
                }
            }
            // edita o nome do vendedor
            else if(opcao == 9){
                if(senha != 4321){
                System.out.print(VERDE + NEGRITO + "");
                System.out.println("dica 4321, 4 chances:");
                System.out.print("digite a senha do gerenciador gerenciavel:\nsenha: ");
                senha = input.nextInt();
                System.out.println("+---------------------------------------------+");

                }
                for(i = 0; gerenciadorloja[0].autenticar(senha + "") ; i ++){
                    if(i == 3){
                        System.out.println("\n\n\n\n\nnumero meximo de tentativa. voltar(0)......."); 
                        System.out.println("+---------------------------------------------+");
                       contagem ++; break;}
                    if(contagem == 1){System.out.println("\n\n\n\n\n\nnumero meximo de erros ao tentar logar como gerente. exit(0)......."); System.exit(0);}
                    System.out.print("senha incorreta.\ntente novamente: ");
                    senha = input.nextInt();
                    System.out.println("+---------------------------------------------+");
                    }
                if(senha == 4321){
                    for(;;){
                    System.out.print(RESET + AZUL + NEGRITO + "");
                    System.out.println("       bem vindo gerenciador gerenciavel.      ");
                    System.out.println("+---------------------------------------------+");
                    System.out.println("digite uma das opicoes:");
                    System.out.println("+---------------------------------------------+");
                    System.out.println("1 - cadastrar veiculo.");
                    System.out.println("2 - mostrar estoque.");
                    System.out.println("3 - excluir veiculo.");
                    System.out.println("4 - alterar veiculo.");
                    System.out.println("5 - registrar venda.");
                    System.out.println("6 - sair.");
                    System.out.print("opcao: ");
                    opcao = input.nextInt();
                    System.out.println("+---------------------------------------------+");

                    if(opcao == 6){
                        System.out.print(VERDE + NEGRITO + "");
                        System.out.println("\n\n\n\n\n\nsaindo.......");break;
                    }

                    else if(opcao == 1){
                        for(;;){
                            System.out.println("digite uma opcao abaixo:");
                            System.out.println("+---------------------------------------------+");
                            System.out.println("1 - carro.");
                            System.out.println("2 - outros.");
                            System.out.println("3 - sair.");
                            System.out.print("opcao: ");
                            opcao = input.nextInt();
                            System.out.println("+---------------------------------------------+");

                            if(opcao == 3){break;}

                            else if(opcao == 1){
                                System.out.println("digite os dados para o cadastro do carro:");
                                System.out.println("+---------------------------------------------+");
                                System.out.print("digite o chassi do carro.\nchassi: ");
                                String chassi = input.next();
                                i = 0;
                                for(Veiculo carro : gerenciadorloja[0].getEstoque()){
                                    if(carro.getChassi().equals(chassi)){
                                        i = 1;
                                        System.out.println("+---------------------------------------------+");
                                        System.out.println("          carro ja existe no sistema:          ");
                                        carro.mostrarDados();
                                        break;
                                    }
                                }
                                if(i == 1){break;}
                                System.out.print("digite a marca do carro.\nmarca: ");
                                String marca = input.next();
                                System.out.print("digite o modelo do carro.\nmodelo: ");
                                String modelo = input.next();
                                System.out.print("digite a cor do carro.\ncor: ");
                                String cor = input.next();
                                System.out.print("digite o preco do carro.\npreco: ");
                                double preco = input.nextDouble();
                                System.out.print("digite o ano do carro.\nano: ");
                                int ano = input.nextInt();
                                System.out.print("digite o numero de portas do carro.\nnumero: ");
                                int portas = input.nextInt();
                                System.out.print("digite o tipo de combustivel do carro.\ntipo: ");
                                String combustivel = input.next();
                                System.out.print("digite a potencia do motor do carro.\npotencia: ");
                                String potenciaMotor = input.next();
                                String classe = "carro";
                                System.out.println("+---------------------------------------------+");
                                Veiculo carro = new Carro(chassi, marca, modelo, cor, preco, ano, portas, combustivel, potenciaMotor, classe);
                                System.out.println("         carro cadastrado com sucesso:         ");
                                gerenciadorloja[0].cadastrarVeiculo(carro);
                                break;
                            }
                            else if(opcao == 2){
                                System.out.println("no systema nao tem mais opcoes no momento, obrigado pela compreencao.");
                                System.out.println("+---------------------------------------------+");
                            }
                            else{
                                System.out.println("       algo deu errado, tente novamente.       ");
                                System.out.println("+---------------------------------------------+");
                            }
                        }   
                    }

                    else if(opcao == 2){
                        for(;;){
                            System.out.println("digite uma opcao abaixo:");
                            System.out.println("+---------------------------------------------+");
                            System.out.println("1 - visualizar 'um' (especifico) veiculo no sistema.");
                            System.out.println("2 - visualizar 'todos' os veiculos no sistema.");
                            System.out.println("3 - voltar.");
                            System.out.print("opcao: ");
                            opcao = input.nextInt();
                            System.out.println("+---------------------------------------------+");

                            if(opcao == 3){break;}
                            else if(opcao == 1){
                                System.out.print("digite o chassi do veiculo: ");
                                String chassi = input.next();
                                i = 0;
                                for(Veiculo veiculo : gerenciadorloja[0].getEstoque()){
                                    if(veiculo.getChassi().equals(chassi)){
                                        i = 1;
                                        System.out.println("+---------------------------------------------+");
                                        System.out.println("              veiculo encontrado:              ");
                                        veiculo.mostrarDados();
                                    }
                                }
                                if(i == 0){
                                    System.out.println("+---------------------------------------------+");
                                    System.out.println("            veiculo nao encontrado.            ");
                                    System.out.println("+---------------------------------------------+");
                                }
                            }
                            else if(opcao == 2){
                                System.out.println("         todos os veiculos no sistema:         ");
                                for(Veiculo veiculo : gerenciadorloja[0].getEstoque()){
                                    veiculo.mostrarDados();
                                }
                                System.out.println("a quantidade de veiculos no sistema e de: (" + gerenciadorloja[0].getEstoque().size() + ").");
                                System.out.println("+---------------------------------------------+");
                            }
                            else{
                                System.out.println("       algo deu errado, tente novamente.       ");
                                System.out.println("+---------------------------------------------+");
                            }
                        }
                    }

                    else if(opcao == 3){
                        System.out.print("digite o chassi do veiculo que voce quer excluir.\nchassi: ");
                        String chassi = input.next();
                        gerenciadorloja[0].excluirVeiculo(chassi, 0);
                    }

                    else if(opcao == 4){
                        System.out.print("digite o chassi para mudar dados do veiculo.\nchassi: ");
                        String chassi = input.next();
                        i = 0;
                        for(Veiculo veiculo : gerenciadorloja[0].getEstoque()){
                            if(veiculo.getChassi().equals(chassi)){
                                i = 10;
                                indice = gerenciadorloja[0].getEstoque().indexOf(veiculo);
                                System.out.println("+---------------------------------------------+");
                                System.out.println("              veiculo encontrado:              ");
                                veiculo.mostrarDados();
                            }
                        }
                        if(i == 0){
                            System.out.println("+---------------------------------------------+");
                            System.out.println("            veiculo nao encontrado.            ");
                            System.out.println("+---------------------------------------------+");
                        }
                        if(i == 10){
                        if(gerenciadorloja[0].getEstoque().get(indice).getClasse().equals("carro")){
                            for(;;){
                                Carro carro = (Carro) gerenciadorloja[0].getEstoque().get(indice);
                                System.out.println("digite uma opcao para alterar dados do veiculo:");
                                System.out.println("1 - chassi.");
                                System.out.println("2 - marca.");
                                System.out.println("3 - modelo.");
                                System.out.println("4 - cor.");
                                System.out.println("5 - preco.");
                                System.out.println("6 - ano.");
                                System.out.println("7 - tipo de combustivel.");
                                System.out.println("8 - potencia do motor.");
                                System.out.println("9 - numero de portas.");
                                System.out.println("10 - sair.");
                                System.out.print("opcao: ");
                                input.nextLine();
                                opcao = input.nextInt();
                                System.out.println("+---------------------------------------------+");
                                if(opcao == 10){
                                    System.out.println("            dados atuais do veiculo:           ");//agora que decobri que nao pode printar o print atraves de metados:(
                                    carro.mostrarDados();
                                    gerenciadorloja[0].getEstoque().add(indice, carro);
                                    break;
                                }
                                else if(opcao == 1){
                                    System.out.print("digite o novo chassi do carro.\nchassi: ");
                                    chassi = input.next();
                                    i = 0;
                                    for(Veiculo auCarro : gerenciadorloja[0].getEstoque()){
                                        if(auCarro.getChassi().equals(chassi)){
                                            i = 1;
                                            System.out.println("+---------------------------------------------+");
                                            System.out.println("       esse chassi ja existe no sistema:       ");
                                            auCarro.mostrarDados();
                                        }
                                    }
                                    if(i == 0){
                                        carro.setChassi(chassi);
                                        carro.mostrarDados();
                                    }
                                }
                                else if(opcao == 2){
                                    System.out.print("digite a nova marca do carro.\nmarca: ");
                                    String marca = input.next();
                                    carro.setMarca(marca);
                                    carro.mostrarDados();
                                }
                                else if(opcao == 3){
                                    System.out.print("digite o novo modelo do carro.\nmodelo: ");
                                    String modelo = input.next();
                                    carro.setModelo(modelo);
                                    carro.mostrarDados();
                                }
                                else if(opcao == 4){
                                    System.out.print("digite a nova cor do carro.\ncor: ");
                                    String cor = input.next();
                                    carro.setCor(cor);
                                    carro.mostrarDados();
                                }
                                else if(opcao == 5){
                                    System.out.print("digite o novo preco do carro.\npreco: ");
                                    double preco = input.nextDouble();
                                    carro.setPreco(preco);
                                    carro.mostrarDados();
                                }
                                else if(opcao == 6){
                                    System.out.print("digite o novo ano do carro.\nano: ");
                                    int ano = input.nextInt();
                                    carro.setAno(ano);
                                    carro.mostrarDados();
                                }
                                else if(opcao == 7){
                                    System.out.print("digite o novo tipo de combustivel do carro.\ntipo de combustivel: ");
                                    String tipoCombustivel = input.next();
                                    carro.setTipoCombustivel(tipoCombustivel);
                                    carro.mostrarDados();
                                }
                                else if(opcao == 8){
                                    System.out.print("digite a nova potencia do motor do carro.\npotencia do motor: ");
                                    String potenciaMotor = input.next();
                                    carro.setPotenciaMotor(potenciaMotor);
                                    carro.mostrarDados();
                                }
                                else if (opcao == 9){
                                    System.out.print("digite o novo numero de portas do carro.\nnumero de portas: ");
                                    int numeroPortas = input.nextInt();
                                    carro.setNumPortas(numeroPortas);
                                    carro.mostrarDados();
                                }
                                else{
                                System.out.println("       algo deu errado. tente novamente.       ");
                                System.out.println("+---------------------------------------------+");
                                }
                            }
                        }}
                    }
                    
                    else if (opcao == 5){
                    for(;;){
                        int[] vetor = new int[4];
                        vetor[0] = 0;
                        System.out.print("digite o chassi do veiculo que voce quer registrar a venda.\nchassi: ");
                        String chassi = input.next();
                        for(Veiculo veiculo : gerenciadorloja[0].getEstoque()){
                            if(veiculo.getChassi().equals(chassi)){
                                vetor[3] = gerenciadorloja[0].getEstoque().indexOf(veiculo);
                                System.out.println("+---------------------------------------------+");
                                System.out.println("              veiculo encontrado:              ");
                                veiculo.mostrarDados();
                                vetor[0] = 1;
                                break;
                            }
                        }
                        if(vetor[0] == 0){
                            System.out.println("+---------------------------------------------+");
                            System.out.println("            veiculo nao encontrado.            ");
                            System.out.println("+---------------------------------------------+");
                            break;
                        }
                    
                        System.out.print("digite o CPF do cliente.\nCPF: ");
                        String cpf = input.next();
                        vetor[0] = 0;
                        for(Cliente cliente : clientes){
                            if(cliente.getCPF().equals(cpf)){
                                vetor[1] = clientes.indexOf(cliente);
                                System.out.println("+---------------------------------------------+");
                                System.out.println("              cliente encontrado:              ");
                                cliente.mostrarDados();
                                vetor[0] = 1;
                                break;
                            }
                        }   
                        if(vetor[0] == 0){
                            System.out.println("+---------------------------------------------+");
                            System.out.println("            cliente nao encontrado.            ");
                            System.out.println("+---------------------------------------------+");
                            break;
                        }

                        System.out.print("digite a matricula do vendedor.\nmatricula: ");
                        int matricul = input.nextInt();
                        String aString = "" + matricul;
                        vetor[0] = 0;
                        for(Vendedor vendedor : vendedores){
                            if(vendedor.getMatricula().equals(aString)){
                                vetor[2] = vendedores.indexOf(vendedor);
                                System.out.println("+---------------------------------------------+");
                                System.out.println("              vendedor encontrado:             ");
                                System.out.println("+---------------------------------------------+");// ok eu me arrependo de nao fazer o metado mostrar dados
                                System.out.println("matricula:   " + vendedor.getMatricula());
                                System.out.println("nome:        " + vendedor.getNome());
                                System.out.println("+---------------------------------------------+");
                                vetor[0] = 1;
                                break;
                            }
                        }
                        if(vetor[0] == 0){
                            System.out.println("+---------------------------------------------+");
                            System.out.println("            vendedor nao encontrado.           ");
                            System.out.println("+---------------------------------------------+");
                            break;
                        }
                    
                        System.out.print("digite a porcentagem do desconto.se nenhuma digite 0.\nvalor: ");
                        double desconto = input.nextDouble();
                        System.out.println("+---------------------------------------------+");

                        gerenciadorloja[0].getEstoque().get(vetor[3]).setPreco(gerenciadorloja[0].calcularPrecoVenda(desconto, gerenciadorloja[0].getEstoque().get(vetor[3])));
                        System.out.println("o atual preco do veiculo e: " + gerenciadorloja[0].getEstoque().get(vetor[3]).getPreco());
                        System.out.println("+---------------------------------------------+");
                        
                        
                        Date data_venda = new Date();
                    
                        String valor = 
                        gerenciadorloja[0].registrarVenda(clientes.get(vetor[1]), vendedores.get(vetor[2]), gerenciadorloja[0].getEstoque().get(vetor[3]), data_venda);
                    
                        gerenciadorloja[0].extratoAdd(valor);
                        for(i = 0; i < gerenciadorloja[0].getExtrato().length; i ++){
                            if(gerenciadorloja[0].getExtrato()[i].contains(valor)){
                                break;
                            }
                        }
                        System.out.println("+---------------------------------------------+");
                        System.out.println("       extrato salvo com sucesso. dados:"      );
                        System.out.println(gerenciadorloja[0].getExtrato()[i]);
                        System.out.println("\npesquisando veiculo pelo sistema.......\n");
                        System.out.println("+---------------------------------------------+");
                        gerenciadorloja[0].excluirVeiculo(chassi, 1);
                        System.out.println("            #!parabens pela venda!#$           ");
                        System.out.println("+---------------------------------------------+");
                        break;
                    }}
                    
                    else{
                        System.out.println("       algo deu errado, tente novamente.       ");
                        System.out.println("+---------------------------------------------+");
                    }                  
                }}
            }    
            // loga o cliente com a sua senha
            else{
                System.out.println("\n       algo deu errado, tente novamente.       \n");
                System.out.println("+---------------------------------------------+");
            }
            // caso digite alguma opcao inexistente mostra mensagem
        }
    }
}
// classe principal que eu sobrecarreguei

// o que foi pedido ta explicado no codigo caso queira saber de algo contate o aluno(eu) Sarruf(Eu de nv)