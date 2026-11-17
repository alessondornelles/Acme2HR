package com.acme.hr.view;

import com.acme.hr.model.Funcionario;
import com.acme.hr.model.FuncionarioCLT;
import com.acme.hr.model.FuncionarioPJ;
import com.acme.hr.model.FuncionarioComissionado;

import com.acme.hr.service.FuncionarioService;
import com.acme.hr.exception.CadastroDuplicadoException;

import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal;

public class FuncionarioUI {

    private final FuncionarioService funcionarioService;
    private final Scanner scanner;

    public FuncionarioUI(
            FuncionarioService funcionarioService,
            Scanner scanner) {

        this.funcionarioService = funcionarioService;
        this.scanner = scanner;
    }

    public void exibirMenu() {

        int opcao = -1;

        do {
            System.out.println("\n================================");
            System.out.println("    GERENCIAR FUNCIONARIOS");
            System.out.println("================================");
            System.out.println("1 - Cadastrar funcionario CLT");
            System.out.println("2 - Cadastrar funcionario PJ");
            System.out.println("3 - Cadastrar funcionario Comissionado");
            System.out.println("4 - Listar todos os funcionários");
            System.out.println("5 - Listar funcionários disponíveis");
            System.out.println("0 - Voltar ao menu principal");
            System.out.println("================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção numérica válida.");
                continue;
            }
            //opcao = scanner.nextInt();
            //scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarFuncionarioCLT();
                    break;
                case 2:
                    cadastrarFuncionarioPJ();
                    break;
                case 3:
                    cadastrarFuncionarioComissionado();
                    break;
                case 4:
                    listarTodos();
                    break;
                case 5:
                    listarDisponiveis();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void listarTodos() {

        List<Funcionario> funcionarios = funcionarioService.listarTodos();

        exibirFuncionarios(funcionarios);
    }

    private void listarDisponiveis() {

        List<Funcionario> funcionarios =
                funcionarioService.listarDisponiveis();

        exibirFuncionarios(funcionarios);
    }

    private void exibirFuncionarios(List<Funcionario> funcionarios) {

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum veículo encontrado.");
            return;
        }

        System.out.println("\n========== FUNCIONARIOS ==========");

        for (Funcionario funcionario : funcionarios) {

            System.out.println("Código: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.printf(
                    "Salário Base: R$ %.2f%n",
                    funcionario.getSalarioBase()
            );

            System.out.println(
                    "Disponível: " +
                            (funcionario.isDisponivel() ? "Sim" : "Não")
            );

            System.out.println("------------------------------");
        }
    }


    private void cadastrarFuncionarioCLT() {

        try {
            System.out.println("\n===== CADASTRO DE FUNCIONARIO CLT =====");

            System.out.print("Código: ");
            String id = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Salário base: ");
            BigDecimal salarioBase =
                    new BigDecimal(scanner.nextLine().replace(",", "."));

            FuncionarioCLT funcionario = new FuncionarioCLT(
                    id,
                    nome,
                    cpf,
                    salarioBase
            );

            funcionarioService.cadastrar(funcionario);

            System.out.println("\nFuncionarioCLT cadastrado com sucesso!");

        } catch (NumberFormatException e) {

            System.out.println("Erro: informe valores numéricos válidos.");

        } catch (IllegalArgumentException e) {

            System.out.println("Erro nos dados: " + e.getMessage());

        } catch (com.acme.hr.exception.CadastroDuplicadoException e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }


    private void cadastrarFuncionarioPJ() {

        try {
            System.out.println("\n===== CADASTRO DE FUNCIONARIO PJ =====");

            System.out.print("Código: ");
            String id = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Salário Base: ");
            BigDecimal salarioBase =
                    new BigDecimal(scanner.nextLine().replace(",", "."));

            FuncionarioPJ funcionarioPJ = new FuncionarioPJ(
                    id,
                    nome,
                    cpf,
                    salarioBase
            );

            funcionarioService.cadastrar(funcionarioPJ);

            System.out.println("\nFuncionarioPJ cadastrada com sucesso!");

        } catch (NumberFormatException e) {

            System.out.println("Erro: informe valores numéricos válidos.");

        } catch (IllegalArgumentException e) {

            System.out.println("Erro nos dados: " + e.getMessage());

        } catch (com.acme.hr.exception.CadastroDuplicadoException e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarFuncionarioComissionado() {

        try {
            System.out.println("\n--- CADASTRO DE FUNCIONARIO COMISSIONADO ---");

            System.out.print("Código: ");
            String id = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Salário Base: ");
            BigDecimal salarioBase =
                    new BigDecimal(scanner.nextLine().replace(",", "."));

            FuncionarioComissionado funcionarioComissionado = new FuncionarioComissionado(
                    id,
                    nome,
                    cpf,
                    salarioBase
            );

            funcionarioService.cadastrar(funcionarioComissionado);

            System.out.println("FuncionarioComissionado cadastrada com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Erro: informe valores numéricos válidos.");

        } catch (CadastroDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
