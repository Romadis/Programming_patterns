package org.example
import Student

fun main() {
    val students = mutableListOf<Student>();
    students.add(Student("Romanov","Vladislav","Vitalevich"));
    students.add(Student("Mamin-Sibiryak","Dmitriy","Narkisovich", telegramValue = "@poet777"));
    students.add(Student("Mahmudov","Ararat","Sarkisovich", gitHubValue = "abu123"));
    students.add(Student("Lermontov","Mihail","Yurevich", emailValue = "literature@gmail.com"));
    students.add(Student("Chernikov","Alexander","Viktorovich", phoneNumberValue = "+79095889812"));
    students.add(Student(hashMapOf(Pair("name","Vladislav"),Pair("surname","Romanov"),Pair("patronymic","Vitalevich"))));
    students.forEach { it: Student -> println(it) };

//    println(Student(hashMapOf(Pair("name","Vladislav"),Pair("surname","Romanov"),Pair("patronymic","Vitalevich"))).validate())
//    println(Student("Lermontov","Mihail","Yurevich", emailValue = "literature@gmail.com", gitHubValue = "famous007").validate());
//
//    val stud = Student("Lermontov","Mihail","Yurevich", emailValue = "literature@gmail.com", gitHubValue = "famous007", phoneNumberValue = "+79186916942");
//    stud.setContacts(hashMapOf(Pair("email","literature@gmail.com"),Pair("telegram","@miha999"),Pair("gitHub",null)));
//    println(stud)
//    stud.name = "Mishanya";
//
//    val data = Student.parseString("Student(id:4,surname:Lermontov,name:Mihail,patronymic:Yurevich,phoneNumber:+79186916942,email:literature@gmail.com,telegram:,gitHub:famous007)")
//    println(data)
//    println(stud)
//    println(Student(data))
//    println(Student(Student.parseString("Student(id:3,surname:Chernenko,name:Viktor,patronymic:Grigorevich,gitHub:)")))
//    println(stud.getInfo())
//    println(StudentShort(4,Student("Lermontov","Mihail","Yurevich", gitHubValue = "famous007", telegramValue = "@poetishka").getInfo()))
//    println(StudentShort(stud))
    Student.writeToTxt("src/","out.txt",Student.readFromTxt("src/test.txt"))
}

