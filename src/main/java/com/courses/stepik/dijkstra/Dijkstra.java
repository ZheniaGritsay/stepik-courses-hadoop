package com.courses.stepik.dijkstra;

import com.courses.stepik.Main;
import com.courses.stepik.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dijkstra implements Task {
    private static final int INF = Integer.MAX_VALUE;

    @Override
    public void exec() {
        Object[] data = readGraphData();
        Map<Integer, Integer> vertexes = dijkstra((Set<Integer>) data[0], (Integer) data[2], (int[][]) data[1]);

        if (data[3] != null && vertexes.get((Integer) data[3]) != INF)
            System.out.println(vertexes.get((Integer) data[3]));
        else
            System.out.println(-1);
    }

    private static Map<Integer, Integer> dijkstra(Set<Integer> vertexes, Integer start, int[][] edges) {
        HashMap<Integer, Integer> dist = new HashMap<>();
        boolean startPresent = false;
        for (Integer v : vertexes) {
            if (v.equals(start)) {
                dist.put(v, 0);
                startPresent = true;
                continue;
            }
            dist.put(v, INF);
        }

        if (!startPresent) {
            dist.put(start, INF);
            return dist;
        }

        Comparator<Map.Entry<Integer, Integer>> c = Comparator.comparing(Map.Entry::getValue);
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(c);
        pq.addAll(dist.entrySet());

        while (!pq.isEmpty()) {
            int u = pq.poll().getKey();
            if (dist.get(u) != INF) {
                for (Integer v : getOutVertexes(u, edges)) {
                    if (dist.get(v) > dist.get(u) + edges[u][v]) {
                        dist.put(v, dist.get(u) + edges[u][v]);
                    }
                }
            }

            PriorityQueue<Map.Entry<Integer, Integer>> temp = pq;
            pq = new PriorityQueue<>(c);
            pq.addAll(temp);
//            refreshQueue(pq, c);
        }

        return dist;
    }

    private static List<Integer> getOutVertexes(int v, int[][] weights) {
        List<Integer> vertexes = new ArrayList<>();
        for (int i = 0; i < weights[v].length; i++) {
            if (weights[v][i] != 0)
                vertexes.add(i);
        }

        return vertexes;
    }

    private static <T> void refreshQueue(PriorityQueue<T> pq, Comparator<T> c) {
        PriorityQueue<T> temp = pq;
        pq = new PriorityQueue<>(c);
        pq.addAll(temp);
    }

    private static Object[] readGraphData() {
        int vNum = 0;
        int wNum = 0;
        int counter = 0;
        int srcV = 0;
        int destV = 0;
        Set<Integer> vertexes = new HashSet<>();
        int[][] weights = null;
        try (FileReader fr = new FileReader(new File(Main.class.getClassLoader().getResource("graph.txt").getPath()));
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(" ");
                if (vNum == 0 && wNum == 0) {
                    vNum = Integer.parseInt(parsedLine[0]);
                    wNum = Integer.parseInt(parsedLine[1]);
                    weights = new int[vNum][vNum];
                    continue;
                }

                if (counter <= wNum - 1) {
                    int v = Integer.parseInt(parsedLine[0]) - 1;
                    int u = Integer.parseInt(parsedLine[1]) - 1;
                    int w = Integer.parseInt(parsedLine[2]);
                    weights[v][u] = w;
                    vertexes.add(v);
                    vertexes.add(u);
                    counter++;
                    continue;
                }

                srcV = Integer.parseInt(parsedLine[0]) - 1;
                destV = Integer.parseInt(parsedLine[1]) - 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Object[]{vertexes, weights, srcV, destV};
    }
}
