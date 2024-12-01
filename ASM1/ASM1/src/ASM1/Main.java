/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASM1;

/**
 *
 * @author admin
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class DuplicateStudentIdException extends RuntimeException {
    public DuplicateStudentIdException(String message) {
        super(message);
    }
}

class InvalidMarksException extends RuntimeException {
    public InvalidMarksException(String message) {
        super(message);
    }
}

class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        setMarks(marks);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        if (marks < 0 || marks > 10) {
            throw new InvalidMarksException("Marks must be between 0 and 10.");
        }
        this.marks = marks;
    }

    public String getRank() {
        if (marks < 5.0) {
            return "Fail";
        } else if (marks < 6.5) {
            return "Medium";
        } else if (marks < 7.5) {
            return "Good";
        } else if (marks < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}

class StudentManagement {
    private ArrayList<Student> students = new ArrayList<>();
    
     public void mergeSortByMarks() {
        students = mergeSort(students);
        System.out.println("Students sorted by marks.");
    }

    private ArrayList<Student> mergeSort(ArrayList<Student> list) {
        if (list.size() <= 1) {
            return list; 
        }

        int mid = list.size() / 2;
        ArrayList<Student> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Student> right = new ArrayList<>(list.subList(mid, list.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private ArrayList<Student> merge(ArrayList<Student> left, ArrayList<Student> right) {
        ArrayList<Student> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getMarks() <= right.get(j).getMarks()) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }

    public void addStudent(int id, String name, double marks) {
        if (findStudentById(id) != null) {
            throw new DuplicateStudentIdException("Student ID " + id + " already exists.");
        }
        try {
            name = cleanName(name);
            students.add(new Student(id, name, marks));
            System.out.println("Student added successfully.");
        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void editStudent(int id, String newName, double newMarks) {
        Student student = findStudentById(id);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + id + " not found.");
        }
        try {
            newName = cleanName(newName); 
            student.setName(newName);
            student.setMarks(newMarks);
            System.out.println("Student edited successfully.");
        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String cleanName(String name) {
       
        return name.trim().replaceAll("[^a-zA-Z\\s]", "");
    }

    public void deleteStudent(int id) {
        if (students.removeIf(student -> student.getId() == id)) {
            System.out.println("Student deleted successfully.");
        } else {
            throw new StudentNotFoundException("Student ID " + id + " not found.");
        }
    }

    public void searchStudentById(int id) {
        Student student = findStudentById(id);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + id + " not found.");
        }
        System.out.println(student);
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Merge Sort Students by Marks");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Display All Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Student Marks: ");
                        double marks = scanner.nextDouble();
                        try {
                            management.addStudent(id, name, marks);
                        } catch (DuplicateStudentIdException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Enter Student ID to Edit: ");
                        int editId = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Marks: ");
                        double newMarks = scanner.nextDouble();
                        try {
                            management.editStudent(editId, newName, newMarks);
                        } catch (StudentNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Enter Student ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        try {
                            management.deleteStudent(deleteId);
                        } catch (StudentNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        management.mergeSortByMarks();
                        management.displayStudents();
                        break;
                    case 5:
                        System.out.print("Enter Student ID to Search: ");
                        int searchId = scanner.nextInt();
                        try {
                            management.searchStudentById(searchId);
                        } catch (StudentNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 6:
                        management.displayStudents();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = -1; 
            }
        } while (choice != 0);

        scanner.close();
    }
}
