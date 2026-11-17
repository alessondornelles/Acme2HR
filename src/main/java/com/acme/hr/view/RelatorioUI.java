
package com.acme.hr.view;

import java.util.List;
import java.util.Scanner;

import com.acme.hr.model.Funcionario;

import com.acme.hr.service.FuncionarioService;
import com.acme.hr.service.ProjetoService;
import com.acme.hr.service.AlocacaoService;


public class RelatorioUI {

    private final FuncionarioService funcionarioService;
    private final ProjetoService clienteService;
    private final AlocacaoService locacaoService;
    private final Scanner scanner;

    public RelatorioUI(
            FuncionarioService funcionarioService,
            ProjetoService clienteService,
            AlocacaoService locacaoService,
            Scanner scanner) {

        this.funcionarioService = funcionarioService;
        this.clienteService = clienteService;
        this.locacaoService = locacaoService;
        this.scanner = scanner;
    }

    public void exibirResumo() {

        System.out.println("\n================================");
        System.out.println("       RESUMO DA LOCADORA");
        System.out.println("================================");

        System.out.println("Total de veículos: " +
                funcionarioService.listarTodos().size());

        System.out.println("Veículos disponíveis: " +
                funcionarioService.listarDisponiveis().size());

        System.out.println("Total de clientes: " +
                clienteService.listarTodos().size());

        System.out.println("Locações ativas: " +
                locacaoService.listarAtivas().size());

        System.out.println("Total de locações: " +
                locacaoService.listarTodas().size());

        System.out.println("================================");
    }


    public void listarFuncionarios() {

        List<Funcionario> funcionarios = funcionarioService.listarTodos();

        System.out.println("\n--- RELATÓRIO DE FUNCIONARIOS ---");

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios) {

            System.out.println("--------------------------------");
            System.out.println("Código: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());

            System.out.printf("Diária base: R$ %.2f%n",
                    funcionario.getSalarioBase());

            System.out.printf("Diária ajustada: R$ %.2f%n",
                    funcionario.calcularValorAluguel(1));

            System.out.println("Disponibilidade: " +
                    (funcionario.isDisponivel() ? "DISPONÍVEL" : "ALUGADO"));

            System.out.println("--------------------------------");
        }
    }


    public void exibirMenu() {

        int opcao = -1;

        do {
            System.out.println("\n================================");
            System.out.println("       RELATÓRIOS");
            System.out.println("================================");
            System.out.println("1 - Resumo geral");
            System.out.println("2 - Listar funcionarios");
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
                    exibirResumo();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }


}
