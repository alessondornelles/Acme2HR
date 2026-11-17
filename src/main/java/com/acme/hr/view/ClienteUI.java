
package com.acme.hr.view;

import com.acme.hr.model.Cliente;
import com.acme.hr.service.ClienteService;
import com.acme.hr.exception.CadastroDuplicadoException;
import com.acme.hr.exception.EntidadeNaoEncontradaException;

import java.util.List;
import java.util.Scanner;

public class ClienteUI {

    private final ClienteService clienteService;
    private final Scanner scanner;

    public ClienteUI(ClienteService clienteService, Scanner scanner) {
        this.clienteService = clienteService;
        this.scanner = scanner;
    }

    public void exibirMenu() {

        int opcao = -1;

        do {
            System.out.println("\n================================");
            System.out.println("     GERENCIAR CLIENTES");
            System.out.println("================================");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar cliente por ID");
            System.out.println("4 - Remover cliente");
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
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    removerCliente();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarCliente() {

        try {
            System.out.println("\n--- CADASTRO DE CLIENTE ---");

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF (000.000.000-00): ");
            String cpf = scanner.nextLine();

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            Cliente cliente = new Cliente(
                    id, nome, cpf, email, telefone
            );

            clienteService.cadastrar(cliente);

            System.out.println("Cliente cadastrado com sucesso!");

        } catch (CadastroDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarClientes() {

        List<Cliente> clientes = clienteService.listarTodos();

        System.out.println("\n--- CLIENTES CADASTRADOS ---");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            exibirCliente(cliente);
        }
    }

    private void buscarCliente() {

        System.out.print("Informe o ID do cliente: ");
        String id = scanner.nextLine();

        try {
            Cliente cliente = clienteService.buscarPorId(id);
            exibirCliente(cliente);

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerCliente() {

        System.out.print("Informe o ID do cliente: ");
        String id = scanner.nextLine();

        try {
            clienteService.remover(id);
            System.out.println("Cliente removido com sucesso!");

        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void exibirCliente(Cliente cliente) {

        System.out.println("--------------------------------");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("E-mail: " + cliente.getEmail());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("--------------------------------");
    }
}
