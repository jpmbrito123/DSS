package code;

public class Piloto {
    //private int Id;
    private String nomePiloto;
    private double cts;
    private double sba;
    private String nacionalidade;
    public Piloto (String nome,double cts,double sba,String nacionalidade){
        //this.Id = Id;
        this.nomePiloto = nome;
        this.cts = cts;
        this.sba = sba;
        this.nacionalidade = nacionalidade;
    }
    public String getNomePiloto() {
        return nomePiloto;
    }
    //public void setId(int Id){this.Id = Id;}
    //public int getId(){return this.Id;}

    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    public double getCts() {
        return cts;
    }

    public void setCts(double cts) {
        this.cts = cts;
    }

    public double getSba() {
        return sba;
    }

    public void setSba(double sba) {
        this.sba = sba;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public double calculaQualidade(){
        return(0.5*this.cts + 0.5*this.sba);
    }
}
