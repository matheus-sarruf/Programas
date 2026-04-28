
import java.util.Scanner;

public class atv_Paulo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        float tx_natalidade, tx_mortalidade, populacao, tx_imigracao, tx_emigracao, ano, densidade;
        System.out.print("Digite o numero inicial de indviduos:");
        populacao = input.nextFloat();
        System.out.print("Digite a taxa de natalidade:");
        tx_natalidade = input.nextFloat();
        System.out.print("Digite a taxa de mortalidade:");
        tx_mortalidade = input.nextFloat();
        System.out.print("Digite a taxa de imigracao:");
        tx_imigracao = input.nextFloat();
        System.out.print("Digite a taxa de emigracao:");
        tx_emigracao = input.nextFloat();
        System.out.print("Digite ate que ano:");
        ano = input.nextFloat();
        System.out.print("Digite a area² onde a populacao reside:");
        densidade = input.nextFloat();
        for (int i = 1; i <= ano; i++) {
            float nascimentos = populacao * tx_natalidade / 100;
            float mortes = populacao * tx_mortalidade / 100;
            float imigrantes = populacao * tx_imigracao / 100;
            float emigrantes = populacao * tx_emigracao / 100;
            populacao += nascimentos - mortes + (imigrantes - emigrantes);
            System.out.println("Ano " + i + ": populacao(" + populacao + ") densidade demografica :" + populacao / densidade);
        }
    }
}
