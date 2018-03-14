package com.courses.stepik.dijkstra;

import com.courses.stepik.Main;
import com.courses.stepik.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BFSMapReduce implements Task {
//    private static final double INF = Double.POSITIVE_INFINITY;

    @Override
    public void exec() {
        try (FileReader fr = new FileReader(new File(Main.class.getClassLoader().getResource("bfs_graph.txt").getPath()));
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            String currentV = null;
            String minDist = "INF";
            ArrayList<String> outV = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t");
                if (currentV != null && !currentV.equals(splitted[0])) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(currentV).append("\t").append(minDist)
                            .append("\t").append("{");

                    for (int i = 0; i < outV.size(); i++) {
                        sb.append(outV.get(i));
                        if (i != outV.size() - 1)
                            sb.append(",");
                    }

                    sb.append("}");

                    System.out.println(sb.toString());

                    currentV = splitted[0];
                    continue;
                }

                String[] vertexes = getVertexes(splitted[2]);

                minDist = getMinDist(minDist, splitted[1]);
                outV.addAll(Arrays.asList(vertexes));

                currentV = splitted[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getVertexes(String data) {
        String tmp = data.substring(1, data.length() - 1);
        return tmp.trim().isEmpty() ? new String[0] : tmp.split(",");
    }

    private String getMinDist(String dist, String newDist) {
        String min;
        if (newDist.equals("INF")) {
            return dist;
        } else if (dist.equals("INF")) {
            return newDist;
        } else {
            min = Integer.valueOf(dist).compareTo(Integer.valueOf(newDist)) > 0 ? newDist : dist;
        }
        return min;
    }
}
