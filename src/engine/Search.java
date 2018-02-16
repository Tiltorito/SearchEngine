package engine;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by harry on 15/02/2018.
 */

public class Search<T extends State> {
    private ArrayDeque<TreeNode<T>> open = new ArrayDeque<>();
    private ArrayDeque<TreeNode<T>> closed = new ArrayDeque<>();
    private Predicate<T> predicate;

    public Search(State<T> initState, Predicate<T> predicate) {
        open.addLast(new TreeNode<T>(initState));
        this.predicate = predicate;
    }

    public Optional<ArrayDeque<State<T>>> runSearch() {
        while(!open.isEmpty()) {
            TreeNode<T> node = pickNode();

            if(predicate.test(node.getState().getState())) {
                ArrayDeque<State<T>> stack = new ArrayDeque<>();

                while(node != null) {
                    stack.push(node.getState());
                    node = node.getParent();
                }

                return Optional.of(stack);
            }

            closed.add(node);

            open.addAll(expand(node));
        }

        return Optional.empty();
    }

    private TreeNode<T> pickNode() {
        return open.removeLast();
    }

    private Collection<TreeNode<T>> expand(TreeNode<T> node) {
        return node.getState().getSuccessors().stream()
                .filter(state -> !open.contains(new TreeNode<T>(state)) && !closed.contains(new TreeNode<T>(state)))
                .map(state -> new TreeNode<T>(state, node))
                .collect(Collectors.toList());
    }

}
