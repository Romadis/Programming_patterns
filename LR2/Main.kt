package org.example

import Student

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
    println(stud)
    val data = Student.parseString("Student(id:1,surname:Lermontov,name:Mihail,patronymic:Yurevich,phoneNumber:+79186916942,email:literature@gmail.com,telegram:,gitHub:famous007)")
    println(data)
    println(Student(data))
}

