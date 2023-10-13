package lab2Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		List<Node> explore = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			} else {
				explore.add(current);
			}
			List<Edge> children = current.getChildren();
			for (Edge child : children) {
				Node n = child.getEnd();
				double newPathCost = current.getPathCost() + child.getWeight();
				if (!explore.contains(n) && !frontier.contains(n)) {
					frontier.add(n);// setParent
					n.setParent(current);

				} else if (frontier.contains(n)&& (n.getPathCost() > newPathCost)) {
					n.setPathCost(newPathCost);
					n.setParent(current);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub

		return null;
	}

}
