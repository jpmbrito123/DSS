package code;

import data.*;

import java.util.*;

public class UtilizadorFacade implements IUtilizadorFacade{
    private Map<String,Utilizador> utilizadores;
    private Map<String,Campeonato> campeonatos = new HashMap<String,Campeonato>();

    private Map<String,Circuito> circuitos;
    private Map<String,Carro> carros;
    private Map<String,Piloto> pilotos;
    private Map<String,PlayerSet> players;


    public UtilizadorFacade(){
        this.utilizadores = UtilizadorDAO.getInstance();
        this.circuitos = CircuitoDAO.getInstance();
        this.carros = CarrosDAO.getInstance();
        this.pilotos = PilotosDAO.getInstance();
        this.players = PlayerSetDAO.getInstance();
    }

    public boolean existeUtilizador(String n) {
        return utilizadores.containsKey(n);
    }
    public void adicionaUtilizador(Utilizador u){
        this.utilizadores.put(u.getUtilizadorNome(),u);
    }

    public boolean passecorreta(String nome,String password){
        return Objects.equals(this.utilizadores.get(nome).getUtilizadorPassword(),password);
    }

    public boolean existeCampeonato(String n){
        return campeonatos.containsKey(n);
    }

    public boolean existeCircuito(String n){
        return circuitos.containsKey(n);
    }

    public void adicionaCircuitoCampeonato(String nome,String circuito){
        this.campeonatos.get(nome).criaCorrida(circuito);
    }

    public void adicionaCircuito(Circuito c){
        this.circuitos.put(c.getNomeCircuito(),c);
    }

    public boolean existePiloto(String n){
        return pilotos.containsKey(n);
    }

    public void adicionaPiloto(Piloto p){
        this.pilotos.put(p.getNomePiloto(),p);
    }

    public boolean existeCarro(String n){
        return carros.containsKey(n);
    }

    public void adicionaC1(C1 c){
        this.carros.put(c.getIdCarro(),c);
    }

    public void adicionaC2(C2 c){
        this.carros.put(c.getIdCarro(),c);
    }

    public void adicionaGT(GT c){
        this.carros.put(c.getIdCarro(),c);
    }

    public void adicionaSC(SC c){
        this.carros.put(c.getIdCarro(),c);
    }

    public void mostraCampeonatos(){
        System.out.println("Campeonatos disponiveis: ");
        for (Map.Entry<String,Campeonato> pair: campeonatos.entrySet()) {
            System.out.println(pair.getKey()+":");
            List<Corrida> cors = pair.getValue().getCorridas();
            for (Corrida corrida:cors){
                System.out.println(corrida.getCircuito());
            }

        }
    }

    public void adicionacampeonato(String name){
        campeonatos.put(name,new Campeonato(name));
    }

    public boolean existePlayer(String nome){
        return players.containsKey(nome);
    }

    public void mostraCarros(){
        System.out.println("Carros disponiveis: ");
        for (String cars: carros.keySet()) {
            System.out.println(cars+" ");

        }
    }

    public void defineCarroPlayer(String id,String nome){
        this.players.get(nome).setCarro(nome);
    }

    public void definePneuPlayer(String pneu,String nome){
        this.players.get(nome).setPneu(pneu);
    }

    public void defineMotorPlayer(int motor,String nome){
        this.players.get(nome).setAgressividadeMotriz(motor);
    }

    public void definePilotoPlayer(String nome, String player){
        this.players.get(player).setPiloto(nome);
    }

    public void mostraPilotos(){
        for (String pil: pilotos.keySet()){
            Piloto p = pilotos.get(pil);
            System.out.println(p.getNomePiloto() + ": SvA-" + p.getSba() + " CTS-" + p.getCts() + " Nacionalidade-" + p.getNacionalidade());
        }
    }

    public void ready(String nome){
        campeonatos.get(nome).putallready();
    }
    
    public void retiraCampeonato(String s){
        campeonatos.remove(s);
    }

    public void retiraCircuito(String s){
        circuitos.remove(s);
        for (Map.Entry<String,Campeonato> pair: campeonatos.entrySet()) {
            for(int i = 0;i<pair.getValue().getCorridas().size();i++){
                if(Objects.equals(pair.getValue().getCorridas().get(i).getCircuito(),s)){
                    pair.getValue().getCorridas().remove(i);
                }
            }
            if(pair.getValue().getCorridas().size()==0){
                campeonatos.remove(pair.getKey());
            }
        }
    }

    public void retiraPiloto(String s){
        pilotos.remove(s);
        for (Map.Entry<String,PlayerSet> pair: players.entrySet()) {
                if(Objects.equals(pair.getValue().getPiloto(),s)){
                    pilotos.remove(pair.getKey());
                }

        }
    }

    public void retiraCarro(String s){
        carros.remove(s);
        for (Map.Entry<String,PlayerSet> pair: players.entrySet()) {
            if(Objects.equals(pair.getValue().getCarro(),s)){
                pilotos.remove(pair.getKey());
            }

        }
    }

    public void mostracircuitos(){
        for (String c:circuitos.keySet()){
            System.out.println(c+" ");
        }
    }


}
