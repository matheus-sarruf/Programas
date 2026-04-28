
import java.util.Scanner;

class Conta {

    String nome, senha, CPF, numero, email;
    double saldo;

    void saca(double num) {
        if (num <= saldo) {
            saldo -= num;
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }
}

public class Entender {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("digite um numero:");
        int num1 = input.nextInt();
        System.out.print("digite outro numero:");
        int num2 = input.nextInt();
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        // diferente do python em java se separa com + (em vez da ,), para fazer seoma no print deve colocar parentece
        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
        // o mesmo vale para o -
        System.out.println(num1 + " * " + num2 + " = " + num1 * num2);
        // para multiplicao nao e necessario parentece
        System.out.println(num1 + " / " + num2 + " = " + num1 / (double) num2);
        // o double traforma uma vareavel inteira em decimal ou real,veja que a divisao nao e necessario parentece
        //cria uma divisao com numeros decimais
        System.out.println(num1 + " % " + num2 + " = " + num1 % num2);
        //retor as sobras da divisao,geralmente usado para ver impar e par
        System.out.println(num1 + " // " + num2 + " = " + (int) num1 / (int) num2);
        //retorna a parte inteira da divisao, para fazer ela que no python(e assim //) aqui em java voce so transforma ela as vareveis em inteiras
        double num3 = num1 + num2;
        System.out.println(num3 + " " + (int) num3 + " " + num3);
        // veja que o "(int)vareavel" trasforma ela no que quiser no caso inteiro
        System.out.println("²√(" + num1 + " + " + num2 + ") = " + Math.sqrt(num1 + num2));
        // importa uma blibiotaca que faz com "Math (class) .sqrt (o acesso que ira executar)" fazendo um amatrix quadrada
        System.out.println("³√(" + num1 + " + " + num2 + ") = " + Math.pow(num1 + num2, 1.0 / 3.0));
        // ATENCAO TEM QUE TER .0 "'1.0' / '3.0'"
        // importa uma blibiotaca que faz com "Math (class) .pow (o acesso que ira executar)" faz uma raiz cubica]
        // so que o interacente e que o que ele faz na verdade e uma potencia muitipinado o numero
        System.out.println("(" + num1 + " + " + num2 + ")² = " + Math.pow(num1 + num2, 2));
        //como pode ver o pow faz potencia e nao so raiz mas da para usalo para fazer qualquer indice na raiz
    }
}
