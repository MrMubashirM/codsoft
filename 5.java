import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.dat";
    private List<Student> students;

    // Constructor to load data from file
    public StudentManagementSystem() {
        students = loadFromFile();
    }

    // Inner Student class
    static class Student implements Serializable {
        private String name;
        private String rollNumber;
        private String grade;
        private String department;
        private String email;

        public Student(String name, String rollNumber, String grade, String department, String email) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
            this.department = department;
            this.email = email;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "Name='" + name + '\'' +
                    ", Roll Number='" + rollNumber + '\'' +
                    ", Grade='" + grade + '\'' +
                    ", Department='" + department + '\'' +
                    ", Email='" + email + '\'' +
                    '}';
        }
    }

    // Add Student
    public void addStudent(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        // Input Validation
        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty() || dept.isEmpty() || email.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        if (searchStudent(roll) != null) {
            System.out.println("Student with this Roll Number already exists.");
            return;
        }

        students.add(new Student(name, roll, grade, dept, email));
        saveToFile();
        System.out.println("Student added successfully.");
    }

    // Remove Student
    public void removeStudent(Scanner sc) {
        System.out.print("Enter Roll Number to Remove: ");
        String roll = sc.nextLine();
        boolean removed = students.removeIf(s -> s.getRollNumber().equals(roll));
        if (removed) {
            saveToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search Student
    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    // Edit Student
    public void editStudent(Scanner sc) {
        System.out.print("Enter Roll Number to Edit: ");
        String roll = sc.nextLine();
        Student s = searchStudent(roll);

        if (s != null) {
            System.out.print("Enter New Name (leave blank to keep unchanged): ");
            String name = sc.nextLine();
            if (!name.isEmpty()) s.setName(name);

            System.out.print("Enter New Grade (leave blank to keep unchanged): ");
            String grade = sc.nextLine();
            if (!grade.isEmpty()) s.setGrade(grade);

            System.out.print("Enter New Department (leave blank to keep unchanged): ");
            String dept = sc.nextLine();
            if (!dept.isEmpty()) s.setDepartment(dept);

            System.out.print("Enter New Email (leave blank to keep unchanged): ");
            String email = sc.nextLine();
            if (!email.isEmpty()) s.setEmail(email);

            saveToFile();
            System.out.println("Student details updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Display All Students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Save to File
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load from File
    private List<Student> loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Main Method - Console Menu
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    sms.addStudent(sc);
                    break;
                case 2:
                    sms.removeStudent(sc);
                    break;
                case 3:
                    System.out.print("Enter Roll Number to Search: ");
                    String rollSearch = sc.nextLine();
                    Student found = sms.searchStudent(rollSearch);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    sms.editStudent(sc);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
