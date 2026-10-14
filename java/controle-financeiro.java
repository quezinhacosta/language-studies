import java.util.Scanner;
import java.util.ArrayList;

class Usuario {
    String nome;
    String email;
    String senha;
    Double saldo_atual = 0.0;
    ArrayList<DespesaFixa> despesasFixas = new ArrayList<>();
    ArrayList<DespesaExtra> despesasExtras = new ArrayList<>();
    ArrayList<Recebimentos> recebimentos = new ArrayList<>();
    ArrayList<MetasFinanceiras> metas = new ArrayList<>();

    public Usuario(String nome, String email, String senha, double saldo_atual) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo_atual = saldo_atual;
    }
}

class DespesaFixa {
    String nome;
    Double valor;
    String data;

    public DespesaFixa(String nome, double valor, String data) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }
}

class DespesaExtra {
    String nome;
    Double valor;
    String data;

    public DespesaExtra(String nome, double valor, String data) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }
}

class Recebimentos {
    String nome;
    Double valor;
    String data;

    public Recebimentos(String nome, double valor, String data) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }
}

class MetasFinanceiras {
    String nome;
    double saldo_atual;
    double valor_meta;  // Corrigido de "objettivo" para "valor_meta"

    public MetasFinanceiras(String nome, double saldo_atual, double valor_meta) {
        this.nome = nome;
        this.saldo_atual = saldo_atual;
        this.valor_meta = valor_meta;
    }
}

public class SistemFinanceiro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, quantidade = 0, b, c;
        Usuario[] usuarios = new Usuario[50];

        System.out.println("Bem-vindo(a) ao seu assistente financeiro pessoal!");

        do {
            System.out.println("Primeiramente, escolha uma opção: ");
            System.out.println("1 - Cadastrar conta.");
            System.out.println("2 - Entrar. ");
            System.out.println("3 - Encerrar operação.");
            a = sc.nextInt();
            sc.nextLine();

            switch (a) {
                case 1:
                    if (quantidade == 50) {
                        System.out.println("O sistema atingiu o limite de usuários!");
                        break;
                    }
                    System.out.println("Cadastramento de conta. ");
                    System.out.print("Nome completo: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    for (int i = 0; i < quantidade; i++) {
                        if (usuarios[i].email.equals(email)) {
                            System.out.println("Este email já está em uso.");
                            return;
                        }
                    }
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Seu saldo atual: ");
                    double saldo = sc.nextDouble();
                    sc.nextLine();

                    usuarios[quantidade] = new Usuario(nome, email, senha, saldo);
                    quantidade++;
                    System.out.println("Conta criada com sucesso!");
                    break;

                case 2:
                    System.out.print("Insira seu email: ");
                    String emailInserido = sc.nextLine();
                    System.out.print("Insira sua senha: ");
                    String senhaInserida = sc.nextLine();

                    Usuario usuarioLogado = procurarUsuario(emailInserido, senhaInserida, usuarios, quantidade);

                    if (usuarioLogado == null) {
                        System.out.println("Email ou senha incorretos.");
                        break;
                    }

                    System.out.println("Login bem-sucedido, " + usuarioLogado.nome + "!");

                    do {
                        System.out.println("Insira uma opção: ");
                        System.out.println("1 - Exibir dados atuais. ");
                        System.out.println("2 - Alterar/adicionar dados.");
                        System.out.println("3 - Sair da conta. ");
                        b = sc.nextInt();
                        sc.nextLine();

                        switch (b) {
                            case 1:
                                exibirDadosUsuario(usuarioLogado);
                                break;
                            case 2:
                                do {
                                    System.out.println("Modificando dados: ");
                                    System.out.println("1 - Atualizar saldo.");
                                    System.out.println("2 - Adicionar despesa fixa.");
                                    System.out.println("3 - Adicionar despesa extra.");
                                    System.out.println("4 - Adicionar recebimentos.");
                                    System.out.println("5 - Adicionar uma meta financeira");
                                    System.out.println("6 - Atualizar uma meta financeira");
                                    System.out.println("7 - Retornar ao menu principal. ");
                                    c = sc.nextInt();
                                    sc.nextLine();

                                    switch (c) {
                                        case 1:
                                            System.out.print("Novo saldo: ");
                                            usuarioLogado.saldo_atual = sc.nextDouble();
                                            sc.nextLine();
                                            System.out.println("Saldo atualizado com sucesso!");
                                            break;
                                        case 2:
                                            System.out.print("Nome da despesa fixa: ");
                                            String nomeDespesa = sc.nextLine();
                                            System.out.print("Valor: ");
                                            double valorDespesa = sc.nextDouble();
                                            if (valorDespesa > usuarioLogado.saldo_atual){
                                                System.out.println("O valor inserido é superior ao saldo disponivel.");
                                                break;
                                            }
                                            sc.nextLine();
                                            System.out.print("Data do pagamento: ");
                                            String dataDespesa = sc.nextLine();
                                            usuarioLogado.despesasFixas.add(new DespesaFixa(nomeDespesa, valorDespesa, dataDespesa));
                                            System.out.println("Despesa fixa registrada!");
                                            usuarioLogado.saldo_atual -= valorDespesa;
                                            System.out.println("Saldo atualizado para R$" + usuarioLogado.saldo_atual);
                                            break;
                                        case 3:
                                            System.out.print("Nome da despesa extra: ");
                                            String nomeDespesaExtra = sc.nextLine();
                                            System.out.print("Valor: ");
                                            double valorDespesaExtra = sc.nextDouble();
                                            sc.nextLine();
                                            if (valorDespesaExtra > usuarioLogado.saldo_atual){
                                                System.out.println("O valor inserido é superior ao saldo disponivel.");
                                                break;
                                            }
                                            System.out.print("Data do pagamento: ");
                                            String dataDespesaExtra = sc.nextLine();
                                            usuarioLogado.despesasExtras.add(new DespesaExtra(nomeDespesaExtra, valorDespesaExtra, dataDespesaExtra));
                                            System.out.println("Despesa extra registrada!");
                                            usuarioLogado.saldo_atual -= valorDespesaExtra;
                                            System.out.println("Saldo atualizado para R$" + usuarioLogado.saldo_atual);
                                            break;
                                        case 4:
                                            System.out.println("Adicione o nome do seu recebimento: ");
                                            String nomeRecebimento = sc.nextLine();
                                            System.out.println("Adicione o valor do recebimento: ");
                                            double valorRecebimento = sc.nextDouble();
                                            sc.nextLine();
                                            System.out.println("Adicione a data do recebimento: ");
                                            String dataRecebimento = sc.nextLine();
                                            usuarioLogado.recebimentos.add(new Recebimentos(nomeRecebimento, valorRecebimento, dataRecebimento));
                                            usuarioLogado.saldo_atual += valorRecebimento;
                                            System.out.println("Valor recebido! O saldo foi atualizado para R$" + usuarioLogado.saldo_atual);
                                            break;
                                        case 5:
                                            System.out.println("Nome da sua meta financeira: ");
                                            String nomeMeta = sc.nextLine();
                                            System.out.println("Saldo inicial para a meta: ");
                                            double saldoMeta = sc.nextDouble();
                                            sc.nextLine();
                                            System.out.println("Valor que deseja atingir: ");
                                            double valorMeta = sc.nextDouble();
                                            double diferenca = valorMeta - saldoMeta;
                                            System.out.println("Meta financeira criada com sucesso! ");
                                            System.out.println("Ainda faltam R$" + diferenca + " para você atingir a meta " + nomeMeta);
                                            usuarioLogado.metas.add(new MetasFinanceiras(nomeMeta, saldoMeta, valorMeta));
                                            break;
                                        case 6:
                                            System.out.println("Escolha a meta financeira que deseja atualizar:");
                                            for (int i = 0; i < usuarioLogado.metas.size(); i++) {
                                                System.out.println((i + 1) + " - " + usuarioLogado.metas.get(i).nome);
                                            }
                                            int metaEscolhida = sc.nextInt();
                                            sc.nextLine();
                                            if (metaEscolhida < 1 || metaEscolhida > usuarioLogado.metas.size()) {
                                                System.out.println("Opção inválida.");
                                                break;
                                            }
                                            MetasFinanceiras meta = usuarioLogado.metas.get(metaEscolhida - 1);
                                            System.out.println("Atualizando a meta financeira: " + meta.nome);
                                            System.out.println("1 - Atualizar saldo atual");
                                            System.out.println("2 - Atualizar valor da meta");
                                            int escolhaAtualizacao = sc.nextInt();
                                            sc.nextLine();

                                            switch (escolhaAtualizacao) {
                                                case 1:
                                                    System.out.print("Novo saldo atual: ");
                                                    double novoSaldo = sc.nextDouble();
                                                    sc.nextLine();
                                                    meta.saldo_atual = novoSaldo;
                                                    System.out.println("Saldo atualizado com sucesso!");
                                                    break;
                                                case 2:
                                                    System.out.print("Novo valor da meta: ");
                                                    double novoValorMeta = sc.nextDouble();
                                                    sc.nextLine();
                                                    meta.valor_meta = novoValorMeta;
                                                    System.out.println("Valor da meta atualizado com sucesso!");
                                                    break;

                                                default:
                                                    System.out.println("Opção inválida.");
                                            }
                                            break;
                                        case 7:
                                            System.out.println("Voltando ao menu...");
                                            break;
                                    }
                                } while (c != 7);
                                break;
                        }
                    } while (b != 3);
                    break;
                case 3:
                    System.out.println("Operação encerrada.");
                    break;
            }
        } while (a != 3);
        sc.close();
    }

    public static void exibirDadosUsuario(Usuario usuario) {
        System.out.println("\n--- DADOS DO USUÁRIO ---");
        System.out.println("Nome: " + usuario.nome);
        System.out.println("Email: " + usuario.email);
        System.out.println("Saldo Atual: R$" + String.format("%.2f", usuario.saldo_atual));
        System.out.println("\n--- Despesas Fixas ---");
        if (usuario.despesasFixas.isEmpty()) {
            System.out.println("Sem despesa fixa.");
        } else {
            for (DespesaFixa despesa : usuario.despesasFixas) {
                System.out.println("Nome: " + despesa.nome);
                System.out.println("Valor: R$" + String.format("%.2f", despesa.valor));
                System.out.println("Data: " + despesa.data);
                System.out.println();
            }
        }
        System.out.println("\n--- Despesas Extras ---");
        if (usuario.despesasExtras.isEmpty()) {
            System.out.println("Sem despesa extra.");
        } else {
            for (DespesaExtra despesa : usuario.despesasExtras) {
                System.out.println("Nome: " + despesa.nome);
                System.out.println("Valor: R$" + String.format("%.2f", despesa.valor));
                System.out.println("Data: " + despesa.data);
                System.out.println();
            }
        }
        System.out.println("\n--- Recebimentos ---");
        if (usuario.recebimentos.isEmpty()) {
            System.out.println("Sem recebimentos.");
        } else {
            for (Recebimentos recebimento : usuario.recebimentos) {
                System.out.println("Nome: " + recebimento.nome);
                System.out.println("Valor: R$" + String.format("%.2f", recebimento.valor));
                System.out.println("Data: " + recebimento.data);
                System.out.println();
            }
        }
        System.out.println("\n--- Metas Financeiras ---");
        if (usuario.metas.isEmpty()) {
            System.out.println("Sem metas financeiras.");
        } else {
            for (MetasFinanceiras meta : usuario.metas) {
                System.out.println("Nome: " + meta.nome);
                System.out.println("Saldo Atual: R$" + String.format("%.2f", meta.saldo_atual));
                System.out.println("Objetivo: " + meta.valor_meta);
                System.out.println();
            }
        }
    }


    public static Usuario procurarUsuario(String emailInserido, String senhaInserida, Usuario[] usuarios, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (usuarios[i].email.equals(emailInserido) && usuarios[i].senha.equals(senhaInserida)) {
                return usuarios[i];
            }
        }
        return null;
    }
}