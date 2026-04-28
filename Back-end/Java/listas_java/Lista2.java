 //exercicio1/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main(String [] args){
//        Scanner ler = new Scanner(System.in);
//        int num1;
//        System.out.println("digite um numero:");
//        num1 = ler.nextInt();
//        if(num1 > 10) {
//            System.out.println("É MAIOR QUE 10");
//       }else{
//       System.out.println("NAO E MAIOR QUE 10");
//     }}}

 //exercicio2/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main(String[] args){
//        Scanner ler = new  Scanner(System.in);
//        double num1;    
//        System.out.println("digite um numero:");
//        num1 = ler.nextDouble();
//        if (num1 % 2 == 0){
//            System.out.println("seu numero e par");
//      } else{
//        System.out.println("seu numero e impar");
//     }}}

 //exercicio3/lista2
//import java.util.Scanner;
//public class lista2 {
//    public static void main(String[] args){
//        Scanner ler = new Scanner(System.in);
//        double macas_compradas;
//        System.out.println("quantas maças a comprar:");
//        macas_compradas = ler.nextDouble();
//        if (macas_compradas >= 12){
//            System.out.println("o preco tatal " + macas_compradas * 1);
//     }  else{
//            System.out.println("o preco tatal " + macas_compradas * 1.30);
//   }}}

 //exercicio4/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main (String[] args){
//        Scanner ler = new Scanner (System.in);
//        double prova1, prova2;
//        System.out.println("digite a nota da sua primeira prova:");
//        prova1 = ler.nextDouble();
//        System.out.println("digite a nota da sua segunda prova:");
//        prova2 = ler.nextDouble();
//        if((prova1 + prova2)/ 2 >= 6){
//            System.out.println("o aluno esta aprovado");
//        }else{
//            System.out.println("o aluno foi reprovado");
//   }}}

 //exercicio5/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main(String[] args){
//        Scanner ler = new Scanner(System.in);
//        double ano;
//        System.out.println("digite o ano em que voce naceu:");
//        ano = ler.nextDouble();
//        if(2024 - ano == 16 || 2024 - ano == 17 || 2024 - ano >= 70);{
//            System.out.println("voce pode votar");}
//        if(2024 - ano >= 18 && 2024 - ano >= 70){
//            System.out.println("voce deve votar");  
//   }}}

 //exercicio6/lista2
// import java.util.Scanner;
// public class lista2{
//     public static void main(String [] args){
//        Scanner ler = new Scanner(System.in);
//        double num1, a, b, c;
//            while (true){
//            System.out.println("digite '1' imprimir os três valores a, b, c em ordem crescente, '2' para a, b, c em ordem decrescente e '3' para a, b, c.O naior fique no meio:");
//            num1 = ler.nextDouble();
//            if(num1 == 1 || num1 == 2||num1== 3) {break;}
//            else{System.out.println("erro 199 retur") ; }}
//        System.out.println("digite o valor de 'A':");
//        a = ler.nextDouble();
//        System.out.println("digite o valor de 'B':");
//        b = ler.nextDouble();
//        System.out.println("digite o valor de 'C':");
//        c = ler.nextDouble();
//        if(num1 == 1){
//            if ( a < b && a < c) {
//            if ( b < c ) { System.out.println(a + " " + b + " "+ c); }
//            else { System.out.println(a + " " + c + " "+ b); }
//            }
//            if (b < a && b < c) {
//            if (a < c) { System.out.println(b + " " + a + " "+ c); }
//            else { System.out.println(b + " " + c + " "+ a); }
//            }
//            if (c < a && c < b) {
//            if (a < b) { System.out.println(c + " " + a + " "+ b); }
//            else { System.out.println(c + " " + b + " "+ a); }
//            }
//        }
//        if(num1 == 2){
//            if ( a > b && a > c) {
//            if ( b > c ) { System.out.println(a + " " + b + " "+ c); }
//            else { System.out.println(a + " " + c + " "+ b); }
//            }
//            if (b > a && b > c) {
//            if (a > c) { System.out.println(b + " " + a + " "+ c); }
//            else { System.out.println(b + " " + c + " "+ a); }
//            }
//            if (c > a && c > b) {
//            if (a > b) { System.out.println(c + " " + a + " "+ b); }
//            else { System.out.println(c + " " + b + " "+ a); }
//            }
//        }
//        if(num1 == 3){
//            if ( a > b && a > c) {
//            if ( b > c ) { System.out.println(b + " " + a + " "+ c); }
//            else { System.out.println(c + " " + a + " "+ b); }
//            }
//            if (b > a && b > c) {
//            if (a > c) { System.out.println(a + " " + b + " "+ c); }
//            else { System.out.println(c + " " + b + " "+ a); }
//            }
//            if (c > a && c > b) {
//            if (a > b) { System.out.println(a + " " + c + " "+ b); }
//            else { System.out.println(b + " " + c + " "+ a); }
//            }
//        }
//    }}

 //exercicio7/lista2
// import java.util.Scanner;
// public class lista2{
//    public static void main (String[] args){
//        Scanner ler = new Scanner(System.in);
//        double num1, num2;
//        System.out.println("digite um numero '1':");
//        num1 = ler.nextDouble();
//        System.out.println("digite outro numero '2':");
//        num2 = ler.nextDouble();
//        if(num1 > num2){System.out.println("o numero 1 e maior que o numero 2   maior=" + num1);}
//        if(num2 > num1){System.out.println("o numero 2 e maior que o numero 1   maior=" + num2);}
//    }}

 //exercicio8/lista2
// import java.util.Scanner;
// public class lista2{
//    public static void main (String[] args){
//        Scanner ler = new Scanner(System.in);
//        int num1, num2;
//        System.out.println("digite um numero '1':");
//        num1 = ler.nextInt();
//        System.out.println("digite outro numero '2':");
//        num2 = ler.nextInt();
//        if(num1 > num2){System.out.println("ordem cresente e: 8" + num2  + " " + num1);}
//        if(num2 > num1){System.out.println("ordem cresente e: " + num1  + " " + num2);}
//    }}

 //exercicio9/lista2
// import java.util.Scanner;
// public class lista2 {
//     public static void main(String[] args) {
//        Scanner ler = new Scanner(System.in);
//      int inicio, fim;
//         System.out.println("horario do comoco da partida: ");
//         inicio = ler.nextInt();
//         System.out.println("horario do fim da partida: ");
//         fim = ler.nextInt();
//         if (inicio <= fim) {
//             System.out.println("aa partida durou " + (fim - inicio) + "horas");
//         } else {
//             System.out.println("estrapolou o limite.");
//         }
//    }}

 //exercicio10/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main (String[] args){
//        Scanner ler = new Scanner(System.in);
//        double hora_trabalhada, salario_hora;
//        System.out.println("digite a hora trabalhada no mes:");
//        hora_trabalhada = ler.nextDouble();
//        System.out.println("digite o salario por hora:");
//        salario_hora = ler.nextDouble();
//        if(hora_trabalhada <= 160) { System.out.println("o seu salario total e de:" + salario_hora * hora_trabalhada + " R&") ; }
//        if(hora_trabalhada > 160)  { System.out.println("o seu salario total e de:" + (((salario_hora * 150) / 100)  * (hora_trabalhada - 160) + salario_hora  * 160) + " R&"); }
//    }}

 //exercicio11/lista2
//import java.util.Scanner;
//public class lista2 {
//    public static void main(String[] args) {
//        Scanner ler = new Scanner(System.in);
//        String nome, sexo;
//        System.out.println("Digite seu nome: ");
//        nome = ler.next();
//        System.out.println("Digite sua altura: ");
//        double altura = ler.nextDouble();
//        while (true) {
//            System.out.println("Digite seu sexo (F ou M): ");
//            sexo = ler.next();
//            if (sexo.equals("M") || sexo.equals("F")) {
//                if (sexo.equals("M")) {System.out.println(nome + ", o seu peso ideal é: " + ((altura * 72.7) - 58));}
//                if (sexo.equals("F")) {System.out.println(nome + ", o seu peso ideal é: " + ((altura * 62.1) - 44.7));}
//            break;
//            } 
//            else {System.out.println("Erro: Sexo diferente de M ou F.");}
//         }
//    }}

 //exercicio12/lista2
//import java.util.Scanner;
//public class lista2 {
//    public static void main (String[] args){
//        Scanner ler = new Scanner(System.in);
//        double salario, caccomisao;
//        System.out.println("digite seu salario:");
//        salario = ler.nextDouble();
//        System.out.println("digite sua vendas:");
//        caccomisao = ler.nextDouble();
//        if(caccomisao <= 1500.00){System.out.println("o seu total a receber e : " +  (salario + caccomisao * 3 / 100 ));}
//        if(caccomisao > 1500.00){System.out.println("o seu total a receber e : " +  (salario + 1500.00 * 3 / 100 + ((caccomisao - 1500.00) * 5 / 100)));}
//    }}

 //exercicio13/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main (String[] args){
//        Scanner ler = new Scanner (System.in);
//        double saldo, debito, credito; 
//        System.out.println("digite o numero da conta: ");
//        String num_conta = ler.nextLine();
//        System.out.println("digite o saldo da conta: ");
//        saldo = ler.nextDouble();
//        System.out.println("digite o valor do debito: ");
//        debito = ler.nextDouble();
//        System.out.println("digite o valor do credito: ");
//        credito = ler.nextDouble();
//        double saldo_final = saldo + credito - debito;
//        if(saldo_final >= 0){System.out.println("o saldo final da conta " + num_conta + " e positivo");}
//        if(saldo_final < 0 ){System.out.println("o saodo final da conta " + num_conta + " e nagativo");}
//    }}

 //exercicio14/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main ( String[] args){
//        Scanner ler = new Scanner(System.in);
//        int quantidade, quantidade_minima, quantidade_maxima;
//        System.out.println("digite a quantidade no estoque: ");
//        quantidade = ler.nextInt();
//        System.out.println("digite a quantidade minima: ");
//        quantidade_minima = ler.nextInt();
//        System.out.println("digite a quantidade maxima: ");
//        quantidade_maxima = ler.nextInt();
//        int media = (quantidade_maxima + quantidade_minima) / 2;
//        if(media <= quantidade){ System.out.println("a media do estoque e " + media + " nao a necessidade de fazer uma copra de reestoque");}
//        else{ System.out.println("a media do estoque e " + media + " a uma necessidade de fazer uma copra de reestoque");}
//    }}

 //exercicio15/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main(String[] args){
//        Scanner ler = new Scanner(System.in);
//        double  num1; 
//        System.out.println("digite um numero:");
//        num1 = ler.nextDouble();
//        if(num1 > 0){System.out.println("numero positivo:");}
//        if(num1 < 0){System.out.println("numero negativo:");}
//        if(num1 == 0){System.out.println("numero nulo:");}
//    }}

 //exercicio16/lista2
// import java.util.Scanner;
// public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         double  num1, num2, num3;
//         System.out.println("digite um numero(num1):");
//         num1 = ler.nextDouble();
//         System.out.println("digite um numero(num2):");
//         num2 = ler.nextDouble();
//         System.out.println("digite um numero(num3):");
//         num3 = ler.nextDouble();
//         if(num1 > num2 && num1 > num2){System.out.println("o maior numero e o (num1)") ;}
//         if(num2 > num1 && num2 > num3){System.out.println("o maior numero e o (num2)") ;}
//         if(num3 > num2 && num3 > num1){System.out.println("o maior numero e o (num3)") ;}
//     }}

 //exercicio17/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         double  num1, num2, num3;
//         System.out.println("digite um numero(num1):");
//         num1 = ler.nextDouble();
//         System.out.println("digite um numero(num2):");
//         num2 = ler.nextDouble();
//         System.out.println("digite um numero(num3):");
//         num3 = ler.nextDouble();
//         if ( num1 > num2 && num1 > num3) {
//            if ( num2 > num3 ) { System.out.println("a soma dos maiores e:" + (num1 + num2)); }
//            else { System.out.println("a soma dos maiores e:" + (num1 + num3)); }
//            }
//            if (num2 > num1 && num2 > num3) {
//            if (num1 > num3) { System.out.println("a soma dos maiores e:" + (num2 + num1)); }
//            else { System.out.println("a soma dos maiores e:" + (num2 + num3)); }
//            }
//            if (num3 > num1 && num3 > num2) {
//            if (num1 > num2) { System.out.println("a soma dos maiores e:" + (num3 + num1)); }
//            else { System.out.println("a soma dos maiores e:" + (num3 + num2)); }
//            }
//     }}

 //exercicio18/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         double  num1, num2, num3;
//         System.out.println("digite um numero(num1):");
//         num1 = ler.nextDouble();
//         System.out.println("digite um numero(num2):");
//         num2 = ler.nextDouble();
//         System.out.println("digite um numero(num3):");
//         num3 = ler.nextDouble();
//            if ( num1 < num2 && num1 < num3) {
//            if ( num2 < num3 ) { System.out.println(num1 + " " + num2 + " "+ num3); }
//            else { System.out.println(num1 + " " + num3 + " "+ num2); }
//            }
//            if (num2 < num1 && num2 < num3) {
//            if (num1 < num3) { System.out.println(num2 + " " + num1 + " "+ num3); }
//            else { System.out.println(num2 + " " + num3 + " "+ num1); }
//            }
//            if (num3 < num1 && num3 < num2) {
//            if (num1 < num2) { System.out.println(num3 + " " + num1 + " "+ num2); }
//            else { System.out.println(num3 + " " + num2 + " "+ num1); }
//            }
//       }}

 //exercicio19/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         double  lado1, lado2, lado3;
//         System.out.println("digite um numero(lado1):");
//         lado1 = ler.nextDouble();
//         System.out.println("digite um numero(lado2):");
//         lado2 = ler.nextDouble();
//         System.out.println("digite um numero(lado3):");
//         lado3 = ler.nextDouble();
//         if(lado1 < lado2 + lado3){
//            if (lado2 < lado3 + lado2){
//                if (lado3 < lado2 + lado1) {System.out.println("e um triangulo");
//                }else{System.out.println("nao e um triangulo:");}
//            }else{System.out.println("nao e um triangulo:");}
//         }else{System.out.println("nao e um triangulo:");}
//         }}

 //exercicio20/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         String time1, time2;
//         double  pontos1, pontos2;
//         System.out.println("digite o nome do time1:");
//         time1 = ler.next();
//         System.out.println("digite o nome do time2:");
//         time2 = ler.next();
//         System.out.println("digite o numero de pontos do time1:");
//         pontos1 = ler.nextDouble();
//         System.out.println("digite o numero de pontos do time2:");
//         pontos2 = ler.nextDouble();
//          if(pontos1 > pontos2){System.out.println("o time1 venceu");}
//          if(pontos2 > pontos1){System.out.println("o time2 venceu");}
//          if(pontos1 == pontos2){System.out.println("empatou");}
//     }}

 //exercicio21/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         double num1, num2;
//         System.out.println("digite um numero (num1):");
//         num1 = ler.nextDouble();
//         System.out.println("digite um numero (num2):");
//         num2 = ler.nextDouble();
//         if(num1 > num2){System.out.println(num1 + " (num1) é maior que (num2) " + num2);}  
//         if(num2 > num1){System.out.println(num2 + " (num2) é maior que (num1)" + num1);}
//         if(num1 == num2){System.out.println("sao iguais");}
//
//    }}

 //exercicio22/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         double x, y;
//         String resposta;
//         System.out.println("digite um numero (num1):");
//         x = ler.nextDouble();
//         System.out.println("digite um numero (num2):");
//         y = ler.nextDouble();
//         double z = x * y + 5;
//         if (z <= 0){resposta = "A";}
//         else{
//             if (z <= 100){resposta = "B";}
//             else{resposta = "C";}
//         }// e do else
//         System.out.println("z = " + z + " resposta = " + resposta);
//     }}
// \x	 \y	    \z	    \R
// |3	 |2	    |11	    /B
// |150	 |3	    |455	/C
// |7	 |-1	|-2	    /A
// |-2	 |5	    |-5	    /A

 //exercicio23/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//         Scanner ler = new Scanner(System.in);
//         double A, G, L;
//         String resposta;
//         System.out.println("digite quantos litros comprados:");
//         L = ler.nextDouble();
//         System.out.println("digite o tipo de combostiveu 'G' para gasoli e 'A' para alco:");
//         String tipo = ler.next();
//         while(true){if(tipo.equals("G") || tipo.equals("A")){break;}else{         System.out.println("erro digite novamente o tipo de combostivel; 'G' para gasolina e 'A' para alco:");tipo = ler.next(); }}
//         if (tipo.equals("G")){
//            if(L < 20)
//            {System.out.println("o preco total da gasolina ficou: " +  L * (3.33 - (3.33 * 3 / 100)));}
//            else {System.out.println("o preco total da gasolina ficou: " +  L * (3.33 - (3.33 * 5 / 100)));}
//         }
//         else{
//            if(L < 20)
//            {System.out.println("o preco total do alco ficou: " +  L * (2.99 - (2.99 * 4 / 100)));}
//            else {System.out.println("o preco total do alco ficou: " +  L * (2.99 - (2.99 * 6 / 100)));}
//         }
//     }}

 //exercicio24/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//        Scanner ler = new Scanner(System.in);
//        double h1,h2,m1,m2;
//        System.out.println("digite o idade do homem1");
//        h1 = ler.nextDouble();
//        System.out.println("digite o idade do homem2");
//        h2 = ler.nextDouble();
//        System.out.println("digite o idade do mulher1");
//        m1 = ler.nextDouble();
//        System.out.println("digite o idade do mulher2");
//        m2 = ler.nextDouble();
//        if(h1 > h2){
//            if(m1 < m2){System.out.println("a soma do homem mais velho com a mulher mais nova e " + (h1 + m1) + " e o produto em o homen mais novo e a mulher mais velha e " + h2 * m2);}
//            else{System.out.println("a soma do homem mais velho com a mulher mais nova e " + (h1 + m2) + " e o produto em o homen mais novo e a mulher mais velha e " + h2 * m1);}
//        }
//        else{
//            if(m1 < m2){System.out.println("a soma do homem mais velho com a mulher mais nova e " + (h2 + m1) + " e o produto em o homen mais novo e a mulher mais velha e " + h1 * m2);}
//            else{System.out.println("a soma do homem mais velho com a mulher mais nova e " + (h2 + m2) + " e o produto em o homen mais novo e a mulher mais velha e " + h2 * m1);}
//        }
//     }}

 //exercicio25/lista2
//import java.util.Scanner;
//public class lista2{
//     public static void main(String[] args){
//        Scanner ler = new Scanner(System.in);
//        double morango, maca;
//        System.out.println("digite quantos Kg de morago comprados:");
//        morango = ler.nextDouble();
//        System.out.println("digite quantos Kg  de maca comprados:");
//        maca = ler.nextDouble();
//        double  preco_morango =  morango * 2.50 ,preco_maca = maca * 1.80;
//        if (morango > 5){preco_morango = morango * 2.20;}
//        if (maca > 5){preco_maca = maca * 1.50;}
//        if (morango + maca > 8 || preco_morango + preco_maca > 25){System.out.println("o total ficou:" + ((preco_morango + preco_maca) - (preco_morango + preco_maca) * 10 / 100));}
//        else{System.out.println("o total ficou:" + (preco_morango + preco_maca));}
//     }}

 //exercicio26/lista2
//import java.util.Scanner;
//public class lista2{
// public static void main(String[] args) {
//    Scanner ler = new Scanner(System.in);
//    String codigo, codigoR = "1234",senha;
//    System.out.println("digite o codigo de usuario:");
//    codigo = ler.next();
//    if (!codigo.equals(codigoR)) {System.out.println("usuario incorreto:");}
//    else {System.out.println("digite a senha do usuario:");
//        senha = ler.next();
//        if(senha.equals("9999")){System.out.println("ACESSO LIBERADO");}
//        else {System.out.println("ACESSO NEGADO");}
//    }
//    }}

 //exercicio27/lista2
//import java.util.Scanner;
//public class lista2 {
//    public static void main(String[] args) {
//        Scanner ler = new Scanner(System.in);
//        double preco,desconto, total;
//        System.out.println("digite o nome do prduto:");
//        String descricao = ler.nextLine();
//        System.out.println("digite a quantidade: ");
//        int quantidade = ler.nextInt();
//        System.out.println("digite o reco por unidade:");
//         preco = ler.nextDouble();
//         total = quantidade * preco;
//        if (quantidade <= 5) {desconto = total * 0.02;} 
//        if (quantidade <= 10) {desconto = total * 0.03;}
//        else {desconto = total * 0.05;}
//        double totalAPagar = total - desconto;
//        System.out.println("tota: " + total + "R&");
//        System.out.println("desconto" + desconto + "R&");
//        System.out.println("total a pagar" + totalAPagar + "R&");
//    }}

 //exercicio28/lista2
// import java.util.Scanner;
// public class lista2 {
//     public static void main(String[] args) {
//         Scanner ler = new Scanner(System.in);
//         double prova1, prova2, prova3, media, mediaAp;
//         System.out.println("digite a nota da prova1:");
//         prova1 = ler.nextDouble();
//         System.out.println("igite a nota da prova1");
//         prova2 = ler.nextDouble();
//         System.out.println("digite a nota da prova2:");
//         prova3 = ler.nextDouble();
//         System.out.println("digite a nota da prova3: ");
//         media = ler.nextDouble();
//         mediaAp = (prova1 + prova2 * 2 + prova3 * 3 + media) / 7;
//         System.out.println("medi de aprooveitamento: " + mediaAp);       
//         String conceito;  
//         if (mediaAp>= 9.0) {
//             conceito = "A";
//         } else if (mediaAp>= 7.5) {
//             conceito = "B";
//         } else if (mediaAp>= 6.0) {
//             conceito = "C";
//         } else {
//             conceito = "D";
//         }
//         System.out.println("Conceito: " + conceito);
//     }}

 //exercicio30/lista2
//import java.util.Scanner;
//public class lista2{
//    public static void main(String[] args){
//        Scanner ler = new Scanner(System.in);
//        double a, b, c;
//        System.out.println("digite o valor de 'A': ");
//        a = ler.nextDouble();
//        System.out.println("digite o valor de 'B': ");
//        b = ler.nextDouble();
//        System.out.println("digite o valor de 'C': ");
//        c = ler.nextDouble();
//        if ((a < b + c) && (b < a + c) && (c < a + b)){
//            if ((a == b) && (b == c)){System.out.println("triangulo equilatero");}
//            else{ if((a == b) || (b == c) || (a == c))
//                 {System.out.println("triangulo isosceles");}
//                 else{System.out.println("triangulo escaleno");}
//                }
//        }
//    }}
