
import java.util.*;
import java.util.stream.Collectors;
public class StudentDatabase {
	    private List<Student> students = new ArrayList<>();
	    private int nextId = 1;

	    public void addStudent(String name, int age, String department, double cgpa, String email) {
	        if (findByEmail(email) != null) {
	            System.out.println("  ✗  A student with this email already exists.");
	            return;
	        }
	        Student s = new Student(nextId++, name, age, department, cgpa, email);
	        students.add(s);
	        System.out.println("  ✓  Student added successfully! (ID: " + s.getId() + ")");
	    }

	    // ──────────────────────────────────────────────
	    // READ ALL
	    public void displayAll() {
	        if (students.isEmpty()) {
	            System.out.println("  No student records found.");
	            return;
	        }
	        printHeader();
	        students.forEach(s -> System.out.println(s));
	        printFooter();
	        System.out.println("  Total records: " + students.size());
	    }

	    // READ ONE
	    // ──────────────────────────────────────────────
	    public void searchById(int id) {
	        Student s = findById(id);
	        if (s == null) {
	            System.out.println("  ✗  No student found with ID " + id);
	        } else {
	            printHeader();
	            System.out.println(s);
	            printFooter();
	        }
	    }

	    public void searchByName(String name) {
	        List<Student> results = students.stream()
	            .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
	            .collect(Collectors.toList());

	        if (results.isEmpty()) {
	            System.out.println("  ✗  No student found with name containing \"" + name + "\"");
	        } else {
	            printHeader();
	            results.forEach(s -> System.out.println(s));
	            printFooter();
	            System.out.println("  Found " + results.size() + " record(s).");
	        }
	    }

	    public void searchByDepartment(String dept) {
	        List<Student> results = students.stream()
	            .filter(s -> s.getDepartment().equalsIgnoreCase(dept))
	            .collect(Collectors.toList());

	        if (results.isEmpty()) {
	            System.out.println("  ✗  No students found in department \"" + dept + "\"");
	        } else {
	            printHeader();
	            results.forEach(s -> System.out.println(s));
	            printFooter();
	            System.out.println("  Found " + results.size() + " record(s).");
	        }
	    }

	    // ──────────────────────────────────────────────
	    // UPDATE
	    // ──────────────────────────────────────────────
	    public void updateStudent(int id, String name, int age, String department, double cgpa, String email) {
	        Student s = findById(id);
	        if (s == null) {
	            System.out.println("  ✗  No student found with ID " + id);
	            return;
	        }
	        // Check email conflict with another student
	        Student emailOwner = findByEmail(email);
	        if (emailOwner != null && emailOwner.getId() != id) {
	            System.out.println("  ✗  Email already used by student ID " + emailOwner.getId());
	            return;
	        }
	        s.setName(name);
	        s.setAge(age);
	        s.setDepartment(department);
	        s.setCgpa(cgpa);
	        s.setEmail(email);
	        System.out.println("  ✓  Student ID " + id + " updated successfully.");
	    }

	    // ──────────────────────────────────────────────
	    // DELETE
	    // ──────────────────────────────────────────────
	    public void deleteStudent(int id) {
	        Student s = findById(id);
	        if (s == null) {
	            System.out.println("  ✗  No student found with ID " + id);
	            return;
	        }
	        students.remove(s);
	        System.out.println("  ✓  Student \"" + s.getName() + "\" (ID: " + id + ") deleted.");
	    }

	    // ──────────────────────────────────────────────
	    // STATISTICS
	    // ──────────────────────────────────────────────
	    public void showStatistics() {
	        if (students.isEmpty()) {
	            System.out.println("  No data to compute statistics.");
	            return;
	        }
	        DoubleSummaryStatistics stats = students.stream()
	            .mapToDouble(Student::getCgpa)
	            .summaryStatistics();

	        Student topper = students.stream()
	            .max(Comparator.comparingDouble(Student::getCgpa))
	            .orElse(null);

	        Map<String, Long> deptCount = students.stream()
	            .collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));

	        System.out.println("\n  ╔══════════════════════════════════════╗");
	        System.out.println("  ║           DATABASE STATISTICS         ║");
	        System.out.println("  ╠══════════════════════════════════════╣");
	        System.out.printf ("  ║  Total Students   : %-17d║%n", students.size());
	        System.out.printf ("  ║  Average CGPA     : %-17.2f║%n", stats.getAverage());
	        System.out.printf ("  ║  Highest CGPA     : %-17.2f║%n", stats.getMax());
	        System.out.printf ("  ║  Lowest CGPA      : %-17.2f║%n", stats.getMin());
	        if (topper != null)
	            System.out.printf("  ║  Topper           : %-17s║%n", topper.getName());
	        System.out.println("  ╠══════════════════════════════════════╣");
	        System.out.println("  ║  Students per Department:             ║");
	        deptCount.forEach((dept, count) ->
	            System.out.printf("  ║    %-15s : %-16d║%n", dept, count));
	        System.out.println("  ╚══════════════════════════════════════╝\n");
	    }

	    // ──────────────────────────────────────────────
	    // SORT
	    // ──────────────────────────────────────────────
	    public void sortByName() {
	        List<Student> sorted = students.stream()
	            .sorted(Comparator.comparing(Student::getName))
	            .collect(Collectors.toList());
	        printHeader();
	        sorted.forEach(s -> System.out.println(s));
	        printFooter();
	    }

	    public void sortByCgpa() {
	        List<Student> sorted = students.stream()
	            .sorted(Comparator.comparingDouble(Student::getCgpa).reversed())
	            .collect(Collectors.toList());
	        printHeader();
	        sorted.forEach(s -> System.out.println(s));
	        printFooter();
	    }

	    // ──────────────────────────────────────────────
	    // HELPERS
	    // ──────────────────────────────────────────────
	    private Student findById(int id) {
	        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
	    }

	    private Student findByEmail(String email) {
	        return students.stream().filter(s -> s.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
	    }

	    private void printHeader() {
	        System.out.println("\n  +" + "-".repeat(7) + "+" + "-".repeat(22) + "+" + "-".repeat(6) + "+"
	            + "-".repeat(17) + "+" + "-".repeat(8) + "+" + "-".repeat(27) + "+");
	        System.out.printf("  | %-5s | %-20s | %-4s | %-15s | %-6s | %-25s |%n",
	            "ID", "Name", "Age", "Department", "CGPA", "Email");
	        System.out.println("  +" + "-".repeat(7) + "+" + "-".repeat(22) + "+" + "-".repeat(6) + "+"
	            + "-".repeat(17) + "+" + "-".repeat(8) + "+" + "-".repeat(27) + "+");
	    }

	    private void printFooter() {
	        System.out.println("  +" + "-".repeat(7) + "+" + "-".repeat(22) + "+" + "-".repeat(6) + "+"
	            + "-".repeat(17) + "+" + "-".repeat(8) + "+" + "-".repeat(27) + "+\n");
	    }

	    public int getCount() { return students.size(); }
	}


