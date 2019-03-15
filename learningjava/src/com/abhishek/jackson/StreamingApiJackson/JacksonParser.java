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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;

public class JacksonParser {
	private final static Logger logger=Logger.getLogger(JacksonTester.class);
   public static void main(String args[]){
      
      PropertyConfigurator.configure("log4j.properties");
      try {        	
         JsonFactory jsonFactory = new JsonFactory();

         JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File(
            "student.json"), JsonEncoding.UTF8);
         logger.debug("JsonGenerator is made using the JsonFactory object");
         jsonGenerator.writeStartObject();
         jsonGenerator.writeStringField("name", "Mahesh Kumar"); 
         jsonGenerator.writeNumberField("age", 21);
         jsonGenerator.writeBooleanField("verified", false); 
         jsonGenerator.writeFieldName("marks"); 
         jsonGenerator.writeStartArray(); // [
         jsonGenerator.writeNumber(100); 
         jsonGenerator.writeNumber(90); 
         jsonGenerator.writeNumber(85); 
         jsonGenerator.writeEndArray(); 
         jsonGenerator.writeEndObject(); 
         jsonGenerator.close();       	 
         logger.debug("JsonGenerator is used to make a Json file");
         //result student.json
         //{ 
         //   "name":"Mahesh Kumar",
         //   "age":21,
         //   "verified":false,
         //   "marks":[100,90,85]
         //}
         //DOM parser for JSON
         JsonParser jsonParser = jsonFactory.createParser(new File("student.json"));
         //parser for student.json has been created
         logger.debug("parser for student.json has been created");
         //Json parser uses tokens which makes it the fastest way to parse the JSON file
         while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            //get the current token
            String fieldname = jsonParser.getCurrentName();
            if ("name".equals(fieldname)) {
               //move to next token
               jsonParser.nextToken();
               //next token gives us the second value of the Value pair as each part of the pair is a token 
               System.out.println(jsonParser.getText());        	 
            }
            if("age".equals(fieldname)){
               //move to next token
               jsonParser.nextToken();
               System.out.println(jsonParser.getNumberValue());        	 
            }
            if("verified".equals(fieldname)){
               //move to next token
               jsonParser.nextToken();
               System.out.println(jsonParser.getBooleanValue());        	 
            }
            if("marks".equals(fieldname)){
               //move to [ 
               jsonParser.nextToken();
               // loop till token equal to "]"
               while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                  System.out.println(jsonParser.getNumberValue()); 
                  //we dont go to the next token here as in an array values are laid side by side
               }
            }
            logger.debug("parsing successfull");
         }
      } catch (JsonParseException e) {
         e.printStackTrace();
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
