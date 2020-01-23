package com.korotkov.core;

import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Arc> forward = new ArrayList<>();
    private ArrayList<Arc> backward = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Node(String name) {
        this.name = name;
    }

    public void addArc(Arc fArc, Arc bArc) {
        forward.add(fArc);
        backward.add(bArc);
    }

    public void changeArcWeigth(int id, int value) {
        forward.get(id).setfWeigth(forward.get(id).getfWeigth() - value);
        backward.get(id).setfWeigth(backward.get(id).getfWeigth() + value);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Arc> getForward() {
        return forward;
    }

    public static Node[] getFlowModel(Data data) {
        ArrayList<Node> fModel = new ArrayList<>();
        fModel.add(new Node("s"));
        for (int i = 0; i < data.getN(); i++) {
            String name = i + 1 + "";
            fModel.add(new Node(name));
            Arc a1 = new Arc(0, (i + 1), data.getMaxProd()[i]),
                    a2 = new Arc((i + 1), 0, 0);
            fModel.get(0).addArc(a1, a2);
            fModel.get(i + 1).addArc(a2, a1);
        }

        for (int i = 0; i < data.getN(); i++) {
            for (int t = 0; t < data.getTick(); t++) {
                int id1 = i + 1,
                        id2 = data.getN() + data.getTick() * i + t + 1;
                fModel.add(new Node((i + 1) + "," + (t + 1)));
                Arc a1 = new Arc(id1, id2, data.getMaxProdPT()[i][t]),
                        a2 = new Arc(id2, id1, 0);
                fModel.get(id1).addArc(a1, a2);
                fModel.get(id2).addArc(a2, a1);
            }
        }


        for (int i = 0; i < data.getM(); i++) {
            for (int t = 0; t < data.getTick(); t++) {
                fModel.add(new Node((i + 1) + "!" + (t + 1)));
            }
        }


        for (int i = 0; i < data.getM(); i++) {
            for (int t = 0; t < data.getTick(); t++) {
                for (int k = 0; k < data.getSuppliers()[i].length; k++) {
                    int num = data.getN() * data.getTick() + data.getN() + 1,
                            ind = data.getSuppliers()[i][k],
                            id1 = data.getN() + data.getTick() * ind + t + 1,
                            id2 = num + data.getTick() * i + t;
                    Arc a1 = new Arc(id1, id2, data.getMaxProdPT()[ind][t]),
                            a2 = new Arc(id2, id1, 0);
                    fModel.get(id1).addArc(a1, a2);
                    fModel.get(id2).addArc(a2, a1);
                }
            }
        }

        fModel.add(new Node("t"));
        for (int i = 0; i < data.getM(); i++) {
            for (int t = 0; t < data.getTick(); t++) {
                int id1 = data.getN() * data.getTick() + data.getN() + 1 + data.getTick() * i + t,
                        id2 = fModel.size() - 1;
                Arc a1 = new Arc(id1, id2, data.getGoalProd()[i][t]),
                        a2 = new Arc(id2, id1, 0);
                fModel.get(id1).addArc(a1, a2);
                fModel.get(id2).addArc(a2, a1);
            }
        }
        return (Node[]) fModel.toArray();
    }
}
