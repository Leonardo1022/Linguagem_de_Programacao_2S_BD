package ImgDoodle;

public class Profissional {
    private String cargo;
    private boolean trabalhando;
    private int tempoServicoAnos;

    public Profissional(String cargo, boolean trabalhando, int tempoServicoAnos) {
        this.cargo = cargo;
        this.trabalhando = trabalhando;
        this.tempoServicoAnos = tempoServicoAnos;
    }

    public String getCargo() {
        return cargo;
    }

    public boolean isTrabalhando() {
        return trabalhando;
    }

    public int getTempoServicoAnos() {
        return tempoServicoAnos;
    }

    public void trabalhar() {
        System.out.println("Trabalha");
    }
    public void resolverTarefa() {
        System.out.println("Tarefa resolvida");
    }
    public void receberSalario() {
        System.out.println("O empregado recebeu o seu salário");
    }
}
