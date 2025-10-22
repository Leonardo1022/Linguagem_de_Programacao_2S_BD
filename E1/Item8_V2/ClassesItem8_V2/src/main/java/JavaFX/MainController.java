package JavaFX;

import ImgDoodle.*;
import ImgVikMuniz.*;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController {
    ///Img Doodle
    //Animal
    @FXML public TextField tfNome_Animal;
    @FXML public TextField tfAlturaMetros_Animal;
    @FXML public TextField tfIdade_Animal;
    //Bioma
    @FXML public TextField tfTipo_Bioma;
    @FXML public TextField tfTemperatura_Bioma;
    //Bioma - Painel
    @FXML public AnchorPane pnPainelTabela;
    @FXML public TextField tfAnimais_Bioma;
    @FXML public ListView<String> lvLista;
    @FXML public HBox hBoxMain;
    //Profissional
    @FXML public TextField tfCargo_Profissional;
    @FXML public CheckBox cbTrabalhando_Profissional;
    @FXML public TextField tfTempoServicoAnos_Profissional;

    ///Img Vik Muniz
    //Construcao
    @FXML public Slider SlrAndares_Construcao;
    @FXML public Label lblAndares_Construcao;
    @FXML public TextField tfFinalidade_Construcao;
    @FXML public TextField tfLocalizacao_Construcao;
    //Passaro
    @FXML public TextField tfEspecie_Passaro;
    @FXML public PasswordField pfId_Passaro;
    @FXML public ToggleButton tbVoando_Passaro;
    //Veiculo
    @FXML public TextField tfMarca_Veiculo;
    @FXML public ColorPicker cpCor_Veiculo;
    @FXML public TextField tfModelo_Veiculo;

    ///Musica
    //Instrumento
    @FXML public TextField tfTipo_Instrumento;

    //Instrumento - painel
    @FXML public AnchorPane apPainel_Instrumento;
    @FXML public Button btn_Arquivo;
    @FXML public ImageView ivFoto_Instrumento;
    @FXML public Label lblMensagem;
    @FXML public HBox hBoxMain11;
    //Pessoa
    @FXML public DatePicker dpNascimento_Pessoa;


    //Outros
    CSVCreator csv = new CSVCreator();
    ObservableList<String> dados;
    ArrayList<String> animais_List = new ArrayList<>();

    ///ImgDoodle
    @FXML
    private void criarAnimal() {
        String nome = tfNome_Animal.getText();
        String alturaMetros = tfAlturaMetros_Animal.getText();
        String idade = tfIdade_Animal.getText();

        try {
            Animal animal = new Animal(Double.parseDouble(alturaMetros), Integer.parseInt(idade), nome);
            System.out.println("Objeto criado com sucesso!");
            System.out.println(animal.getAlturaMetros());
            System.out.println(animal.getIdade());
            System.out.println(animal.getNome());
            String csv_cabecalho = csv.criarLinhaCSV("alturaMetros","idade","nome");
            String csv_Linha = csv.criarLinhaCSV(animal.getAlturaMetros(), animal.getIdade(), animal.getNome());
            csv.criarCSV(Animal.class.getSimpleName(), csv_Linha, csv_cabecalho);
            tfNome_Animal.clear();
            tfAlturaMetros_Animal.clear();
            tfIdade_Animal.clear();
        }
        catch (Exception e) {
            System.out.println("Ocorreu um erro na criação do objeto" + e);
        }
    }

    @FXML
    private void criarBioma() {
        String tipo = tfTipo_Bioma.getText();
        String temperatura = tfTemperatura_Bioma.getText();
        ArrayList<String> animais = animais_List;

        try {
            Bioma bioma = new Bioma(tipo, animais, Integer.parseInt(temperatura));
            System.out.println("Objeto criado com sucesso!");
            System.out.println(bioma.getTipo());
            System.out.println(bioma.getAnimais());
            System.out.println(bioma.getTemperatura());
            String csv_cabecalho = csv.criarLinhaCSV("tipo","animais","temperatura");
            String csv_Linha = csv.criarLinhaCSV(bioma.getTipo(), bioma.getAnimais(), bioma.getTemperatura());
            csv.criarCSV(Bioma.class.getSimpleName(), csv_Linha, csv_cabecalho);
            tfTipo_Bioma.clear();
            animais_List.clear();
            tfTemperatura_Bioma.clear();
        }
        catch (Exception e) {
            System.out.println("Ocorreu um erro na criação do objeto" + e);
        }
    }

    @FXML
    private void criarProfissional() {
        String cargo = tfCargo_Profissional.getText();
        boolean trabalhando = cbTrabalhando_Profissional.isSelected();
        String tempoServicoAnos = tfTempoServicoAnos_Profissional.getText();

        try {
            CSVCreator csv =  new CSVCreator();
            Profissional profissional = new Profissional(cargo, trabalhando, Integer.parseInt(tempoServicoAnos));
            System.out.println("Objeto criado com sucesso!");
            System.out.println(profissional.getCargo());
            System.out.println(profissional.isTrabalhando());
            System.out.println(profissional.getTempoServicoAnos());
            String csv_cabecalho = csv.criarLinhaCSV("cargo","trabalhando","tempoServicoAnos");
            String csv_Linha = csv.criarLinhaCSV(profissional.getCargo(), profissional.isTrabalhando(), profissional.getTempoServicoAnos());
            csv.criarCSV(Profissional.class.getSimpleName(), csv_Linha, csv_cabecalho);
            tfCargo_Profissional.clear();
            cbTrabalhando_Profissional.setSelected(false);
            tfTempoServicoAnos_Profissional.clear();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na criação do objeto" + e);
        }
    }

    public void adicionarAnimal() {
        dados.add(tfAnimais_Bioma.getText());
        lvLista.setItems(dados);
        tfAnimais_Bioma.clear();
    }

    @FXML
    private void abrirPainelTabela() {
        dados = FXCollections.observableArrayList();
        lvLista.setItems(dados);
        pnPainelTabela.setVisible(true);
        pnPainelTabela.setManaged(true);
        hBoxMain.setVisible(false);
        hBoxMain.setManaged(false);
    }

    @FXML
    private void fecharPainelTabela() {
        pnPainelTabela.setVisible(false);
        pnPainelTabela.setManaged(false);
        hBoxMain.setVisible(true);
        hBoxMain.setManaged(true);
        animais_List.clear();
    }

    @FXML
    private ArrayList<String> salvarListaAnimais() {
        animais_List.addAll(dados);
        pnPainelTabela.setVisible(false);
        pnPainelTabela.setManaged(false);
        hBoxMain.setVisible(true);
        hBoxMain.setManaged(true);
        return animais_List;
    }

    ///ImgVikMuniz
    @FXML
    private void criarConstrucao() {
        int andares = Integer.parseInt(lblAndares_Construcao.getText());
        String finalidade = tfFinalidade_Construcao.getText();
        String localizacao = tfLocalizacao_Construcao.getText();

        try {
            Construcao construcao = new Construcao(andares, finalidade, localizacao);
            System.out.println("Objeto criado com sucesso!");
            System.out.println(construcao.getAndares());
            System.out.println(construcao.getFinalidade());
            System.out.println(construcao.getLocalizacao());
            String csv_cabecalho = csv.criarLinhaCSV("andares","finalidade","localizacao");
            String csv_Linha = csv.criarLinhaCSV(construcao.getAndares(), construcao.getFinalidade(), construcao.getLocalizacao());
            csv.criarCSV(Construcao.class.getSimpleName(), csv_Linha, csv_cabecalho);
            lblAndares_Construcao.setText("0");
            SlrAndares_Construcao.setValue(0);
            tfFinalidade_Construcao.clear();
            tfLocalizacao_Construcao.clear();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na criação do objeto" + e);
        }

    }

    @FXML
    private void mudarLabelConstrucao() {
        SlrAndares_Construcao.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblAndares_Construcao.setText(String.valueOf(newVal.intValue()));
        });
    }

    @FXML
    private void criarPassaro() {
        String especie = tfEspecie_Passaro.getText();
        int id = Integer.parseInt(pfId_Passaro.getText());
        boolean voando = tbVoando_Passaro.selectedProperty().getValue();

        try {
            Passaro passaro = new Passaro(especie, id, voando);
            System.out.println("Objeto criado com sucesso!");
            System.out.println(passaro.getEspecie());
            System.out.println(passaro.getId());
            System.out.println(passaro.isVoando());
            String csv_cabecalho = csv.criarLinhaCSV("especie","id","voando");
            String csv_Linha = csv.criarLinhaCSV(passaro.getEspecie(), passaro.getId(), passaro.isVoando());
            csv.criarCSV(Passaro.class.getSimpleName(), csv_Linha, csv_cabecalho);
            tfEspecie_Passaro.clear();
            pfId_Passaro.clear();
            tbVoando_Passaro.setSelected(false);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na criação do objeto" + e);
        }
    }

    @FXML
    private void mudarCorBotao() {
        if(tbVoando_Passaro.selectedProperty().getValue()) {
            tbVoando_Passaro.setStyle("-fx-background-color: #c6c6c6;");
        } else {
            tbVoando_Passaro.setStyle("");
        }
    }

    @FXML
    private void criarVeiculo() {
        String marca = tfMarca_Veiculo.getText();
        Color cor = cpCor_Veiculo.getValue();
        String modelo = tfModelo_Veiculo.getText();

        try {
            Veiculo veiculo = new Veiculo(marca, cor, modelo);
            System.out.println("Objeto criado com sucesso!");
            System.out.println(veiculo.getMarca());
            System.out.println(veiculo.getCor());
            System.out.println(veiculo.getModelo());
            String csv_cabecalho = csv.criarLinhaCSV("marca","cor","modelo");
            String csv_Linha = csv.criarLinhaCSV(veiculo.getMarca(), veiculo.getCor(), veiculo.getModelo());
            csv.criarCSV(Veiculo.class.getSimpleName(), csv_Linha, csv_cabecalho);
            tfMarca_Veiculo.clear();
            cpCor_Veiculo.setValue(Color.WHITE);
            tfModelo_Veiculo.clear();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na criação do objeto" + e);
        }
    }

    ///Musica
    @FXML
    private void criarInstrumento() {}

    @FXML
    private void criarPessoa() {
        LocalDate nascimento = dpNascimento_Pessoa.getValue();

    }

    @FXML
    private void criarSom() {

    }
    //Instrumento - painel
    @FXML
    private void abrirPainelImagem() {
        apPainel_Instrumento.setVisible(true);
        apPainel_Instrumento.setManaged(true);
        hBoxMain11.setVisible(false);
        hBoxMain11.setManaged(false);
    }

    @FXML
    private void cancelarImagem() {
        apPainel_Instrumento.setVisible(false);
        apPainel_Instrumento.setManaged(false);
        hBoxMain11.setVisible(true);
        hBoxMain11.setManaged(true);
    }

    @FXML
    private void salvarImagem() {
        apPainel_Instrumento.setVisible(false);
        apPainel_Instrumento.setManaged(false);
        hBoxMain11.setVisible(true);
        hBoxMain11.setManaged(true);
    }
    @FXML
    private Image escolherImagem() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Adicionar Arquivo");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagem", "*.png", "*.jpeg", "*.webmp")
        );

        Stage stage = (Stage) btn_Arquivo.getScene().getWindow();
        File arquivo_selecionado = fc.showOpenDialog(stage);

        if(arquivo_selecionado != null) {
            Path caminho_Arquivo = arquivo_selecionado.toPath();
            Image imagem = new Image(String.valueOf(caminho_Arquivo.toFile()));
            ivFoto_Instrumento.setImage(imagem);
            lblMensagem.setText("Foto adicionada com sucesso!");
            lblMensagem.setVisible(true);

            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            Runnable atraso = () -> lblMensagem.setVisible(false);
            executor.schedule(atraso, 4, TimeUnit.SECONDS);
            executor.shutdown();
            return imagem;

        } else {
            return null;
        }
    }
}

