package map;

import java.util.ArrayList;

/**
 * Created by harry on 25/02/2018.
 */

public class City {
    private String name;
    private ArrayList<City> availableCities = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public void connectWith(City... cities) {
        for(City city : cities) {
            if(!availableCities.contains(city) && !city.availableCities.contains(this)) {
                availableCities.add(city);
                city.availableCities.add(this);
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<City> getAvailableCities() {
        return availableCities;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof City) {
            City other = (City)obj;
            return name.equals(other.name);
        }

        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
