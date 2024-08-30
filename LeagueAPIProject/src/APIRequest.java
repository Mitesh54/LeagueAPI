import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APIRequest {
    private static final String API_KEY = "RGAPI-6b314847-a571-45b6-ad87-589d363b2ece";
    private static final String PLATFORM_BASE_URL = "https://na1.api.riotgames.com/lol/";
    private static final String REGIONAL_BASE_URL = "https://americas.api.riotgames.com/lol/";

    private OkHttpClient client;

    public APIRequest() {
        client = new OkHttpClient();
    }

    // Method to send a GET request and handle common API errors
    private String sendGetRequest(String baseUrl, String endpoint) throws Exception {
        String url = baseUrl + endpoint + "?api_key=" + API_KEY;

        // Debugging step: Print the full URL
        System.out.println("Request URL: " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int responseCode = response.code();
            if (responseCode == 400) {
                throw new IOException("Error 400: Bad Request - Invalid parameters or endpoint");
            } else if (responseCode == 404) {
                throw new IOException("Error 404: Resource not found");
            } else if (responseCode == 429) {
                throw new IOException("Error 429: Rate limit exceeded. Try again later.");
            } else if (!response.isSuccessful()) {
                throw new IOException("Error " + responseCode + ": " + response.message() + " - URL: " + url);
            }
            return response.body().string();
        }
    }

    // Method to get Summoner information by name using platform base URL
    public Summoner getSummonerInfo(String summonerName) throws Exception {
        String encodedSummonerName = URLEncoder.encode(summonerName, StandardCharsets.UTF_8.toString());
        String endpoint = "summoner/v4/summoners/by-name/" + encodedSummonerName;
        String jsonResponse = sendGetRequest(PLATFORM_BASE_URL, endpoint);

        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Summoner.class);
    }

    // Method to fetch match history by account ID using regional base URL
    public Match[] getMatchHistory(String accountId) throws Exception {
        String endpoint = "match/v4/matchlists/by-account/" + accountId;
        String jsonResponse = sendGetRequest(REGIONAL_BASE_URL, endpoint);

        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Match[].class);
    }
}
