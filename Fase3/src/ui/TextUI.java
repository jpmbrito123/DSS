package ui;

import code.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextUI {


    private IUtilizadorFacade modelUtilizador;
    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;

    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {
        // Criar o menu
        this.menu = new Menu(new String[]{
                "Criar Campeonato",
                "Criar Circuitos",
                "Criar Piloto",
                "Criar Carro",
                "Escolher Campeonato",
        });
        this.menu.setHandler(1, this::trataCriarCampeonatos);
        this.menu.setHandler(2, this::trataCriarCircuitos);
        this.menu.setHandler(3, this::trataCriarPiloto);
        this.menu.setHandler(4, this::trataCriarCarro);
        this.menu.setHandler(5, this::trataEscolherCampeonato);


        this.modelUtilizador = new UtilizadorFacade();
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("Bota que tem...");
    }

    // Métodos auxiliares
    private void trataRegistar() {
        try {
            System.out.println("Inserir nome utilizador: ");
            String nomeUtilizador = scin.nextLine();
            if (!this.modelUtilizador.existeUtilizador(nomeUtilizador)) {
                System.out.println("Email do novo utilizador: ");
                String email = scin.nextLine();
                System.out.println("Palavra passe do novo utilizador: ");
                String passe = scin.nextLine();
                this.modelUtilizador.adicionaUtilizador(new Utilizador(nomeUtilizador,passe,1));
                System.out.println("Utilizador registado adicionado");
            } else {
                System.out.println("Este utilizador ja existe!!!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataFazerLogin() {
        try {
            System.out.println("Insira nome utilizador: ");
            String nome = scin.nextLine();
            if (this.modelUtilizador.existeUtilizador(nome)) {
                System.out.println("Insira palavra passe: ");
                String passe = scin.nextLine();
                if (this.modelUtilizador.passecorreta(nome,passe)){
                    System.out.println("Login efetuado com sucesso");//!!!!!!!! nao sei como se da o login
                }else{
                    System.out.println("Palavra passe errada");
                }
            } else {
                System.out.println("Esse nome de utilizador não existe!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataCriarCampeonatos() {
        try {
            System.out.println("Insira o nome do campeonato");
            String nome = scin.nextLine();
            if(!this.modelUtilizador.existeCampeonato(nome)){
                System.out.println("Campeonato criado!");
            }else {
                System.out.println("Nome ja existe!!!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataCriarCircuitos() {
        try {
            System.out.println("Insira o nome do circuito: ");
            String circuito = scin.nextLine();
            if (!this.modelUtilizador.existeCircuito(circuito)) {
                System.out.println("Numero de Retas: ");
                int retas = scin.nextInt();
                System.out.println("Numero de Curvas: ");
                int curvas = scin.nextInt();
                System.out.println("Numero de Chicanes: ");
                int chicanes = scin.nextInt();
                System.out.println("Distancia: ");
                int distancia = scin.nextInt();
                System.out.println("Nº de voltas: ");
                int voltas = scin.nextInt();
                scin.nextLine();    // Limpar o buffer depois de ler o inteiro
                ArrayList<String> difretas = new ArrayList<>();
                ArrayList<String> difcurvas = new ArrayList<>();
                for(int i =0;i<retas;i++){
                    System.out.println("Insira a dificuldade da reta nª" +i+": ");
                    String dif = scin.nextLine();
                    difretas.add(dif);
                }
                for(int j =0;j<curvas;j++){
                    System.out.println("Insira a dificuldade da curva nª" +j+": ");
                    String dif = scin.nextLine();
                    difcurvas.add(dif);
                }
                this.modelUtilizador.adicionaCircuito(new Circuito(circuito,chicanes,retas,curvas,distancia,voltas,difretas,difcurvas));
                System.out.println("Circuito criado");
            } else {
                System.out.println("Esse nome ja existe!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataCriarPiloto() {
        try {
            System.out.println("Insere nome do piloto: ");
            String nome = scin.nextLine();
            if (!this.modelUtilizador.existePiloto(nome)) {
                System.out.println("CTS : ");
                double cts = scin.nextDouble();
                System.out.println("SBA: ");
                double sba = scin.nextDouble();
                scin.nextLine();
                System.out.println("Nacionalidade: ");
                String nacionalidade = scin.nextLine();
                this.modelUtilizador.adicionaPiloto(new Piloto(nome, cts, sba,nacionalidade));
                System.out.println("Piloto criado");
            } else {
                System.out.println("Esse nome ja existe!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//String idCarro, String marca, String modelo,int cilindrada, int potencia,double pac, double fiabilidade
    private void trataCriarCarro() {
        try {
            System.out.println("Insere o id do carro: ");
            String id = scin.nextLine();
            if (!this.modelUtilizador.existeCarro(id)) {
                System.out.println("Indique o tipo do carro: C1, C2, SC ou GT");
                String tipo = scin.nextLine();
                System.out.println("Marca: ");
                String marca = scin.nextLine();
                System.out.println("Modelo: ");
                String modelo = scin.nextLine();
                System.out.println("Cilindrada: ");
                int cilindrada = scin.nextInt();
                System.out.println("Potencia: ");
                int potencia = scin.nextInt();
                System.out.println("PAC: ");
                double pac = scin.nextDouble();
                System.out.println("Fiabilidade: ");
                double fiabilidade = scin.nextDouble();
                if(Objects.equals(tipo,"C1")) {
                    this.modelUtilizador.adicionaC1(new C1(id, marca, modelo, cilindrada, potencia, pac, fiabilidade));
                }else if(Objects.equals(tipo,"C2")) {
                    System.out.println("Indique a afinaçao mecanica: ");
                    int mec = scin.nextInt();
                    this.modelUtilizador.adicionaC2(new C2(id, marca, modelo, cilindrada, potencia, pac, mec));
                }else if(Objects.equals(tipo,"SC")) {
                    this.modelUtilizador.adicionaSC(new SC(id, marca, modelo, cilindrada, potencia, pac, fiabilidade));
                }else{
                    this.modelUtilizador.adicionaGT(new GT(id, marca, modelo, cilindrada, potencia, pac));

                }
                System.out.println("Carro criado");
            } else {
                System.out.println("Esse ID ja existe!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    private void trataEscolherCampeonato() {
        try {
            this.modelUtilizador.mostraCampeonatos();
            System.out.println("Escolher campeonato ");
            String camp = scin.nextLine();
            if(this.modelUtilizador.existeCampeonato(camp)) {
                new CampeonatoUI(camp);
            }else {
                System.out.println("Esse campeonato nao existe!!!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void trataRemoverCampeonato() {
        try {
            System.out.println("Insere nome do campeonato que pretende remover: ");
            String nome = scin.nextLine();
            if (this.modelUtilizador.existeCampeonato(nome)) {
                this.modelUtilizador.retiraCampeonato(nome);
            } else {
                System.out.println("Esse campeonato nao existe!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataRemoverCircuito() {
        try {
            System.out.println("CUIDADO!!! Se remover o unico circuito de um campeonato, o campeonato tambem é removido ");
            System.out.println("Insere nome do circuito que pretende remover: ");
            String nome = scin.nextLine();
            if (this.modelUtilizador.existeCircuito(nome)) {
                this.modelUtilizador.retiraCircuito(nome);
            } else {
                System.out.println("Esse circuito nao existe!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataRemoverPiloto() {
        try {
            System.out.println("CUIDADO!!! Se remover piloto de player o player tambem é removido ");
            System.out.println("Insere nome do piloto que pretende remover: ");
            String nome = scin.nextLine();
            if (this.modelUtilizador.existePiloto(nome)) {
                this.modelUtilizador.retiraPiloto(nome);
            } else {
                System.out.println("Esse piloto nao existe!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataRemoverCarro() {
        try {
            System.out.println("CUIDADO!!! Se remover carro de player o player tambem é removido ");
            System.out.println("Insere nome do carro que pretende remover: ");
            String nome = scin.nextLine();
            if (this.modelUtilizador.existeCarro(nome)) {
                this.modelUtilizador.retiraCarro(nome);
            } else {
                System.out.println("Esse carro nao existe!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
