import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResultGui
{
    private static JFrame frame;
    private static JScrollPane scrollPane;
    private static JPanel statsPanel;

    public static void DisplayResult(String result)
    {
        System.out.println("Result: " + result);
        frame = new JFrame("Results");
        frame.setSize(400 + 200, 700);
        frame.setLayout(null);
        frame.setVisible(true);

        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.add(Box.createVerticalStrut(0));


        scrollPane = new JScrollPane(statsPanel);
        scrollPane.setBounds(0, 0, 385 + 200, 700);
        scrollPane.getVerticalScrollBar().setUnitIncrement(40);
        frame.add(scrollPane);

        //AddPanel("aaaa", 0.5);

        String[] body = result.split(";");
        Collections.reverse(Arrays.asList(body));

        List<String> languages = new ArrayList<>();
        List<Double> scores = new ArrayList<>();

        double sum = 0.0;
        double max = 0.0;

        for(String s : body)
        {
            if(s.length() > 0)
            {
                String[] parts = s.split(",");
                sum += Double.parseDouble(parts[1]);

                //AddPanel(WebScrapper.localNames.get(parts[0]), Double.parseDouble(parts[1]));
            }
        }

        for(String s : body)
        {
            if(s.length() > 0)
            {
                String[] parts = s.split(",");
                max =  Math.max(1/ (Double.parseDouble(parts[1]) / sum), max);
            }
        }



        for(String s : body)
        {
            if(s.length() > 0)
            {
                String[] parts = s.split(",");
                AddPanel(WebScrapper.localNames.get(parts[0]), (1/ (Double.parseDouble(parts[1]) / sum)) / max);
                System.out.println((1 / (Double.parseDouble(parts[1]) / sum)) / max);
            }
        }
    }

    private static void AddPanel(String language, double probability)
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 50));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        panel.setLayout(null);

        JPanel rectangle1 = new JPanel();
        rectangle1.setBackground( Color.WHITE );
        rectangle1.setBounds(1, 0, 600, 48);
        rectangle1.setLayout(null);
        panel.add(rectangle1);

        JLabel title = new JLabel(language);
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setBounds(10, 0, 200, 50);

        JPanel rectangle = new JPanel();
        rectangle.setBackground( Color.RED );
        rectangle.setBounds(210, 20, (int)(350 * probability), 10);
        rectangle1.add(rectangle);

        rectangle1.add(title);

        statsPanel.add(panel);
        statsPanel.revalidate();
        statsPanel.repaint();
    }
}
