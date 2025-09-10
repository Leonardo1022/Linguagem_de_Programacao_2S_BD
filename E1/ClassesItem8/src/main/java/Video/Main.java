package Video;

public class Main {
    public static void main(String[] args) {
        Humano humano = new Humano();
        Item item = new Item();
        Acao acao = new Acao();

        humano.atacar();
        item.quebrar();
        acao.afetar();
    }
}
