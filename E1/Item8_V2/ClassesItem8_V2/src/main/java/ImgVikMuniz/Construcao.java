package ImgVikMuniz;

public class Construcao {
    private int andares;
    private String finalidade;
    private String localizacao;

    public Construcao(int andares, String finalidade, String localizacao) {
        this.andares = andares;
        this.finalidade = finalidade;
        this.localizacao = localizacao;
    }

    public int getAndares() {
        return andares;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void abrigar(){
        System.out.println("A construção abriga pessoas");
    }
}
