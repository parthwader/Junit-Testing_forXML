package com.company;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.JavaClassConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.*;

public class XmlToObject {
    public static Demo2 demo2;
    public static void main(String[] args){
        try{

            XStream xstream = new XStream(new DomDriver()) {
                @Override
                protected MapperWrapper wrapMapper(MapperWrapper next) {
                    return new MapperWrapper(next) {
                        @Override
                        public boolean shouldSerializeMember(Class definedIn,
                                                             String fieldName) {
                            if (definedIn == Object.class) {
                                return false;
                            }
                            return super.shouldSerializeMember(definedIn, fieldName);
                        }
                    };
                }
            };
            xstream.alias("Demo1",Demo2.class);
//            xstream.registerConverter(new JavaClassConverter());

            BufferedReader bufferedReader = new BufferedReader(new FileReader("demo1.xml"));
            demo2=(Demo2)xstream.fromXML(bufferedReader);
            System.out.println(demo2);

            String ObjToXmlString=xstream.toXML(demo2);
            FileWriter fWriter = new FileWriter("demo2.xml");
            fWriter.write(ObjToXmlString);
            fWriter.close();

            FileOutputStream foutputStream = new FileOutputStream("demo2obj.sfo");
            ObjectOutputStream ooutputStream = new ObjectOutputStream(foutputStream);

            ooutputStream.writeObject(demo2);
            ooutputStream.flush();
            //closing the stream
            ooutputStream.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

}
