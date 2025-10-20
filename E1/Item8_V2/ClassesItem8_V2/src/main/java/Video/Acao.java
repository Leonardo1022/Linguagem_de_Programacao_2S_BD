package Video;

import java.util.Random;

public class Acao {
    private enum Tipo {
        DANO,
        SUPORTE,
        MOBILIDADE,
        INTERACAO
    };

    private Tipo tipo;
    private Object efeito;
    private double chanceSucesso;

    public Acao(Tipo tipo, Object efeito, double chanceSucesso) {
        this.tipo = tipo;
        this.efeito = efeito;
        this.chanceSucesso = chanceSucesso;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Object getEfeito() {
        return efeito;
    }

    public double getChanceSucesso() {
        return chanceSucesso;
    }
    //Resolver a parte de colocar mais subatributos no enum
    public Object resultado(Acao acao) {
        if(Math.random() < acao.getChanceSucesso()) {
            switch (acao.getTipo()) {
                case DANO:
                    return acao.getEfeito();
                case SUPORTE:
                    return acao.getEfeito();
                case MOBILIDADE:
                    return acao.getEfeito();
                case INTERACAO:
                    return acao.getEfeito();

            }
        }
        return null;
    }

    public void afetar() {
        System.out.println("O alvo sofreu dano");
    }
    public void durar() {
        System.out.println("O indivíduo está imóvel");
    }
    public void modificar() {
        System.out.println("As paredes começaram a queimar");
    }
}



