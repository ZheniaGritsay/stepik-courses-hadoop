package com.courses.stepik;

import com.courses.stepik.dijkstra.BFSMapReduce;
import com.courses.stepik.dijkstra.Dijkstra;
import com.courses.stepik.page.rank.PageRankMapper;

public class Main {

    public static void main(String[] args) {
        Task task = createPageRank();
        task.exec();
    }

    static Task createDijkstra() {
        return new Dijkstra();
    }

    static Task createDFSMapReduce() {
        return new BFSMapReduce();
    }

    static Task createPageRank() {
        return new PageRankMapper();
    }
}
