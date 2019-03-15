package com.abhishek.jaxb;

//Create POJO or bind the schema and generate the classes(Already done)
//Create the JAXBContext object
//Create the Unmarshaller objects
//Call the unmarshal method
//Use getter methods of POJO to access the data

import java.io.File;  
import java.util.List;  
  
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller;  
   
public class XMLToObject {  
    public static void main(String[] args) {  
   
     try {  
   
        File file = new File("question.xml");  
        JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);  
        //JAXBContext object is created using Constructor.newInstance 
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        // Unmarshaller object is created using deserialization
        Question que= (Question) jaxbUnmarshaller.unmarshal(file);  
        //printing the contents of the OBJECT "que" that was created using the unmarshall function from file
        System.out.println(que.getId()+" "+que.getQuestionname());  
        System.out.println("Answers:");  
        List<Answer> list=que.getAnswers();  
        for(Answer ans:list)  
          System.out.println(ans.getId()+" "+ans.getAnswername()+"  "+ans.getPostedby());  
   
      } catch (JAXBException e) {  
        e.printStackTrace();  
      }  
   
    }  
}  