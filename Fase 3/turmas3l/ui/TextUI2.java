package uminho.dss.turmas3l.ui;

import uminho.dss.turmas3l.business.ITurmasFacade;
import uminho.dss.turmas3l.business.TurmasFacade;

import java.util.Scanner;

public class TextUI2 {
    private ITurmasFacade model;

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;
    public TextUI2() {

        // Criar o menu
        this.menu = new Menu(new String[]{
                "Entrar como Admin",
                "Entrar como Player",

        });


        this.model = new TurmasFacade();
        scin = new Scanner(System.in);
    }

    public void run() {
        this.menu.runOnce();
        System.out.println("Até breve!...");
    }
}
