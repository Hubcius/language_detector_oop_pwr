import java.util.Random;

// 1. Oleh Rozhko         02% pracy
// 2. Mikołaj Szychowski  49% pracy
// 3. Hubert Tubis        49% pracy
public class App {
    public static void main(String[] args) throws Exception {
        //MainGui.main_gui();
        Database.database_initialize();
        WebScrapper.scrapper();
        WebScrapperGui.scrapperGui();
        ResultGui.DisplayResult("");
        double f = 432;
        String t = "" + f + "dgfdh";
    }

    public static void query_language(String letters_and_percentage){
        for (String lan : WebScrapper.languageCodes){
            String lang_stats = Database.get_data_language(lan);
            System.out.println(Statistics.pearson_test(lang_stats, letters_and_percentage));
        }
    }


}
