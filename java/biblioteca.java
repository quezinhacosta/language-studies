import java.util.Scanner;

class ContaCorrente {
    String numero_conta;
    String nome;
    double saldo;
    String data_abertura;

    public ContaCorrente(String numero_conta, String nome, double saldo, String data_abertura) {
        this.numero_conta = numero_conta;
        this.nome = nome;
        this.saldo = saldo;
        this.data_abertura = data_abertura;
    }
}
public class Banco {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;
        int numero_contas = 0;
        ContaCorrente[] usuarios = new ContaCorrente[100];

        System.out.println("Bem-vindo ao banco Rosé!");

        do {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Criar conta.");
            System.out.println("2 - Entrar.");
            System.out.println("3 - Sair.");
            System.out.print("Opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\nCriando conta...");
                    System.out.print("Insira seu nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Insira o número da conta: ");
                    String numero_conta = sc.nextLine();
                    double saldo;
                    do {
                        System.out.println("Insira o saldo inicial: ");
                        saldo = sc.nextDouble();
                        sc.nextLine();
                        if(saldo < 200.00){
                            System.out.println("O valor inicial não pode ser menor do que R$ 200,00");
                        }
                    } while (saldo < 200.00);
                    System.out.print("Insira a data de abertura (dd/mm/aaaa): ");
                    String data_abertura = sc.nextLine();
                    usuarios[numero_contas] = new ContaCorrente(numero_conta, nome, saldo, data_abertura);
                    System.out.println("Conta criada com sucesso!\n");
                    numero_contas++;
                    break;
                case 2:
                    System.out.print("\nEntrando em sua conta.\nNúmero de conta: ");
                    String contaDigitada = sc.nextLine();
                    ContaCorrente contaAtual = busca(usuarios, contaDigitada, numero_contas);

                    if (contaAtual != null) {
                        System.out.println("\nBem-vindo, " + contaAtual.nome + "!");
                        System.out.println("Saldo atual: R$ " + contaAtual.saldo);

                        int escolha;
                        do {
                            System.out.println("\nSelecione uma opção: ");
                            System.out.println("1 - Depositar.");
                            System.out.println("2 - Transferir.");
                            System.out.println("3 - Sacar.");
                            System.out.println("4 - Sair da conta.");
                            System.out.print("Opção: ");
                            escolha = sc.nextInt();
                            sc.nextLine();

                            switch (escolha) {
                                case 1:
                                    System.out.println("Depositando...");
                                    System.out.print("Insira o valor que deseja depositar: ");
                                    double deposito = sc.nextDouble();
                                    sc.nextLine();
                                    contaAtual.saldo += deposito;
                                    System.out.println("O novo valor da conta é de " + contaAtual.saldo);
                                    break;

                                case 2:
                                    System.out.println("Transferindo...");
                                    System.out.print("Insira o nome da conta que deseja transferir o valor: ");
                                    String conta_destino = sc.nextLine();
                                    ContaCorrente contaDestino = busca(usuarios, conta_destino, numero_contas);

                                    if (contaDestino != null) {
                                        System.out.print("Insira o valor que deseja transferir: ");
                                        double valor_transferencia = sc.nextDouble();
                                        sc.nextLine();

                                        if (valor_transferencia > 0 && valor_transferencia <= contaAtual.saldo) {
                                            contaAtual.saldo -= valor_transferencia;
                                            contaDestino.saldo += valor_transferencia;
                                            System.out.println("O novo valor da conta é de " + contaAtual.saldo);
                                        } else {
                                            System.out.println("Saldo insuficiente ou valor inválido!");
                                        }
                                    } else {
                                        System.out.println("Conta não encontrada!");
                                    }
                                    break;

                                case 3:
                                    System.out.println("Sacando...");
                                    System.out.print("Insira o valor que deseja sacar: ");
                                    double saque = sc.nextDouble();
                                    sc.nextLine();

                                    if (saque > 0 && saque <= contaAtual.saldo) {
                                        contaAtual.saldo -= saque;
                                        System.out.println("O novo valor da conta é de " + contaAtual.saldo);
                                    } else {
                                        System.out.println("Saldo insuficiente ou valor inválido!");
                                    }
                                    break;

                                case 4:
                                    System.out.println("Saindo da conta...");
                                    break;

                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }
                        } while (escolha != 4);

                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (op != 3);

        System.out.println("Operação finalizada!");
        sc.close();
    }

    public static ContaCorrente busca(ContaCorrente[] contas, String numeroConta, int totalContas) {
        for (int i = 0; i < totalContas; i++) {
            if (contas[i].numero_conta.equals(numeroConta)) {
                return contas[i];
            }
        }
        return null;
    }
}