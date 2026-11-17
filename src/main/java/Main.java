package com.acme.hr;

import java.util.Scanner;

import com.acme.hr.view.MenuPrincipal;

import com.acme.hr.repository.FuncionarioRepositoryMemoria;
import com.acme.hr.service.FuncionarioService;
import com.acme.hr.view.FuncionarioUI;

import com.acme.hr.repository.ClienteRepositoryMemoria;
import com.acme.hr.service.ClienteService;
import com.acme.hr.view.ClienteUI;

import com.acme.hr.repository.AlocacaoRepositoryMemoria;
import com.acme.hr.service.AlocacaoService;
import com.acme.hr.view.AlocacaoUI;

import com.acme.hr.view.RelatorioUI;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ----------------------------------------
        // Repositórios
        // ----------------------------------------

        FuncionarioRepositoryMemoria funcionarioRepository =
                new FuncionarioRepositoryMemoria();

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        AlocacaoRepositoryMemoria alocacaoRepository =
                new AlocacaoRepositoryMemoria();

        // ----------------------------------------
        // Serviços
        // ----------------------------------------

        FuncionarioService funcionarioService =
                new FuncionarioService(funcionarioRepository);

        ClienteService clienteService =
                new ClienteService(clienteRepository);

        AlocacaoService alocacaoService =
                new AlocacaoService(
                        clienteRepository,
                        funcionarioRepository,
                        alocacaoRepository
                );



        // ----------------------------------------
        // Interfaces
        // ----------------------------------------

        FuncionarioUI funcionarioUI =
                new FuncionarioUI(funcionarioService, scanner);

        ClienteUI clienteUI =
                new ClienteUI(clienteService, scanner);

        AlocacaoUI alocacaoUI =
                new AlocacaoUI(alocacaoService, scanner);

        RelatorioUI relatorioUI =
                new RelatorioUI(
                        funcionarioService,
                        clienteService,
                        alocacaoService,
                        scanner
                );

        // ----------------------------------------
        // Menu principal
        // ----------------------------------------

        MenuPrincipal menu =
                new MenuPrincipal(
                        scanner,
                        funcionarioUI,
                        clienteUI,
                        alocacaoUI,
                        relatorioUI
                );

        menu.exibirMenu();
    }
}
