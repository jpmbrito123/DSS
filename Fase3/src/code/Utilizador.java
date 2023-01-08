package code;

public class Utilizador {
    //private int Id;
    private String nomeUtilizador;
    //private String email;
    private String password;
    private int Tipo;
    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public Utilizador(String nome, String password, int tipo) {
        //this.Id = ID;
        this.nomeUtilizador = nome;
        //this.email = email;
        this.password = password;
        this.Tipo = tipo;
    }
    public Utilizador(){};
    //public int getUtilizadorId(){return this.Id;}
    public String getUtilizadorNome(){
        return this.nomeUtilizador;
    }
    public String getUtilizadorPassword(){
        return this.password;
    }
    public int getUtilizadorTipo(){
        return this.Tipo;
    }



}
