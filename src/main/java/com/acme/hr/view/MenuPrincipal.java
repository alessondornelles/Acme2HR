package com.acme.hr.view;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner;
    private final FuncionarioUI funcionarioUI;
    private final ProjetoUI projetoUI;
    private final AlocacaoUI alocacaoUI;
    private final RelatorioUI relatorioUI;

    public MenuPrincipal(
            Scanner scanner,
            FuncionarioUI funcionarioUI,
            ProjetoUI projetoUI,
            AlocacaoUI alocacaoUI,
            RelatorioUI relatorioUI) {

        this.scanner = scanner;
        this.funcionarioUI = funcionarioUI;
        this.projetoUI = projetoUI;
        this.alocacaoUI = alocacaoUI;
        this.relatorioUI = relatorioUI;
    }

    public void exibirMenu() {

        int opcao = -1;

        do {
            System.out.println("\n================================");
            System.out.println("     ACME HR");
            System.out.println("================================");
            System.out.println("1 - Gerenciar funcionários");
            System.out.println("2 - Gerenciar projetos");
            System.out.println("3 - Gerenciar alocações");
//            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.println("================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção numérica válida.");
                continue;
            }

            switch (opcao) {
                case 1:
                    funcionarioUI.exibirMenu();
                    break;
                case 2:
                    projetoUI.exibirMenu();
                    break;
                case 3:
                    alocacaoUI.exibirMenu();
                    break;
                case 4:
                    //relatorioUI.exibirMenu();
                    //relatorioUI.exibirResumo();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}
