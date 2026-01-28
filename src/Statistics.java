import java.util.HashMap;

public class Statistics {
    public static double pearson_test(String langauge_satistics, String text_statistics){
        Double pearson_test_chi_squared = 0.0;

 

        String [] no_and_letetrs = langauge_satistics.split("#");
        Double no_page = Double.parseDouble(no_and_letetrs[0]);
        String [] letters_stats = no_and_letetrs[1].split(";");
        HashMap <Character, Double> language_letter_standard_devation = new HashMap<>();
        HashMap <Character, Double> language_letter_avg_percentage = new HashMap<>();
        for (String letter : letters_stats){
            String [] letter_stats = letter.split(",");
            Character ch = letter_stats[0].charAt(0);
            Double avg_percentage = Double.parseDouble(letter_stats[1]);
            Double sum_squared_percentage = Double.parseDouble(letter_stats[2]);
            language_letter_standard_devation.put(ch, (1 / (no_page - 1)) * (sum_squared_percentage + no_page * avg_percentage * avg_percentage));
            language_letter_avg_percentage.put(ch, avg_percentage);
            //pearson_test_chi_squared += ((avg_percentage - text_percentage_amount) / letter_standard_deviation) * ((avg_percentage - text_percentage_amount) / letter_standard_deviation) ;
        }

        String [] text_letetrs = text_statistics.split(";");
        for (String text_letter : text_letetrs){
            String [] letter_and_percentage = text_letter.split(",");
            Character ch = letter_and_percentage[0].charAt(0);
            Double text_percentage_amount = Double.parseDouble(letter_and_percentage[1]);
            Double avg_percentage = 2.0;
            Double letter_standard_deviation = 0.0000001;
            if(language_letter_avg_percentage.containsKey(ch)){
                avg_percentage = language_letter_avg_percentage.get(ch);
                letter_standard_deviation = language_letter_standard_devation.get(ch);
            }
            pearson_test_chi_squared += ((avg_percentage - text_percentage_amount) / letter_standard_deviation) * ((avg_percentage - text_percentage_amount) / letter_standard_deviation) ;

        }


        return pearson_test_chi_squared;
    }
}
