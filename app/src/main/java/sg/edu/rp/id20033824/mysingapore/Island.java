package sg.edu.rp.id20033824.mysingapore;

import java.io.Serializable;

public class Island implements Serializable {
    private int id;
    private String name;
    private String description;
    private int square;
    private int stars;

    public Island(int id, String name, String description, int square, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.square = square;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
