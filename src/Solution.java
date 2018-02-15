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
        Search search = new Search(new JugState(new Jug(12, 0), new Jug(11, 0)));

        search.runSearch().ifPresent( stack -> {
            while(!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
        });
    }
}
