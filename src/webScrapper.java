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

        String pageId = getRandomPageId(currentLanguage);
        String body = getWikiArticle(currentLanguage, pageId);

        System.out.println(pageId + " " + body);
        parametrization(currentLanguage, pageId, body);
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
            
            //region check chars
            if(body.codePointAt(i) == '=' ||
               body.codePointAt(i) == ':' ||
               body.codePointAt(i) == ' ' ||
               body.codePointAt(i) == ')' ||
               body.codePointAt(i) == '(' ||
               body.codePointAt(i) == ']' ||
               body.codePointAt(i) == '[' ||
               body.codePointAt(i) == '}' ||
               body.codePointAt(i) == '{' ||
               body.codePointAt(i) == '\"' ||
               body.codePointAt(i) == '\'' ||
               body.codePointAt(i) == '|' ||
               body.codePointAt(i) == '1' ||
               body.codePointAt(i) == '2' ||
               body.codePointAt(i) == '3' ||
               body.codePointAt(i) == '4' ||
               body.codePointAt(i) == '5' ||
               body.codePointAt(i) == '6' ||
               body.codePointAt(i) == '7' ||
               body.codePointAt(i) == '8' ||
               body.codePointAt(i) == '9' ||
               body.codePointAt(i) == '0' ||
               body.codePointAt(i) == '*' ||
               body.codePointAt(i) == '-' ||
               body.codePointAt(i) == '_' ||
               body.codePointAt(i) == '&' ||
               body.codePointAt(i) == '^' ||
               body.codePointAt(i) == '%' ||
               body.codePointAt(i) == '$' ||
               body.codePointAt(i) == '#' ||
               body.codePointAt(i) == '@' ||
               body.codePointAt(i) == '!' ||
               body.codePointAt(i) == '`' ||
               body.codePointAt(i) == '~' ||
               body.codePointAt(i) == '<' ||
               body.codePointAt(i) == '>' ||
               body.codePointAt(i) == '+' ||
               body.codePointAt(i) == ';' ||
               body.codePointAt(i) == '?' ||
               body.codePointAt(i) == '.' ||
               body.codePointAt(i) == ',')
            {
                continue;
            }
            //endregion

            res += (char)body.codePointAt(i);
        }

        System.out.println(pageId + " " + res);

        return language + "#" + pageId + "#";
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
