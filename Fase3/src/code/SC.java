package code;

public class SC extends Carro {

    public SC() {
        super();
    }

    public SC(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac, double fiabilidade) {
        super(idCarro, marca, modelo, cilindrada, potencia, pac, fiabilidade);
    }

    public SC(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac) {
        super(idCarro, marca, modelo, cilindrada, potencia, pac, cilindrada*0.25);

    }

    public SC(SC p) {
        super(p.getIdCarro(), p.getMarca(), p.getModelo(), p.getCilindrada(), p.getPotencia(), p.getPac(), p.getFiabilidade());
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public SC clone() {
        return new SC(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;

        return super.equals(o);
    }
    @Override
    public double DNF(int totalVoltas) {

        return super.getFiabilidade();

    }
}