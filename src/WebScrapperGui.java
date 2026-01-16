import javax.swing.*;
import java.awt.*;

public class WebScrapperGui
{
    public static JFrame frame;
    public static JTextArea logArea;
    public static JTextField inputField;

    public static void scrapperGui()
    {
        frame = new JFrame("WebScrapper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(0, 10, 200, 450);
        frame.add(scrollPane);

        inputField = new JTextField("1", 20);
        inputField.setBounds(400, 10, 50, 30);
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(inputField);

        JLabel label = new JLabel("Pages per language:");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBounds(210, 10, 200, 30);
        frame.add(label);

        JButton startButton = new JButton("Start Scraping");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setBounds(240, 400, 200, 50);
        startButton.addActionListener(e ->

                new Thread(() -> {
                    WebScrapper.scrapWikipedia(Integer.parseInt(inputField.getText()));
                }).start());


        frame.add(startButton);

        frame.repaint();
    }

    public static void addLog(String text)
    {
        logArea.append(text + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    //new SwingWorker<void, String>

}
