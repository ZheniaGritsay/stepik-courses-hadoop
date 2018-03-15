package com.courses.stepik;

import com.courses.stepik.dijkstra.BFSMapReduce;
import com.courses.stepik.dijkstra.Dijkstra;
import com.courses.stepik.page.rank.PageRankMapper;
import com.courses.stepik.page.rank.PageRankReducer;

public class Main {

    public static void main(String[] args) {
        Task task = createPageRankReducer();
        task.exec();
    }

    static Task createDijkstra() {
        return new Dijkstra();
    }

    static Task createDFSMapReduce() {
        return new BFSMapReduce();
    }

    static Task createPageRankMapper() {
        return new PageRankMapper();
    }

    static Task createPageRankReducer() {
        return new PageRankReducer();
    }
}
