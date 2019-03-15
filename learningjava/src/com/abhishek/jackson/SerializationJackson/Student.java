package com.abhishek.jackson.SerializationJackson;

//This part of the program is just used to create an object that can later be mapped to form a JSON file
class Student {
 private String name;
 private int age;
 public Student(){}
 public String getName() {
    return name;
 }
 public void setName(String name) {
    this.name = name;
 }
 public int getAge() {
    return age;
 }
 public void setAge(int age) {
    this.age = age;
 }
 public String toString(){
    return "Student [ name: "+name+", age: "+ age+ " ]";
 }	
}