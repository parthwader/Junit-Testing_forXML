package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Demo1 implements Serializable {
    public static final long serialVersionUID = 1L;
    public int simpleint;
    public long longval;
    public String str;
    public char defaultv;


    public int arr[];
    public Map<Integer,Integer> map = new HashMap<Integer, Integer>();
    public Map<Sample,Integer> objmap=new HashMap<Sample,Integer>();
    public Color enum1;
    public Color enum2;

    // Default constructor
    public Demo1(int a, String b, boolean bool, String str, long val, int sz,long lval,char defaultv)
    {
        this.simpleint = a;
        this.str = b;
        this.longval=lval;
        this.arr = new int[sz];
        for(int i=0;i<sz;i++) {
            this.arr[i]=i;
            this.map.put(i,i);
        }
        this.defaultv=defaultv;

        Sample sample=new Sample(33,"somestring",2);
        Sample sample1=new Sample(6,"anotherone",1);
        objmap.put(sample,100);
        objmap.put(sample1,200);

        enum1=Color.BLUE;
        enum2=Color.GREEN;
    }
}
