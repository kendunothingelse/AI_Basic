package lab2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explore = new ArrayList<Node>();// explore set to store the node
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node parent = frontier.poll();// remove the leaf node in frontier and make a parent node
			if (parent.getLabel().contains(goal)) {
				return parent;
			}
			explore.add(parent);
			List<Node> children = parent.getChildrenNodes();
			for (Node child : children) {
				if (!frontier.contains(child) && !explore.contains(child)) {
					frontier.add(child);
					child.setParent(parent);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explore = new ArrayList<Node>();// explore set to store the node
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node parent = frontier.poll();// remove the leaf node in frontier and make a parent node
			if (parent.getLabel().contains(goal)) {
				return parent;
			}
			explore.add(parent);
			List<Node> children = parent.getChildrenNodes();
			for (Node child : children) {
				if (!frontier.contains(child) && !explore.contains(child)) {
					frontier.add(child);
					child.setParent(parent);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String goal, int limitedDepth) {
		// TODO Auto-generated method stub
		return null;
	}

}
