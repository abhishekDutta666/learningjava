package com.abhishek.jackson.ObjectMapperJackson;

//Comments by Abhishek Dutta to understand and learn Jackson
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {
	private final static Logger logger=Logger.getLogger("JacksonTester");
   public static void main(String args[]){
	  PropertyConfigurator.configure("log4j.properties");
      ObjectMapper mapper = new ObjectMapper();//this object is used to map the elements with the values
      String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
      logger.debug("ObjectMapper object created and JSON string created");
      //map json to student
      try{
         Student student = mapper.readValue(jsonString, Student.class);
         logger.debug("POJO created");
         //readValue takes two parameters byte array and class<T>Value Type
         //And it returns an object
         System.out.println(student);
         //POJO to json
         jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
         logger.debug("POJO to JSON conversion complete");
         //PrettyPrinter helps write the JSON properly
         //objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true); this can also be used to pretty Print
         //objectMapper.writeValue(stringEmp, emp1); this is an alternative statement.
         //here the value from emp1 is stored in stringEmp
         System.out.println(jsonString);
      }
      catch (JsonParseException e) { e.printStackTrace();}
      catch (JsonMappingException e) { e.printStackTrace(); }
      catch (IOException e) { e.printStackTrace(); }
   }
}