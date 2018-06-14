package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GraphPlotter {

    private Graph graph;

    GraphPlotter(List<UserNode> points) {
        graph = new SingleGraph("Friend circle");
        // add all nodes to the graph.
        for (UserNode userNode : points) {
            Node newNode = graph.addNode(Long.toString(userNode.getId()));
            newNode.addAttribute("ui.label", userNode.getFirstName() + " " + userNode.getLastName() + " (" + userNode.getId() + ")");
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
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet",
                "graph {" +
                            "fill-color: #c0ffe3;" +
                        "}" +
                        "node {" +
                            "size: 6px;" +
                            "fill-color: #ddddff;" +
                            "text-color: #444;" +
                            "text-size: 10gu;" +
                            "z-index: 1;" +
                        "}" +
                        "node.highlight {" +
                            "size: 14px;" +
                            "fill-color: #222;" +
                            "text-color: #222;" +
                            "z-index: 2;" +
                        "}" +
                        "node.first{" +
                            "size: 18px;" +
                            "fill-color: #388E3C;" +
                            "text-color: #388E3C;" +
                            "z-index: 2;" +
                        "}" +
                        "node.last {" +
                            "size: 18px;" +
                            "fill-color: #d32f2f;" +
                            "text-color: #d32f2f;" +
                            "z-index: 2;" +
                        "}" +
                        "edge {" +
                            "shape: line;" +
                            "fill-color: #999;" +
                            "arrow-size: 3px, 2px;" +
                            "z-index: 0;" +
                        "}" +
                        "edge.highlight {" +
                            "fill-color: #222;" +
                        "}"
        );
        graph.display();
    }


    // Helper method to visualize routes in the graph
    void highlightRoute(List<List<UserNode>> ways){
        for (List<UserNode> points : ways) {
            for (UserNode userNode: points) {
                Node node = graph.getNode(Long.toString(userNode.getId()));
                node.addAttribute("ui.class", "highlight");

                if(node.getId().equals( String.valueOf(points.get(0).getId()) )){
                    node.addAttribute("ui.class", "first");
                }
                if(node.getId().equals( String.valueOf(points.get(points.size()-1).getId()) )){
                    node.addAttribute("ui.class", "last");
                }

                List<String> pointIds = new ArrayList<>();
                points.forEach((UserNode node1) -> pointIds.add(String.valueOf(node1.getId())));

                for(Edge edge: node.getEachEdge()){
                    if(edge.getNode0().getId().equals(node.getId())){
                        if (pointIds.contains(edge.getNode1().getId())){
                            edge.addAttribute("ui.class", "highlight");
                        }
                    }
                }
            }
        }
    }

    // Helper method to highlight a set of nodes plus a special one
    void highlightNodes(Set<UserNode> nodes, UserNode special) {
        for (UserNode n : nodes) {
            Node node = graph.getNode(Long.toString(n.getId()));
            node.addAttribute("ui.class", "highlight");
        }
        Node node = graph.getNode(Long.toString(special.getId()));
        node.addAttribute("ui.class", "first");
    }
}
