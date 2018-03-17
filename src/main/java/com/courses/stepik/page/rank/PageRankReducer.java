package com.courses.stepik.page.rank;

import com.courses.stepik.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PageRankReducer implements Task {
    private static final double ALPHA = 0.1;
    private static final double N = 5;

    @Override
    public void exec() {
        try (FileReader fr = new FileReader(new File(PageRankReducer.class.getClassLoader()
                .getResource("page_rank_reducer.txt").getPath()));
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            String p = "0";
            String structure = null;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\t");

                if (structure != null && !equalVertexes(structure, line)) {
                    String temp[] = structure.split("\t");
                    temp[1] = computePR(p);
                    System.out.println(temp[0] + "\t" + temp[1] + "\t" + temp[2]);
                    p = "0";
                    structure = null;
                }

                if (isNode(line)) {
                    structure = line;
                } else {
                    p = computeP(Double.parseDouble(p), Double.parseDouble(splitted[1]));
                }
            }

            String temp[] = structure.split("\t");
            temp[1] = computePR(p);
            System.out.println(temp[0] + "\t" + temp[1] + "\t" + temp[2]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isNode(String data) {
        String[] arr = data.split("\t");
        return arr[2].length() > 2;
    }

    private boolean equalVertexes(String currentV, String newV) {
        return currentV.charAt(0) == newV.charAt(0);
    }

    private String computePR(String p) {
        double tmp = Double.parseDouble(p);
        double firstPart = (1 / N) * ALPHA;
        tmp = (1 - ALPHA) * tmp;
        return computeP(firstPart, tmp);
    }

    private String computeP(double prev, double num) {
        String sNum = (prev + num) + "";
        sNum = fillWithZeros(sNum);
        if (sNum.length() > 5) {
            double tmp = Double.parseDouble(sNum) * 1000;
            tmp = Math.round(tmp);
            tmp /= 1000;
            sNum = tmp + "";

            sNum = fillWithZeros(sNum);
        }

        return sNum;
    }

    private String fillWithZeros(String s) {
        if (s.length() < 5) {
            int i = 5 - s.length();
            for (int j = 0; j < i; j++) {
                s += "0";
            }
        }

        return s;
    }
}
