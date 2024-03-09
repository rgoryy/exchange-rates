package com.example.exchangerates;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField initialPriceOfFirstTextField;

    @FXML
    private TextField initialPriceOfSecondTextField;

    @FXML
    private LineChart<Integer, Double> lineChart;

    private Timer timer;

    private Currency firstCurrency;

    private Currency secondCurrency;

    @FXML
    protected void onStartStopButtonClick() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
            return;
        }

        lineChart.getData().clear();

        timer = new Timer();

        XYChart.Series<Integer, Double> firstCurrencySeries = new XYChart.Series<>();
        XYChart.Series<Integer, Double> secondCurrencySeries = new XYChart.Series<>();

        firstCurrency = new Currency(
                Double.parseDouble(
                        initialPriceOfFirstTextField.getText()
                )
        );

        secondCurrency = new Currency(
                Double.parseDouble(
                        initialPriceOfSecondTextField.getText()
                )
        );

        firstCurrencySeries.getData().add(
                new XYChart.Data<Integer, Double>(
                        firstCurrency.getDay(),
                        firstCurrency.getPrice()
                )
        );

        secondCurrencySeries.getData().add(
                new XYChart.Data<Integer, Double>(
                        secondCurrency.getDay(),
                        secondCurrency.getPrice()
                )
        );

        firstCurrencySeries.setName("First currency");
        secondCurrencySeries.setName("Second currency");

        lineChart.getData().addAll(firstCurrencySeries, secondCurrencySeries);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> drawNextDay());
            }
        };

        timer.schedule(task, 1000, 100);

    }

    public void drawNextDay() {

        firstCurrency.updatePriceForNextDay();

        secondCurrency.updatePriceForNextDay();

        lineChart.getData().get(0).getData().add(
                new XYChart.Data<>(firstCurrency.getDay(), firstCurrency.getPrice())
        );

        lineChart.getData().get(1).getData().add(
                new XYChart.Data<Integer, Double>(
                        secondCurrency.getDay(),
                        secondCurrency.getPrice()
                )
        );
    }
}