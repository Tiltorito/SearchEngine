package map;

import engine.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harry on 25/02/2018.
 */

public class MapState implements State<MapState> {
    private Map map;
    private City city;

    public MapState(Map map, City city) {
        this.map = map;
        this.city = city;
    }

    public Map getMap() {
        return map;
    }

    public City getCity() {
        return city;
    }

    @Override
    public ArrayList<State<MapState>> getSuccessors() {
        List<City> cities = city.getAvailableCities();
        ArrayList<State<MapState>> list = new ArrayList<>();

        for(City city : cities) {
            list.add(new MapState(map, city));
        }

        return list;
    }

    @Override
    public boolean sameAs(MapState state) {
        return map == state.map && city.equals(state.city);
    }

    @Override
    public MapState getState() {
        return this;
    }

    @Override
    public String toString() {
        return city.toString();
    }
}
