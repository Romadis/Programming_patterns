package org.example

import Student
import StudentShort

fun main() {
//    val students = mutableListOf<Student>();
//    students.add(Student("Романов","Владислав","Витальевич"));
//    students.add(Student("Мамин-Сибиряк","Дмитрий","Наркисович", telegramValue = "@poet777"));
//    students.add(Student("Махмудов","Арарат-Магомед","Саркисович", gitHubValue = "abu123"));
//    students.add(Student("Лермонтов","Михаил","Юрьевич", emailValue = "literature@gmail.com"));
//    students.add(Student("Баладин","Максим","Викторович", phoneNumberValue = "+79095889812"));
//    students.add(Student(hashMapOf(Pair("name","Владислав"),Pair("surname","Романов"),Pair("patronymic","Витальевич"))));
//    students.forEach { it: Student -> println(it) };
//    println(Student(hashMapOf(Pair("name","Владислав"),Pair("surname","Романов"),Pair("patronymic","Витальевич"))).validate())
//    println(Student("Лермонтов","Михаил","Юрьевич", emailValue = "literature@gmail.com", gitHubValue = "famous007").validate());

    val stud = Student("Lermontov","Mihail","Yurevich", emailValue = "literature@gmail.com", gitHubValue = "famous007", phoneNumberValue = "+79186916942");
//    stud.setContacts(hashMapOf(Pair("email","literature@gmail.com"),Pair("telegram","@miha999"),Pair("gitHub",null)));
//    println(stud)
//    stud.name = "Мишаня";

    val data = Student.parseString("Student(id:4,surname:Lermontov,name:Mihail,patronymic:Yurevich,phoneNumber:+79186916942,email:literature@gmail.com,telegram:,gitHub:famous007)")
    println(data)
    println(stud)
    println(Student(data))
    println(Student(Student.parseString("Student(id:1,surname:Romanov,name:Vladislav,patronymic:Vitalevich,phoneNumber:+78005553535,email:romadis@gmail.com,telegram:,gitHub:romadis)")))
    println(stud.getInfo())
    println(StudentShort(4,stud.getInfo()))
    println(StudentShort(stud))
}

