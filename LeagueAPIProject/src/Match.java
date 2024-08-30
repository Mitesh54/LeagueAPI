public class Match {
    private final long gameId;
    private final int champion;
    private final String role;

    public Match(long gameId, int champion, String role) {
        this.gameId = gameId;
        this.champion = champion;
        this.role = role;
    }

    public long getGameId() {
        return gameId;
    }

    public int getChampion() {
        return champion;
    }

    public String getRole() {
        return role;
    }
}
