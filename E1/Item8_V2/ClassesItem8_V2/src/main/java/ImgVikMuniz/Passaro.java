package ImgVikMuniz;

public class Passaro {
    private String especie;
    private int id;
    private boolean voando;

    public Passaro(String especie, int id, boolean voando) {
        this.especie = especie;
        this.id = id;
        this.voando = voando;
    }

    public String getEspecie() {
        return especie;
    }

    public int getId() {
        return id;
    }

    public boolean isVoando() {
        return voando;
    }

    public void cantar() {
        System.out.println("O pássaro canta");
    }
}
