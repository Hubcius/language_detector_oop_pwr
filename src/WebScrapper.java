import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class WebScrapper
{
    public static boolean isRunning = false;
    public static List<String> languageCodes = Arrays.asList("aa", "ab", "ace", "ady", "af", "als", "alt", "am", "ami", "an", "ang", "ann", "anp", "ar", "arc", "ary", "arz", "as", "ast", "atj", "av", "avk", "awa", "ay", "az", "azb", "ba", "ban", "bar", "bat-smg", "bbc", "bcl", "bdr", "be", "be-tarask", "bew", "bg", "bh", "bi", "bjn", "blk", "bm", "bn", "bo", "bpy", "br", "bs", "btm", "bug", "bxr", "ca", "cbk-zam", "cdo", "ce", "ceb", "ch", "chr", "chy", "ckb", "co", "crh", "cs", "csb", "cu", "cv", "cy", "da", "dag", "de", "dga", "din", "diq", "dsb", "dtp", "dty", "dv", "dz", "ee", "el", "eml", "en", "eo", "es", "et", "eu", "ext", "fa", "fat", "ff", "fi", "fiu-vro", "fj", "fo", "fon", "fr", "frp", "frr", "fur", "fy", "ga", "gag", "gan", "gcr", "gd", "gl", "glk", "gn", "gom", "gor", "got", "gpe", "gu", "guc", "gur", "guw", "gv", "ha", "hak", "haw", "he", "hi", "hif", "hr", "hsb", "ht", "hu", "hy", "hyw", "ia", "iba", "id", "ie", "ig", "igl", "ik", "ilo", "inh", "io", "is", "it", "iu", "ja", "jam", "jbo", "jv", "ka", "kaa", "kab", "kbd", "kbp", "kcg", "kg", "kge", "ki", "kk", "km", "kn", "knc", "ko", "koi", "krc", "ks", "ksh", "ku", "kus", "kv", "kw", "ky", "la", "lad", "lb", "lbe", "lez", "lfn", "lg", "li", "lij", "lld", "lmo", "ln", "lo", "lt", "ltg", "lv", "lzh", "mad", "mai", "map-bms", "mdf", "mg", "mhr", "mi", "min", "mk", "ml", "mn", "mni", "mnw", "mo", "mos", "mr", "mrj", "ms", "mt", "mwl", "my", "myv", "mzn", "nah", "nan", "nap", "nds", "nds-nl", "ne", "new", "nia", "nl", "nn", "no", "nov", "nqo", "nr", "nrm", "nso", "nup", "nv", "ny", "oc", "olo", "om", "or", "os", "pa", "pag", "pam", "pap", "pcd", "pcm", "pdc", "pfl", "pi", "pl", "pms", "pnb", "pnt", "ps", "pt", "pwn", "qu", "rki", "rm", "rmy", "rn", "ro", "roa-rup", "roa-tara", "rsk", "ru", "rue", "rup", "rw", "sa", "sah", "sat", "sc", "scn", "sco", "sd", "se", "sg", "sgs", "sh", "shi", "shn", "shy", "si", "simple", "sk", "skr", "sl", "sm", "smn", "sn", "so", "sq", "sr", "srn", "ss", "st", "stq", "su", "sv", "sw", "syl", "szl", "szy", "ta", "tay", "tcy", "tdd", "te", "tet", "tg", "th", "ti", "tig", "tk", "tl", "tly", "tn", "to", "tok", "tpi", "tr", "trv", "ts", "tt", "tum", "tw", "ty", "tyv", "udm", "ug", "uk", "ur", "uz", "ve", "vec", "vep", "vi", "vls", "vo", "vro", "wa", "war", "wo", "wuu", "xal", "xh", "xmf", "yi", "yo", "yue", "za", "zea", "zgh", "zh", "zh-classical", "zh-min-nan", "zh-yue", "zu");
    public static Map<String, String> localNames = new HashMap<String, String>();

    public static void initializeLanguages()
    {
        localNames.put("aa", "Afar");
        localNames.put("ab", "Abkhazian");
        localNames.put("ace", "Acehnese");
        localNames.put("ady", "Adyghe");
        localNames.put("af", "Afrikaans");
        localNames.put("ak", "Akan");
        localNames.put("als", "Alemannic");
        localNames.put("alt", "Southern Altai");
        localNames.put("am", "Amharic");
        localNames.put("ami", "Amis");
        localNames.put("an", "Aragonese");
        localNames.put("ang", "Old English");
        localNames.put("ann", "Obolo");
        localNames.put("anp", "Angika");
        localNames.put("ar", "Arabic");
        localNames.put("arc", "Aramaic");
        localNames.put("ary", "Moroccan Arabic");
        localNames.put("arz", "Egyptian Arabic");
        localNames.put("as", "Assamese");
        localNames.put("ast", "Asturian");
        localNames.put("atj", "Atikamekw");
        localNames.put("av", "Avaric");
        localNames.put("avk", "Kotava");
        localNames.put("awa", "Awadhi");
        localNames.put("ay", "Aymara");
        localNames.put("az", "Azerbaijani");
        localNames.put("azb", "South Azerbaijani");
        localNames.put("ba", "Bashkir");
        localNames.put("ban", "Balinese");
        localNames.put("bar", "Bavarian");
        localNames.put("bat-smg", "Samogitian");
        localNames.put("bbc", "Batak Toba");
        localNames.put("bcl", "Central Bikol");
        localNames.put("bdr", "West Coast Bajau");
        localNames.put("be", "Belarusian");
        localNames.put("be-tarask", "Belarusian (Tara\u0161kievica orthography)");
        localNames.put("be-x-old", "Belarusian (Tara\u0161kievica orthography)");
        localNames.put("bew", "Betawi");
        localNames.put("bg", "Bulgarian");
        localNames.put("bh", "Bhojpuri");
        localNames.put("bi", "Bislama");
        localNames.put("bjn", "Banjar");
        localNames.put("blk", "Pa'O");
        localNames.put("bm", "Bambara");
        localNames.put("bn", "Bangla");
        localNames.put("bo", "Tibetan");
        localNames.put("bpy", "Bishnupriya");
        localNames.put("br", "Breton");
        localNames.put("bs", "Bosnian");
        localNames.put("btm", "Batak Mandailing");
        localNames.put("bug", "Buginese");
        localNames.put("bxr", "Russia Buriat");
        localNames.put("ca", "Catalan");
        localNames.put("cbk-zam", "Chavacano");
        localNames.put("cdo", "Mindong");
        localNames.put("ce", "Chechen");
        localNames.put("ceb", "Cebuano");
        localNames.put("ch", "Chamorro");
        localNames.put("cho", "Choctaw");
        localNames.put("chr", "Cherokee");
        localNames.put("chy", "Cheyenne");
        localNames.put("ckb", "Central Kurdish");
        localNames.put("co", "Corsican");
        localNames.put("cr", "Cree");
        localNames.put("crh", "Crimean Tatar");
        localNames.put("cs", "Czech");
        localNames.put("csb", "Kashubian");
        localNames.put("cu", "Church Slavic");
        localNames.put("cv", "Chuvash");
        localNames.put("cy", "Welsh");
        localNames.put("da", "Danish");
        localNames.put("dag", "Dagbani");
        localNames.put("de", "German");
        localNames.put("dga", "Southern Dagaare");
        localNames.put("din", "Dinka");
        localNames.put("diq", "Dimli");
        localNames.put("dsb", "Lower Sorbian");
        localNames.put("dtp", "Central Dusun");
        localNames.put("dty", "Doteli");
        localNames.put("dv", "Divehi");
        localNames.put("dz", "Dzongkha");
        localNames.put("ee", "Ewe");
        localNames.put("el", "Greek");
        localNames.put("eml", "Emiliano-Romagnolo");
        localNames.put("en", "English");
        localNames.put("eo", "Esperanto");
        localNames.put("es", "Spanish");
        localNames.put("et", "Estonian");
        localNames.put("eu", "Basque");
        localNames.put("ext", "Extremaduran");
        localNames.put("fa", "Persian");
        localNames.put("fat", "Fanti");
        localNames.put("ff", "Fula");
        localNames.put("fi", "Finnish");
        localNames.put("fiu-vro", "V\\u00f5ro");
        localNames.put("fj", "Fijian");
        localNames.put("fo", "Faroese");
        localNames.put("fon", "Fon");
        localNames.put("fr", "French");
        localNames.put("frp", "Arpitan");
        localNames.put("frr", "Northern Frisian");
        localNames.put("fur", "Friulian");
        localNames.put("fy", "Western Frisian");
        localNames.put("ga", "Irish");
        localNames.put("gag", "Gagauz");
        localNames.put("gan", "Gan");
        localNames.put("gcr", "Guianan Creole");
        localNames.put("gd", "Scottish Gaelic");
        localNames.put("gl", "Galician");
        localNames.put("glk", "Gilaki");
        localNames.put("gn", "Guarani");
        localNames.put("gom", "Goan Konkani");
        localNames.put("gor", "Gorontalo");
        localNames.put("got", "Gothic");
        localNames.put("gpe", "Ghanaian Pidgin");
        localNames.put("gsw", "Alemannic");
        localNames.put("gu", "Gujarati");
        localNames.put("guc", "Wayuu");
        localNames.put("gur", "Frafra");
        localNames.put("guw", "Gun");
        localNames.put("gv", "Manx");
        localNames.put("ha", "Hausa");
        localNames.put("hak", "Hakka Chinese");
        localNames.put("haw", "Hawaiian");
        localNames.put("he", "Hebrew");
        localNames.put("hi", "Hindi");
        localNames.put("hif", "Fiji Hindi");
        localNames.put("ho", "Hiri Motu");
        localNames.put("hr", "Croatian");
        localNames.put("hsb", "Upper Sorbian");
        localNames.put("ht", "Haitian Creole");
        localNames.put("hu", "Hungarian");
        localNames.put("hy", "Armenian");
        localNames.put("hyw", "Western Armenian");
        localNames.put("hz", "Herero");
        localNames.put("ia", "Interlingua");
        localNames.put("iba", "Iban");
        localNames.put("id", "Indonesian");
        localNames.put("ie", "Interlingue");
        localNames.put("ig", "Igbo");
        localNames.put("igl", "Igala");
        localNames.put("ii", "Sichuan Yi");
        localNames.put("ik", "Inupiaq");
        localNames.put("ilo", "Iloko");
        localNames.put("inh", "Ingush");
        localNames.put("io", "Ido");
        localNames.put("is", "Icelandic");
        localNames.put("it", "Italian");
        localNames.put("iu", "Inuktitut");
        localNames.put("ja", "Japanese");
        localNames.put("jam", "Jamaican Creole English");
        localNames.put("jbo", "Lojban");
        localNames.put("jv", "Javanese");
        localNames.put("ka", "Georgian");
        localNames.put("kaa", "Kara-Kalpak");
        localNames.put("kab", "Kabyle");
        localNames.put("kbd", "Kabardian");
        localNames.put("kbp", "Kabiye");
        localNames.put("kcg", "Tyap");
        localNames.put("kg", "Kongo");
        localNames.put("kge", "Komering");
        localNames.put("ki", "Kikuyu");
        localNames.put("kj", "Kuanyama");
        localNames.put("kk", "Kazakh");
        localNames.put("kl", "Kalaallisut");
        localNames.put("km", "Khmer");
        localNames.put("kn", "Kannada");
        localNames.put("knc", "Central Kanuri");
        localNames.put("ko", "Korean");
        localNames.put("koi", "Komi-Permyak");
        localNames.put("kr", "Kanuri");
        localNames.put("krc", "Karachay-Balkar");
        localNames.put("ks", "Kashmiri");
        localNames.put("ksh", "Colognian");
        localNames.put("ku", "Kurdish");
        localNames.put("kus", "Kusaal");
        localNames.put("kv", "Komi");
        localNames.put("kw", "Cornish");
        localNames.put("ky", "Kyrgyz");
        localNames.put("la", "Latin");
        localNames.put("lad", "Ladino");
        localNames.put("lb", "Luxembourgish");
        localNames.put("lbe", "Lak");
        localNames.put("lez", "Lezghian");
        localNames.put("lfn", "Lingua Franca Nova");
        localNames.put("lg", "Ganda");
        localNames.put("li", "Limburgish");
        localNames.put("lij", "Ligurian");
        localNames.put("lld", "Ladin");
        localNames.put("lmo", "Lombard");
        localNames.put("ln", "Lingala");
        localNames.put("lo", "Lao");
        localNames.put("lrc", "Northern Luri");
        localNames.put("lt", "Lithuanian");
        localNames.put("ltg", "Latgalian");
        localNames.put("lv", "Latvian");
        localNames.put("lzh", "Literary Chinese");
        localNames.put("mad", "Madurese");
        localNames.put("mai", "Maithili");
        localNames.put("map-bms", "Banyumasan");
        localNames.put("mdf", "Moksha");
        localNames.put("mg", "Malagasy");
        localNames.put("mh", "Marshallese");
        localNames.put("mhr", "Eastern Mari");
        localNames.put("mi", "M\u0101ori");
        localNames.put("min", "Minangkabau");
        localNames.put("mk", "Macedonian");
        localNames.put("ml", "Malayalam");
        localNames.put("mn", "Mongolian");
        localNames.put("mni", "Manipuri");
        localNames.put("mnw", "Mon");
        localNames.put("mo", "Moldovan");
        localNames.put("mos", "Mossi");
        localNames.put("mr", "Marathi");
        localNames.put("mrj", "Western Mari");
        localNames.put("ms", "Malay");
        localNames.put("mt", "Maltese");
        localNames.put("mus", "Muscogee");
        localNames.put("mwl", "Mirandese");
        localNames.put("my", "Burmese");
        localNames.put("myv", "Erzya");
        localNames.put("mzn", "Mazanderani");
        localNames.put("na", "Nauru");
        localNames.put("nah", "Nahuatl");
        localNames.put("nan", "Minnan");
        localNames.put("nap", "Neapolitan");
        localNames.put("nds", "Low German");
        localNames.put("nds-nl", "Low Saxon");
        localNames.put("ne", "Nepali");
        localNames.put("new", "Newari");
        localNames.put("ng", "Ndonga");
        localNames.put("nia", "Nias");
        localNames.put("nl", "Dutch");
        localNames.put("nn", "Norwegian Nynorsk");
        localNames.put("no", "Norwegian");
        localNames.put("nov", "Novial");
        localNames.put("nqo", "N\u2019Ko");
        localNames.put("nr", "South Ndebele");
        localNames.put("nrm", "Norman");
        localNames.put("nso", "Northern Sotho");
        localNames.put("nup", "Nupe");
        localNames.put("nv", "Navajo");
        localNames.put("ny", "Nyanja");
        localNames.put("oc", "Occitan");
        localNames.put("olo", "Livvi-Karelian");
        localNames.put("om", "Oromo");
        localNames.put("or", "Odia");
        localNames.put("os", "Ossetic");
        localNames.put("pa", "Punjabi");
        localNames.put("pag", "Pangasinan");
        localNames.put("pam", "Pampanga");
        localNames.put("pap", "Papiamento");
        localNames.put("pcd", "Picard");
        localNames.put("pcm", "Nigerian Pidgin");
        localNames.put("pdc", "Pennsylvania German");
        localNames.put("pfl", "Palatine German");
        localNames.put("pi", "Pali");
        localNames.put("pih", "Pitcairn-Norfolk");
        localNames.put("pl", "Polish");
        localNames.put("pms", "Piedmontese");
        localNames.put("pnb", "Western Punjabi");
        localNames.put("pnt", "Pontic");
        localNames.put("ps", "Pashto");
        localNames.put("pt", "Portuguese");
        localNames.put("pwn", "Paiwan");
        localNames.put("qu", "Quechua");
        localNames.put("rki", "Arakanese");
        localNames.put("rm", "Romansh");
        localNames.put("rmy", "Vlax Romani");
        localNames.put("rn", "Rundi");
        localNames.put("ro", "Romanian");
        localNames.put("roa-rup", "Aromanian");
        localNames.put("roa-tara", "Tarantino");
        localNames.put("rsk", "Pannonian Rusyn");
        localNames.put("ru", "Russian");
        localNames.put("rue", "Rusyn");
        localNames.put("rup", "Aromanian");
        localNames.put("rw", "Kinyarwanda");
        localNames.put("sa", "Sanskrit");
        localNames.put("sah", "Yakut");
        localNames.put("sat", "Santali");
        localNames.put("sc", "Sardinian");
        localNames.put("scn", "Sicilian");
        localNames.put("sco", "Scots");
        localNames.put("sd", "Sindhi");
        localNames.put("se", "Northern Sami");
        localNames.put("sg", "Sango");
        localNames.put("sgs", "Samogitian");
        localNames.put("sh", "Serbo-Croatian");
        localNames.put("shi", "Tachelhit");
        localNames.put("shn", "Shan");
        localNames.put("shy", "Shawiya");
        localNames.put("si", "Sinhala");
        localNames.put("simple", "Simple English");
        localNames.put("sk", "Slovak");
        localNames.put("skr", "Saraiki");
        localNames.put("sl", "Slovenian");
        localNames.put("sm", "Samoan");
        localNames.put("smn", "Inari Sami");
        localNames.put("sn", "Shona");
        localNames.put("so", "Somali");
        localNames.put("sq", "Albanian");
        localNames.put("sr", "Serbian");
        localNames.put("srn", "Sranan Tongo");
        localNames.put("ss", "Swati");
        localNames.put("st", "Southern Sotho");
        localNames.put("stq", "Saterland Frisian");
        localNames.put("su", "Sundanese");
        localNames.put("sv", "Swedish");
        localNames.put("sw", "Swahili");
        localNames.put("syl", "Sylheti");
        localNames.put("szl", "Silesian");
        localNames.put("szy", "Sakizaya");
        localNames.put("ta", "Tamil");
        localNames.put("tay", "Atayal");
        localNames.put("tcy", "Tulu");
        localNames.put("tdd", "Tai Nuea");
        localNames.put("te", "Telugu");
        localNames.put("tet", "Tetum");
        localNames.put("tg", "Tajik");
        localNames.put("th", "Thai");
        localNames.put("ti", "Tigrinya");
        localNames.put("tig", "Tigre");
        localNames.put("tk", "Turkmen");
        localNames.put("tl", "Tagalog");
        localNames.put("tly", "Talysh");
        localNames.put("tn", "Tswana");
        localNames.put("to", "Tongan");
        localNames.put("tok", "Toki Pona");
        localNames.put("tpi", "Tok Pisin");
        localNames.put("tr", "Turkish");
        localNames.put("trv", "Taroko");
        localNames.put("ts", "Tsonga");
        localNames.put("tt", "Tatar");
        localNames.put("tum", "Tumbuka");
        localNames.put("tw", "Twi");
        localNames.put("ty", "Tahitian");
        localNames.put("tyv", "Tuvinian");
        localNames.put("udm", "Udmurt");
        localNames.put("ug", "Uyghur");
        localNames.put("uk", "Ukrainian");
        localNames.put("ur", "Urdu");
        localNames.put("uz", "Uzbek");
        localNames.put("ve", "Venda");
        localNames.put("vec", "Venetian");
        localNames.put("vep", "Veps");
        localNames.put("vi", "Vietnamese");
        localNames.put("vls", "West Flemish");
        localNames.put("vo", "Volap\u00fck");
        localNames.put("vro", "V\u00f5ro");
        localNames.put("wa", "Walloon");
        localNames.put("war", "Waray");
        localNames.put("wo", "Wolof");
        localNames.put("wuu", "Wu");
        localNames.put("xal", "Kalmyk");
        localNames.put("xh", "Xhosa");
        localNames.put("xmf", "Mingrelian");
        localNames.put("yi", "Yiddish");
        localNames.put("yo", "Yoruba");
        localNames.put("yue", "Cantonese");
        localNames.put("za", "Zhuang");
        localNames.put("zea", "Zeelandic");
        localNames.put("zgh", "Standard Moroccan Tamazight");
        localNames.put("zh", "Chinese");
        localNames.put("zh-classical", "Literary Chinese");
        localNames.put("zh-min-nan", "Minnan");
        localNames.put("zh-yue", "Cantonese");
        localNames.put("zu", "Zulu");
    }

    public static void getAllLanguages()
    {
        String body = getUrlData("https://commons.wikimedia.org/w/api.php?action=sitematrix&smtype=language&format=json");
        System.out.println(body);
        String code = "";
        for (int i = 0; i < body.length() - 3; i++)
        {
            if(body.startsWith("{\"code", i))
            {
                code += "localNames.put(\"";
                for (int j = i + 9; j < i + 100; j++)
                {
                    if(body.charAt(j) == '\"') break;
                    code += body.charAt(j);
                }
                code += "\", ";
            }

            if(body.startsWith("localname", i))
            {
                code += "\"";
                for (int j = i + 12; j < i + 100; j++)
                {
                    if(body.charAt(j) == '\"') break;
                    code += body.charAt(j);
                }
                code += "\"); \n";
            }
        }
        System.out.println(code);
    }

    public static void checkLanguage()
    {
        String currentLanguage = "cu";
        String pageId = getRandomPageId(currentLanguage);
        System.out.println(pageId);
        String body = getWikiArticle(currentLanguage, pageId);
        System.out.println(pageId + " " + body);
        System.out.println(getData(currentLanguage));
    }

    public static void scrapper()
    {
        initializeLanguages();
        //checkLanguage();
        //scrapWikipedia(1);
        //System.out.println(getData("en"));
        //System.out.println(getData("en"));
        //System.out.println(getData("en"));
        //getAllLanguages();
        //scrapWikipedia(1);
    }

    public static void scrapWikipedia(int articlesPerLanguage)
    {
        if(isRunning)
        {
            WebScrapperGui.addLog("Already running");
            return;
        }
        isRunning = true;
        WebScrapperGui.addLog("Parametrization history");
        //wait(1000);
        for(int i = 0; i < articlesPerLanguage; i++)
        {
            for (int j = 0; j < languageCodes.size(); j++)
            {
                long startTime = System.currentTimeMillis();
                String body = getData(languageCodes.get(j));
                if(body.charAt(0) == '0' &&  body.charAt(1) == '#')
                {
                    continue;
                }
                System.out.println(body);
                Database.add_data(body);

                long endTime = System.currentTimeMillis();    // Koniec pomiaru
                long duration = endTime - startTime;

                WebScrapperGui.addLog(localNames.get(languageCodes.get(j)) + ": " + duration + " ms");
                wait(800);
            }
        }
        WebScrapperGui.addLog("Success");
        isRunning = false;
    }

    private static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static String getData(String language)
    {
        String pageId = getRandomPageId(language);
        System.out.println(pageId);
        String body = getWikiArticle(language, pageId);
        System.out.println(pageId + " " + body.length());
        String tmp = body;
        if(tmp.trim().isEmpty())
        {
            pageId = "0";
        }
        return parametrization(language, pageId, body);
    }

    private static String parametrization(String language, String pageId, String body)
    {
        Map<String, Integer> dict = new HashMap<>();
        body += "----------";
        String res = "";
        System.out.println(body);

        for (int i = 0; i < body.length(); i++)
        {
            if(body.codePointAt(i) == '\\' &&  body.codePointAt(i + 1) == 'n')
            {
                i+=1;
                continue;
            }

            if(body.codePointAt(i) == '\\' &&  body.codePointAt(i + 1) == 'u')
            {
                String unicode = body.substring(i, i + 6);
                int code = Integer.parseInt(unicode.substring(2), 16);
                char c = (char) code;
                if(Character.isLetter(c))
                {
                    char lowerCaseChar = Character.toLowerCase(c);
                    String s = "" + lowerCaseChar;
                    dict.merge(s, 1, Integer::sum);
                    i+=5;
                    continue;
                }
                else
                {
                    i+=5;
                    continue;
                }
            }

            if(body.codePointAt(i) < 65 || body.codePointAt(i) > 122 || (body.codePointAt(i) > 90 && body.codePointAt(i) < 97))
            {
                continue;
            }

            //res += (char)body.codePointAt(i);
            dict.merge(String.valueOf((char)body.codePointAt(i)).toLowerCase(), 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            res += entry.getKey() + ":" + entry.getValue() + ",";
        }

        return pageId + "#" + language + "#" + res;
    }

    private static String getRandomPageId(String language)
    {
        String body = getUrlData("https://" + language + ".wikipedia.org/w/api.php?action=query&list=random&rnnamespace=0&rnlimit=1&format=json");
        return extractText(body, "\"id\":", ",");
    }

    private static String getWikiArticle(String language, String pageId)
    {
        String body = getUrlData("https://" + language + ".wikipedia.org/w/api.php?action=query&prop=extracts&explaintext=1&pageids=" + pageId + "&format=json");
        return extractText(body, "\"extract\":\"", "\"}}}}");
    }

    public static String extractText(String str, String left, String right)
    {
        int start = str.indexOf(left) + left.length();
        int end = str.indexOf(right, start);
        if (end == -1)
        {
            return "0";
        }
        return str.substring(start, end);
    }


    public static String getUrlData(String url)
    {
        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
        catch (Exception e)
        {

            return "0 " + e.getMessage();
        }
    }
}
