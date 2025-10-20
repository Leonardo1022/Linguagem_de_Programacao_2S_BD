package Musica;

public class Main {
    public static void main(String[] args) {
        Som som = new Som();
        Pessoa pessoa = new Pessoa();
        Instrumento instrumento = new Instrumento();

        som.entreterPublico();
        pessoa.andar();
        instrumento.tocar();
    }
}
