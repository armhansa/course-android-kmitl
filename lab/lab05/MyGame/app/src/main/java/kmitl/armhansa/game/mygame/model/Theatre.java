package kmitl.armhansa.game.mygame.model;

public class Theatre {
    protected int number;
    protected String name;
    protected String situation;
    protected String detail;
    protected long income;

    public Theatre(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public Theatre(int number, String name, String situation, String detail, long income) {
        this.number = number;
        this.name = name;
        this.situation = situation;
        this.detail = detail;
        this.income = income;
    }

    public void changeName(String name) { this.name = name; }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSituation() {
        return situation;
    }

    public String getDetail() {
        return detail;
    }

    public long getIncome() {
        return income;
    }

}
