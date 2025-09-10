package ImgVikMuniz;

public class Main {
    public static void main(String[] args) {
        Passaro passaro = new Passaro();
        Veiculo veiculo = new Veiculo();
        Construcao construcao = new Construcao();

        passaro.cantar();
        veiculo.andar();
        construcao.abrigar();
    }
}
