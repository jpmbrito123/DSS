package code;

import data.CarrosDAO;
import data.PilotosDAO;

import java.util.Random;

public class PlayerSet {
    private String idPlayerSet;
    private double agressividadeMotriz;
    private String piloto;
    private String carro;
    private String pneu;
    private int countAlteracoes;

    public PlayerSet(String id,double agress,String piloto,String carro,String pneu,int alteraçao){
        this.idPlayerSet = id;
        this.agressividadeMotriz = agress;
        this.piloto = piloto;
        this.carro = carro;
        this.pneu = pneu;
        this.countAlteracoes = alteraçao;
    }

    public PlayerSet(String idPlayerSet, String piloto, String carro) {
        this.idPlayerSet = idPlayerSet;
        this.piloto = piloto;
        this.carro = carro;
        this.countAlteracoes = 0;
    }

    public boolean despistar(int totalVoltas){
        Carro c = CarrosDAO.getInstance().get(this.carro);
        Piloto p = PilotosDAO.getInstance().get(this.piloto);
        Random random = new Random();
        int x = random.nextInt(100);

        if (c.getClass()==SC.class){
            return x>((c.DNF(totalVoltas))+0.75*p.getSba())*this.agressividadeMotriz/3;
        }
        else
            return x > c.DNF(totalVoltas)*this.agressividadeMotriz/3;

    }

    public double desempenho(int totalvoltas,int condicaoclimaterias){
        Piloto p = PilotosDAO.getInstance().get(this.piloto);
        double v;
        if (this.pneu.equals("seco")){
            v = 1/(totalvoltas/2+1);
        } else if (this.pneu .equals("chuva")) {
            v = -1;
        } else {
            v = 0.7;
        }

        if (condicaoclimaterias==0) {// 0 == seco
            if (v<0) v=0.4;
            v = v * p.getCts();
        }else{
            if (v>=0) v=-v+0.3;
            v = -v * (1 - p.getCts());
        }
        return v;
    }

    public String getIdPlayerSet() {
        return idPlayerSet;
    }

    public void setIdPlayerSet(String idPlayerSet) {
        this.idPlayerSet = idPlayerSet;
    }

    public double getAgressividadeMotriz() {
        return agressividadeMotriz;
    }

    public void setAgressividadeMotriz(double agressividadeMotriz) {
        this.agressividadeMotriz = agressividadeMotriz;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public String getPneu() {
        return pneu;
    }

    public void setPneu(String pneu) {
        this.pneu = pneu;
    }

    public int getCountAlteracoes() {
        return countAlteracoes;
    }

    public void setCountAlteracoes(int countAlteracoes) {
        this.countAlteracoes = countAlteracoes;
    }

}
