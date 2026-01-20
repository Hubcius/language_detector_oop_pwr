import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        MainGui.main_gui();
        //Database.database_initialize();
        //WebScrapperGui.scrapperGui();
        //WebScrapper.scrapper();
    }

    public static void query_language(String letters_and_percentage){
        for (String lan : WebScrapper.languageCodes){
            String lang_stats = Database.get_data_language(lan);
            System.out.println(Statistics.pearson_test(lang_stats, letters_and_percentage));
        }
    }


}
