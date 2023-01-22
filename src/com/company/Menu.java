package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu extends ScannerGetter{
    public Menu() throws SQLException, ClassNotFoundException {
        Manipulations manipulations = new Manipulations();
        MYSQL mysql = new MYSQL();
        String choice = "";
        while (!choice.equals("0")) {
            System.out.println("Hello and welcome to the school management software \uD83D\uDC4B\uD83C\uDF8A\uD83C\uDF89\n" +
                    "\n" +
                    "Here you have a menu of actions you can do: \n" +
                    "Choose your action number - \n" +
                    "\n" +
                    "[1] - get a list of all students at school organized by classes \n" +
                    "[2] - get get a list of all students at school organized by courses \n" +
                    "[3] - get a list of all the teachers at the school\n" +
                    "[4] - get a list of all students at specific class\n" +
                    "[5] - get a list of all students at specific course \n" +
                    "[6] - get all teacher’s students \n" +
                    "[7] - get all students in specific class with gpa above x \n" +
                    "[8] - get all students in specific course with gpa above x \n" +
                    "[9] - get all students in specific class with gpa under x \n" +
                    "[10] - get all students in specific course with gpa under x \n" +
                    "[11] - update student information \n" +
                    "[12] - update teacher information \n" +
                    "[13] - get the best student at class\n" +
                    "[14] - get best students at course \n" +
                    "[15] - add new student \n" +
                    "[16] - add new teacher \n" +
                    "[17] - get specific student \n" +
                    "[18] - get specific teacher \n" +
                    "[19] - delete student \n" +
                    "[20] - delete teacher \n" +
                    "\n" +
                    "[0] - to exit \n");
            choice = scanString();
            switch (choice) {
                case "1":
                    manipulations.studentsByClass();
                    System.out.println("\n");
                    break;
                case "2":
                    manipulations.studentsByCourse();
                    System.out.println("\n");
                    break;
                case "3":
                    manipulations.printTeachers();
                    System.out.println("\n");
                    break;
                case "4":
                    System.out.println("Enter class name: ");
                    String className = scanString();
                    System.out.println(manipulations.getClassStudents(className));;
                    System.out.println("\n");
                    break;
                case "5":
                    System.out.println("Enter course name: ");
                    String courseName = scanString();
                    System.out.println(manipulations.getCurseStudents(courseName));
                    System.out.println("\n");
                    break;
                case "6":
                    System.out.println("Enter teacher name: ");
                    String teacherName = scanString();
                    System.out.println(manipulations.getAllTeachStudents(teacherName));
                    System.out.println("\n");
                    break;
                case "7":
                    System.out.println("Enter class name: ");
                    String className1 = scanString();
                    System.out.println("Enter gpa: ");
                    double gpa = scanDouble();
                    System.out.println(manipulations.getStudentsAboveGpaInClass(gpa, className1));
                    System.out.println("\n");
                    break;
                case "8":
                    System.out.println("Enter course name: ");
                    String courseName1 = scanString();
                    System.out.println("Enter gpa: ");
                    double gpa1 = scanDouble();
                    System.out.println(manipulations.getStudentsAboveGpaInCourse(gpa1, courseName1));
                    System.out.println("\n");
                    break;
                case "9":
                    System.out.println("Enter class name: ");
                    String className2 = scanString();
                    System.out.println("Enter gpa: ");
                    double gpa2 = scanDouble();
                    System.out.println(manipulations.getStudentsUnderGpaInClass(gpa2, className2));
                    System.out.println("\n");
                    break;
                case "10":
                    System.out.println("Enter course name: ");
                    String courseName2 = scanString();
                    System.out.println("Enter gpa: ");
                    double gpa3 = scanDouble();
                    System.out.println(manipulations.getStudentsUnderGpaInCourse(gpa3, courseName2));
                    System.out.println("\n");
                    break;
                case "11":
                    System.out.println("Enter student id:");
                    String id = scanString();
                    manipulations.updateStudent(id);
                    System.out.println("\n");
                    break;
                case "12":
                    System.out.println("Enter teacher id:");
                    String id1 = scanString();
                    manipulations.updateTeacher(id1);
                    System.out.println("\n");
                    break;
                case "13":
                    System.out.println("Enter class name: ");
                    String className3 = scanString();
                    System.out.println(manipulations.getBestStudentInClass(className3).toString());
                    System.out.println("\n");
                    break;
                case "14":
                    System.out.println("Enter course name: ");
                    String courseName3 = scanString();
                    System.out.println(manipulations.getBestStudentInCurse(courseName3).toString());
                    System.out.println("\n");
                    break;
                case "15":
                    manipulations.addNewStudent();
                    System.out.println("\n");
                    break;
                case "16":
                    manipulations.addNewTeacher();
                    System.out.println("\n");
                    break;
                case "17":
                    System.out.println("Enter student's id: ");
                    String id4 = scanString();
                    System.out.println(manipulations.getStudentById(id4).toString());
                    System.out.println("\n");
                    break;
                case "18":
                    System.out.println("Enter teacher's name: ");
                    String name = scanString();
                    System.out.println(manipulations.getTeacherByName(name).toString());
                    System.out.println("\n");
                    break;
                case "19":
                    System.out.println("Enter student id: ");
                    String id2 = scanString();
                    manipulations.deleteStudent(id2);
                    System.out.println("\n");
                    break;
                case "20":
                    System.out.println("Enter teacher id: ");
                    String id3 = scanString();
                    manipulations.deleteTeacher(id3);
                    System.out.println("\n");
                    break;

                case "0":
                    System.out.println("Bye Bye! 👋🏻");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option");


            }
        }
    }
}
