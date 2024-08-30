public class Main {
    public static void main(String[] args) {
        // Create an instance of the APIRequest class to interact with the Riot Games API
        APIRequest apiRequest = new APIRequest();

        try {
            // Fetch summoner information using the summoner's name
            Summoner summoner = apiRequest.getSummonerInfo("Faker"); // Replace with any valid summoner name
            // Print basic summoner details
            System.out.println("Name: " + summoner.getName());
            System.out.println("Level: " + summoner.getSummonerLevel());

            // Fetch match history using the account ID obtained from the summoner's info
            Match[] matches = apiRequest.getMatchHistory(summoner.getAccountId());
            // Loop through and print details of each match
            for (Match match : matches) {
                System.out.println("Match ID: " + match.getGameId());
                System.out.println("Champion: " + match.getChampion());
                System.out.println("Role: " + match.getRole());
                // You can add more prints to show other details like champion, role, etc.
            }
        } catch (Exception e) {
            // If anything goes wrong, print the stack trace to debug
            e.printStackTrace();
        }
    }
}

