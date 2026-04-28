import java.util.*;

//ex1/lista3
class Funcionario{
    protected String nome;
    protected double salario;
    public Funcionario(String nome, double salario){
        this.nome = nome; this.salario = salario;
    }
    public String getNome(){return this.nome;}
    public double getSalario(){return salario;}
    public void mostrarDados(){
        System.out.println("nome:" + nome);
         System.out.println("salario:" + salario);
    }
}

class Gerente extends Funcionario{
    private int setor;
    public Gerente(String nome, double salario, int setor){
        super(nome, salario);
        this.setor = setor;
    }
    public void mostrarDados(){
        super.mostrarDados();
        System.out.println("setor:" + setor);
    }
}

class Assistente extends Funcionario{
    private int numeroMatricula;
    public Assistente(String nome, double salario, int numeroMatricula){
        super(nome, salario);
        this.numeroMatricula = numeroMatricula;
    }
    public int getNumeroMatricula(){return this.numeroMatricula;}
    public void mostrarDados(){
        super.mostrarDados();
        System.out.println("numero da matricula:" + numeroMatricula);
    }
}

class AssistenteTecnico extends Assistente{
    private double bonus;
    public AssistenteTecnico(String nome, double salario, int numeroMatricula, double bonus){
        super(nome, salario, numeroMatricula);
        this.bonus = bonus;
    }
    public void mostrarDados(){
        super.mostrarDados();
        System.out.println("bonus:" + bonus);
        System.out.println("salario atual" +(super.getSalario() + bonus));
    }
}

class AssistenteAdiministrativo extends Assistente{
    private String turno;
    private double addNoite;
    public AssistenteAdiministrativo(String nome, double salario, int numeroMatricula, String turno, double addNoite){
        super(nome, salario, numeroMatricula);
        this.turno = turno;this.addNoite = addNoite;
    }
    public void mostrarDados(){
        super.mostrarDados();
        System.out.println("turno:" + turno);
        if(turno.equals("noite")){System.out.println("adicionais da noite:" + addNoite);}
        else{System.out.println("adicionais da noite:" + 0);}
        System.out.println("salario atual" +(super.getSalario() + addNoite));
    }
}

//ex2/lista3
abstract class Animal{
    protected String nome, raca;
    protected Animal(String nome, String raca){
        this.nome = nome; this.raca = raca;
    }
    protected abstract void animal();
    protected void animal(String nome){}
    protected String caminha(){return "normal";}
}

class Cachorro extends Animal{
    public Cachorro(String nome, String raca){
        super(nome, raca);
    }
    public void animal(){System.out.println("nome:" + this.nome + " raca:" + this.raca);}
    public String late(){return "auau akkkkk";}
}

class Gato extends Animal{
    public Gato(String nome, String raca){
        super(nome, raca);
    }
    public void animal(){System.out.println("nome:" + this.nome + " raca:" + this.raca);}
    public String mia(){return "miau miau";}
}

abstract class Pessoa{
    protected String nome;
    protected int idade;
    protected Pessoa(String nome, int idade){
        this.nome = nome; this.idade = idade;
    }
    public abstract void pessoa();
} 

class Pobre extends Pessoa{
    public Pobre(String nome, int idade){
        super(nome, idade);
    }
    public void pessoa(){System.out.println("nome:" + this.nome + " idade:" + this.idade + " pobre");}
    public void trabalha(){}
}

class Miseravel extends Pessoa{
    public Miseravel(String nome, int idade){
        super(nome, idade);
    }
    public void pessoa(){System.out.println("nome:" + this.nome + " idade:" + this.idade + " miseravel");}
}

class Rica extends Pessoa{
    private double patrimonio;
    public Rica(String nome, int idade, double patrimonio){
        super(nome, idade);
        this.patrimonio = patrimonio;
    }
    public void pessoa(){System.out.println("nome:" + this.nome + " idade:" + this.idade + " dinheiro:" + patrimonio + " rica");}
}

//ex3/lista3
class Ingresso{
    protected double preco;
    public Ingresso(double preco){
        this.preco = preco;
    }
    public void imprimirValor(){System.out.println("preco do ingresso: " + preco);}
}

class VIP extends Ingresso{
    protected double add;
    public VIP(double preco, double add){
        super(preco);
        this.add = add;
    }
    public void imprimirValor(){System.out.println("preco do ingresso: " + (preco + add));}
}

class Normal extends Ingresso{
    public Normal(double preco){
        super(preco);
    }
    public void imprimirValor(){super.imprimirValor(); System.out.println("ingresso normal");}
}

class CamaroteInferior extends VIP{
    private String localIngresso;
    public CamaroteInferior(double preco, double add, String localIngresso){
        super(preco, add);
        this.localIngresso = localIngresso;
    }
    public void imprimirValor(){
        super.imprimirValor();
        System.out.println("local do ingreso " + localIngresso);
    }
}

class CamaroteSuperior extends VIP{
    private double addd;
    public CamaroteSuperior(double preco, double add, double addd){
        super(preco, add);
        this.addd = addd;
    }
    public void imprimirValor(){
        System.out.println("preco total:" + preco + add + addd);
    }
}

//ex4/lista3
class Imovel{
    protected double preco;
    private String endereco;
    public Imovel(double preco, String endereco){
        this.preco = preco; this.endereco = endereco;
    }
    public void mostarDados(){System.out.println("preco do imovel:" + preco + " endereco:" + endereco);}
}

class Novo extends Imovel{
    private double add;
    public Novo(double preco, String endereco, double add){
        super(preco, endereco);
        this.add = add;
    }
    public void mostarDados(){System.out.println("preco do imovel:" + (preco + add));}
}

class Velho extends Imovel{
    private double desconto;
    public Velho(double preco, String endereco, double desconto){
        super(preco, endereco);
        this.desconto = desconto;
    }
    public void mostarDados(){System.out.println("preco do imovel:" + (preco - (preco * desconto / 100)));}
}

//ex5/lista3
public class Lista3{
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    AssistenteAdiministrativo semiadm = new AssistenteAdiministrativo("ninguem", 0, 655, "noite", 50);
    semiadm.mostrarDados();
    Assistente assistentetec = new AssistenteTecnico("Filipe", 2, 13, 12);
    assistentetec.mostrarDados();
    System.out.println("\n nome do assistente adm: " + semiadm.getNome() + " num matricula: " + semiadm.getNumeroMatricula());
    System.out.println("\n nome do assistente tecnico: " + assistentetec.getNome() + " num matricula: " + assistentetec.getNumeroMatricula() + "\n");
    //
    Cachorro cachorro = new Cachorro("DESTRUIDOR DE MUNDOS", "Pinscher");
    cachorro.animal();
    Gato gato = new Gato("FIFI", "Siames");
    gato.animal();
    cachorro.late(); gato.mia(); cachorro.caminha(); gato.caminha();
    //
    Rica rica = new Rica(null, 0, 10000);
    rica.pessoa();
    Pobre pobre = new Pobre("null", 0);
    pobre.pessoa(); pobre.trabalha();
    Miseravel miseravel = new Miseravel("null", 0);
    miseravel.pessoa(); 
    //
    for(;;){
    System.out.println("digite uma opcao");
    System.out.println("1.ingresso normal");
    System.out.println("2.ingresso VIP");
    System.out.println("opcao:");
    int opcao = input.nextInt();
    if (opcao == 1){Ingresso ingresso = new Ingresso(50); ingresso.imprimirValor(); break;}
    else if (opcao == 2){
        System.out.println("digite o tipo de ingresso VIP");
        System.out.println("1.camarote inferior");
        System.out.println("2.camarote superior");
        System.out.println("opcao:");
        opcao = input.nextInt();
        if (opcao == 1){
            System.out.println("digite o local do camarote inferior");
            String local = input.next();
            VIP ingresso = new CamaroteInferior(50, 100, local); ingresso.imprimirValor(); break;
        }
        else if (opcao == 2){
            System.out.println("digite o desconto do camarote superior");
            double desconto = input.nextDouble();
            VIP ingresso = new CamaroteSuperior(50, 1000, desconto); ingresso.imprimirValor(); break;
        }
        else{System.out.println("Erro1314B#@, tente novamente");}
    }
    else{System.out.println("Erro1314B#@, tente novamente");}

    }
    for(;;){
    System.out.println("digite uma opcao");
    System.out.println("1.imovel nova");
    System.out.println("2.imovel veio");
    System.out.println("opcao:");
    int opcao1 = input.nextInt();
    if (opcao1 == 1){Novo novo = new Novo(2000000.40, "Ibirapuera", 1000000.90); break;}
    else if (opcao1 == 2){
        System.out.println("digite o desconto do imovel velho");
        double desconto = input.nextDouble();
        Imovel velho = new Velho(2000000.40, "Ibirapuera", desconto); break;
    }
    else{System.out.println("Erro1314B#@, tente novamente");}
    }

    }
}