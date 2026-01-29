import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) throws Exception {
        MainGui.main_gui();
        Database.database_initialize();
        WebScrapperGui.scrapperGui();
        WebScrapper.scrapper();
        //ResultGui.DisplayResult("pl,1;pl,0.3;");//code,liczba;
    }

    public static void query_language(String letters_and_percentage){
        Map<Double, String> map = new TreeMap<>(java.util.Collections.reverseOrder());
        String result = "";
        for (String lan : WebScrapper.languageCodes){
            String lang_stats = Database.get_data_language(lan);
            if(!lang_stats.equals("null#")){
                Double score = Statistics.pearson_test(lang_stats, letters_and_percentage);
                map.put(score, lan);
            }
        }
        for (Map.Entry<Double, String> entry : map.entrySet()) {
            result += entry.getValue() + "," + entry.getKey() + ";";
        }
        ResultGui.DisplayResult(result);
    }


}
