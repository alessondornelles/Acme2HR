
package com.acme.hr.view;

import com.acme.hr.model.Projeto;
import com.acme.hr.service.ProjetoService;
import com.acme.hr.exception.CadastroDuplicadoException;
import com.acme.hr.exception.EntidadeNaoEncontradaException;

import java.util.List;
import java.util.Scanner;

public class ProjetoUI {

    private final ProjetoService projetoService;
    private final Scanner scanner;

    public ProjetoUI(ProjetoService projetoService, Scanner scanner) {
        this.projetoService = projetoService;
        this.scanner = scanner;
    }

    public void exibirMenu() {

        int opcao = -1;

        do {
            System.out.println("\n================================");
            System.out.println("     GERENCIAR PROJETOS");
            System.out.println("================================");
            System.out.println("1 - Cadastrar projeto");
            System.out.println("2 - Listar projetos");
            System.out.println("3 - Buscar projeto por ID");
            System.out.println("4 - Remover projeto");
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
                    cadastrarProjeto();
                    break;
                case 2:
                    listarProjetos();
                    break;
                case 3:
                    buscarProjeto();
                    break;
                case 4:
                    removerProjeto();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarProjeto() {

        try {
            System.out.println("\n--- CADASTRO DE PROJETO ---");

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            Projeto projeto = new Projeto(
                    id, nome
            );

            projetoService.cadastrar(projeto);

            System.out.println("Projeto cadastrado com sucesso!");

        } catch (CadastroDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarProjetos() {

        List<Projeto> projetos = projetoService.listarTodos();

        System.out.println("\n--- PROJETOS CADASTRADOS ---");

        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
            return;
        }

        for (Projeto projeto : projetos) {
            exibirProjeto(projeto);
        }
    }

    private void buscarProjeto() {

        System.out.print("Informe o ID do projeto: ");
        String id = scanner.nextLine();

        try {
            Projeto projeto = projetoService.buscarPorId(id);
            exibirProjeto(projeto);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerProjeto() {

        System.out.print("Informe o ID do projeto: ");
        String id = scanner.nextLine();

        try {
            projetoService.remover(id);
            System.out.println("Projeto removido com sucesso!");

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void exibirProjeto(Projeto projeto) {

        System.out.println("--------------------------------");
        System.out.println("ID: " + projeto.getId());
        System.out.println("Nome: " + projeto.getNome());
        System.out.println("--------------------------------");
    }
}
