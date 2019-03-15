package com.abhishek.jaxb;

//Create POJO or bind the schema and generate the classes (already done)
//Create the JAXBContext object
//Create the Marshaller objects
//Create the content tree by using set methods
//Call the marshal method


import java.io.FileOutputStream;  
import java.util.ArrayList;  
  
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.Marshaller;  
  
  
public class ObjectToXML {  
public static void main(String[] args) throws Exception{  
    JAXBContext contextObj = JAXBContext.newInstance(Question.class);  
    //the object is created through Constructor.newInstance
    //creation of JAXBContext object
    
    Marshaller marshallerObj = contextObj.createMarshaller();  
    //Object deserialization is nothing but creating an object from its serialized form and thats how 
    //we are creating an object here
    //creation of Marshaller objects
    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
    //for pretty-print XML in JAXB
    
  
    Answer ans1=new Answer(101,"java is a programming language","ravi");  
    Answer ans2=new Answer(102,"java is a platform","john");  
      
    ArrayList<Answer> list=new ArrayList<Answer>();  
    list.add(ans1);  
    list.add(ans2);  
      
    Question que=new Question(1,"What is java?",list);  
    marshallerObj.marshal(que, new FileOutputStream("question.xml"));  
    //we write this to question.xml file
       
}  
}  
