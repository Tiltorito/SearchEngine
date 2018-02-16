import engine.Search;
import hanoi.HanoiState;
import hanoi.Stack;
import jug.Jug;
import jug.JugState;

/**
 * Created by harry on 15/02/2018.
 */

public class Solution {
    public static void main(String[] args) {
        Search<JugState> search = new Search<JugState>(new JugState(new Jug(12, 0), new Jug(11, 0))
                , state -> state.getContainer1().getCurrentCapacity() == 6|| state.getContainer2().getCurrentCapacity() == 6);

        search.runSearch().ifPresent( stack -> {
            while(!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
        });

    }
}
