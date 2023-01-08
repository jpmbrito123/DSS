package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Circuito {
    //private String Id;
    private String nomeCircuito;
    private int chicane;
    private int retas;
    private int curvas;
    private int distancia;

    private int nvoltas;
    private double tempoMedio;
    private double recorde;
    private double tempoBox;
    private double tempoDesvio;
    private List<String> listaDificuldadeRetas;
    private List<String> listaDificuldadeCurvas;

    public Circuito(String nomeCircuito, int chicane, int retas, int curvas, int distancia, int nvoltas, List<String> listaDificuldadeRetas, List<String> listaDificuldadeCurvas) {
        this.nomeCircuito = nomeCircuito;
        this.chicane = chicane;
        this.retas = retas;
        this.curvas = curvas;
        this.distancia = distancia;
        this.nvoltas = nvoltas;
        this.listaDificuldadeRetas = listaDificuldadeRetas;
        this.listaDificuldadeCurvas = listaDificuldadeCurvas;
    }
    public Circuito(String nome,int chincane,int retas,int curvas,int distancia,int nvoltas,double tempoMedio,double record,double tempoBox,double tempoDesvio,String dificulRetas,String dificulCurvas){
        //this.Id = Id;
        this.nomeCircuito = nome;
        this.chicane = chincane;
        this.retas = retas;
        this.curvas = curvas;
        this.distancia = distancia;
        this.nvoltas = nvoltas;
        this.tempoMedio = tempoMedio;
        this.recorde = record;
        this.tempoBox = tempoBox;
        this.tempoDesvio = tempoDesvio;
        String[] aux = dificulRetas.split("/");
        this.listaDificuldadeRetas.addAll(Arrays.asList(aux));
        String[] aux2 = dificulCurvas.split("/");
        this.listaDificuldadeCurvas.addAll(Arrays.asList(aux2));
    }
    public Circuito(){};

    //public String getId(){return this.Id;}

    public String getNomeCircuito() {
        return this.nomeCircuito;
    }

    public void setNomeCircuito(String nomeCircuito) {
        this.nomeCircuito = nomeCircuito;
    }

    public int getChicane() {
        return this.chicane;
    }

    public void setChicane(int chicane) {
        this.chicane = chicane;
    }

    public int getRetas() {
        return this.retas;
    }

    public void setRetas() {
        this.retas = this.chicane+this.curvas;
    }

    public int getCurvas() {
        return this.curvas;
    }

    public void setCurvas(int curvas) {
        this.curvas = curvas;
    }

    public int getDistancia() {
        return this.distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getNvoltas() {
        return this.nvoltas;
    }

    public void setNvoltas(int nvoltas) {
        this.nvoltas = nvoltas;
    }

    public double getTempoMedio() {
        return this.tempoMedio;
    }

    public void setTempoMedio(long tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public double getRecorde() {
        return this.recorde;
    }

    public void setRecorde(long recorde) {
        this.recorde = recorde;
    }

    public double getTempoBox() {
        return this.tempoBox;
    }

    public void setTempoBox(long tempoBox) {
        this.tempoBox = tempoBox;
    }

    public double getTempoDesvio() {
        return this.tempoDesvio;
    }

    public void setTempoDesvio(long tempoDesvio) {
        this.tempoDesvio = tempoDesvio;
    }

    public List<String> getListaDificuldadeRetas() {
        return this.listaDificuldadeRetas;
    }
    public String getListaDificuldadeRetasString(){
        StringBuilder res = new StringBuilder();
        for(String dif : this.listaDificuldadeRetas){
            res.append(dif).append("/");
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }

    public void setListaDificuldadeRetas(List<String> listaDificuldadeRetas) {
        this.listaDificuldadeRetas = listaDificuldadeRetas;
    }

    public List<String> getListaDificuldadeCurvas() {
        return listaDificuldadeCurvas;
    }
    public String getListaDificuldadeCurvasString(){
        StringBuilder res = new StringBuilder();
        for(String dif : this.listaDificuldadeCurvas){
            res.append(dif).append("/");
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }

    public void setListaDificuldadeCurvas(List<String> listaDificuldadeCurvas) {
        this.listaDificuldadeCurvas = listaDificuldadeCurvas;
    }

}
