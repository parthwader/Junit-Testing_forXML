import com.company.Demo1;
import com.company.Demo2;
import com.company.Serialize;
import com.company.XmlToObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
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
    @Test
    public void checkNotNull(){
        assertNotNull(d1);
        assertNotNull(d2);
    }
    @Test(timeout = 1000)
    public void test1() throws IOException{
        assertArrayEquals(d1.arr, d2.arr);

    }


}
