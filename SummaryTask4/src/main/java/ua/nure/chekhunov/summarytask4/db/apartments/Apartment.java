package ua.nure.chekhunov.summarytask4.db.apartments;

/**
 * Simple Apartment entity with getters and setters.
 *
 * @author IC
 */
public class Apartment {
    private int aptId;
    private String number;
    private int places;
    private int classOfComfort;
    private int cost;
    private int description;

    public int getAptId() {
        return aptId;
    }

    public void setAptId(int aptId) {
        this.aptId = aptId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getClassOfComfort() {
        return classOfComfort;
    }

    public void setClassOfComfort(int classOfComfort) {
        this.classOfComfort = classOfComfort;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }
}
