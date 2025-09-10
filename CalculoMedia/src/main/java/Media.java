import java.util.Scanner;

public class Media {
    //Média = ((P1*0.5+E1*0.2+E2*0.3+X+SUB*0.15)*0.5)+(max(P1*0.5+E1*0.2+E2*0.3+X+(SUB*0.15)-5.9, 0)/(P1*0.5+E1*0.2+E2*0.3+X+(SUB*0.15)-5.9))*API*0.5
    private int sub;
    private int x;
    private int api;
    private int p1;
    private int e1;
    private int e2;
    private double med;
    private double max;

    public void fazerMedia() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a nota da prova p1: ");
        p1 = scanner.nextInt();
        if (p1 < 6) {
            System.out.println("Digite um a nota da prova substitutiva: ");
            sub = scanner.nextInt();
        }
        System.out.println("Digite a nota da atividade e1: ");
        e1 = scanner.nextInt();
        System.out.println("Digite a nota da atividade e2: ");
        e2 = scanner.nextInt();
        System.out.println("Digite a nota da api: ");
        api = scanner.nextInt();
        do {
            System.out.println("Digite a nota das atividades extras (0 ou 1): ");
            x = scanner.nextInt();
            if (x > 1) {
                System.out.println("Digite uma nota entre 0 e 1!");
            }
        }
        while (x > 1);

        max = p1*0.5+e1*0.2+e2*0.3+x+(sub*0.15)-5.9;
        if (max < 0) {
            max = 0;
        }

        med = ((p1*0.5+e1*0.2+e2*0.3+x+sub*0.15)*0.5)+(max)/(p1*0.5+e1*0.2+e2*0.3+x+(sub*0.15)-5.9)*api*0.5;

        System.out.println("A media tirada do aluno foi: " + med);
        if (med >= 6 && api > 0) {
            System.out.println("O aluno foi aprovado!");
        } else {
            System.out.println("O aluno foi reprovado!");
        }
    }
    public static void main(String[] args) {
        Media media = new Media();
        media.fazerMedia();
    }
}
