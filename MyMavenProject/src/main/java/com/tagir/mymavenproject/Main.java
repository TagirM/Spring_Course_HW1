package com.tagir.mymavenproject;

/*
Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код, использующий эти зависимости.
Задание:
1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
3. Создайте класс Person с полями firstName, lastName и age.
4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class Main {
    public static void main(String[] args){
        Person person1 = new Person("Ivan", "Ivanov", 35);
        Person person2 = new Person("Ivan", "Ivanov", 35);
        Person person3 = new Person("Petr", "Petrov", 21);

        System.out.println(person1.hashCode());
        System.out.println(person1.toString());
        System.out.println(person2.hashCode());
        System.out.println(person3.hashCode());
        System.out.println(person1.equals(person2));
        System.out.println(person1.equals(person3));

        File fileGson = new File("gson.json");
        try(FileWriter writer = new FileWriter(fileGson,false)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(person1));
            writer.flush();
            System.out.println("Объект person1 успешно сериализован в json-формат.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try(FileReader reader = new FileReader(fileGson)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Person person = gson.fromJson(reader, Person.class);
            System.out.println("Объект person успешно десериализован из файла json: " + person);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
