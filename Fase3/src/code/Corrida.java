package code;

import data.CircuitoDAO;
import data.PilotosDAO;
import data.PlayerSetDAO;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Corrida {
    private String idCorrida;
    private int clima;
    private String circuito;
    private List<String> players = new ArrayList<>();
    private Map<String, Integer>dNF = new HashMap<>();
    private List<String> resultados = new ArrayList<>();
    private List<String> readys = new ArrayList<>();
    private List<String> primeiroVolta = new ArrayList<>();

    public Corrida(String idCorrida, String circuito) {
        this.idCorrida = idCorrida;
        this.circuito = circuito;
    }

    public List<String> simuladorCorrida(){
        Circuito c = CircuitoDAO.getInstance().get(this.circuito);
        int nvoltas = c.getNvoltas();
        int nretas = c.getRetas();

        Map<String,PlayerSet> sets = new HashMap<>();
        for (String player : this.players) {
            sets.put(player, PlayerSetDAO.getInstance().get(player));
        }

        List<String> posicoes = new ArrayList<>();
        posicoes.addAll(this.players);

        for (int i=0;i<nvoltas;i++){
            for (String p:posicoes){
                if (this.dNF.containsKey(p) && sets.get(p).despistar(i)){
                    this.dNF.put(p,1);
                    posicoes.remove(p);
                }
            }
            for (int x=0;x<nretas;x++){
                int dificuldadereta = parseInt(c.getListaDificuldadeRetas().get(x));
                if (dificuldadereta != 0){

                    String player_ant =posicoes.get(0);
                    PlayerSet playerset_ant = sets.get(player_ant);
                    int n = 0;
                    for (i=n+1;i<posicoes.size();i++){
                        String player=posicoes.get(i);
                        PlayerSet playerset = sets.get(player);
                        if(!this.dNF.containsKey(posicoes.get(i))) {
                            if (ultrapassa(playerset_ant,playerset,dificuldadereta,i)) {
                                posicoes.set(n, player);
                                posicoes.set(i, player_ant);
                            }else {
                                n = i;
                                player_ant = player;
                                playerset_ant = playerset;
                            }
                        }
                    }
                }

                if (c.getListaDificuldadeCurvas().size()>x) {
                    int dificuldadecurva = parseInt(c.getListaDificuldadeCurvas().get(x));
                    if (dificuldadecurva != 0) {
                        int n= 0;
                        String player_ant =posicoes.get(n);
                        PlayerSet playerset_ant = sets.get(player_ant);

                        for (i=n+1;i<posicoes.size();i++){
                            String player=posicoes.get(i);
                            PlayerSet playerset = sets.get(player);
                            if(!this.dNF.containsKey(posicoes.get(i))) {
                                if (ultrapassa(playerset_ant,playerset,dificuldadecurva,i)) {
                                    posicoes.set(n, player);
                                    posicoes.set(i, player_ant);
                                }else {
                                    n = i;
                                    player_ant = player;
                                    playerset_ant = playerset;
                                }
                            }
                        }
                        }
                    }
                }
            }
        return posicoes;
    }

    public boolean ultrapassa(PlayerSet player_afrente,PlayerSet player_atual,int dificuldade,int volta){
        if (dificuldade == 0) return false;
        if (player_afrente.desempenho(volta,this.clima)>player_atual.desempenho(volta,this.clima)) return false;
        double p=1/dificuldade;
        Piloto atual = PilotosDAO.getInstance().get(player_atual.getPiloto());
        p = p * player_atual.getAgressividadeMotriz() * atual.getSba();
        Random rand=new Random();
        int x= rand.nextInt(100);

        return x<=p;
    }

    public String getIdCorrida() {
        return idCorrida;
    }

    public void setIdCorrida(String idCorrida) {
        this.idCorrida = idCorrida;
    }

    public String gerarCondicaoClimaterica(){return null;}

    public void primeiraoVolta(List<Carro> carros, int volta){}

    public String printPrimeiroVolta(){return null;}

    public String printResultados(){return null;}

    public String printDNF(){return null;}

    public String printCarrosParticipantes(){return null;}

    public boolean allready(){return this.readys.size()==this.players.size();}

    public void putready(String id){
        if (!this.readys.contains(id) && this.players.contains(id)){
            this.readys.add(id);
        }
    }

    public void atualizap(List<String> newplayers){
        for(String p : newplayers){
            if (!this.players.contains(p)){
                this.players.add(p);
            }
        }
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }
}
