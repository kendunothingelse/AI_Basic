package game_alphabeta_student;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		// Enter your code here
		int maxValue = Integer.MIN_VALUE;
		Node result = null;
		for (Node child : node.getChildren()) {
			int value = minValue(child);
			if (value > maxValue) {
				maxValue = value;
				result = child;
			}
		}
		System.out.println("Best move of " + node.getLabel() + " is: " + result.getValue());
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		// Enter your code here
        if (node.isTerminal()) {
            return node.getValue();
        }

        int result = Integer.MIN_VALUE;
        for (Node child : node.getChildren()) {
            result = Math.max(result, minValue(child));
        }

        return result;
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		// Enter your code here
        if (node.isTerminal()) {
            return node.getValue();
        }

        int result = Integer.MAX_VALUE;
        for (Node child : node.getChildren()) {
            result = Math.min(result, maxValue(child));
        }

        return result;
	}
}
