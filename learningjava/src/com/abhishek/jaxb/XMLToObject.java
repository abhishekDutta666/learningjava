package com.abhishek.jaxb;

//Create POJO or bind the schema and generate the classes(Already done)
//Create the JAXBContext object
//Create the Unmarshaller objects
//Call the unmarshal method
//Use getter methods of POJO to access the data
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.File;  
import java.util.List;  
  
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller;  
   
public class XMLToObject {  
	private final static Logger logger=Logger.getLogger(XMLToObject.class);
    public static void main(String[] args) {  
   
     try {  
    	PropertyConfigurator.configure("log4j.properties");
        File file = new File("question.xml");  
        JAXBContext jaxbContext = JAXBContext.newInstance(Question.class); 
        logger.debug("JAXBContext object created");
        //JAXBContext object is created using Constructor.newInstance 
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        logger.debug("Unmarshaller object created");
        // Unmarshaller object is created using deserialization
        Question que= (Question) jaxbUnmarshaller.unmarshal(file);  
        logger.debug("Question object created");
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