import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class webScrapper
{
    public static void scrapper()
    {
        String currentLanguage = "en";

        System.out.println(getData(currentLanguage));
    }

    public static String getData(String language)
    {
        String pageId = getRandomPageId(language);
        String body = getWikiArticle(language, pageId);
        return parametrization(language, pageId, body);
    }

    private static String parametrization(String language, String pageId, String body)
    {
        Map<String, Integer> dict = new HashMap<>();

        String res = "";

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
                    dict.merge(unicode, 1, Integer::sum);
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

        return language + "#" + pageId + "#" + res;
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
