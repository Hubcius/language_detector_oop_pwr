import javax.swing.*;
import java.awt.*;

public class ResultGui
{
    private static JFrame frame;
    private static JScrollPane scrollPane;
    private static JPanel statsPanel;

    public static void DisplayResult(String result)
    {
        frame = new JFrame("Results");
        frame.setSize(400, 700);
        frame.setLayout(null);
        frame.setVisible(true);

        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.add(Box.createVerticalStrut(0));


        scrollPane = new JScrollPane(statsPanel);
        scrollPane.setBounds(0, 0, 385, 700);
        scrollPane.getVerticalScrollBar().setUnitIncrement(40);
        frame.add(scrollPane);

        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
        AddPanel();
    }

    private static void AddPanel()
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 50));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panel.setLayout(null);

        JLabel title = new JLabel("Results");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setBounds(50, 0, 400, 50);

        panel.add(title);

        statsPanel.add(panel);
        statsPanel.revalidate();
        statsPanel.repaint();
    }
}
