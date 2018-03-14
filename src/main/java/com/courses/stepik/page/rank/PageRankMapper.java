package com.courses.stepik.page.rank;

import com.courses.stepik.Main;
import com.courses.stepik.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageRankMapper implements Task {

    @Override
    public void exec() {
        try (FileReader fr = new FileReader(new File(Main.class.getClassLoader().getResource("page_rank.txt").getPath()));
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            String p;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t");
                List<String> adjacencyList = getAdjacencyList(splitted[2]);
                p = roundToThreeDigit(Double.parseDouble(splitted[1]) / adjacencyList.size());
                System.out.println(line);
                for (String s : adjacencyList) {
                    System.out.println(s + "\t" + p + "\t" + "{}");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getAdjacencyList(String data) {
        List<String> adjacencyList = new ArrayList<>();
        String[] vertexes = data.substring(1, data.length() - 1).split(",");
        adjacencyList.addAll(Arrays.asList(vertexes));

        return adjacencyList;
    }

    private String roundToThreeDigit(double num) {
        String sNum = num + "";
        if (sNum.length() < 5) {
            int i = 5 - sNum.length();
            for (int j = 0; j < i; j++) {
                sNum += "0";
            }
        } else if (sNum.length() > 5) {
            num = num * 1000;
            num = Math.round(num);
            num /= 1000;
            sNum = num + "";
        }

        return sNum;
    }
}
