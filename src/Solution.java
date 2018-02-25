import engine.Search;
import engine.State;
import hanoi.HanoiState;
import hanoi.Stack;
import jug.Jug;
import jug.JugState;
import map.City;
import map.Map;
import map.MapState;

/**
 * Created by harry on 15/02/2018.
 */

public class Solution {
    public static void main(String[] args) {
        City S = new City("S");
        City A = new City("A");
        City B = new City("B");
        City C = new City("C");
        City D = new City("D");
        City E = new City("E");
        City G = new City("G");


        S.connectWith(A, B);
        B.connectWith(C, A);
        C.connectWith(E);
        A.connectWith(D);
        D.connectWith(G);

        //System.out.println(S.getAvailableCities().get(0).getAvailableCities().toString());


        Map map = new Map(S, G);

        MapState initState = new MapState(map, S);

        Search<MapState> search = new Search<>(initState, state -> state.getCity().equals(state.getMap().getGoalCity()));

        search.runSearch().ifPresent(System.out::println);
    }
}
