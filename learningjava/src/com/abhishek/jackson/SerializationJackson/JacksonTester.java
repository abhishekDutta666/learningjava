package com.abhishek.jackson.SerializationJackson;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {
	private final static Logger logger=Logger.getLogger(JacksonTester.class); 
   public static void main(String args[]){
	  PropertyConfigurator.configure("log4j.properties");
      JacksonTester tester = new JacksonTester();//we create an instance of this class
      try {
         Student student = new Student();//We create an instance of the Student class
         //we use the setter functions to save the values
         student.setAge(10); 
         student.setName("Mahesh");
         logger.debug("Object creation done");
         //writeJSON is an user defined function just to separate the part of the program that makes the 
         //JSON file. We can or cannot write it separately
         tester.writeJSON(student);
         logger.debug("JSON creation complete");
         Student student1 = tester.readJSON();
         logger.debug("POJO from JSON creation done");
         //This is how we create a POJO from a JSON file which we just created
         System.out.println(student1);

      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void writeJSON(Student student) throws JsonGenerationException, JsonMappingException, IOException{
      ObjectMapper mapper = new ObjectMapper();	
      mapper.writeValue(new File("student.json"), student);//jSON to POJO
   }

   private Student readJSON() throws JsonParseException, JsonMappingException, IOException{
      ObjectMapper mapper = new ObjectMapper();
      Student student = mapper.readValue(new File("student.json"), Student.class);//POJO to JSON
      return student;
   }
}
