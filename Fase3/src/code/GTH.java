package code;

import java.util.Random;

public class GTH extends GT implements Hibrido{
    private int motor_eletrico;

    public GTH()
    {
        super();
        this.motor_eletrico = 0;
    }

    public GTH(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac, double fiabilidade, int eletrico)
    {
        super(idCarro, marca,modelo, cilindrada,potencia,pac ,fiabilidade);
        this.motor_eletrico = eletrico;
    }
    public GTH(String idCarro, String marca, String modelo, int cilindrada, int potencia,double pac) {
        super(idCarro, marca, modelo, cilindrada, potencia, pac, ((100000 / cilindrada) * 2.55));
    }

    public GTH(GTH p)
    {
        super(p);
        this.motor_eletrico = p.getPotenciaMotorEletrico();
    }

    public GTH clone()
    {
        return new GTH(this);
    }

    public int getPotenciaMotorEletrico()
    {
        return this.motor_eletrico;
    }

    public void setPotenciaMotorEletrico(int potencia)
    {
        this.motor_eletrico = potencia;
    }

    public double DNF(int volta,int totalvoltas,int clima)
    {
        int motorh = this.getPotenciaMotorEletrico()/20;
        int desgaste = (int)((volta+1)*0.5);
        return (super.getFiabilidade() - desgaste - motorh);

    }

    public boolean equals(Object o)
    {
        if(this==o)
            return true;

        if(o==null || this.getClass()!=o.getClass())
            return false;

        C1H c = (C1H) o;
        return ( super.equals(c) && this.motor_eletrico == c.getPotenciaMotorEletrico());
    }
}
