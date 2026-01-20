import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Map;

public class WebScrapperGui
{
    public static JFrame frame;
    public static JTextArea logArea;
    public static JTextField inputField;
    static JComboBox c1;

    public static void scrapperGui()
    {
        frame = new JFrame("WebScraper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
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
        {
            new Thread(() ->
            {
                String langCode = "";
                if(c1.getSelectedItem().toString().equals("All"))
                {
                    WebScrapper.scrapWikipedia(Integer.parseInt(inputField.getText()), "All");
                }
                else
                {
                    for (Map.Entry<String, String> entry : WebScrapper.localNames.entrySet())
                    {
                        if (entry.getValue().equals(c1.getSelectedItem().toString()))
                        {
                            langCode =  entry.getKey();
                            break;
                        }
                    }
                    WebScrapper.scrapWikipedia(Integer.parseInt(inputField.getText()), langCode);
                }
            }).start();
        });

        frame.add(startButton);


        JLabel label2 = new JLabel("Language to scrap");
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        label2.setBounds(210, 60, 200, 30);
        frame.add(label2);


        // create checkbox
        c1 = new JComboBox(WebScrapper.nameList.toArray());
        c1.setBounds(240, 100, 200, 30);
        frame.add(c1);

        frame.repaint();
    }


    public static void addLog(String text)
    {
        logArea.append(text + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    //new SwingWorker<void, String>

}
