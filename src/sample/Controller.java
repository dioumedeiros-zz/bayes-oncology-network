package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import smile.Network;

public class Controller {

    private static final String resultNode = "Tempo_Tratamento";
    private static final String xdslFile = "trabalhoGeNIe.xdsl";

    Network network;

    @FXML
    private Label perc;

    @FXML
    ComboBox<String> region;

    @FXML
    ComboBox<String> sex;

    @FXML
    ComboBox<String> age;

    @FXML
    ComboBox<String> model;


    @FXML
    BarChart<String, Integer> bar;

    @FXML
    private CategoryAxis xAxis;


    public void initialize() {
        this.initCombos();
        this.startNetwork();
    }

    public void startNetwork() {
        this.network = new Network();
        network.readFile(this.xdslFile);

        ObservableList<String> monthNames = FXCollections.observableArrayList();
        monthNames.add("DIAS_1_A_10");
        monthNames.add("DIAS_11_A_20");
        monthNames.add("DIAS_21_A_30");
        monthNames.add("DIAS_31_A_40");
        monthNames.add("DIAS_41_A_50");
        monthNames.add("DIAS_51_A_60");
        monthNames.add("DIAS_61_A_90");
        monthNames.add("DIAS_91_A_120");
        monthNames.add("DIAS_121_A_300");
        monthNames.add("DIAS_301_A_365");
        monthNames.add("DIAS_366_A_730");
        xAxis.setCategories(monthNames);
    }

    @FXML
    void calculateResult() {
        this.bar.getData().clear();

        this.setEvidences();

        network.updateBeliefs();
        double[] beliefs = network.getNodeValue(this.resultNode);
        String result = "";
        XYChart.Series<String, Integer> data = new XYChart.Series();

        for (int i = 0; i < beliefs.length; i++) {
            result += String.format("      %s         ", Math.ceil(beliefs[i] * 100) + "%");
            data.getData().add(new XYChart.Data(network.getOutcomeId(this.resultNode, i).toUpperCase(),
                                                Math.ceil(beliefs[i] * 100)));

        }
        perc.setText(result);
        bar.getData().add(data);
    }

    public void setEvidences() {
        network.clearAllEvidence();

        this.setSingleEvidence(network, "Regiao", this.region);
        this.setSingleEvidence(network, "Sexo", this.sex);
        this.setSingleEvidence(network, "Faixa_Etaria", this.age);
        this.setSingleEvidence(network, "Modalidade_Terapeutica", this.model);
    }

    public void setSingleEvidence(Network network, String evidenceName, ComboBox combo) {
        if (combo.getValue() != null) {
            String comboValue = combo.getValue().toString();
            if (combo != null && comboValue != null && comboValue != "Não informar") {
                network.setEvidence(evidenceName, combo.getValue().toString());
            }
        }
    }

    void initCombos() {
        this.region.getItems().addAll(
                "RS",
                "SC",
                "PR"
        );

        this.sex.getItems().addAll(
                "Masculino",
                "Feminino"
        );

        this.age.getItems().addAll(
                "Jovem",
                "Adulto",
                "Idoso"
        );

        this.model.getItems().addAll(
                "Cirurgia",
                "Quimioterapia",
                "Radioterapia"
        );
    }

}