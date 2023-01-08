package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Campeonato {
    private String nomeCampeonato;
    private HashMap<String, Integer> classificacoes = new HashMap<>();
    private int corridasRealizadas;
    private List<String> playerssets = new ArrayList<>();
    private List<Corrida> corridas = new ArrayList<>();

    public Campeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
        this.corridasRealizadas= 0;
    }

    public Campeonato(String nomeCampeonato, List<String> circuitos) {
        this.nomeCampeonato = nomeCampeonato;
        this.corridasRealizadas = 0;
        for (String c:circuitos){
            Corrida corrida = new Corrida("",c);
            this.corridas.add(corrida);
        }
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public List<String> getPlayressets() {
        return playerssets;
    }

    public void setPlayerssets(List<String> playerssets) {
        this.playerssets = playerssets;
    }

    public void addPlayersets(String playerset){
        this.playerssets.add(playerset);
    }

    public List<Corrida> getCorridas() {
        return corridas;
    }

    public void setCcorridas(List<Corrida> corridas) {
        this.corridas = corridas;
    }

    public void criaCorrida(String circuito){
        Corrida corrida = new Corrida("",circuito);
        this.corridas.add(corrida);
    }
    public void simulaProximaCorrida(){
        if(this.corridas.size()>this.corridasRealizadas) {
            Corrida proxcorrida = this.corridas.get(this.corridasRealizadas);
            if(proxcorrida.allready()) {
                List<String> classificaçoes = proxcorrida.simuladorCorrida();
                atualizaClassificacao(classificaçoes);
                printClassificacao();
                this.corridasRealizadas++;
            }
        }
    }
    public Corrida getCorrida(int x){return null;}
    public String agendaCorrida(){return null;}
    public void printClassificacao(){
        ArrayList<Integer> points = new ArrayList<>();
        ArrayList<String> playrs = new ArrayList<>();
        for (String player:this.classificacoes.keySet()){
            playrs.add(player);
            points.add(this.classificacoes.get(player));
        }
        ordenaClassificacoes(points,playrs);
        for (String player:playrs){
            String pri = player + " " +this.classificacoes.get(player);
            System.out.println(pri);
        }
    }
    public void atualizaClassificacao(List<String> resultados){
        int n = 1;
        for(String p:resultados){
            int points =this.classificacoes.get(p);
            if(n==1){
                points+=12;
            } else if (n==2) {
                points+=10;
            } else if (n==3) {
                points+=8;
            } else if (n==4) {
                points+=7;
            }
            this.classificacoes.put(p,points);
            n++;
        }
    }
    public void ordenaClassificacoes(ArrayList<Integer> points,ArrayList<String> playrs){
        for(int i = 0;i<points.size()-1 && i <playrs.size()-1;i++){
            int points_atual=points.get(i);
            int points_prox=points.get(i+1);
            if(points_prox>points_atual){
                String player_atual=playrs.get(i);
                String player_prox=playrs.get(i+1);
                points.set(i, points_prox);
                playrs.set(i, player_prox);
                points.set(i+1, points_atual);
                playrs.set(i+1, player_atual);
            }
        }
    }
    public String resultadosCorrida(int x){return null;}
    public boolean corridaRealizada(int x){
        return this.corridasRealizadas >= x;
        }

    public String listaParticipante(int x){return null;}
    public String listaCircuitos(int x){return null;}
    public boolean alteracaoPossivel(){return true;}

    public void putready(String id) {
        if (this.corridas.size() > this.corridasRealizadas) {
            Corrida proxcorrida = this.corridas.get(this.corridasRealizadas);
            proxcorrida.atualizap(this.playerssets);
            proxcorrida.putready(id);
            if(proxcorrida.allready()){
                simulaProximaCorrida();
            }
        }
    }

    public void putallready(){
        for (String player:this.playerssets){
            putready(player);
        }
    }

}
