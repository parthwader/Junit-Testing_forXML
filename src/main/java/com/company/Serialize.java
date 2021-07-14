package com.company;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serialize {
    public static Demo1 demo1=new Demo1(1,"Part2",false, "cisco", 20,7,22,'e');
    public static void main(String[] args){
        try {
//            demo1 = new Demo1(1,"Part2",false, "cisco", 20,7);
            System.out.println(demo1);
            FileOutputStream foutputStream = new FileOutputStream("demo.sfo");
            ObjectOutputStream ooutputStream = new ObjectOutputStream(foutputStream);

            ooutputStream.writeObject(demo1);
            ooutputStream.flush();
            //closing the stream
            ooutputStream.close();

        } catch(Exception e) {
            System.out.println("error :C");
            System.out.println(e);
            e.printStackTrace();
        }

    }


}
