public class Player {
    private String name;
    private int team;

    public Player(String name) {
        this.name = name;
        this.team = 0;
    }

    public Player(String name, int team) {
        this.name = name;
        this.team = team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getTeam() {
        return team;
    }
}
