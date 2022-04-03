package dao.simple;

public class Developer {

    private String developerName;
    private int developerId;

    public Developer(String developerName, int developerId) {
        this.developerName = developerName;
        this.developerId = developerId;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }
}
