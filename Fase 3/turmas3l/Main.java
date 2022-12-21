/*
 *  DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas de DSS, representando
 *  uma solução em construção. Como tal, não deverá ser visto como uma solução canónica, ou mesmo acabada.
 *  É disponibilizado para auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente o
 *  código fornecido e a procurar soluções alternativas, à medida que forem adquirindo mais conhecimentos.
 */
package uminho.dss.turmas3l;

import uminho.dss.turmas3l.ui.TextUI;
import uminho.dss.turmas3l.ui.TextUI2;

/**
 * @author DSS
 * @version 20201206
 */
public class Main {

    public static void main(String[] args) {
        try {
            new TextUI2().run();
            new TextUI().run();
        }
        catch (Exception e) {
            System.out.println("Não foi possível arrancar: "+e.getMessage());
        }
    }
}
