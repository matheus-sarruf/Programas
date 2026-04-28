
import java.util.Scanner;

class Data {

    int dia, mes, ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void printDados() {
        System.out.print(dia + "/" + mes + "/" + ano);
    }
}

class Conta {

    private String senha, usuario;
    private double saldo, limite;

    public Conta(String usuario, String senha, double saldo, double limite) {
        this.usuario = usuario;
        this.senha = senha;
        this.saldo = saldo;
        this.limite = limite;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String num) {
        this.senha = num;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String nome) {
        this.usuario = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double num) {
        this.saldo = num;
    }

    public double getlimite() {
        return limite;
    }

    public void setLimite(double num) {
        this.limite = num;
    }

    void depositar(double num) {
        this.saldo += num;
    }

    void sacar(double num) {
        if (this.saldo - num > limite * -1) {
            System.out.print("nao a como sacar ja que o valor passa o limite. aumente o limite ou deposite");
        } else {
            this.saldo -= num;
        }
    }

    void transferir(Conta usuario, double num) {
        if (this.saldo - num > limite * -1) {
            this.sacar(num);
            usuario.depositar(num);
        } else {
            System.out.print("nao a como transferir ja que o valor passa o limite. aumente o limite ou deposite");
        }
    }
}

public class Coisa {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao, i = 0;
        double num;
        Conta[] contas = new Conta[10];
        for (;;) {
            System.out.println("digite uma opcao");
            System.out.println("1 - cadastrar nova conta");
            System.out.println("2 - fazer login");
            System.out.println("3 - modificar conta");
            System.out.println("4 - sair");
            System.out.print("opcao: ");
            opcao = input.nextInt();

            if (opcao == 1) {
                for (; i < 10; i++) {
                    System.out.print("digite o nome de usuario: ");
                    String usuario = input.next();
                    System.out.print("digite a senha: ");
                    String senha = input.next();
                    System.out.print("digite o saldo inicial: ");
                    double saldo = input.nextDouble();
                    System.out.print("digite o limite: ");
                    double limite = input.nextDouble();
                    contas[i] = new Conta(usuario, senha, saldo, limite);
                    System.out.println("conta criada com sucesso");
                    break;
                }
            } else if (opcao == 2) {//2
                System.out.print("digite o nome de usuario: ");
                String usuario = input.next();
                System.out.print("digite a senha: ");
                String senha = input.next();
                for (int j = 0; j < 10; j += 1) {
                    if (contas[j].getUsuario().equals(usuario) && contas[j].getSenha().equals(senha)) {
                        System.out.println("login realizado com sucesso");
                        for (;;) {
                            System.out.println("Usuario = " + contas[j].getUsuario() + ", Saldo = " + contas[j].getSaldo() + "Limite = " + contas[j].getlimite());
                            System.out.println("digite uma das opcoes abaixo");
                            System.out.println("1 - fazer deposito");
                            System.out.println("2 - fazer saque");
                            System.out.println("3 - fazer transferencia");
                            System.out.println("4 - sair");
                            System.out.print("opcao: ");
                            opcao = input.nextInt();

                            if (opcao == 1) {
                                System.out.println("digite o valor a ser depositado:");
                                num = input.nextDouble();
                                contas[j].depositar(num);
                            }
                            if (opcao == 2) {
                                System.out.println("digite o valor a ser sacado:");
                                num = input.nextDouble();
                                contas[j].sacar(num);
                            }
                            if (opcao == 3) {
                                System.out.println("digite o nome do usuario para transferir:");
                                String usuarioDestino = input.next();
                                System.out.println("digite o valor a ser transferido:");
                                num = input.nextDouble();
                                for (int k = 0; k < 10; k += 1) {
                                    if (contas[k].getUsuario().equals(usuarioDestino)) {
                                        contas[j].transferir(contas[k], num);
                                    }
                                }
                                if (opcao == 4) {
                                    break;
                                }
                            }

                        }
                    } else {
                        System.out.println("Usuario nao encontrado");
                    }//2
                }
            } else if (opcao == 3) {//3
                System.out.print("digite o nome de usuario: ");
                String usuario = input.next();
                System.out.print("digite a senha mestre do funcionario: ");
                String senha = input.next();
                for (int j = 0; j < 10; j += 1) {
                    for (;;) {
                        if (contas[j].getUsuario().equals(usuario) && senha.equals(4466)) {
                            System.out.println("digite uma das opcoes abaixo");
                            System.out.println("1 - alterar nome de usuario");
                            System.out.println("2 - alterar senha");
                            System.out.println("3 - alterar limite");
                            System.out.println("4 - sair");
                            System.out.print("opcao: ");
                            opcao = input.nextInt();
                            if (opcao == 1) {
                                System.out.print("digite o novo nome de usuario: ");
                                String novoNome = input.next();
                                contas[j].setUsuario(novoNome);
                            }
                            if (opcao == 2) {
                                System.out.print("digite a nova senha: ");
                                String novaSenha = input.next();
                                contas[j].setSenha(novaSenha);
                            }
                            if (opcao == 3) {
                                System.out.print("digite o novo limite: ");
                                double novoLimite = input.nextDouble();
                                contas[j].setLimite(novoLimite);
                            }
                            if (opcao == 4) {
                                break;
                            }

                        } else {
                            System.out.println("Usuario nao encontrado or senha incorreta");
                        }//3  
                    }
                }
            }

            if (opcao == 4) {//4
                System.out.println("Saindo...");
                break;
            }

        }
    }
}
