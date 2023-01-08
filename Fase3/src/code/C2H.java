package code;

import java.util.Random;

public class C2H extends C2 implements Hibrido{
    private int motor_eletrico;

    public C2H()
    {
        super();
        this.motor_eletrico = 0;
    }

    public C2H(String idCarro, String marca, String modelo, int cilindrada, int potencia, double pac, double fiabilidade, int p_mecanico, int eletrico)
    {
        super(idCarro, marca,modelo, cilindrada,potencia,pac ,fiabilidade, p_mecanico);
        this.motor_eletrico = eletrico;
    }
    public C2H(String idCarro, String marca, String modelo, int cilindrada, int potencia,double pac, int p_mecanica, int motor_eletrico) {
        super(idCarro,marca,modelo,cilindrada,potencia,pac,(cilindrada/1200) + p_mecanica/10, p_mecanica);
        this.motor_eletrico = motor_eletrico;
    }


    public C2H(C2H p)
    {
        super(p);
        this.motor_eletrico = p.getPotenciaMotorEletrico();
    }

    public C2H clone()
    {
        return new C2H(this);
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
