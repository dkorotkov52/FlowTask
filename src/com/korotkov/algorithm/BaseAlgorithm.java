package com.korotkov.algorithm;

import com.korotkov.core.Arc;
import com.korotkov.core.Node;

import java.util.ArrayList;

public class BaseAlgorithm {
    private Node[] nodes;
    private static ArrayList<Node> way = new ArrayList<Node>();
    private static int[] mark;

    public boolean solve(Node[] nodes, int tMaxFlow, boolean f) {
        int maxFlow = 0, maxFlowt = 0, cnt = 0;
        this.nodes = nodes;
        while (true) {
            int weigth = 2147483647; // int MaxValue;
            ArrayList<Integer> ids = new ArrayList<>();
            mark = new int[nodes.length];
            Node[] way = getWay(ids, weigth);
            if (way == null) {
                maxFlow = 0;
                int idT = nodes.length - 1;
                Arc[] arcs = (Arc[]) nodes[idT].getForward().toArray();
                for (int i = 0; i < arcs.length; i++) {
                    maxFlow += arcs[i].getfWeigth();
                }
                if (tMaxFlow == maxFlow) {
                    if (f) System.out.println("Flow is maximum. Value = " + maxFlow);
                    return true;
                } else {
                    if (f) System.out.println("Teoretical max flow = " + tMaxFlow + ". Max flow = " + maxFlow);
                    return false;
                }
            } else {
                maxFlow = 0;
                for (int i = 0; i < way.length - 1; i++) {
                    way[i].changeArcWeigth(ids.get(i), weigth);
                }
                maxFlowt = maxFlow;
            }
        }
    }

    private Node[] getWay(ArrayList<Integer> ids, int weigth) {
        weigth = 0;
        for (int i = 0; i < nodes[0].getForward().size(); i++) {
            Arc[] arcs = (Arc[]) nodes[0].getForward().toArray();
            if (arcs[i].getfWeigth() > 0) {
                //List<Node> way = new List<Node>();
                //way.Add(nodes[0]);
                //ids.Add(i);
                //way.Add(nodes[i + 1]);
                way = new ArrayList<>();
                way.add(nodes[0]);
                //mark.Add(nodes[0]);
                mark[0] = 1;
                ids.add(i);
                way.add(nodes[i + 1]);
                //mark.Add(nodes[i + 1]);
                mark[i + 1] = 1;
                recGetWay(/*way,*/ ids, i + 1);
                if (way.get(way.size() - 1).getName().equals("t")) {
                    weigth = 2147483647; // int MaxValue;
                    for (int j = 0; j < way.size() - 1; j++) {
                        int w = way.get(j).getForward().get(ids.get(j)).getfWeigth();
                        if (w < weigth)
                            weigth = w;
                    }
                    return (Node[]) way.toArray();
                }
                ids.remove(ids.size() - 1);
            }
        }
        return null;
    }

    private ArrayList<Node> recGetWay(/*List<Node> tmp/*n,*/ ArrayList<Integer> ids, int num) {
        //if (n[n.Count - 1].Name == "t")
        //{
        //    return n;//если пришел путь - возвращаем его
        //}
        if (way.get(way.size() - 1).getName().equals("t")) {
            return way;//если пришел путь - возвращаем его
        } else {
            Arc[] arcs = nodes[num].getForward().toArray(new Arc[0]);
            for (int i = 0; i < arcs.length; i++) {
                if (arcs[i].getfWeigth() > 0 && mark[arcs[i].getId2()] == 0) {
                    //List<Node> tmp = new List<Node>(n);
                    way.add(nodes[arcs[i].getId2()]);
                    //mark.Add(nodes[arcs[i].Id2]);
                    mark[arcs[i].getId2()] = 1;
                    ids.add(i);
                    recGetWay(/*tmp,*/ ids, arcs[i].getId2());
                    if (/*tmp != null*/way.get(way.size() - 1).getName().equals("t")) {
                        return way;//если вернулся не NULL, значит вернулся путь - возвращаем его
                    }
                    way.remove(way.size() - 1);
                    ids.remove(ids.size() - 1);
                }
            }
        }
        return way;//если не вернули путь, то не нашли, то возвращаем NULL
    }

}
