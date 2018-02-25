package jug;

import engine.State;

import java.util.ArrayList;

/**
 * Created by harry on 15/02/2018.
 */

public class JugState implements State<JugState> {
    private Jug container1;
    private Jug container2;

    public JugState(Jug container1, Jug container2) {
        this.container1 = container1;
        this.container2 = container2;
    }

    @Override
    public ArrayList<State<JugState>> getSuccessors() {
        ArrayList<State<JugState>> successors = new ArrayList<>();

        if(!container1.isEmpty()) {
            container1.fillInto(container2).ifPresent(successors::add);

            successors.add(new Builder()
                    .setContainer1(container1.empty())
                    .setContainer2(container2).build());
        }

        if(!container1.isFull()) {
            successors.add(new Builder()
                    .setContainer1(container1.fill())
                    .setContainer2(container2).build());
        }

        if(!container2.isEmpty()) {
            container2.fillInto(container1).ifPresent(successors::add);

            successors.add(new Builder()
                    .setContainer1(container1)
                    .setContainer2(container2.empty()).build());
        }

        if(!container2.isFull()) {
            successors.add(new Builder()
                    .setContainer1(container1)
                    .setContainer2(container2.fill()).build());
        }

        return successors;
    }

    @Override
    public boolean sameAs(JugState state) {
        return container1.equals(state.container1) && container2.equals(state.container2);
    }

    public Jug getContainer1() {
        return container1;
    }

    public Jug getContainer2() {
        return container2;
    }

    @Override
    public String toString() {
        return "(" + container1 + " , " + container2 + ")";
    }

    public static class Builder {
        private Jug container1;
        private Jug container2;

        public Builder setContainer1(Jug container1) {
            this.container1 = container1;
            return this;
        }

        public Builder setContainer2(Jug container2) {
            this.container2 = container2;
            return this;
        }

        public JugState build() {
            return new JugState(container1, container2);
        }
    }

    @Override
    public JugState getState() {
        return this;
    }
}
