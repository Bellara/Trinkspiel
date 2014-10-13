package de.lauverngers.objects;

public class Challenge {

    private long id;
    private String title;
    private String text;
    private Integer points;
    private Integer roundCount;
    private String action;

    public Challenge(long id, String title, String text, Integer points, Integer roundCount, String action) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.points = points;
        this.roundCount = roundCount;
        this.action = action;
    }
    public void reduceRoundCounter(){
        if(roundCount > 0){
            roundCount--;
        }
    }

    public boolean isActive(){
        return roundCount > 0;
    }

    public void setCredits(int credits){
        this.points = credits;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Integer getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
