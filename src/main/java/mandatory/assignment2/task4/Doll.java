package mandatory.assignment2.task4;

public class Doll {
    private int id;
    private int qualityScore;
    private boolean isPerfect = false;
    private boolean isPainted = false;

    public Doll(int id, int qualityScore) {
        this.id = id;
        this.qualityScore = qualityScore;
    }

    // TODO: implement getters, setters as needed
    public int getQualityScore() {
        return qualityScore;
    }

    public void setPerfect() {
        this.isPerfect = true;
    }

    public void setPainted() {
        this.isPainted = true;
    }

}