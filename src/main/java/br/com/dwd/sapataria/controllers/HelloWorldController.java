package br.com.dwd.sapataria.controllers;

import javax.enterprise.inject.Model;

@Model
public class HelloWorldController
{

   private String name;
   private String label;

   public void action()
   {
      System.out.println("button clicked!");
      label = "Hello " + name;
   }

   public String getLabel()
   {
      return label;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
}