package JavaFX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCreator {
    public void criarCSV(Object nome_classe, Object linha, Object cabecalho) {
        String string_nome_classe = String.valueOf(nome_classe);
        String string_linha = String.valueOf(linha);
        String string_cabecalho = String.valueOf(cabecalho);

        boolean arquivo_existente;
        String nome_arquivo = "src/main/resources/JavaFX/" + string_nome_classe + ".csv";
        File arquivo = new File(nome_arquivo);
        arquivo_existente = arquivo.exists();
        try(FileWriter fw = new FileWriter(nome_arquivo, true)) {
            if (!arquivo_existente) {
                //Adicionar o cabecalho
                fw.write(string_cabecalho);
            }
            fw.append(string_linha);
            fw.close();
            System.out.println("CSV criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String criarLinhaCSV(Object a,Object b,Object c) {
        String String_a = String.valueOf(a);
        String String_b = String.valueOf(b);
        String String_c = String.valueOf(c);

        return String_a + ";" + String_b + ";" + String_c + "\n";
    }
}