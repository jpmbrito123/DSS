package ui;

import code.IUtilizadorFacade;
import code.PlayerSet;
import code.UtilizadorFacade;
import data.PlayerSetDAO;

import java.util.Scanner;

public class PlayerUI {
        private IUtilizadorFacade modelUtilizador;

        // Menus da aplicação
        private Menu menu;

        // Scanner para leitura
        private Scanner scin;
        private String nome;

        private PlayerSet p = new PlayerSet("",0,"","","",0);
        public PlayerUI(String nome) {
            this.nome = nome;
            this.p.setIdPlayerSet(nome);
            this.menu = new Menu(nome,new String[]{
                    "Escolher Carro",
                    "Escolher Piloto",
                    "Escolher Pneu",
                    "Escolher Motor",
                    "Guardar Player"
            });
            this.menu.setHandler(1, this::trataEscolherCarro);
            this.menu.setHandler(2, this::trataEscolherPiloto);
            this.menu.setHandler(3, this::trataEscolherPneu);
            this.menu.setHandler(4, this::trataEscolherMotor);
            this.menu.setHandler(5, this::trataGuardarPlayer);


            this.modelUtilizador = new UtilizadorFacade();
            scin = new Scanner(System.in);
        }

        public void run() {
            this.menu.run();
            System.out.println("Até breve!...");
        }

    private void trataEscolherCarro() {
        try {
            this.modelUtilizador.mostraCarros();
            System.out.println("Escolha carro ");
            String idcarro = scin.nextLine();
            if(this.modelUtilizador.existeCarro(idcarro)){
                this.p.setCarro(idcarro);}
            else {
                System.out.println("Esse carro nao existe!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trataGuardarPlayer(){
        if(this.modelUtilizador.existeCarro(this.p.getCarro()) && this.modelUtilizador.existePiloto(this.p.getPiloto())){
            PlayerSetDAO.getInstance().put(this.p.getIdPlayerSet(),this.p);}
        else {
            System.out.println("Esse carro nao existe ou o pilote nao existe!");
        }

    }
    private void trataEscolherPneu(){
        try {
            System.out.println("Escolher pneu : Chuva , duro ,seco");
            String tipo = scin.nextLine();
            this.p.setPneu(tipo);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataEscolherMotor(){
        try {
            System.out.println("Escolher agressividade motor : 1-Arriscado , 2-Normal , 3-Seguro");
            int motor = scin.nextInt();
            this.p.setAgressividadeMotriz(motor);
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
                this.p.setPiloto(camp);
            }
            else
                System.out.println("Esse piloto não existe");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
