package com.korotkov.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public static Data getData(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(name));
        String str;

        ArrayList<String> list = new ArrayList<>();
        while((str = reader.readLine()) != null ){
            if(!str.isEmpty()){
                list.add(str);
                System.out.println(str);
            }}
        String[] strs = list.toArray(new String[0]);

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        int Tick = Integer.parseInt(strs[2]);

        int[] maxProd = new int[N];
        String[] tmp = strs[4].split(" ");
        for (int i = 0; i < N; i++)
        {
            maxProd[i] = Integer.parseInt(tmp[i]);
        }

        int[][] maxProdPT = new int[N][];
        for (int i = 0; i < N; i++)
        {
            tmp = strs[i + 6].split(" ");
            maxProdPT[i] = new int[Tick];
            for (int j = 0; j < Tick; j++)
            {
                maxProdPT[i][j] = Integer.parseInt(tmp[j]);
            }

        }

        int[][] goalProd = new int[M][];
        for (int i = 0; i < M; i++)
        {
            tmp = strs[i + N + 7].split(" ");
            goalProd[i] = new int[Tick];
            for (int j = 0; j < Tick; j++)
            {
                goalProd[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        int[][] suppliers = new int[M][];
        for (int i = 0; i < M; i++)
        {
            tmp = strs[i + M + N + 8].split(" ");
            suppliers[i] = new int[tmp.length];
            for (int j = 0; j < tmp.length; j++)
            {
                suppliers[i][j] = Integer.parseInt(tmp[j]) - 1;
            }

        }
        return new Data(N, M, Tick, maxProd, maxProdPT, goalProd, suppliers);
    }
}
