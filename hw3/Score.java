package hw3;

class Score {
    String name;
    int credit;
    int avg;
    Score next;

    public Score(String name, int credit, int avg) {
        this.name = name;
        this.credit = credit;
        this.avg = avg;
        this.next = null;
    }
}
