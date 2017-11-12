package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.List;


public class GraphPlotter {

    public static void plot(List<UserNode> points) {
        Graph graph = new SingleGraph("Friend circle");
        // add all nodes to the graph. IDs are filled automatically by the DB at this point.
        List<UserNode> allEdges = new ArrayList<>();
        for (UserNode userNode : points) {
            Node newNode = graph.addNode(Long.toString(userNode.getId()));
            newNode.addAttribute("ui.label", userNode.getFirstName() + " " + userNode.getLastName());
        }

        for (UserNode userNode : points) {
            for (UserNode n1 : userNode.getFriends()) {
                String leftKey = Long.toString(userNode.getId());
                String rightKey = Long.toString(n1.getId());
                String edgeId = leftKey + "-" + rightKey;
                if (graph.getEdge(edgeId) == null && graph.getEdge(rightKey + "-" + leftKey) == null) {
                    graph.addEdge(leftKey + "-" + rightKey, leftKey, rightKey);
                }
            }
        }
        graph.display();

    }
}
