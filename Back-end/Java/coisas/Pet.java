
import java.util.Scanner;

class Slaime {

    String nome, clase, familia;
    int calorias, forca, idade;
    boolean estado;

    void comer() {
        if (calorias + 10 > 100 || forca - 2 < 0) {
            System.out.println("nao a como comer. calorias = " + calorias + ", forca = " + forca + ", idade = " + idade);
        } else {
            calorias += 10;
            forca -= 5;
        }
    }

    void correr() {
        if (calorias - 5 < 0 || forca - 5 < 0) {
            System.out.println("nao a como correr. calorias = " + calorias + ", forca = " + forca + ", idade = " + idade);
        } else {
            calorias -= 7;
            forca -= 5;
        }
    }

    void dormir() {
        if (calorias - 2 < 0 || forca + 10 > 100) {
            System.out.println("nao a como dormir. calorias = " + calorias + ", forca = " + forca + ", idade = " + idade);
        } else {
            calorias -= 5;
            forca += 10;
            idade += 1;
        }
    }

    void morrer() {
        forca = 0;
        estado = false;
    }
}

public class Pet {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Slaime slaime1 = new Slaime();
        while (true) {
            System.out.println("\ndigite uma das opicoes abaixo:\n");
            System.out.println("1°opicao .nacer:");
            System.out.println("2°opicao .morrer:");
            System.out.println("3°opicao .dormir:");
            System.out.println("4°opicao .comer:");
            System.out.println("5°opicao .correr:");
            System.out.print("opicao:");
            int opicoes = input.nextInt();

            if (opicoes == 1) {
                if (slaime1.estado == true) {
                    System.out.println("seu pet ja naceu.");
                } else {
                    System.out.println("digite o nome do slaime: ");
                    slaime1.nome = input.next();
                    slaime1.idade = 0;
                    slaime1.calorias = 10;
                    slaime1.forca = 10;
                    slaime1.estado = true;
                }
            } else if (opicoes == 2) {
                if (slaime1.estado != true) {
                    System.out.println("voce precissa nacer para morrer.");
                } else {
                    slaime1.morrer();
                    System.out.println("VOCE MATOU SEU PET: " + slaime1.nome + ", SEU MOSTROOOOO!!       :( ");
                    break;
                }
            } else if (opicoes == 3) {
                if (slaime1.estado != true) {
                    System.out.println("voce precissa nacer para dormir.");
                } else {
                    slaime1.dormir();
                }
            } else if (opicoes == 4) {
                if (slaime1.estado != true) {
                    System.out.println("voce precissa nacer para comer.");
                } else {
                    slaime1.comer();
                }
            } else if (opicoes == 5) {
                if (slaime1.estado != true) {
                    System.out.println("voce precissa nacer para correr.");
                } else {
                    slaime1.correr();
                }
            }
            System.out.println("idade = " + slaime1.idade + " dia, calorias = " + slaime1.calorias + ", forca = " + slaime1.forca);
        }
    }
}
