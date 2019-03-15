package com.abhishek.jackson.TreeModelJackson;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {
	private final static Logger logger=Logger.getLogger(JacksonTester.class);
   public static void main(String args[]){
		PropertyConfigurator.configure("log4j.properties");
      try {
         ObjectMapper mapper = new ObjectMapper();
         String jsonString = "{\"name\":\"Mahesh Kumar\",  \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
         logger.debug("ObjectMapper object and JSON string successfully created");
         //This is the JSON string can also be a byte array
         JsonNode rootNode = mapper.readTree(jsonString);//root of the JSON DOM which saves the entire JSON node in it
       //This method .path(string) is similar to get(String) it fetches the value from the JsonNode object
         logger.debug("rootnode has been saved");
         JsonNode nameNode = rootNode.path("name");
         System.out.println("Name: "+ nameNode.textValue());
         
         JsonNode ageNode = rootNode.path("age");
         System.out.println("Age: " + ageNode.intValue());

         JsonNode verifiedNode = rootNode.path("verified");
         System.out.println("Verified: " + (verifiedNode.booleanValue() ? "Yes":"No"));

         JsonNode marksNode = rootNode.path("marks");
         Iterator<JsonNode> iterator = marksNode.elements();//iterator is used to read all the values in an array
         System.out.print("Marks: [ ");

         while (iterator.hasNext()) {//checks if there are still any array elements left
            JsonNode marks = iterator.next();
            System.out.print(marks.intValue() + " "); 
         }

         System.out.println("]");
      }
      catch (JsonParseException e) { e.printStackTrace(); }
      catch (JsonMappingException e) { e.printStackTrace(); }
      catch (IOException e) { e.printStackTrace(); }
   }
}