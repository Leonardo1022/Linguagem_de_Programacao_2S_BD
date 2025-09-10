package Musica;

public class Main {
    public static void main(String[] args) {
        Som som = new Som();
        Pessoa pessoa = new Pessoa();
        Doenca doenca = new Doenca();

        som.entreterPublico();
        pessoa.andar();
        doenca.prejudicar();
    }
}
