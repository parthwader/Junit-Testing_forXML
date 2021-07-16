import com.company.Demo1;
import com.company.Demo2;
import com.company.Sample;
import com.company.Serialize;

import com.sun.deploy.util.StringUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class JunitTest {
    public Demo1 d1;
    public Demo2 d2;

    public JunitTest()
    {
        d1=Serialize.demo1;
        try {
            FileInputStream file = new FileInputStream("demo2obj.sfo");
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
             d2 = (Demo2) in.readObject();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Test(timeout = 1000)
    public void checkNotNull(){
        assertNotNull(d1);
        assertNotNull(d2);
    }
    @Test(timeout = 1000)
    public void notExactlySame(){
        String sd1=d1.toString();
        String sd2=d2.toString();
        assertNotEquals(sd1,sd2);
    }
    @Test(timeout = 1000)
    public void test1() {
        assertArrayEquals(d1.arr, d2.arr);

    }
    @Test(timeout = 1000)
    public void test2(){
        assertEquals(d1.longval,d2.longval);
        assertEquals(d1.str,d2.str);
        assertEquals(d1.defaultv,d2.defaultv);
    }

    @Test(timeout = 1000)
    public void testMap(){
        //Test size
        assertEquals(d1.map.size(),d2.map.size());

        //Test Equals,ignore order
        assertThat(d1.map,is(d2.map));
        // or do : assertEquals(d1.map,d2.map) same thing

    }

    @Test(timeout = 1000)
    public void testObjectMap(){
        // both are assigned some values
        assertNotNull(d1.objmap);
        assertNotNull(d2.objmap);

        //Test size
        assertEquals(d1.objmap.size(),d2.objmap.size());

        XStream xStream=new XStream(new DomDriver());
        String mp1= xStream.toXML(d1.objmap);
        String mp2= xStream.toXML(d2.objmap);

//        System.out.println(mp1);
//        System.out.println(mp2);
        assertEquals(mp1,mp2);

//        for(Map.Entry<Sample,Integer> entry:d2.objmap.entrySet())
//        {
//            assertThat(d1.objmap,IsMapContaining.hasEntry(entry.getKey(),entry.getValue()));
//        }
    }

    @Test
    public void testBidiMap()
    {
        // both are assigned some values
        assertNotNull(d1.bidiMap);
        assertNotNull(d2.bidiMap);
        //Test size
        assertEquals(d1.bidiMap.size(),d2.bidiMap.size());

        assertEquals(d1.bidiMap,d2.bidiMap);
    }
    @Test(timeout = 1000)
    public void testEnums(){
        assertEquals(d1.enum1,d2.enum1);
        assertNotEquals(d1.enum2,d2.enum3);
    }
}
