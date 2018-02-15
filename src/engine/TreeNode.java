package engine;

/**
 * Created by harry on 15/02/2018.
 */

public class TreeNode {
    private TreeNode parent;
    private State state;

    public TreeNode(State state) {
        this(state, null);
    }

    public TreeNode(State state, TreeNode parent) {
        this.state = state;
        this.parent = parent;

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

        return false;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
