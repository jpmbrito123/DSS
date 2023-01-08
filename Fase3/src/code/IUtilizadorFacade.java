package code;

public interface IUtilizadorFacade {


    public boolean existeUtilizador(String n);

    public void adicionaUtilizador(Utilizador u);

    public boolean passecorreta(String n,String p);

    public boolean existeCampeonato(String n);

    public boolean existeCircuito(String n);

    public void adicionaCircuitoCampeonato(String nome,String circuito);

    public void adicionaCircuito(Circuito c);

    public boolean existePiloto(String n);

    public void adicionaPiloto(Piloto p);

    public boolean existeCarro(String n);

    public void adicionaC1(C1 c);

    public void adicionaC2(C2 c);

    public void adicionaGT(GT c);

    public void adicionaSC(SC c);

    public void mostraCampeonatos();

    public boolean existePlayer(String nome);

    public void defineCarroPlayer(String id,String nome);

    public void definePneuPlayer(String pneu,String nome);

    public void defineMotorPlayer(int motor,String nome);

    public void mostraPilotos();

    public void definePilotoPlayer(String nome, String player);

    public void ready(String nome);

    public void mostraCarros();
    
    public void retiraCampeonato(String s);
    
    public void retiraCircuito(String s);

    public void retiraPiloto(String s);

    public void retiraCarro(String s);

    public void mostracircuitos();

    public void adicionacampeonato(String name);
}
