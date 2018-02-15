package engine;

/**
 * Created by harry on 15/02/2018.
 */

public class TreeNode {
    private TreeNode parent;
    private State state;

    public TreeNode() {

    }

    public TreeNode(State state) {
        this(null, state);
    }

    public TreeNode(TreeNode parent, State state) {
        this.parent = parent;
        this.state = state;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TreeNode) {
            TreeNode otherNode = (TreeNode) obj;
            return state.sameAs(otherNode.state);
        }
        else if(obj instanceof State) {
            return state.sameAs((State)obj);
        }

        return false;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
