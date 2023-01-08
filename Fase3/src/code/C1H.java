package code;

import java.util.Random;

public class C1H extends C1 implements Hibrido{
    private int motor_eletrico;

    public C1H()
    {
        super();
        this.motor_eletrico = 0;
    }

    public C1H(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac, double fiabilidade, int eletrico)
    {
        super(idCarro, marca,modelo, cilindrada,potencia,pac ,fiabilidade);
        this.motor_eletrico = eletrico;
    }
    public C1H(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac, int motor_eletrico) {
        super(idCarro, marca, modelo, cilindrada, potencia, pac, (1.0f - (0.000076f * potencia)));
        this.motor_eletrico=motor_eletrico;
    }

    public C1H(C1H p)
    {
        super(p);
        this.motor_eletrico = p.getPotenciaMotorEletrico();
    }

    public C1H clone()
    {
        return new C1H(this);
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
        return (super.getFiabilidade()-motorh);
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
