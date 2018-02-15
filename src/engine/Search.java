package engine;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by harry on 15/02/2018.
 */

public class Search {
    private ArrayDeque<TreeNode> open = new ArrayDeque<>();
    private ArrayDeque<TreeNode> closed = new ArrayDeque<>();

    public Search(State initState) {
        open.addLast(new TreeNode(initState));
    }

    public Optional<ArrayDeque<State>> runSearch() {
        while(!open.isEmpty()) {
            TreeNode node = pickNode();

            if(node.getState().isTheGoal()) {
                ArrayDeque<State> stack = new ArrayDeque<>();

                while(node != null) {
                    stack.push(node.getState());
                    node = node.getParent();
                }

                return Optional.of(stack);
            }

            closed.add(node);

            expand(node);
        }

        return Optional.empty();
    }

    private TreeNode pickNode() {
        return open.removeLast();
    }

    private void expand(TreeNode node) {
        open.addAll(node.getState().getSuccessors().stream()
                .filter(state -> !open.contains(new TreeNode(state)) && !closed.contains(new TreeNode(state)))
                .map(state -> new TreeNode(state, node))
                .collect(Collectors.toList()));
    }
}
