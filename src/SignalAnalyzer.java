import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SignalAnalyzer extends VBox {

    private TextField amplitudeField = new TextField("1");
    private TextField frequencyField = new TextField("5");
    private TextField phaseField = new TextField("0");
    private TextField durationField = new TextField("1");
    private TextField harmonicsField = new TextField("9");

    private ComboBox<String> signalTypeBox = new ComboBox<>();

    private LineChart<Number, Number> timeChart;
    private LineChart<Number, Number> frequencyChart;

    public SignalAnalyzer() {
        setSpacing(15);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: linear-gradient(to bottom, #2c3e50, #4ca1af);");

        // Título principal
        Label titleLabel = new Label("Analizador de Señales");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setPadding(new Insets(0, 0, 10, 0));

        // Configuración ComboBox y TextFields
        signalTypeBox.getItems().addAll(
            "Senoidal", "Cuadrada", "Cuadrada (Fourier)", "Triangular", "Diente de sierra",
            "Pulso", "Exponencial", "Sampling (sinc)"
        );
        signalTypeBox.setValue("Senoidal");
        signalTypeBox.setPrefWidth(150);

        configureTextField(amplitudeField);
        configureTextField(frequencyField);
        configureTextField(phaseField);
        configureTextField(durationField);
        configureTextField(harmonicsField);

        Label lblSignal = styledLabel("Señal:");
        Label lblAmplitude = styledLabel("Amplitud:");
        Label lblFrequency = styledLabel("Frecuencia (Hz):");
        Label lblPhase = styledLabel("Fase (rad):");
        Label lblDuration = styledLabel("Duración (seg):");
        Label lblHarmonics = styledLabel("Armónicos:");

        GridPane inputsGrid = new GridPane();
        inputsGrid.setHgap(15);
        inputsGrid.setVgap(10);
        inputsGrid.setPadding(new Insets(10));
        inputsGrid.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 10;");

        inputsGrid.add(lblSignal, 0, 0);
        inputsGrid.add(signalTypeBox, 1, 0);

        inputsGrid.add(lblAmplitude, 2, 0);
        inputsGrid.add(amplitudeField, 3, 0);

        inputsGrid.add(lblFrequency, 0, 1);
        inputsGrid.add(frequencyField, 1, 1);

        inputsGrid.add(lblPhase, 2, 1);
        inputsGrid.add(phaseField, 3, 1);

        inputsGrid.add(lblDuration, 0, 2);
        inputsGrid.add(durationField, 1, 2);

        inputsGrid.add(lblHarmonics, 2, 2);
        inputsGrid.add(harmonicsField, 3, 2);

        // Inicialmente ocultar campo armónicos y su etiqueta
        harmonicsField.setDisable(true);
        harmonicsField.setVisible(false);
        lblHarmonics.setVisible(false);

        signalTypeBox.setOnAction(e -> {
            boolean showHarmonics = signalTypeBox.getValue().equals("Cuadrada (Fourier)");
            harmonicsField.setDisable(!showHarmonics);
            harmonicsField.setVisible(showHarmonics);
            lblHarmonics.setVisible(showHarmonics);
        });

        Button plotButton = new Button("Graficar Señal");
        plotButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        plotButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");
        plotButton.setOnMouseEntered(e -> plotButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;"));
        plotButton.setOnMouseExited(e -> plotButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;"));
        plotButton.setOnAction(e -> plotSignal());

        // Crear ejes para tiempo
        NumberAxis xAxis1 = new NumberAxis();
        NumberAxis yAxis1 = new NumberAxis();
        xAxis1.setLabel("Tiempo (s)");
        yAxis1.setLabel("Amplitud");

        timeChart = new LineChart<>(xAxis1, yAxis1);
        timeChart.setTitle("Señal en el tiempo");
        timeChart.setCreateSymbols(false);
        timeChart.setLegendVisible(false);
        timeChart.setStyle("-fx-background-color: #34495e;");

        // Crear ejes para frecuencia
        NumberAxis xAxis2 = new NumberAxis();
        NumberAxis yAxis2 = new NumberAxis();
        xAxis2.setLabel("Frecuencia (Hz)");
        yAxis2.setLabel("Magnitud");

        frequencyChart = new LineChart<>(xAxis2, yAxis2);
        frequencyChart.setTitle("Espectro de Frecuencia");
        frequencyChart.setCreateSymbols(false);
        frequencyChart.setLegendVisible(false);
        frequencyChart.setStyle("-fx-background-color: #34495e;");

        enableZoom(timeChart);
        enableZoom(frequencyChart);

        // Aplicar estilo a etiquetas de ejes y ticks cuando la escena esté lista
        timeChart.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                applyWhiteStyleToAxisLabels(xAxis1, yAxis1);
            }
        });

        frequencyChart.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                applyWhiteStyleToAxisLabels(xAxis2, yAxis2);
            }
        });

        getChildren().addAll(titleLabel, inputsGrid, plotButton, timeChart, frequencyChart);
        setPrefWidth(950);
        setPrefHeight(700);
    }

    private void applyWhiteStyleToAxisLabels(NumberAxis xAxis, NumberAxis yAxis) {
        // Eje X
        if (xAxis.lookup(".axis-label") != null)
            xAxis.lookup(".axis-label").setStyle("-fx-text-fill: white;");
        xAxis.lookupAll(".tick-label").forEach(t -> t.setStyle("-fx-text-fill: white;"));

        // Eje Y
        if (yAxis.lookup(".axis-label") != null)
            yAxis.lookup(".axis-label").setStyle("-fx-text-fill: white;");
        yAxis.lookupAll(".tick-label").forEach(t -> t.setStyle("-fx-text-fill: white;"));
    }

    private Label styledLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        return label;
    }

    private void configureTextField(TextField tf) {
        tf.setPrefWidth(80);
        tf.setStyle(
            "-fx-background-color: rgba(255,255,255,0.9); " +
            "-fx-background-radius: 5; " +
            "-fx-border-radius: 5; " +
            "-fx-padding: 3 5 3 5;"
        );
    }

    private void plotSignal() {
        double amplitude, frequency, phase, duration;
        try {
            amplitude = Double.parseDouble(amplitudeField.getText());
            frequency = Double.parseDouble(frequencyField.getText());
            phase = Double.parseDouble(phaseField.getText());
            duration = Double.parseDouble(durationField.getText());
        } catch (NumberFormatException ex) {
            showAlert("Error", "Por favor ingrese valores numéricos válidos.");
            return;
        }

        int samples = 1024;
        double sampleRate = samples / duration;
        double[] t = new double[samples];
        double[] signal = new double[samples];

        String selectedSignal = signalTypeBox.getValue();

        for (int i = 0; i < samples; i++) {
            t[i] = i / sampleRate;

            switch (selectedSignal) {
                case "Senoidal":
                    signal[i] = amplitude * Math.sin(2 * Math.PI * frequency * t[i] + phase);
                    break;

                case "Cuadrada":
                    signal[i] = amplitude * Math.signum(Math.sin(2 * Math.PI * frequency * t[i] + phase));
                    break;

                case "Cuadrada (Fourier)":
                    int harmonics = 9; // valor por defecto
                    try {
                        harmonics = Integer.parseInt(harmonicsField.getText());
                        if (harmonics < 1) harmonics = 1;
                        if (harmonics % 2 == 0) harmonics--; // asegurar impar
                    } catch (NumberFormatException ex) {
                        // si falla usar default
                    }
                    signal[i] = 0;
                    for (int n = 1; n <= harmonics; n += 2) {
                        signal[i] += (1.0 / n) * Math.sin(2 * Math.PI * n * frequency * t[i] + phase);
                    }
                    signal[i] *= (4 * amplitude / Math.PI);
                    break;

                case "Triangular":
                    signal[i] = 2 * amplitude / Math.PI *
                            Math.asin(Math.sin(2 * Math.PI * frequency * t[i] + phase));
                    break;

                case "Diente de sierra":
                    signal[i] = (2 * amplitude / Math.PI) *
                            (frequency * Math.PI * (t[i] % (1 / frequency)) - (Math.PI / 2));
                    break;

                case "Pulso":
                    double cycle = 1 / frequency;
                    double duty = 0.1 * cycle; // 10% duty cycle
                    signal[i] = (t[i] % cycle) < duty ? amplitude : 0;
                    break;

                case "Exponencial":
                    signal[i] = amplitude * Math.exp(-frequency * t[i]);
                    break;

                case "Sampling (sinc)":
                    double arg = Math.PI * (t[i] - duration / 2);
                    signal[i] = amplitude * (arg == 0 ? 1 : Math.sin(arg) / arg);
                    break;

                default:
                    signal[i] = 0;
            }
        }

        // Graficar señal en el tiempo
        timeChart.getData().clear();
        XYChart.Series<Number, Number> timeSeries = new XYChart.Series<>();
        timeSeries.setName(selectedSignal);
        for (int i = 0; i < samples; i++) {
            timeSeries.getData().add(new XYChart.Data<>(t[i], signal[i]));
        }
        timeChart.getData().add(timeSeries);

        // Calcular y graficar FFT (magnitud)
        double[] fft = FourierUtils.computeFFT(signal);

        frequencyChart.getData().clear();
        XYChart.Series<Number, Number> freqSeries = new XYChart.Series<>();
        freqSeries.setName("Magnitud FFT");

        for (int i = 0; i < fft.length / 2; i++) {
            double freq = i * sampleRate / fft.length;
            freqSeries.getData().add(new XYChart.Data<>(freq, fft[i]));
        }
        frequencyChart.getData().add(freqSeries);
    }

    private void enableZoom(LineChart<Number, Number> chart) {
        NumberAxis xAxis = (NumberAxis) chart.getXAxis();
        NumberAxis yAxis = (NumberAxis) chart.getYAxis();

        chart.setOnScroll((ScrollEvent event) -> {
            double delta = event.getDeltaY();
            double zoomFactor = 0.1;

            double xLower = xAxis.getLowerBound();
            double xUpper = xAxis.getUpperBound();
            double yLower = yAxis.getLowerBound();
            double yUpper = yAxis.getUpperBound();

            double xRange = xUpper - xLower;
            double yRange = yUpper - yLower;

            if (delta > 0) { // Zoom in
                xAxis.setLowerBound(xLower + xRange * zoomFactor);
                xAxis.setUpperBound(xUpper - xRange * zoomFactor);
                yAxis.setLowerBound(yLower + yRange * zoomFactor);
                yAxis.setUpperBound(yUpper - yRange * zoomFactor);
            } else { // Zoom out
                xAxis.setLowerBound(xLower - xRange * zoomFactor);
                xAxis.setUpperBound(xUpper + xRange * zoomFactor);
                yAxis.setLowerBound(yLower - yRange * zoomFactor);
                yAxis.setUpperBound(yUpper + yRange * zoomFactor);
            }

            event.consume();
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
