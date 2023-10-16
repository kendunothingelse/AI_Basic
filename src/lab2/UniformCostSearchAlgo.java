package lab2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>((o1, o2) -> {
			return (int) (o1.getPathCost() - o2.getPathCost());
		});
		List<Node> explore = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explore.add(current);

			List<Edge> children = current.getChildren();
			for (Edge child : children) {
				Node n = child.getEnd();
				double newPathCost = current.getPathCost() + child.getWeight();
				if (!explore.contains(n) && !frontier.contains(n)) {
					frontier.add(n);// setParent
					n.setParent(current);

				} else if (frontier.contains(n) && (n.getPathCost() > newPathCost)) {
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

	@Override
	public Node execute(Node root, String goal, int limitedDepth) {
		// TODO Auto-generated method stub
		return null;
	}

}
