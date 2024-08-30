public class Summoner {
    private final String accountId;
    private final String name;
    private final int summonerLevel;

    public Summoner(String accountId, String name, int summonerLevel) {
        this.accountId = accountId;

        this.name = name;
        this.summonerLevel = summonerLevel;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

}

