import java.util.Scanner;

class Carros {

    double preco, preco_frabrica, preco_imposto, preco_concesionaria;
    String marca, modelo, nome;
    boolean estado;

    void imposto(int taxa_imposto) {
        preco_imposto = preco_frabrica * taxa_imposto / 100;
    }

    void concesionaria(int taxa_concesionaria) {
        preco_concesionaria = preco_frabrica * taxa_concesionaria / 100;
    }
}

public class FazOL {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Carros carro1 = new Carros();
        System.out.print("digite a marca do carro:");
        carro1.marca = input.next();
        System.out.print("digite o modelo do carro:");
        carro1.modelo = input.next();
        System.out.print("digite o nome do carro:");
        carro1.nome = input.next();
        System.out.print("digite 'true' se o carro for novo ou 'false' caso nao seja:");
        carro1.estado = input.nextBoolean();
        if (carro1.estado) {
            System.out.print("digite o preco de fabrica:");
            carro1.preco_frabrica = input.nextDouble();
            System.out.print("digite a porcentagem do imposto: %");
            carro1.imposto(input.nextInt());
            System.out.print("digiet a porcentagem da concesionaria: %");
            carro1.concesionaria(input.nextInt());
            System.out.println("preco final do carro: " + (carro1.preco_frabrica + carro1.preco_imposto - carro1.preco_concesionaria));
        }
    }
}
