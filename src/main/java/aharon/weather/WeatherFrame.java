package aharon.weather;

import javax.swing.*;
import java.awt.*;

public class WeatherFrame extends JFrame {

    private JLabel temperatureLabel;
    private WeatherController controller;

    public WeatherFrame() {
        setTitle("Weather");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(315, 150);
        setLayout(new BorderLayout());

        temperatureLabel = new JLabel("Loading...", SwingConstants.CENTER);
        temperatureLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(temperatureLabel, BorderLayout.CENTER);

        controller = new WeatherController(new WeatherServiceFactory().getService(), temperatureLabel);
        controller.display();
    }

    public static void main(String[] args) {
        new WeatherFrame().setVisible(true);
    }
}


