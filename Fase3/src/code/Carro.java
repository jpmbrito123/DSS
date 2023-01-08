package code;

public abstract class Carro {
    private String idCarro;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potencia;
    private double pac;
    private double fiabilidade;
    //private long tempo;

    public Carro()
    {
        this.idCarro= "";
        this.marca = "";
        this.modelo = "";
        this.cilindrada = 0;
        this.potencia = 0;
        this.pac=0.0;
        this.fiabilidade = 0;
        //this.tempo = 0;
    }

    public Carro(String idCarro, String marca, String modelo,int cilindrada, int potencia,double pac, double fiabilidade)
    {
        this.idCarro = idCarro;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.pac=pac;
        this.fiabilidade = fiabilidade;
        //this.tempo = 0;
    }

    public String getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(String idCarro) {
        this.idCarro = idCarro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int clindrada) {
        this.cilindrada = clindrada;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public double getPac() {
        return pac;
    }

    public void setPac(double pac) {
        this.pac = pac;
    }

    public double getFiabilidade() {
        return fiabilidade;
    }

    public void setFiabilidade(double fiabilidade) {
        this.fiabilidade = fiabilidade;
    }
    /*
    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }
     */

    public int calculaFiabilidade(){return 0;}
    public long tempoProximoVolta(Circuito circuito, int clima, int volta){return 0;}

    public abstract double DNF(int totalVoltas);

    public String toString(){
        String s = "id:" + this.idCarro + "\nmarca:" + this.marca + " modelo:"+ this.modelo +"\ncilidrada:"+ this.cilindrada + " fiabilidade:"+ this.fiabilidade +" pac:"+ this. pac;
        return s;
    }

}
