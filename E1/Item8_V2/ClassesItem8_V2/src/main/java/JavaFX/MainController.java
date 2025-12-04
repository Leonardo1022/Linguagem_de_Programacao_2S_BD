package JavaFX;

import ImgDoodle.*;
import ImgVikMuniz.*;
import Musica.*;

import SqLite.MainDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController implements Initializable {
    ///Img Doodle
    @FXML public AnchorPane apDoodle;
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
    @FXML public Button btnSalvar_Bioma;
    @FXML public Button btn_Animais_Bioma;
    @FXML public Button btnMusicas_Artista;
    @FXML public Label lblTitulo_painelTabela;
    @FXML public ListView<String> lvLista;
    @FXML public HBox hBoxDoodle;
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

    ///Musica - Principal
    @FXML public AnchorPane apMusica;
    //Artista
    @FXML public TextField tfNome_Artista;
    //Música
    @FXML public TextField tfNome_Musica;
    @FXML public ComboBox<Artista> cbArtista_Musica;
    @FXML public ComboBox<Album> cbAlbum_Musica;
    //Música - painel
    @FXML public AnchorPane apPainel_Player;
    @FXML private WebView webView;
    @FXML private TextField tfInput;
    private WebEngine webEngine;
    private static final String CLIENT_ID = ""; //Colocar
    private static final String CLIENT_SECRET = "";
    //Album
    @FXML public TextField tfNome_Album;
    @FXML public DatePicker dpData_Album;
    @FXML public ComboBox<Artista> cbArtista_Album;
    //Album - painel
    @FXML public AnchorPane apPainel_Album;
    @FXML public Button btn_Arquivo;
    @FXML public ImageView ivFoto_Album;
    @FXML public Label lblMensagem;
    @FXML public HBox hBoxMusica;


    //Outros
    CSVCreator csv = new CSVCreator();
    ObservableList<String> dados;
    ArrayList<String> Lista_itens = new ArrayList<>();

    //Vai ocorrer no início
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apPainel_Player.setVisible(false);
        apPainel_Player.setManaged(false);

        try {
            cbArtista_Musica.getItems().addAll(MainDAO.coletarArtistas());
            cbArtista_Album.getItems().addAll(MainDAO.coletarArtistas());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Quando o ComboBox for selecionado faz isso
        cbArtista_Musica.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    try {
                        cbAlbum_Musica.getItems().clear();
                        cbAlbum_Musica.getItems().addAll(MainDAO.coletarAlbuns(cbArtista_Musica.getValue()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public MainController() {
    }

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
        ArrayList<String> animais = Lista_itens;

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
            Lista_itens.clear();
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

    private void mudarVisibilidade(Node tela_principal, Node tela_secundaria, boolean para_tela_principal) {
        if(para_tela_principal) {
            tela_secundaria.setVisible(false);
            tela_secundaria.setManaged(false);
            tela_principal.setVisible(true);
            tela_principal.setManaged(true);
        } else {
            tela_principal.setVisible(false);
            tela_principal.setManaged(false);
            tela_secundaria.setVisible(true);
            tela_secundaria.setManaged(true);
        }
    }

    @FXML
    private void abrirPainelTabela(ActionEvent event) {
        dados = FXCollections.observableArrayList();
        lvLista.setItems(dados);
        if(event.getSource().equals(btn_Animais_Bioma)) {
            try {
                apMusica.getChildren().remove(pnPainelTabela);
                apDoodle.getChildren().add(pnPainelTabela);
                lblTitulo_painelTabela.setText("Adicione os animais");
            } catch(Exception e) {
                e.printStackTrace();
            }
            mudarVisibilidade(hBoxDoodle, pnPainelTabela, false);
        } else if(event.getSource().equals(btnMusicas_Artista)) {
            try {
                apDoodle.getChildren().remove(pnPainelTabela);
                apMusica.getChildren().add(pnPainelTabela);
                lblTitulo_painelTabela.setText("Adicione as músicas");
            } catch(Exception e) {
                e.printStackTrace();
            }
            mudarVisibilidade(hBoxMusica, pnPainelTabela, false);
        }
        //System.out.println(event.getSource().equals(btn_Animais_Bioma));
        //System.out.println(event.getSource().equals(btnMusicas_Artista));
        //btnMusicas_Artista.getParent();
    }

    @FXML
    private void fecharPainelTabela() {
        if(!hBoxMusica.isVisible()) {
            mudarVisibilidade(hBoxMusica, pnPainelTabela, true);
        } else if(!hBoxDoodle.isVisible()) {
            mudarVisibilidade(hBoxDoodle, pnPainelTabela, true);
        }
        Lista_itens.clear();
    }

    @FXML
    private void salvarListaAnimais() {
        Lista_itens.addAll(dados);
        if(!hBoxMusica.isVisible()) {
            mudarVisibilidade(hBoxMusica, pnPainelTabela, true);
        } else if(!hBoxDoodle.isVisible()) {
            mudarVisibilidade(hBoxDoodle, pnPainelTabela, true);
        }
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
    private void criarArtista() throws SQLException {
        Artista artista = new Artista(tfNome_Artista.getText());
        MainDAO.inserirArtista(artista.toString());
        cbArtista_Musica.getItems().add(artista);
        cbArtista_Album.getItems().add(artista);

        tfNome_Artista.clear();
        System.out.println("Adicionado o artista com sucesso!");
    }

    @FXML
    private void criarMusica() throws SQLException {
        Musica musica = new Musica(
                tfNome_Musica.getText(),
                cbArtista_Musica.getValue(),
                cbAlbum_Musica.getValue()
        );
        tfNome_Musica.clear();

        MainDAO.inserirMusica(musica);
        System.out.println("Adicionado a música com sucesso!");
    }

    @FXML
    private void criarAlbum() throws SQLException {
        Album album = new Album(
                tfNome_Album.getText(),
                ivFoto_Album.getImage(),
                cbArtista_Album.getValue(),
                dpData_Album.getValue()
        );
        tfNome_Album.clear();

        MainDAO.inserirAlbum(album);
        System.out.println("Adicionado o album com sucesso!");
    }
    //Album - painel
    @FXML
    private void abrirPainelImagem() {
        mudarVisibilidade(hBoxMusica, apPainel_Album, false);
    }

    @FXML
    private void cancelarImagem() {
        mudarVisibilidade(hBoxMusica, apPainel_Album, true);
    }

    @FXML
    private void salvarImagem() {
        mudarVisibilidade(hBoxMusica, apPainel_Album, true);
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
            ivFoto_Album.setImage(imagem);
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

    @FXML
    private void abrirPainelPlayer() throws Exception {
        mudarVisibilidade(hBoxMusica, apPainel_Player, false);
        String input = tfNome_Musica.getText();
        String codigo = searchTrack(input, getAccessToken()) + '"';
        String html = """
        <!DOCTYPE html>
        <html>
        <head>
        <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
        </head>
        <body style='margin:0;overflow: hidden;'>
        <iframe style="border-radius:12px"
        src="https://open.spotify.com/embed/track/""" + codigo + "\n" +
        """
        width="100%" height="100%" frameBorder="0"
        allow="autoplay; clipboard-write; fullscreen; picture-in-picture">
        </iframe>
        </body>
        </html>
        """;
        //System.out.println(html);
        if(webEngine != webView.getEngine()) {
            webEngine = webView.getEngine();
            webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        }
        webEngine.loadContent(html);
        webEngine.setJavaScriptEnabled(true);
    }

    @FXML
    private void fecharPainelPlayer() {
        mudarVisibilidade(hBoxMusica, apPainel_Player, true);
    }

    public static String getAccessToken() throws Exception {

        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        // captura o access_token
        int index = body.indexOf("access_token\":\"") + "access_token\":\"".length();
        String token = body.substring(index, body.indexOf("\"", index));

        return token;
    }

    public static String searchTrack(String query, String token) throws Exception {
        //Query tratada para retirar espaços e caracteres especiais
        String query_tratada = URLEncoder.encode(query, StandardCharsets.UTF_8);
        //Quantidade de resultados retornados
        String resultados = "3";

        HttpRequest requisicao = HttpRequest.newBuilder()
                //Cria uma requisição
                .uri(URI.create("https://api.spotify.com/v1/search?q=" + query_tratada + "&type=track&limit=" + resultados))
                //Dá o token para adquirir a resposta
                .header("Authorization", "Bearer " + token)
                //Finaliza a requisição
                .build();

        HttpClient client = HttpClient.newHttpClient();
        //Manda a requisição e obtém a resposta
        HttpResponse<String> resposta = client.send(requisicao, HttpResponse.BodyHandlers.ofString());
        //Guarda a resposta numa ‘String’
        String json = resposta.body();

        System.out.println("\nJSON retornado:");
        //Printa a json pura
        System.out.println(json);

        /// Extração simples (sem JSON parser)
        //Verifica se a String possui a um vídeo preview
        if (json.contains("https://open.spotify.com/track/")) {
            //Marca a primeira ocorrência do trecho
            int local = json.indexOf("https://open.spotify.com/track/") + "https://open.spotify.com/track/".length();
            System.out.println(local);
            String preview = json.substring(local, json.indexOf("\"", local + 1));
            System.out.println("\npreview: " + preview);
            return preview;
        } else {
            System.out.println("\nEssa música não possui preview.");
        }
        //Verifica se a String possui um id
        if (json.contains("\"id\":\"")) {
            int local = json.indexOf("id\":\"") + "id\":\"".length();
            System.out.println(local);
            String id = json.substring(local, json.indexOf("\"", local + 1));
            System.out.println("\nid: " + id);
        } else {
            System.out.println("\nEssa música não possui ID.");
        }
        return null;
    }
}


