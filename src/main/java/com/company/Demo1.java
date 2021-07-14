package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Demo1 implements Serializable {
    public static final long serialVersionUID = 1L;
    public int simpleint;
    public long lval;
    public String str;
    public char defaultv;

    public int arr[];
    public Map<Integer,Integer> map = new HashMap<Integer, Integer>();

    // Default constructor
    public Demo1(int a, String b, boolean bool, String str, long val, int sz,long lval,char defaultv)
    {
        this.simpleint = a;
        this.str = b;
        this.lval=lval;
        this.arr = new int[sz];
        for(int i=0;i<sz;i++) {
            this.arr[i]=i;
            this.map.put(i,i);
        }
        this.defaultv=defaultv;
    }
}
