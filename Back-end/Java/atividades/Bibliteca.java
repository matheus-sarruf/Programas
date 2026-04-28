
import java.util.*;

import javax.xml.transform.SourceLocator;

class Livro {

    private String nome, nome_ator, data, nome_editora;
    private int quantidade_estoque;
    private double preco_venda, preco_alugel, preco_point;

    public Livro(String nome, String nome_ator, String data, String nome_editora, int quantidade_estoque, double preco_venda, double preco_alugel, double preco_point) {
        this.nome = nome;
        this.nome_ator = nome_ator;
        this.data = data;
        this.nome_editora = nome_editora;
        this.quantidade_estoque = quantidade_estoque;
        this.preco_venda = preco_venda;
        this.preco_alugel = preco_alugel;
        this.preco_point = preco_point;
    }

}

class Conta {

    private String nome, senha, telefone, email, cpf;
    private double point;
    private String[] Livro_alugados = new String[3];
    private boolean c;
    private int i = 0;

    public Conta(String nome, String senha, String telefone, String email, String cpf, double point) {
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.point = point;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String num) {
        this.senha = num;
    }

    public void setCheio() {
        for (i = 0; i < Livro_alugados.length; i++) {
            if (Livro_alugados[i] == null) {
                c = true;
            }
            if (c == false) {
                break;
            }
        }
    }

    public void setAlugar(String ID, double point, String metado) {
        c = false;
        if (metado.equals("point")) {
            if (this.point >= point) {
                setCheio();
                if (c) {
                    Livro_alugados[i] = ID;
                    System.out.println("Livro alugado com sucesso");
                    this.point -= point;
                    System.out.println("O total de pontos atual sao de:" + this.point);
                } else {
                    System.out.println("Pontos insuficientes");
                }
            }
            //termina de fazer
        }

    }

    public void devolver(String ID) {
        for (int i = 0; i < Livro_alugados.length; i++) {
            if (Livro_alugados[i] != null && Livro_alugados[i].equals(ID)) {
                Livro_alugados[i] = null;
                break;
            }
        }
        System.out.println("Livro devolvido com sucesso");
    }

}
