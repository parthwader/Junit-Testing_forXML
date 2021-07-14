package com.company;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.JavaClassConverter;
import com.thoughtworks.xstream.converters.extended.SubjectConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;


import java.io.*;

public class SFOtoXML {

    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("demo.sfo");
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
             Demo1 d1 = (Demo1)in.readObject();
             System.out.println(d1);
            in.close();
            file.close();

            //Convert to XML File
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
            xstream.alias("Demo1",Demo1.class);
//            xstream.registerConverter(new JavaClassConverter());

            String ObjToXMLString= xstream.toXML(d1);

            FileWriter fWriter = new FileWriter("demo1.xml");
            fWriter.write(ObjToXMLString);
            fWriter.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}

