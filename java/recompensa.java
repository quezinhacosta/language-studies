import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class Cliente {
    String nome;
    String email;
    int pontos_cliente;

    public Cliente(String nome, String email, int pontos_cliente) {
        this.nome = nome;
        this.email = email;
        this.pontos_cliente = pontos_cliente;
    }
}

class Recompensa {
    String descricao;
    int custoEmPontos;

    public Recompensa(String descricao, int custoEmPontos) {
        this.descricao = descricao;
        this.custoEmPontos = custoEmPontos;
    }
}

public class Atv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cliente[] clientes = new Cliente[3];

        // Criando clientes
        clientes[0] = new Cliente("Renata", "renata@loja.com", 0);
        clientes[1] = new Cliente("Márcio", "marcio@loja.com", 0);
        clientes[2] = new Cliente("Jeanderson", "jeanderson@loja.com", 0);

        // Criando recompensas
        Recompensa[] recompensas = {
                new Recompensa("Desconto de R$10", 100),
                new Recompensa("Desconto de R$25", 250),
                new Recompensa("Frete grátis", 500)
        };

        int op;
        System.out.println("Bem-vindo ao sistema de recompensa.");

        do {
            System.out.println("\nSelecione uma opção: ");
            System.out.println("1 - Adicionar compra/pontos");
            System.out.println("2 - Resgatar recompensa");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Email do cliente que deseja adicionar compra: ");
                    String emailBusca = sc.nextLine();
                    Cliente clienteEncontrado = buscaEmail(clientes, emailBusca);

                    if (clienteEncontrado != null) {
                        System.out.print("Digite o valor da compra: ");
                        double valorCompra = sc.nextDouble();
                        sc.nextLine();

                        int pontosGanhos = calcularPonto(valorCompra);
                        clienteEncontrado.pontos_cliente += pontosGanhos;

                        System.out.println("Compra registrada! " + clienteEncontrado.nome +
                                " agora tem " + clienteEncontrado.pontos_cliente + " pontos.");
                    } else {
                        System.out.println("E-mail não encontrado!");
                    }
                    break;

                case 2:
                    System.out.print("Digite o email do cliente que deseja resgatar uma recompensa: ");
                    String emailResgate = sc.nextLine();
                    Cliente clienteParaResgate = buscaEmail(clientes, emailResgate);

                    if (clienteParaResgate != null) {
                        System.out.println("\nRecompensas disponíveis:");
                        for (int i = 0; i < recompensas.length; i++) {
                            System.out.println((i + 1) + " - " + recompensas[i].descricao +
                                    " (Custa " + recompensas[i].custoEmPontos + " pontos)");
                        }
                        System.out.print("Escolha uma recompensa pelo número: ");
                        int escolha = sc.nextInt();
                        sc.nextLine();

                        if (escolha > 0 && escolha <= recompensas.length) {
                            Recompensa recompensaEscolhida = recompensas[escolha - 1];

                            if (clienteParaResgate.pontos_cliente >= recompensaEscolhida.custoEmPontos) {
                                clienteParaResgate.pontos_cliente -= recompensaEscolhida.custoEmPontos;
                                System.out.println("Recompensa resgatada: " + recompensaEscolhida.descricao);
                            } else {
                                System.out.println("Pontos insuficientes para essa recompensa.");
                            }
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else {
                        System.out.println("E-mail não encontrado!");
                    }
                    break;
                case 3:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }

        } while (op != 3);

        sc.close();
    }

    public static Cliente buscaEmail(Cliente[] clientes, String emailBusca) {
        for (Cliente cliente : clientes) {
            if (cliente.email.equals(emailBusca)) {
                return cliente;
            }
        }
        return null;
    }

    public static int calcularPonto(double valorCompra) {
        return (int) (valorCompra * 10);
    }

}