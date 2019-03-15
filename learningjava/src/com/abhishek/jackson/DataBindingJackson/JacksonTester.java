package com.abhishek.jackson.DataBindingJackson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JacksonTester {
	private final static Logger logger=Logger.getLogger(JacksonTester.class);
   public static void main(String args[]){
	   PropertyConfigurator.configure("log4j.properties");
         try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String,Object> studentDataMap = new HashMap<String,Object>(); 
            logger.debug("Hashmap created");
            int[] marks = {1,2,3};
            //Student object is initialized using setter functions
            Student student = new Student();
            student.setAge(10);
            student.setName("Mahesh");
            // JAVA Object is put into the hashmap
            studentDataMap.put("student", student);
            // JAVA String is put into the map
            studentDataMap.put("name", "Mahesh Kumar");   		
            // JAVA Boolean values are put into the map
            studentDataMap.put("verified", Boolean.FALSE);
            // Array is put into the map
            studentDataMap.put("marks", marks);
            logger.debug("Values successfully put into the map");
            //mapper object of ObjectMapper class is used to create a new JSON file from the map
            mapper.writeValue(new File("student.json"), studentDataMap);
            logger.debug("student.json file created ");
            //result student.json
			//{ 
            //   "student":{"name":"Mahesh","age":10},
            //   "marks":[1,2,3],
            //   "verified":false,
            //   "name":"Mahesh Kumar"
            //}
            //we can get back a map from the JSON file by using the readValue function, for this we need to 
            //specify the Map.class to make an object of that class
            studentDataMap = mapper.readValue(new File("student.json"), Map.class);
            logger.debug("Hashmap from the json file Student.json has been created");
            System.out.println(studentDataMap.get("student"));
            System.out.println(studentDataMap.get("name"));
            System.out.println(studentDataMap.get("verified"));
            System.out.println(studentDataMap.get("marks"));
      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
            e.printStackTrace();
      }
   }
}
