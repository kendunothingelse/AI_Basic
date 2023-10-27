package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearch implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return (int) (o1.getH() - o2.getH());
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
