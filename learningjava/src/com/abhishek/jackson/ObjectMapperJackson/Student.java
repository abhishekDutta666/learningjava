package com.abhishek.jackson.ObjectMapperJackson;

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
	   @Override// we have overridden this String function to display it the way we want
	   public String toString(){
	      return "Student [ name: "+name+", age: "+ age+ " ]";
	   }
}