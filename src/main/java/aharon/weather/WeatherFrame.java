package aharon.weather;

import com.andrewoid.apikeys.ApiKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherFrame extends JFrame {

    private JTextField tempBox = new JTextField();
    private JLabel tempLabel = new JLabel("Enter Location Below: ");
    private JLabel temperatureLabel;
    private WeatherController controller;

    public WeatherFrame() {
        setTitle("Weather Now");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BorderLayout());
        weatherPanel.setPreferredSize(new Dimension(300, 110));

        weatherPanel.add(tempBox, BorderLayout.CENTER);
        weatherPanel.add(tempLabel, BorderLayout.NORTH);
        add(weatherPanel, BorderLayout.NORTH);

        temperatureLabel = new JLabel("Loading...", SwingConstants.CENTER);
        temperatureLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(temperatureLabel, BorderLayout.CENTER);


        tempBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location = tempBox.getText();
                ApiKey apiKey = new ApiKey();
                String apikey = apiKey.get();
                WeatherService service = new WeatherServiceFactory().getService();
                controller = new WeatherController(service, temperatureLabel, apikey);
                controller.display(location);
            }
        });
    }

    public static void main(String[] args) {
        new WeatherFrame().setVisible(true);
    }
}


