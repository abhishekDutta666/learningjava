package com.abhishek.jackson.StreamingApiJackson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.abhishek.jackson.TreeModelJackson.JacksonTester;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonGenerator {
	private final static Logger logger=Logger.getLogger(JacksonTester.class);
   public static void main(String args[]){
	   PropertyConfigurator.configure("log4j.properties");
      try {         
         JsonFactory jsonFactory = new JsonFactory();
         JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File("student.json"), JsonEncoding.UTF8);
         logger.debug("JsonGenerator is made using the JsonFactory object");
         //this specifies the encoding in which the JSON needs to be made
         //Object deserialization is nothing but creating an object from its serialized form and thats how 
         //we are creating an object here i.e. jsonGenerator
         jsonGenerator.writeStartObject();
         //marks the beginning of the formation of the JSON 
         // "name" : "Mahesh Kumar"
         jsonGenerator.writeStringField("name", "Mahesh Kumar"); 
         //We enter values like we would enter in a map and they would become JSON files later 

         // "age" : 21
         jsonGenerator.writeNumberField("age", 21);

         // "verified" : false
         jsonGenerator.writeBooleanField("verified", false);

         // "marks" : [100, 90, 85]
         jsonGenerator.writeFieldName("marks"); 
         logger.debug("Generated the values pairs");
         // [
         jsonGenerator.writeStartArray(); //marks the starting of the defining of an array
         // 100, 90, 85
         jsonGenerator.writeNumber(100); 
         jsonGenerator.writeNumber(90); 
         jsonGenerator.writeNumber(85); 
         // ]

         jsonGenerator.writeEndArray(); //marks the end or stops putting values into the array 
         logger.debug("Generated an array and closed the input");
         jsonGenerator.writeEndObject(); //stops putting new value pairs into the JSON files
         logger.debug("JsonGenerator object writing has been stopped");
         jsonGenerator.close();   //we need to close the generator or else someone could use it to make changes     
         logger.debug("JsonGenerator has been closed");
         //result student.json
         //{ 
         //   "name":"Mahesh Kumar",
         //   "age":21,
         //   "verified":false,
         //   "marks":[100,90,85]
         //}

         ObjectMapper mapper = new ObjectMapper();
         Map<String,Object> dataMap = mapper.readValue(new File("student.json"), Map.class);
         logger.debug("A map is created using the ObjectMapper function from the newly created json file");

         System.out.println(dataMap.get("name"));
         System.out.println(dataMap.get("age"));
         System.out.println(dataMap.get("verified"));
         System.out.println(dataMap.get("marks"));
      } 
      catch (JsonParseException e) { e.printStackTrace(); } 
      catch (JsonMappingException e) { e.printStackTrace(); } 
      catch (IOException e) { e.printStackTrace(); }
   }
}
