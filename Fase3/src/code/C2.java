package code;

public class C2 extends Carro implements Hibrido{
    private double preparacao_mecanica;
    public C2()
    {
        super();
    }

    public C2(String idCarro, String marca, String modelo, int cilindrada, int potencia,double pac, double fibilidade,double p_mecanica) {
        super(idCarro,marca,modelo,cilindrada,potencia,pac,fibilidade);
        this.preparacao_mecanica = p_mecanica;
    }

    public C2(String idCarro, String marca, String modelo, int cilindrada, int potencia,double pac, int p_mecanica) {
        super(idCarro,marca,modelo,cilindrada,potencia,pac,(cilindrada/1200) + p_mecanica/10);
        this.preparacao_mecanica = p_mecanica;
    }

    public void setPreparacao_mecanica(double preparacao_mecanica) {
        this.preparacao_mecanica = preparacao_mecanica;
    }
    public double getPreparacaoMecaninca()
    {
        return this.preparacao_mecanica;
    }

    public C2(C2 p) {
        super(p.getIdCarro(),p.getMarca(),p.getModelo(),p.getCilindrada(),p.getPotencia(),p.getPac(),p.getFiabilidade());
    }

    public C2 clone() {
        return new C2(this);
    }
    @Override
    public String toString() {
        return super.toString() + " preparacaomecanica:" + this.preparacao_mecanica;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        return (super.equals(o));
    }
    public double DNF(int totalVoltas) {

        return super.getFiabilidade();

    }
}
