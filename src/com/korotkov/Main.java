package com.korotkov;

import com.korotkov.algorithm.BaseAlgorithm;
import com.korotkov.algorithm.MinStoreAlg;
import com.korotkov.algorithm.StoreAlgorithm;
import com.korotkov.core.Data;
import com.korotkov.core.Node;
import com.korotkov.core.Parser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Data data = Parser.getData("/Users/Apple1111/Documents/labs/4/Task4_v2/task_4_07_n30_m15_T12.txt");
        Node[] nodes = Node.getFlowModel(data);
        BaseAlgorithm alg = new BaseAlgorithm();

        int tMaxFlow = 0;
        for (int i = 0; i < data.getM(); i++) {
            for (int j = 0; j < data.getTick(); j++) {
                tMaxFlow += data.getGoalProd()[i][j];
            }
        }

        alg.solve(nodes, tMaxFlow, false);
        StoreAlgorithm sAlg = new StoreAlgorithm();
        sAlg.solve(data, tMaxFlow);
        MinStoreAlg mAlg = new MinStoreAlg();
        mAlg.solve(data, tMaxFlow);
    }
}
