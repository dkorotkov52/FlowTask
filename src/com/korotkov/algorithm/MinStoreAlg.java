package com.korotkov.algorithm;

import com.korotkov.core.Arc;
import com.korotkov.core.Data;
import com.korotkov.core.Node;

public class MinStoreAlg {
    public void solve(Data data, int tMaxFlow) {
        boolean[] Stores = new boolean[data.getM()];
        int StoreCap = tMaxFlow;
        Node[] nodes = Node.getFlowModel(data);
        for (int i = 0; i < data.getM(); i++) {
            if (!Stores[i])
                for (int t = 0; t < data.getTick() - 1; t++) {
                    int id1 = data.getN() * data.getTick() + data.getN() + 1 + data.getTick() * i + t,
                            id2 = id1 + 1;
                    Arc a1 = new Arc(id1, id2, StoreCap),
                            a2 = new Arc(id2, id1, 0);
                    nodes[id1].addArc(a1, a2);
                    nodes[id2].addArc(a2, a1);
                }
        }

        if (!new BaseAlgorithm().solve(nodes, tMaxFlow, false)) {
            System.out.println("Max flow is not reached");
        } else {
            for (int j = 0; j < data.getM(); j++) {
                nodes = Node.getFlowModel(data);
                Stores[j] = true;
                for (int i = 0; i < data.getM(); i++) {
                    if (!Stores[i])
                        for (int t = 0; t < data.getTick() - 1; t++) {
                            int id1 = data.getN() * data.getTick() + data.getN() + 1 + data.getTick() * i + t,
                                    id2 = id1 + 1;
                            Arc a1 = new Arc(id1, id2, StoreCap),
                                    a2 = new Arc(id2, id1, 0);
                            nodes[id1].addArc(a1, a2);
                            nodes[id2].addArc(a2, a1);
                        }
                }
                if (!new BaseAlgorithm().solve(nodes, tMaxFlow, false)) {
                    Stores[j] = false;
                }

            }
            int cnt = 0;
            for (int i = 0; i < data.getM(); i++) {
                if (!Stores[i]) {
                    System.out.print(i + 1);
                    cnt++;
                }
            }
            System.out.println();
            System.out.println("No sort. Stores count = " + cnt);


            //sort
            int[] diff = new int[data.getM()];
            int[] ids = new int[data.getM()];
            for (int i = 0; i < data.getM(); i++) {
                ids[i] = i;
                int max = 0,
                        min = 2147483647; // int MaxValue;
                for (int j = 0; j < data.getTick(); j++) {
                    if (data.getGoalProd()[i][j] > max)
                        max = data.getGoalProd()[i][j];
                    if (data.getGoalProd()[i][j] < min)
                        min = data.getGoalProd()[i][j];
                }
                diff[i] = max - min;
            }

            for (int i = 0; i < data.getM() - 1; i++) {
                boolean f = false;
                for (int j = i; j < data.getM() - i - 1; j++) {
                    if (diff[j] > diff[j + 1]) {
                        f = true;
                        int buf = diff[j];
                        diff[j] = diff[j + 1];
                        diff[j + 1] = buf;
                        buf = ids[j];
                        ids[j] = ids[j + 1];
                        ids[j + 1] = buf;
                    }
                }
                if (!f)
                    break;
            }


            for (int j = 0; j < data.getM(); j++) {
                nodes = Node.getFlowModel(data);
                Stores[ids[j]] = true;
                for (int i = 0; i < data.getM(); i++) {
                    if (!Stores[ids[i]])
                        for (int t = 0; t < data.getTick() - 1; t++) {
                            int id1 = data.getN() * data.getTick() + data.getN() + 1 + data.getTick() * ids[i] + t,
                                    id2 = id1 + 1;
                            Arc a1 = new Arc(id1, id2, StoreCap),
                                    a2 = new Arc(id2, id1, 0);
                            nodes[id1].addArc(a1, a2);
                            nodes[id2].addArc(a2, a1);
                        }
                }
                if (!new BaseAlgorithm().solve(nodes, tMaxFlow, false)) {
                    Stores[ids[j]] = false;
                }

            }
            cnt = 0;
            for (int i = 0; i < data.getM(); i++) {
                if (!Stores[ids[i]]) {
                    System.out.print(ids[i] + 1);
                    cnt++;
                }
            }
            System.out.println();
            System.out.println("Sort. Stores count = " + cnt);

        }
    }
}
