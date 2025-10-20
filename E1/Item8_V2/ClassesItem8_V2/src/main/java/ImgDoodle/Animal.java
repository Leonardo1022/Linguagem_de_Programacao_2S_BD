package ImgDoodle;

public class Animal {
    private String nome;
    private int idade;
    private double alturaMetros;

    public Animal(double alturaMetros, int idade, String nome) {
        this.alturaMetros = alturaMetros;
        this.idade = idade;
        this.nome = nome;
    }

    //Get
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public double getAlturaMetros() {
        return alturaMetros;
    }
    //Set
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setAlturaMetros(double alturaMetrosMetros) {
        this.alturaMetros = alturaMetrosMetros;
    }
    //Métodos
    public void comer(){
        System.out.println("Comendo");
    }
    public void andar(){
        System.out.println("Andando");
    }
    public void comunicar(){
        System.out.println("Comunicando");
    }


}
