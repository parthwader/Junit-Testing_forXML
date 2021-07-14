package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Demo2 implements Serializable{
    public static final long serialVersionUID = 2L;
    public int a;
    public int lval;
    public String bo;
    public int newval;
    public char defaultv;
    public int arr[];
    public Map<Integer,Integer> map = new HashMap<Integer, Integer>();

    // Default constructor
    public Demo2(int a, String b, boolean bool, String str, long val, int sz,int newval,int lval)
    {
        this.a = a;
        this.bo = b;
        this.lval=lval;
        // this.obj = new NestedClass(bool, str, val);
        this.arr = new int[sz];
        for(int i=0;i<sz;i++) {
            this.arr[i]=i;
            this.map.put(i,i);
        }
        this.newval=newval;
        this.defaultv='x';
    }
}

