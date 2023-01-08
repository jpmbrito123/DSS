package code;

public class GT extends Carro implements Hibrido{
        public GT(){super();}

    public GT(String idCarro, String marca, String modelo, int cilindrada, int potencia,double pac,double fiabilidade)
    {
        super(idCarro,marca,modelo,cilindrada,potencia,pac,fiabilidade);

    }
    public GT(String idCarro, String marca, String modelo, int cilindrada, int potencia,double pac) {
        super(idCarro, marca, modelo, cilindrada, potencia, pac, ((100000 / cilindrada) * 2.55));
    }

    public GT(GT p){
            super(p.getIdCarro(),p.getMarca(),p.getModelo(),p.getCilindrada(),p.getPotencia(),p.getPac(),p.calculaFiabilidade());
    }
    @Override
    public String toString()
    {
        return super.toString();
    }
    @Override
    public double DNF(int totalVoltas) {

        double desgaste = ((totalVoltas)*0.5);
        return (super.getFiabilidade()-desgaste);
    }
}
