import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;

public class WebScrapperGui
{
    public static JFrame frame;
    public static JTextArea logArea;
    public static JTextArea statsArea;
    public static JTextField inputField;
    static JComboBox c1;
    static JComboBox c2;
    static JLabel entryCountText;
    static JScrollPane scrollPane2;

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

        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setLineWrap(true);
        statsArea.setWrapStyleWord(true);
        //statsArea.setFont(new Font("Nirmala UI", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(0, 10, 200, 450);
        frame.add(scrollPane);

        scrollPane2 = new JScrollPane(statsArea);
        scrollPane2.setBounds(500, 150, 200, 310);
        frame.add(scrollPane2);

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
                if(c1.getSelectedItem().toString().equals(" All"))
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

        entryCountText = new JLabel("Scraped articles: ");
        entryCountText.setFont(new Font("Arial", Font.PLAIN, 20));
        entryCountText.setBounds(500, 110, 200, 30);
        frame.add(entryCountText);

        JLabel title = new JLabel("Database data");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(500, 10, 200, 30);
        frame.add(title);

        // create checkbox
        c1 = new JComboBox(Arrays.stream(WebScrapper.nameList.toArray()).sorted().toArray());
        c1.setBounds(240, 100, 200, 30);
        frame.add(c1);

        c2 = new JComboBox(Arrays.stream(WebScrapper.nameList.toArray()).sorted().skip(1).toArray());
        c2.setBounds(500, 70, 200, 30);
        c2.addActionListener(e -> DisplayStats());
        frame.add(c2);
        DisplayStats();


        frame.repaint();
    }

    private static void DisplayStats()
    {
        JScrollBar vertical = scrollPane2.getVerticalScrollBar();
        int scrollPos = vertical.getValue();
        statsArea.setText("");
        entryCountText.setText("Scraped articles: 0");
        String s = c2.getSelectedItem().toString();
        String langCode = "";
        for (Map.Entry<String, String> entry : WebScrapper.localNames.entrySet())
        {
            if (entry.getValue().equals(s))
            {
                langCode =  entry.getKey();
                break;
            }
        }
        //System.out.println(langCode);
        String data = Database.get_data_language(langCode);
        //System.out.println(data);

        String[] data1 = data.split("#");
        if(data1.length < 2) return;
        entryCountText.setText("Scraped articles: " + data1[0]);
        String[] letters = data1[1].split(";");
        List<String> letters_and_percentage = new ArrayList<String>();
        for(String letter : letters)
        {
            String l = letter.split(",")[0];
            double d = (int)(Double.parseDouble(letter.split(",")[1]) * 1000);
            String let = d / 10 + "%#" + l;
            //statsArea.append(" " + l + " - " + d / 10 + "%" + "\n");
            letters_and_percentage.add(let);
        }

        letters_and_percentage = letters_and_percentage.stream()
                .sorted(Comparator.comparingDouble(s1 ->
                        Double.parseDouble(s1.substring(0, 3))
                ))
                .toList().reversed();//.stream().sorted().toList().reversed();

        String text = "";

        for (String letter : letters_and_percentage)
        {
            text += (" " + letter.split("#")[1] + " - " + letter.split("#")[0] + "\n");
        }

        DefaultCaret caret = (DefaultCaret) statsArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        Document doc = statsArea.getDocument();

        try {
            doc.remove(0, doc.getLength());
            doc.insertString(0, text, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        //SwingUtilities.invokeLater(() -> {vertical.setValue(scrollPos);});
    }

    public static void addLog(String text)
    {
        logArea.append(" " +  text + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
        SwingUtilities.invokeLater(() -> DisplayStats());
    }

    //new SwingWorker<void, String>

}
