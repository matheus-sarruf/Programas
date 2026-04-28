import java.util.*;
class Usuario{
    private String senha, usuario, email, numero;
    private int id;
    public Usuario(int id, String senha, String usuario, String email, String numero){
        this.id = id; this.senha = senha; this.usuario = usuario; this.email = email; this.numero = numero;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}

    public void mostrarDados() {
        System.out.println("!------------------------!");
        System.out.println("Usuario: " + this.usuario);
        System.out.println("Email: " + this.email);
        System.out.println("Número: " + this.numero);
        System.out.println("ID: " + this.id);
        System.out.println("Senha: " + this.senha);
        System.out.println("!------------------------!");

    }

    public boolean mudarSenha(){
        Scanner input = new Scanner(System.in);
        System.out.print("Digite a sua senha: ");
        String senhaAtual = input.nextLine();
        if(senhaAtual.equals(senha)){
            System.out.print("Digite a nova senha: ");
            String novaSenha = input.nextLine();
            this.senha = novaSenha;
            System.out.println("Senha alterada com sucesso!");
            return true;
        }
        else {return false;}
    }

   /* public boolean verificarEmail(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        Matcher matcher = pat.matcher(email);
        return matcher.matches();
    }*/
}

public class PPL{
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    List<Usuario> usuarios = new ArrayList<>();
    //System.out.println("Bem vindo ao PPL!");
    Usuario[] usuarioss = new Usuario[50];
    int j = 0;
    int id = 0;
    boolean vf = true;
    System.out.println("digite a senha do adm:");
    String sanha = input.next();
    if(sanha.equals("m999")){
    for(;;){
        System.out.println("\nEscolha uma opcao:");
        System.out.println("1 - Cadastrar novo usuário");
        System.out.println("2 - Acessar Usuarios");
        System.out.println("3 - Sair");
        System.out.print("Opção: ");
        int opcao = input.nextInt();
        if(opcao == 1){
            for(;opcao == 1;){
            System.out.print("Digite a senha: ");
            String senha = input.next();
            System.out.print("Digite o usuário: ");
            String usuario = input.next();
            System.out.print("Digite o email: ");
            String email = input.next();
            System.out.print("Digite o número de telefone: ");
            String numero = input.next();
            vf = true;
            
            for(int i = 0; j > i; i ++){
                if(i == 30){System.out.println("\n\nmaximo de tentativas"); System.exit(0);}
                if(usuarioss[i].getUsuario().equals(usuario) || usuarioss[i].getEmail().equals(email)){
                System.out.println("\n\nUsuário ou email já cadastrado!");
                System.out.println("digite 1 para tentar novamente");
                System.out.println("digite 2 para sair");
                System.out.print("Opção: ");
                opcao = input.nextInt();
                vf = false;
                break;
                }
            }
            if(vf){usuarioss[j] = new Usuario(id, senha, usuario, email, numero);  j ++;  id ++;  System.out.println("Usuarios cadastrado com sussefull.");  break;}
            }
        }
    }}}
}