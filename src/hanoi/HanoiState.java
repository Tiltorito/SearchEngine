package hanoi;

import engine.State;

import java.util.ArrayList;

/**
 * Created by harry on 15/02/2018.
 */

public class HanoiState implements State<HanoiState> {
    private Stack<Integer> A;
    private Stack<Integer> B;
    private Stack<Integer> C;

    public HanoiState(Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
        A = a;
        B = b;
        C = c;
    }

    @Override
    public ArrayList<State<HanoiState>> getSuccessors() {
        ArrayList<State<HanoiState>> successors = new ArrayList<>();

        if(validMove(A, B)) {
            Pair p = transfer(A,B);
            successors.add(new HanoiState(p.getP1(), p.getP2(), C));
        }
        if(validMove(A, C)) {
            Pair p = transfer(A,C);
            successors.add(new HanoiState(p.getP1(), B, p.getP2()));
        }

        if(validMove(B, A)) {
            Pair p = transfer(B, A);
            successors.add(new HanoiState(p.getP2(), p.getP1(), C));
        }

        if(validMove(B, C)) {
            Pair p = transfer(B, C);
            successors.add(new HanoiState(A, p.getP1(), p.getP2()));
        }

        if(validMove(C, A)) {
            Pair p = transfer(C, A);
            successors.add(new HanoiState(p.getP2(), B, p.getP1()));
        }

        if(validMove(C, B)) {
            Pair p = transfer(C, B);
            successors.add(new HanoiState(A, p.getP2(), p.getP1()));
        }

        return successors;
    }

    @Override
    public boolean sameAs(State state) {
        if(state instanceof HanoiState) {
            HanoiState hanoiState = (HanoiState) state;
            return A.equals(hanoiState.A) && B.equals(hanoiState.B) && C.equals(hanoiState.C);
        }

        return false;
    }


    private boolean validMove(Stack<Integer> from, Stack<Integer> to) {
        if(from.isEmpty()) {
            return false;
        }

        if(to.isEmpty() || from.peek() < to.peek()) {
            return true;
        }

        return false;
    }

    public Pair transfer(Stack<Integer> from, Stack<Integer> to) {
        Stack<Integer> fromCopy = (Stack<Integer>) from.clone();
        Stack<Integer> toCopy = (Stack<Integer>) to.clone();

        toCopy.push(fromCopy.pop());

        return new Pair(fromCopy, toCopy);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder().append('\n');
        str.append("A -> ").append(A.toString()).append('\n');
        str.append("B -> ").append(B.toString()).append('\n');
        str.append("C -> ").append(C.toString());

        return str.toString();
    }

    @Override
    public HanoiState getState() {
        return this;
    }
}
