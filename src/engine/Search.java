package engine;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by harry on 15/02/2018.
 */

public class Search<T extends State> {
    private ArrayDeque<TreeNode<T>> open = new ArrayDeque<>();
    private ArrayDeque<TreeNode<T>> closed = new ArrayDeque<>();
    private Predicate<T> predicate;
    private Result<State<T>> result = new Result<>();

    public Search(State<T> initState, Predicate<T> predicate) {
        open.addLast(new TreeNode<T>(initState));
        this.predicate = predicate;
    }

    public Result<State<T>> runSearch() {
        while(!open.isEmpty()) {
            TreeNode<T> node = pickNode();

            if(predicate.test(node.getState().getState())) {
                ArrayDeque<State<T>> stack = new ArrayDeque<>();

                while(node != null) {
                    stack.push(node.getState());
                    node = node.getParent();
                }

                result.setFound(true);
                result.setStack(stack);
                return result;
            }

            closed.add(node);
            result.increaseNodesAddedOnClose();

            Collection<TreeNode<T>> list = expand(node);
            result.increaseExpandedNodes();

            open.addAll(list);
            result.increaseNodesAddedOnOpen(list.size());
        }

        return result;
    }

    protected TreeNode<T> pickNode() {
        return open.removeLast();
    }

    protected Collection<TreeNode<T>> expand(TreeNode<T> node) {
        return node.getState().getSuccessors().stream()
                .filter(state -> !open.contains(new TreeNode<T>(state)) && !closed.contains(new TreeNode<T>(state)))
                .map(state -> new TreeNode<T>(state, node))
                .collect(Collectors.toList());
    }

    public class Result<T> {
        private int expandedNodes;
        private int nodesAddedOnOpen;
        private int nodesAddedOnClose;
        private boolean found;
        private ArrayDeque<T> stack;

        public int getExpandedNodes() {
            return expandedNodes;
        }

        public void setExpandedNodes(int expandedNodes) {
            this.expandedNodes = expandedNodes;
        }

        public int getNodesAddedOnOpen() {
            return nodesAddedOnOpen;
        }

        public void setNodesAddedOnOpen(int nodesAddedOnOpen) {
            this.nodesAddedOnOpen = nodesAddedOnOpen;
        }

        public int getNodesAddedOnClosed() {
            return nodesAddedOnClose;
        }

        public void setNodesAddedOnClose(int nodesAddedOnClosed) {
            this.nodesAddedOnClose = nodesAddedOnClosed;
        }

        public boolean isFound() {
            return found;
        }

        public void setFound(boolean found) {
            this.found = found;
        }

        public ArrayDeque<T> getStack() {
            return stack;
        }

        public void setStack(ArrayDeque<T> stack) {
            this.stack = stack;
        }

        public void increaseExpandedNodes() {
            expandedNodes++;
        }

        public void increaseNodesAddedOnOpen(int value) {
            nodesAddedOnOpen += value;
        }

        public void increaseNodesAddedOnClose() {
            nodesAddedOnClose++;
        }

        public void ifPresent(Consumer<Result<T>> consumer) {
            if(found)
                consumer.accept(this);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            builder.append("Result found: ").append(found).append('\n');
            builder.append("Nodes expanded: ").append(expandedNodes).append('\n');
            builder.append("Nodes added on open list: ").append(nodesAddedOnOpen).append('\n');
            builder.append("Nodes added on close list: ").append(nodesAddedOnClose).append('\n');

            return builder.toString();
        }
    }
}
