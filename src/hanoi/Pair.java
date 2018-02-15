package hanoi;

/**
 * Created by harry on 15/02/2018.
 */

public class Pair {
    private Stack<Integer> p1;
    private Stack<Integer> p2;

    public Pair(Stack<Integer> p1, Stack<Integer> p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Stack<Integer> getP1() {
        return p1;
    }

    public Stack<Integer> getP2() {
        return p2;
    }
}
