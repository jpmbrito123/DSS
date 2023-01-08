package ui;

import code.IUtilizadorFacade;
import code.UtilizadorFacade;

import java.util.Scanner;

public class PlayerUI {
        private IUtilizadorFacade modelUtilizador;

        // Menus da aplicação
        private Menu menu;

        // Scanner para leitura
        private Scanner scin;
        private String nome;
        public PlayerUI(String nome) {
            this.nome = nome;
            // Criar o menu
            this.menu = new Menu(nome,new String[]{
                    "Escolher Carro",
                    "Escolher Piloto",
                    "Escolher Pneu",
                    "Escolher Motor"
            });
            this.menu.setHandler(1, this::trataEscolherCarro);
            this.menu.setHandler(2, this::trataEscolherPiloto);
            this.menu.setHandler(3, this::trataEscolherPneu);
            this.menu.setHandler(4, this::trataEscolherMotor);


            this.modelUtilizador = new UtilizadorFacade();
            scin = new Scanner(System.in);
        }

        public void run() {
            this.menu.runOnce();
            System.out.println("Até breve!...");
        }

    private void trataEscolherCarro() {
        try {
            this.modelUtilizador.mostraCarros();
            System.out.println("Escolha carro ");
            String idcarro = scin.nextLine();
            if(this.modelUtilizador.existeCarro(idcarro)){
            this.modelUtilizador.defineCarroPlayer(idcarro,nome);}
            else {
                System.out.println("Esse carro nao existe!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataEscolherPneu(){
        try {
            System.out.println("Escolher pneu : Chuva , duro ,seco");
            String tipo = scin.nextLine();
            this.modelUtilizador.definePneuPlayer(tipo,nome);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataEscolherMotor(){
        try {
            System.out.println("Escolher agressividade motor : 1-Arriscado , 2-Normal , 3-Seguro");
            int motor = scin.nextInt();
            this.modelUtilizador.defineMotorPlayer(motor,nome);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataEscolherPiloto(){
        try {this.modelUtilizador.mostraPilotos();
            System.out.println("Selecione um piloto");
            String camp = scin.nextLine();
            if(this.modelUtilizador.existePiloto(camp)){
                this.modelUtilizador.definePilotoPlayer(nome,camp);
            }
            else
                System.out.println("Esse piloto não existe");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
