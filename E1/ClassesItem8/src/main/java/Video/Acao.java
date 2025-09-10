package Video;

public class Acao {
    private String tipo;
    private String efeito;
    private int chanceSucesso;

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



