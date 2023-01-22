package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Manipulations extends ScannerGetter{


    // Method to create a new student
    public static Student addStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        while (rs.next()) {
            student.setId(rs.getString("id"));
            student.setName(rs.getString("name"));
            student.setPhoneNumber(rs.getString("phoneNumber"));
            student.setClassName(rs.getString("Class"));
            student.setGrades(rs.getDouble("gpa"));
            student.setCurses(rs.getString("curse"));
            student.setAge(rs.getInt("age"));
        }
        return student;

    }
    // Method to create a new student ArrayList
    public static ArrayList<Student> addStudentArrayList(ResultSet rs) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        while (rs.next()) {
            students.add(new Student(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getInt("age"),
                    rs.getString("phoneNumber"),
                    rs.getString("class"),
                    rs.getString("major"),
                    rs.getDouble("gpa"),
                    rs.getString("curse")));
        }
        return students;
    }

    // Method to create a new teacher
    public static Teacher addTeacher(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        while (rs.next()) {
            teacher.setId(rs.getString("id"));
            teacher.setName(rs.getString("name"));
            teacher.setAge(rs.getInt("age"));
            teacher.setPhoneNumber(rs.getString("phoneNumber"));
            teacher.setEducationClass(rs.getString("teacherClass"));
            teacher.setCurseSubject(rs.getString("teacherCurse"));

        }
        return teacher;

    }

    // Method to create a new teacher ArrayList
    public static ArrayList<Teacher> addTeacherArrayList(ResultSet rs) throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        while (rs.next()) {
            teachers.add(new Teacher(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getInt("age"),
                    rs.getString("phoneNumber"),
                    rs.getString("teacherCurse"),
                    rs.getString("teacherClass")));
        }
        return teachers;
    }


    // Method to get all students
    public static ArrayList<Student> getAllStudents() throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query =  "SELECT * FROM students";
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;
    }

    // Method to get student by id
    public static Student getStudentById(String id) throws SQLException {
        MYSQL sql = new MYSQL();
        String query = "SELECT * FROM students WHERE id = '" + id + "'";
        ResultSet rs = sql.query(query);
        Student student = addStudent(rs);
        return student;
    }
    // Method to get teacher by name
    public static Teacher getTeacherByName(String name) throws SQLException {
        MYSQL sql = new MYSQL();
        String query = "SELECT * FROM teachers WHERE name = '" + name + "'";
        ResultSet rs = sql.query(query);
        Teacher teacher = addTeacher(rs);
        return teacher;
    }

    // Method to get all teachers
    public static ArrayList<Teacher> getAllTeachers() throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        ResultSet rs = sql.query(query);
        teachers = addTeacherArrayList(rs);
        return teachers;
    }

    // Method to print all students
    public static void printStudents() throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = getAllStudents();
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    // Method to print all teachers
    public static void printTeachers() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> teachers = getAllTeachers();
        for (Teacher teacher : teachers) {
            System.out.println(teacher.toString());
        }
    }

    // Method to print all students is a specific curse
    public static void studentsByCourse() throws SQLException {
        MYSQL sql = new MYSQL();
        sql.query("SELECT name,curse FROM students ORDER BY curse DESC", (rs) -> {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " | " + rs.getString("curse"));

            }
        });
    }

    // Method to print all students is a specific class
    public static void studentsByClass() throws SQLException {
        MYSQL sql = new MYSQL();
        sql.query("SELECT name,class FROM students ORDER BY class DESC", (rs) -> {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " | " + rs.getString("class"));
            }
        });
    }

    // Method to get all students is a specific class
    public static ArrayList<Student> getCurseStudents(String curse) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE curse = '" + curse + "'";
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;

    }

    // Method to get all students is a specific class
    public static ArrayList<Student> getClassStudents(String classs) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE class = '" + classs + "'";
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;

    }

    // Method to get all students thought by a specific teacher
    public static ArrayList<Student> getAllTeachStudents(String major) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Student> students2 = new ArrayList<>();

        String query ="SELECT * FROM students WHERE major = \"%s\";".formatted(major);
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);


            // getting the curse of the teacher
            sql.query("SELECT teacherCurse FROM Teachers WHERE name = \"%s\";".formatted(major), (rs1) -> {
                String curse = "";
                while (rs1.next()) {
                    curse = rs1.getString("teacherCurse");
                }
                // getting the students of the curse and print them with the curse name
                sql.query("SELECT * FROM students WHERE curse = \"%s\";".formatted(curse), (rs2) -> {
                    while (rs2.next()) {
                        students2.add(new Student(
                                rs2.getString("name"),
                                rs2.getString("id"),
                                rs2.getInt("age"),
                                rs2.getString("phoneNumber"),
                                rs2.getString("class"),
                                rs2.getString("major"),
                                rs2.getDouble("gpa"),
                                rs2.getString("curse")));
                    }
                });
            });
        // make sure that we don't print a student that already printed
        // because the student can be taught by the teacher in the education class and also in the curse
        for (Student student : students2) {
                for (Student student1 : students) {
                    if (student.getId().equals(student1.getId())) {
                        students.remove(student1);
                    }
                }
            }
        students.addAll(students2);
        return students;
    }

    // Method to get all students with gpa above a specific gpa
    public static ArrayList<Student> getStudentsAboveGpa(double gpa) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE gpa > %s;".formatted(gpa);
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;
    }

    // Method to get all students with gpa below a specific gpa in a specific class
    public static ArrayList<Student> getStudentsAboveGpaInClass(double gpa, String className) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE gpa > %s AND class = \"%s\";".formatted(gpa, className);
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;
    }

    // Method to get all students with gpa below a specific gpa in a specific curse
    public static ArrayList<Student> getStudentsAboveGpaInCourse(double gpa, String course) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE gpa > %s AND curse = \"%s\";".formatted(gpa, course);
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;
    }

    // Method to get all students with gpa under a specific gpa
    public static ArrayList<Student> getStudentsUnderGpa(double gpa) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE gpa < %s;".formatted(gpa);
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;
    }

    // Method to get all students with gpa under a specific gpa in a specific class
    public static ArrayList<Student> getStudentsUnderGpaInClass(double gpa, String className) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE gpa < %s AND class = \"%s\";".formatted(gpa, className);
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;
    }

    // Method to get all students with gpa under a specific gpa in a specific curse
    public static ArrayList<Student> getStudentsUnderGpaInCourse(double gpa, String course) throws SQLException {
        MYSQL sql = new MYSQL();
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE gpa < %s AND curse = \"%s\";".formatted(gpa, course);
        ResultSet rs = sql.query(query);
        students = addStudentArrayList(rs);
        return students;
    }

    // Method to update a student's information
    public static void updateStudent(String id) throws SQLException {
        MYSQL sql = new MYSQL();

        Scanner scanner = new Scanner(System.in);
        String update = "";

        while (!update.equals("9")) {
            System.out.println("Choose the field you want to update:" +
                    "\n[1] -  Name" + "\n[2] - id" + "\n[3] - age" + "\n[4] - phoneNumber" +
                    "\n[5] - class" + "\n[6] - major" + "\n[7] - gpa" + "\n[8] - curse" + "\n[9] - exit");
            update = scanner.nextLine();
            switch (update) {
                case "1":
                    System.out.println("Enter the new name:");
                    String newName = scanner.nextLine();
                    sql.updateQuery("UPDATE students SET name = '" + newName + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "2":
                    System.out.println("Enter the new id:");
                    String newId = scanner.nextLine();
                    sql.updateQuery("UPDATE students SET id = '" + newId + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "3":
                    System.out.println("Enter the new age:");
                    int newAge = scanner.nextInt();
                    sql.updateQuery("UPDATE students SET age = '" + newAge + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "4":
                    System.out.println("Enter the new phoneNumber:");
                    String newPhoneNumber = scanner.nextLine();
                    sql.updateQuery("UPDATE students SET phoneNumber = '" + newPhoneNumber + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "5":
                    System.out.println("Enter the new class:");
                    String newClass = scanner.nextLine();
                    sql.updateQuery("UPDATE students SET class = '" + newClass + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "6":
                    System.out.println("Enter the new major:");
                    String newMajor = scanner.nextLine();
                    sql.updateQuery("UPDATE students SET major = '" + newMajor + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "7":
                    System.out.println("Enter the new gpa:");
                    double newGpa = scanner.nextDouble();
                    sql.updateQuery("UPDATE students SET gpa = '" + newGpa + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "8":
                    System.out.println("Enter the new curse:");
                    String newCurse = scanner.nextLine();
                    sql.updateQuery("UPDATE students SET curse = '" + newCurse + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "9":
                    break;
            }
        }
    }

    // Method to update teacher's information
    public static void updateTeacher(String id) throws SQLException {
        MYSQL sql = new MYSQL();

        Scanner scanner = new Scanner(System.in);
        String update = "";


        while (!update.equals("7")) {
            System.out.println("Choose the field you want to update:" +
                    "\n[1] -  id" + "\n[2] - name" + "\n[3] - phoneNumber" + "\n[4] - teacherClass" +
                    "\n[5] - teacherCurse" + "\n[6] - age" + "\n[7] - exit");
            update = scanner.nextLine();

            switch (update) {
                case "1":
                    System.out.println("Enter the new id:");
                    String newId = scanner.nextLine();
                    sql.updateQuery("UPDATE teachers SET id = '" + newId + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "2":
                    System.out.println("Enter the new name:");
                    String newName = scanner.nextLine();
                    sql.updateQuery("UPDATE teachers SET name = '" + newName + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "3":
                    System.out.println("Enter the new phone number:");
                    String newPhoneNumber = scanner.nextLine();
                    sql.updateQuery("UPDATE teachers SET phoneNumber = '" + newPhoneNumber + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "4":
                    System.out.println("Enter the new class:");
                    String newClass = scanner.nextLine();
                    sql.updateQuery("UPDATE teachers SET teacherClass = '" + newClass + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "5":
                    System.out.println("Enter the new curse:");
                    String newCurse = scanner.nextLine();
                    sql.updateQuery("UPDATE teachers SET teacherCurse = '" + newCurse + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "6":
                    System.out.println("Enter the new age:");
                    int newAge = scanner.nextInt();
                    sql.updateQuery("UPDATE teachers SET age = '" + newAge + "' WHERE id = '" + id + "'");
                    update = "0";
                    break;
                case "7":
                    break;
            }
        }
    }

    // Method to get best student in a class
    public static Student getBestStudentInClass(String className) throws SQLException {
        MYSQL sql = new MYSQL();
        String query = "SELECT * FROM students WHERE Class = '" + className + "' ORDER BY gpa DESC LIMIT 1";
        ResultSet rs = sql.query(query);
        return addStudent(rs);
    }
    // Method to get best student in a curse
    public static Student getBestStudentInCurse(String curse) throws SQLException {
        MYSQL sql = new MYSQL();
        String query = "SELECT * FROM students WHERE curse = '" + curse + "' ORDER BY gpa DESC LIMIT 1";
        ResultSet rs = sql.query(query);
        return addStudent(rs);
    }
    // Method to add new student to database
    public static void addNewStudent() throws SQLException {
        MYSQL sql = new MYSQL();
        String name, phoneNumber, className, curse, id, major;
        int age;
        double gpa;

        ScannerGetter scannerGetter = new ScannerGetter();
        System.out.println("Enter the name:");
        name = scannerGetter.scanString();
        System.out.println("Enter id:");
        id = scannerGetter.scanString();
        System.out.println("Enter the age:");
        age = scannerGetter.scanInt();
        System.out.println("Enter the phone number:");
        phoneNumber = scannerGetter.scanString();
        System.out.println("Enter the class:");
        className = scannerGetter.scanString();
        System.out.println("Enter the major:");
        major = scannerGetter.scanString();
        System.out.println("Enter the gpa:");
        gpa = scannerGetter.scanDouble();
        System.out.println("Enter the curse:");
        curse = scannerGetter.scanString();

        sql.updateQuery("INSERT INTO students (name, id, age, phoneNumber, class, major, gpa, curse) VALUES ('" + name + "', '" + id + "', '" + age + "', '" + phoneNumber + "', '" + className + "', '" + major + "', '" + gpa + "', '" + curse + "')");
          }

    // Method to add new teacher to database
    public static void addNewTeacher() throws SQLException {
        MYSQL sql = new MYSQL();
        ScannerGetter scannerGetter = new ScannerGetter();
        String name, id, phoneNumber, className, curse;
        int age;
        System.out.println("Enter the name:");
        name = scannerGetter.scanString();
        System.out.println("Enter id:");
        id = scannerGetter.scanString();
        System.out.println("Enter the age:");
        age = scannerGetter.scanInt();
        System.out.println("Enter the phone number:");
        phoneNumber = scannerGetter.scanString();
        System.out.println("Enter the curse:");
        curse = scannerGetter.scanString();
        System.out.println("Enter the class:");
        className = scannerGetter.scanString();
        sql.updateQuery("INSERT INTO teachers (name,id,age, phoneNumber, teacherCurse,teacherClass ) VALUES ('" + name + "', '" + id + "', '" + age + "', '" + phoneNumber + "', '" + curse + "', '" + className + "')");
    }
    public static void deleteStudent(String id) throws SQLException {
        MYSQL sql = new MYSQL();
        sql.updateQuery("DELETE FROM students WHERE id = '" + id + "'");
    }
    public static void deleteTeacher(String id) throws SQLException {
        MYSQL sql = new MYSQL();
        sql.updateQuery("DELETE FROM teachers WHERE id = '" + id + "'");
    }


}
