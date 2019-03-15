//comments written by Abhishek Dutta for the sake of understanding and learning JAXB

package com.abhishek.jaxb;
import java.util.List;  //since we are using list form the collections package
import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;  
//Annotations start with ‘@’ and Annotations help to associate metadata (information) to the program elements
//in this case annotations are used to specify what is the root or the elements of the XML file

//@XmlTransient: This will make sure that the Object property is not written to the XML.
//@XmlType: It maps the class to the XML schema type. We can use it for ordering the elements in the XML.
//All the functions are public so that everyone can use it

@XmlRootElement  //specifies the root element for the xml document i.e. the root of the tree structure of the xml file 
public class Question {  
private int id;  
private String questionname;  
private List<Answer> answers;

public Question() {} //default constructor

public Question(int id, String questionname, List<Answer> answers) {  
    super(); //calling the constructor of the super class 
    this.id = id;  
    this.questionname = questionname;  
    this.answers = answers;  
}  
//getter and setters are used so that no class can get direct access to the values of the variables
@XmlAttribute  //specifies the attribute for the root element
public int getId() {  
    return id;  
}  
public void setId(int id) {  
    this.id = id;  //this operator specifies that the variable is of this object and not the parameterized variable
}  
@XmlElement  // specifies the sub element for the root element
public String getQuestionname() {  
    return questionname;  
}  
public void setQuestionname(String questionname) {  
    this.questionname = questionname;  
}  
@XmlElement  
public List<Answer> getAnswers() {  
    return answers;  
}  
public void setAnswers(List<Answer> answers) {  
    this.answers = answers;  
}  
}  
