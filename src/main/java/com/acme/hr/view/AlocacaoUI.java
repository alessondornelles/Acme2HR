
package com.acme.hr.view;

import java.util.List;
import java.util.Scanner;

import com.acme.hr.model.Alocacao;
import com.acme.hr.service.AlocacaoService;
import com.acme.hr.exception.EntidadeNaoEncontradaException;

public class AlocacaoUI {

    private final AlocacaoService alocacaoService;
    private final Scanner scanner;

    public AlocacaoUI(AlocacaoService alocacaoService, Scanner scanner) {
        this.alocacaoService = alocacaoService;
        this.scanner = scanner;
    }

    public void exibirMenu() {

        int opcao = -1;

        do {
            System.out.println("\n================================");
            System.out.println("     GERENCIAR LOCAÇÕES");
            System.out.println("================================");
            System.out.println("1 - Registrar locação");
            System.out.println("2 - Listar locações ativas");
            System.out.println("3 - Consultar histórico");
            System.out.println("4 - Devolver funcionário");
            System.out.println("0 - Voltar");
            System.out.println("================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção numérica válida.");
                continue;
            }
            //opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    registrarAlocacao();
                    break;
                case 2:
                    listarLocacoesAtivas();
                    break;
                case 3:
                    consultarHistorico();
                    break;
                case 4:
                    devolverFuncionario();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }


    private void registrarAlocacao() {

        try {
            System.out.println("\n--- REGISTRAR LOCAÇÃO ---");

            System.out.print("ID da locação: ");
            String id = scanner.nextLine();

            System.out.print("ID do cliente: ");
            String clienteId = scanner.nextLine();

            System.out.print("ID do veículo: ");
            String veiculoId = scanner.nextLine();

            System.out.print("Quantidade de dias: ");
            int dias = Integer.parseInt(scanner.nextLine());

            Alocacao alocacao = alocacaoService.criarAlocacao(
                    id,
                    clienteId,
                    veiculoId,
                    dias
            );

            System.out.println("\nLocação registrada com sucesso!");
            System.out.println("--------------------------------");
            System.out.println("Locação: " + alocacao.getId());
            System.out.println("Cliente: " + alocacao.getCliente().getNome());
            System.out.println("Funcionário: " +
                    alocacao.getFuncionario().getCpf() + " ");
            System.out.println("Dias: " + alocacao.getDias());
            System.out.printf("Valor total: R$ %.2f%n",
                    alocacao.getValorTotal());
            System.out.println("--------------------------------");

        } catch (NumberFormatException e) {
            System.out.println("Erro: informe uma quantidade de dias válida.");

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    private void listarLocacoesAtivas() {

        List<Alocacao> locacoes = alocacaoService.listarAtivas();

        System.out.println("\n--- LOCAÇÕES ATIVAS ---");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações ativas.");
            return;
        }

        for (Alocacao alocacao : locacoes) {

            System.out.println("--------------------------------");
            System.out.println("ID da locação: " + alocacao.getId());
            System.out.println("Cliente: " +
                    alocacao.getCliente().getNome());

            System.out.println("Funcionário: " +
                    alocacao.getFuncionario().getCpf() + " "                   );

            System.out.println("Nome: " +
                    alocacao.getFuncionario().getNome());

            System.out.println("Quantidade de dias: " +
                    alocacao.getDias());

            System.out.printf("Valor total: R$ %.2f%n",
                    alocacao.getValorTotal());

            System.out.println("Situação: ATIVA");
            System.out.println("--------------------------------");
        }
    }


    private void devolverFuncionario() {

        try {
            System.out.println("\n--- DEVOLUÇÃO DE VEÍCULO ---");

            System.out.print("Informe o ID da locação: ");
            String id = scanner.nextLine();

            Alocacao alocacao = alocacaoService.buscarPorId(id);

            alocacaoService.devolverAlocacao(id);

            System.out.println("\nDevolução realizada com sucesso!");
            System.out.println("--------------------------------");
            System.out.println("Locação: " + alocacao.getId());
            System.out.println("Cliente: " +
                    alocacao.getCliente().getNome());

            System.out.println("Veículo: " +
                    alocacao.getFuncionario().getCpf() + " ");

            System.out.printf("Valor da locação: R$ %.2f%n",
                    alocacao.getValorTotal());

            System.out.println("Situação: ENCERRADA");
            System.out.println("--------------------------------");

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    private void consultarHistorico() {

        List<Alocacao> locacoes = alocacaoService.listarTodas();

        System.out.println("\n--- HISTÓRICO DE LOCAÇÕES ---");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações registradas.");
            return;
        }

        for (Alocacao alocacao : locacoes) {

            System.out.println("--------------------------------");
            System.out.println("ID da locação: " + alocacao.getId());

            System.out.println("Cliente: " +
                    alocacao.getCliente().getNome());

            System.out.println("Funcionario: " +
                    alocacao.getFuncionario().getCpf() + " " );

            System.out.println("Nome: " +
                    alocacao.getFuncionario().getNome());

            System.out.println("Quantidade de dias: " +
                    alocacao.getDias());

            System.out.printf("Valor total: R$ %.2f%n",
                    alocacao.getValorTotal());

            String situacao = alocacao.isAtiva()
                    ? "ATIVA"
                    : "ENCERRADA";

            System.out.println("Situação: " + situacao);

            System.out.println("--------------------------------");
        }
    }

}
