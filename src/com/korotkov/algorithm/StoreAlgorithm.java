package com.korotkov.algorithm;

import com.korotkov.core.Arc;
import com.korotkov.core.Data;
import com.korotkov.core.Node;

public class StoreAlgorithm {
    public boolean solve(Data data, int tMaxFlow)
    {
        int up = tMaxFlow,
                down = 0;
        while (up > down)
        {
            Node[] nodes = Node.getFlowModel(data);
            int cap = down + (up - down) / 2;
            if (solveCap(nodes, data, tMaxFlow, cap))
            {
                up = cap;
            }
            else
            {
                down = cap + 1;
            }
        }
        System.out.println("Max flow on cap: {0} " + down);
        return true;
    }

    private boolean solveCap(Node[] nodes, Data data, int tMaxFlow, int StoreCap)
    {
        Node[] tmp = (Node[])nodes.clone();
        for (int i = 0; i < data.getM(); i++)
        {
            for (int t = 0; t < data.getTick() - 1; t++)
            {
                int id1 = data.getN() * data.getTick() + data.getN() + 1 + data.getTick() * i + t,
                        id2 = id1 + 1;
                Arc a1 = new Arc(id1, id2, StoreCap),
                        a2 = new Arc(id2, id1, 0);
                tmp[id1].addArc(a1, a2);
                tmp[id2].addArc(a2, a1);
            }
        }
        BaseAlgorithm alg = new BaseAlgorithm();
        return alg.solve(tmp, tMaxFlow, false);
    }
}
