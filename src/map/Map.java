package map;

/**
 * Created by harry on 25/02/2018.
 */

public class Map {
    private City startCity;
    private City goalCity;

    public Map(City startCity, City goalCity) {
        this.startCity = startCity;
        this.goalCity = goalCity;
    }

    public City getStartCity() {
        return startCity;
    }

    public City getGoalCity() {
        return goalCity;
    }
}
