package utils.shuffle_utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utils.ShuffleGraph;

public final class GraphWriter {
    ShuffleGraph graph;

    public GraphWriter(ShuffleGraph graph) {
        this.graph = graph;
    }

    public void write(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        write(writer);
    }

    public void write(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        write(writer);
    }

    public void write(FileWriter writer) throws IOException {
        Map<Node, Integer> nodeMap = new HashMap<>();
        Map<Connection, Integer> connectionMap = new HashMap<>();
        for (int i = 0; i < graph.nodes.size(); i++) {
            Node node = graph.nodes.get(i);
            nodeMap.put(node, i);
        }
        nodeMap.put(null, -1);
        for (int i = 0; i < graph.connections.size(); i++) {
            Connection conn = graph.connections.get(i);
            connectionMap.put(conn, i);
        }
        connectionMap.put(null, -1);

        for (int i = 1; i < graph.nodes.size(); i++) {
            Node node = graph.nodes.get(i);
            writer.write("N ");
            if (node.getValue().isDistribution()) {
                writer.write("true ");
                writer.write(node.getValue().getDistribution().name() + " ");
            } else {
                writer.write("false ");
                writer.write(node.getValue().getShuffle().name() + " ");
            }
            writer.write(node.x + " ");
            writer.write(node.y + " ");
            writer.write(connectionMap.get(node.preConnection) + " ");
            writer.write(connectionMap.get(node.postConnection) + "\n");
        }

        for (int i = 0; i < graph.connections.size(); i++) {
            Connection conn = graph.connections.get(i);
            writer.write("C ");
            writer.write(nodeMap.get(conn.from) + " ");
            writer.write(nodeMap.get(conn.to) + "\n");
        }

        writer.close();
    }
}
