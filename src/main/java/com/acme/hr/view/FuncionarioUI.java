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

            System.out.println("ID: " + funcionario.getId());
            System.out.println("Placa: " + funcionario.getPlaca());
            System.out.println("Marca: " + funcionario.getMarca());
            System.out.println("Modelo: " + funcionario.getModelo());
            System.out.println("Ano: " + funcionario.getAnoFabricacao());
            System.out.printf(
                    "Diária base: R$ %.2f%n",
                    funcionario.getValorDiaria()
            );

            System.out.printf(
                    "Diária com ajuste: R$ %.2f%n",
                    funcionario.calcularValorAluguel(1)
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

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Placa: ");
            String placa = scanner.nextLine();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Ano de fabricação: ");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.print("Valor da diária: ");
            BigDecimal diaria =
                    new BigDecimal(scanner.nextLine().replace(",", "."));

            System.out.print("Quantidade de portas: ");
            int portas = Integer.parseInt(scanner.nextLine());

            FuncionarioCLT funcionario = new FuncionarioCLT(
                    id,
                    placa,
                    marca,
                    modelo,
                    ano,
                    diaria,
                    portas
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

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Placa: ");
            String placa = scanner.nextLine();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Ano de fabricação: ");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.print("Valor da diária: ");
            BigDecimal diaria =
                    new BigDecimal(scanner.nextLine().replace(",", "."));

            System.out.print("Cilindradas: ");
            int cilindradas = Integer.parseInt(scanner.nextLine());

            FuncionarioPJ funcionarioPJ = new FuncionarioPJ(
                    id,
                    placa,
                    marca,
                    modelo,
                    ano,
                    diaria,
                    cilindradas
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

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Placa: ");
            String placa = scanner.nextLine();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Ano de fabricação: ");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.print("Valor da diária: ");
            BigDecimal valorDiaria =
                    new BigDecimal(scanner.nextLine().replace(",", "."));

            System.out.print("Capacidade de carga (kg): ");
            int capacidade = Integer.parseInt(scanner.nextLine());

            FuncionarioComissionado funcionarioComissionado = new FuncionarioComissionado(
                    id,
                    placa,
                    marca,
                    modelo,
                    ano,
                    valorDiaria,
                    capacidade
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
