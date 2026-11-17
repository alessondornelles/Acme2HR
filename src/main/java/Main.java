package com.acme.hr;

import java.util.Scanner;

import com.acme.hr.view.MenuPrincipal;

import com.acme.hr.repository.FuncionarioRepository;
import com.acme.hr.service.FuncionarioService;
import com.acme.hr.view.FuncionarioUI;

import com.acme.hr.repository.ProjetoRepository;
import com.acme.hr.service.ProjetoService;
import com.acme.hr.view.ProjetoUI;

import com.acme.hr.repository.AlocacaoRepository;
import com.acme.hr.service.AlocacaoService;
import com.acme.hr.view.AlocacaoUI;

import com.acme.hr.view.RelatorioUI;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ----------------------------------------
        // Repositórios
        // ----------------------------------------

        FuncionarioRepository funcionarioRepository =
                new FuncionarioRepository();

        ProjetoRepository clienteRepository =
                new ProjetoRepository();

        AlocacaoRepository alocacaoRepository =
                new AlocacaoRepository();

        // ----------------------------------------
        // Serviços
        // ----------------------------------------

        FuncionarioService funcionarioService =
                new FuncionarioService(funcionarioRepository);

        ProjetoService clienteService =
                new ProjetoService(clienteRepository);

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

        ProjetoUI clienteUI =
                new ProjetoUI(clienteService, scanner);

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
