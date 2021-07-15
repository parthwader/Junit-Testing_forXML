package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Demo2 implements Serializable{
    public static final long serialVersionUID = 2L;
    public int extra;
    public int longval;
    public String str;
    public int default1=101;
    public char defaultv='d';

    public int arr[];
    public Map<Integer,Integer> map = new HashMap<Integer, Integer>();
    public Map<Sample,Integer> objmap=new HashMap<Sample,Integer>();
    public Color enum1;
    public Color enum3;
    // Default constructor
    public Demo2(int a, String b, boolean bool, String str, long val, int sz,int default1,int lval)
    {
        this.extra = a;
        this.str = b;
        this.longval=lval;
        // this.obj = new NestedClass(bool, str, val);
        this.arr = new int[sz];
        for(int i=0;i<sz;i++) {
            this.arr[i]=i;
            this.map.put(i,i);
        }
       this.default1=100;
        this.defaultv='x';

    }
}

