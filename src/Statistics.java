import java.util.HashMap;

public class Statistics {
    public static double pearson_test(String langauge_satistics, String text_statistics){
        String [] text_letetrs = text_statistics.split(";");
        HashMap <Character, Double> text_letter_stats = new HashMap<>();
        for (String text_letter : text_letetrs){
            String [] letter_and_percentage = text_letter.split(",");
            text_letter_stats.put(letter_and_percentage[0].charAt(0), Double.parseDouble(letter_and_percentage[1]));
        }

        String [] no_and_letetrs = langauge_satistics.split("#");
        Double no_page = Double.parseDouble(no_and_letetrs[0]);
        String [] letters_stats = no_and_letetrs[1].split(";");
        HashMap <Character, Double> standard_deviation = new HashMap<>();
        for (String letter : letters_stats){
            String [] letter_stats = letter.split(",");
            Character ch = letter_stats[0].charAt(0);
            Double avg_percentage = Double.parseDouble(letter_stats[1]);
            Double sum_squared_percentage = Double.parseDouble(letter_stats[2]);
            Double letter_standard_deviation = (1 / (no_page - 1)) * (sum_squared_percentage + no_page * avg_percentage * avg_percentage);
            standard_deviation.put(ch, letter_standard_deviation);
        }

        return 0.1;
    }
}
