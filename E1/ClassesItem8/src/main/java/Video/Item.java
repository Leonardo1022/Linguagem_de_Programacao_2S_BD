package Video;

public class Item {
    private String nome;
    private String efeito;
    private int valor;

    public void acionarEfeito() {
        System.out.println("não acontece nada");
    }
    public void utilizar() {
        System.out.println("O item é usado");
    }
    public void quebrar() {
        System.out.println("O item quebrou");
    }
}

