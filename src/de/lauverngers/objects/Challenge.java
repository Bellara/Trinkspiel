package de.lauverngers.objects;

public class Challenge {

    private long id;
    private String title;
    private String text;
    private Integer points;
    private Integer lifetime;
    private String action;

    public Challenge(long id, String title, String text, Integer points, Integer lifetime, String action) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.points = points;
        this.lifetime = lifetime;
        this.action = action;
    }
    public void reduceActiveTime(){
        if(lifetime > 0){
            lifetime--;
        }
    }

    public boolean isActive(){
        return lifetime > 0;
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

    public Integer getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
