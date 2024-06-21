package hw3;

public class ScoreM {
    private String name;
    private int credit;
    private int score;

    public ScoreM(String name, int credit, int score) {
        this.name = name;
        this.credit = credit;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int additionalCredit, int additionalScore) {
        double totalPoints = this.score * this.credit + additionalScore * additionalCredit;
        this.credit += additionalCredit;
        this.score = (int) Math.round(totalPoints / this.credit);
    }

    @Override
    public String toString() {
        return String.format("姓名: %s, 學分: %d, 成績: %d", name, credit, score);
    }
}
