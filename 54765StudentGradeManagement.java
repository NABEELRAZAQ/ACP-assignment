import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int[] marks = new int[3];
    int total;
    double average;

    // Constructor
    public Student(int rollNo, String name, int[] marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks.clone();
        calculateTotalAndAverage();
    }

    // Method to calculate total and average
    void calculateTotalAndAverage() {
        total = 0;
        for (int mark : marks) {
            total += mark;
        }
        average = total / 3.0;
    }
}

public class StudentGradeManagement {
    static final int MAX = 50;
    static Student[] students = new Student[MAX];
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Welcome to Student Grade Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Marks");
            System.out.println("3. Remove Student");
            System.out.println("4. View All Students");
            System.out.println("5. Search Student");
            System.out.println("6. Highest Scorer");
            System.out.println("7. Class Average");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> updateMarks(sc);
                case 3 -> removeStudent(sc);
                case 4 -> viewAllStudents();
                case 5 -> searchStudent(sc);
                case 6 -> highestScorer();
                case 7 -> classAverage();
                case 8 -> exitSummary();
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 8);

        sc.close();
    }

    // 1. Add Student
    static void addStudent(Scanner sc) {
        if (count >= MAX) {
            System.out.println("Cannot add more students! Limit reached.");
            return;
        }
        System.out.print("Enter Roll No: ");
        int rollNo = sc.nextInt();

        // Check unique roll number
        if (findStudentIndex(rollNo) != -1) {
            System.out.println("Roll number already exists!");
            return;
        }

        sc.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter Marks in Subject " + (i + 1) + ": ");
            int mark = sc.nextInt();
            if (mark < 0 || mark > 100) {
                System.out.println("Invalid marks! Must be between 0 and 100.");
                i--; // re-enter this subject
                continue;
            }
            marks[i] = mark;
        }

        students[count++] = new Student(rollNo, name, marks);
        System.out.println("Student added successfully!");
    }

    // 2. Update Marks
    static void updateMarks(Scanner sc) {
        System.out.print("Enter Roll No to update marks: ");
        int rollNo = sc.nextInt();
        int idx = findStudentIndex(rollNo);

        if (idx == -1) {
            System.out.println("Student not found!");
            return;
        }

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter New Marks in Subject " + (i + 1) + ": ");
            int mark = sc.nextInt();
            if (mark < 0 || mark > 100) {
                System.out.println("Invalid marks! Must be between 0 and 100.");
                i--; 
                continue;
            }
            marks[i] = mark;
        }

        students[idx].marks = marks.clone();
        students[idx].calculateTotalAndAverage();
        System.out.println("Marks updated successfully!");
    }

    // 3. Remove Student
    static void removeStudent(Scanner sc) {
        System.out.print("Enter Roll No to remove: ");
        int rollNo = sc.nextInt();
        int idx = findStudentIndex(rollNo);

        if (idx == -1) {
            System.out.println("Student not found!");
            return;
        }

        for (int i = idx; i < count - 1; i++) {
            students[i] = students[i + 1];
        }
        count--;
        System.out.println("Student removed successfully!");
    }

    // 4. View All Students
    static void viewAllStudents() {
        if (count == 0) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\nRollNo\tName\tSub1\tSub2\tSub3\tTotal\tAverage");
        for (int i = 0; i < count; i++) {
            Student s = students[i];
            System.out.printf("%d\t%s\t%d\t%d\t%d\t%d\t%.2f%n",
                    s.rollNo, s.name, s.marks[0], s.marks[1], s.marks[2], s.total, s.average);
        }
    }

    // 5. Search Student
    static void searchStudent(Scanner sc) {
        System.out.print("Enter Roll No to search: ");
        int rollNo = sc.nextInt();
        int idx = findStudentIndex(rollNo);

        if (idx == -1) {
            System.out.println("Student not found!");
            return;
        }

        Student s = students[idx];
        System.out.printf("RollNo: %d | Name: %s | Marks: %d, %d, %d | Total: %d | Average: %.2f%n",
                s.rollNo, s.name, s.marks[0], s.marks[1], s.marks[2], s.total, s.average);
    }

    // 6. Highest Scorer
    static void highestScorer() {
        if (count == 0) {
            System.out.println("No students available.");
            return;
        }

        Student top = students[0];
        for (int i = 1; i < count; i++) {
            if (students[i].total > top.total) {
                top = students[i];
            }
        }

        System.out.printf("Highest Scorer: %s (Roll No: %d) with Total: %d, Average: %.2f%n",
                top.name, top.rollNo, top.total, top.average);
    }

    // 7. Class Average
    static void classAverage() {
        if (count == 0) {
            System.out.println("No students available.");
            return;
        }

        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += students[i].average;
        }
        System.out.printf("Class Average: %.2f%n", sum / count);
    }

    // 8. Exit Summary
    static void exitSummary() {
        System.out.println("\nExiting Program...");
        System.out.println("Total Students: " + count);
        classAverage();
    }

    // Helper: Find index by roll number
    static int findStudentIndex(int rollNo) {
        for (int i = 0; i < count; i++) {
            if (students[i].rollNo == rollNo) return i;
        }
        return -1;
    }
}
