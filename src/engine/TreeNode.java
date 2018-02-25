package engine;

/**
 * Created by harry on 15/02/2018.
 */

public class TreeNode<T extends State> {
    private TreeNode<T> parent;
    private State<T> state;

    public TreeNode(State<T> state) {
        this(state, null);
    }

    public TreeNode(State<T> state, TreeNode<T> parent) {
        this.state = state;
        this.parent = parent;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public State<T> getState() {
        return state;
    }

    public void setState(State<T> state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TreeNode) {
            TreeNode<T> otherNode = (TreeNode<T>) obj; // this is risky, examine it later
            boolean value =  state.sameAs(otherNode.state.getState());
            return value;
        }

        return false;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
