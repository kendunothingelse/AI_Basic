package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Depth_LimitedSearch implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node execute(Node root, String goal, int limitedDepth) {
		Stack<Node> frontier = new Stack();
		List<Node> explored = new ArrayList<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal)) {
				return current;
			} else {
				if (current.getDepth() < limitedDepth) {
					List<Node> children = current.getChildrenNodes();
					for (Node node : children) {
						if (!explored.contains(node) && !frontier.contains(node)) {
							frontier.add(node);
							node.setParent(current);
							node.setDepth(current.getDepth() + 1);
						}
					}
				}
			}
		}
		return null;
	}
}
