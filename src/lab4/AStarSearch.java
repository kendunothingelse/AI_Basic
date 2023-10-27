package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class AStarSearch implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				Double h1 = o1.getH();
				Double h2 = o2.getH();
				int result = h1.compareTo(h2);
				if (result == 0)
					return o1.getLabel().compareTo(o2.getLabel());
				else
					return result;
			}
		});
		List<Node> explore = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node parent = frontier.poll();
			if (parent.getLabel().contains(goal)) {
				return parent;
			} else {
				explore.add(parent);
				List<Edge> children = new ArrayList<Edge>();
				for (Edge edge : children) {
					Node child = edge.getEnd();
					if (!frontier.contains(child) && !explore.contains(child)) {
						frontier.add(child);
						child.setG(parent.getG() + edge.getWeight());
					} else if (frontier.contains(child) && (child.getG() > (parent.getG() + edge.getWeight()))) {
						frontier.remove(child);
						frontier.add(child);
					}

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
