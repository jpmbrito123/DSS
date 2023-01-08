package code;

public class C1 extends Carro implements Hibrido{
    public C1()
    {
        super();
    }


    public C1(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac, double fiabilidade) {
        super(idCarro, marca, modelo, cilindrada, potencia, pac, fiabilidade);
    }


    public C1(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac) {
        super(idCarro, marca, modelo, cilindrada, potencia, pac, (1.0f - (0.000076f * potencia)));
    }

    public C1(C1 p) {
        super(p.getIdCarro(),p.getMarca(),p.getModelo(),p.getCilindrada(),p.getPotencia(),p.getPac(),p.getFiabilidade());
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

    @Override
    public C1 clone() {
        return new C1(this);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;

        return super.equals(o);
    }




    @Override
    public double DNF(int totalVoltas) {
        return  super.getFiabilidade();
    }
}
