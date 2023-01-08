package ui;

import code.Campeonato;
import code.IUtilizadorFacade;
import code.Utilizador;
import code.UtilizadorFacade;

import java.util.Scanner;

public class CampeonatoUI {
    private IUtilizadorFacade modelUtilizador;

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;
    private String nome;

    private Campeonato c;
    public CampeonatoUI(String nome) {
        this.nome = nome;
        this.c = new Campeonato(nome);

        // Criar o menu
        this.menu = new Menu(nome,new String[]{
                "Criar Player Set",
                "Adicionar Circuito",
                "Ready",

        });
        this.menu.setHandler(1, this::trataCriaPlayerSet);
        this.menu.setHandler(2, this::trataAdicionarCircuitos);
        this.menu.setHandler(3, this::trataReady);
        this.modelUtilizador = new UtilizadorFacade();
        scin = new Scanner(System.in);
    }



    public void run() {
        this.menu.run();
        System.out.println("Até breve!...");
    }

    private void trataCriaPlayerSet() {
        try {
            System.out.println("Inserir nome do novo Player: ");
            String nome = scin.nextLine();
            if (!this.modelUtilizador.existePlayer(nome)) {
                new PlayerUI(nome).run();
                c.addPlayersets(nome);
            } else {
                System.out.println("Este player ja existe!!!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataReady() {
        try {
            this.c.putallready();
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    private void trataAdicionarCircuitos(){
            System.out.println("Insira a quantidade de circuitos");
            int n = scin.nextInt();
            scin.nextLine();
            this.modelUtilizador.mostracircuitos();
            for(int i = 0;i<n;i++){
                System.out.println("Indique o nome do circuito que pretende adicionar");
                String circuito = scin.nextLine();
                if(!this.modelUtilizador.existeCircuito(circuito)){
                    System.out.println("Circuito nao existe");
                }else {
                    this.c.criaCorrida(circuito);
                    System.out.println("Circuito adicionado com sucesso!");
                }
        }

    }



}
