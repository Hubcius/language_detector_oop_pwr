import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) throws Exception {
        MainGui.main_gui();
        Database.database_initialize();
        WebScrapperGui.scrapperGui();
        WebScrapper.scrapper();
    }

    public static void query_language(String letters_and_percentage){
        Map<Double, String> map = new TreeMap<>();
        for (String lan : WebScrapper.languageCodes){
            String lang_stats = Database.get_data_language(lan);
            if(!lang_stats.equals("null#")){
                map.put(Statistics.pearson_test(lang_stats, letters_and_percentage), WebScrapper.localNames.get(lan));
                System.out.println(Statistics.pearson_test(lang_stats, letters_and_percentage));
            }
        }
        System.out.println("1");
    }


}
