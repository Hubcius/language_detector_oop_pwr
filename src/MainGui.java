import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainGui {
    private static JFrame frame;

    public static void main_gui(){
        frame = new JFrame("Language Detector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        JTextArea input_area = new JTextArea();
        
        JScrollPane scrollPane = new JScrollPane(input_area);
        JButton check_language_button = new JButton("Check language");
        check_language_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = input_area.getText();
                Map<Character, Double> dict = new HashMap<>();
                Double amount_of_letter = 0.0;
                for (int i = 0; i < text.length(); i++) {
                    if(Character.isLetter(text.charAt(i))){
                        Character ch = text.charAt(i);
                        Character lowerCaseChar = Character.toLowerCase(ch);
                        dict.merge(lowerCaseChar, 1.0, Double::sum);
                        amount_of_letter += 1;
                    }
                }
                String letters_and_percentage = "";
                for (Character key : dict.keySet()) {
                    letters_and_percentage += key + "," + (dict.get(key) / amount_of_letter) + ";";
                }
                App.query_language(letters_and_percentage);

            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(check_language_button, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
}
