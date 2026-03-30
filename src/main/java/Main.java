
		// TODO Auto-generated method stub
		import java.util.InputMismatchException;
		import java.util.Scanner;

		public class Main {

		    static Scanner sc = new Scanner(System.in);
		    static StudentDatabase db = new StudentDatabase();

		    public static void main(String[] args) {
		        loadSampleData();
		        System.out.println("\n╔══════════════════════════════════════════════╗");
		        System.out.println("  ║   STUDENT DATABASE MANAGEMENT SYSTEM  v1.0   ║");
		        System.out.println("  ╚══════════════════════════════════════════════╝");

		        boolean running = true;
		        while (running) {
		            showMainMenu();
		            int choice = readInt("  Enter choice: ");
		            System.out.println();
		            switch (choice) {
		                case 1  -> addStudentMenu();
		                case 2  -> displayAllMenu();
		                case 3  -> searchMenu();
		                case 4  -> updateMenu();
		                case 5  -> deleteMenu();
		                case 6  -> db.showStatistics();
		                case 7  -> sortMenu();
		                case 0  -> { running = false; System.out.println("  Goodbye!\n"); }
		                default -> System.out.println("  ✗  Invalid option. Try again.\n");
		            }
		        }
		        sc.close();
		    }

		    // ──────────────────────────────────────────────
		    // MENUS
		    // ──────────────────────────────────────────────
		    static void showMainMenu() {
		        System.out.println("  ┌─────────────────────────────────┐");
		        System.out.println("  │           MAIN MENU             │");
		        System.out.println("  ├─────────────────────────────────┤");
		        System.out.println("  │  1. Add Student                 │");
		        System.out.println("  │  2. Display All Students        │");
		        System.out.println("  │  3. Search Student              │");
		        System.out.println("  │  4. Update Student              │");
		        System.out.println("  │  5. Delete Student              │");
		        System.out.println("  │  6. View Statistics             │");
		        System.out.println("  │  7. Sort Records                │");
		        System.out.println("  │  0. Exit                        │");
		        System.out.println("  └─────────────────────────────────┘");
		    }

		    static void addStudentMenu() {
		        System.out.println("  ── ADD NEW STUDENT ──────────────────");
		        String name  = readString("  Name       : ");
		        int    age   = readInt   ("  Age        : ");
		        String dept  = readString("  Department : ");
		        double cgpa  = readDouble("  CGPA (0-10): ");
		        String email = readString("  Email      : ");

		        if (cgpa < 0 || cgpa > 10) {
		            System.out.println("  ✗  CGPA must be between 0 and 10.");
		            return;
		        }
		        db.addStudent(name, age, dept, cgpa, email);
		        System.out.println();
		    }

		    static void displayAllMenu() {
		        System.out.println("  ── ALL STUDENT RECORDS ──────────────");
		        db.displayAll();
		    }

		    static void searchMenu() {
		        System.out.println("  ── SEARCH ───────────────────────────");
		        System.out.println("  1. Search by ID");
		        System.out.println("  2. Search by Name");
		        System.out.println("  3. Search by Department");
		        int choice = readInt("  Enter choice: ");
		        switch (choice) {
		            case 1 -> { int id = readInt("  Enter Student ID: "); db.searchById(id); }
		            case 2 -> { String name = readString("  Enter Name: ");       db.searchByName(name); }
		            case 3 -> { String dept = readString("  Enter Department: "); db.searchByDepartment(dept); }
		            default -> System.out.println("  ✗  Invalid option.");
		        }
		        System.out.println();
		    }

		    static void updateMenu() {
		        System.out.println("  ── UPDATE STUDENT ───────────────────");
		        int id = readInt("  Enter Student ID to update: ");
		        System.out.println("  (Enter new values for the student)");
		        String name  = readString("  New Name       : ");
		        int    age   = readInt   ("  New Age        : ");
		        String dept  = readString("  New Department : ");
		        double cgpa  = readDouble("  New CGPA       : ");
		        String email = readString("  New Email      : ");

		        if (cgpa < 0 || cgpa > 10) {
		            System.out.println("  ✗  CGPA must be between 0 and 10.");
		            return;
		        }
		        db.updateStudent(id, name, age, dept, cgpa, email);
		        System.out.println();
		    }

		    static void deleteMenu() {
		        System.out.println("  ── DELETE STUDENT ───────────────────");
		        int id = readInt("  Enter Student ID to delete: ");
		        System.out.print("  Are you sure? (yes/no): ");
		        String confirm = sc.next(); sc.nextLine();
		        if (confirm.equalsIgnoreCase("yes")) {
		            db.deleteStudent(id);
		        } else {
		            System.out.println("  Deletion cancelled.");
		        }
		        System.out.println();
		    }

		    static void sortMenu() {
		        System.out.println("  ── SORT ─────────────────────────────");
		        System.out.println("  1. Sort by Name (A-Z)");
		        System.out.println("  2. Sort by CGPA (Highest first)");
		        int choice = readInt("  Enter choice: ");
		        switch (choice) {
		            case 1 -> { System.out.println("  Sorted by Name:\n"); db.sortByName(); }
		            case 2 -> { System.out.println("  Sorted by CGPA:\n"); db.sortByCgpa(); }
		            default -> System.out.println("  ✗  Invalid option.");
		        }
		        System.out.println();
		    }

		    // ──────────────────────────────────────────────
		    // INPUT HELPERS
		    // ──────────────────────────────────────────────
		    static String readString(String prompt) {
		        System.out.print(prompt);
		        return sc.nextLine().trim();
		    }

		    static int readInt(String prompt) {
		        while (true) {
		            System.out.print(prompt);
		            try {
		                int val = sc.nextInt(); sc.nextLine();
		                return val;
		            } catch (InputMismatchException e) {
		                sc.nextLine();
		                System.out.println("  ✗  Please enter a valid integer.");
		            }
		        }
		    }

		    static double readDouble(String prompt) {
		        while (true) {
		            System.out.print(prompt);
		            try {
		                double val = sc.nextDouble(); sc.nextLine();
		                return val;
		            } catch (InputMismatchException e) {
		                sc.nextLine();
		                System.out.println("  ✗  Please enter a valid number.");
		            }
		        }
		    }

		    // ──────────────────────────────────────────────
		    // SAMPLE DATA
		    // ──────────────────────────────────────────────
		    static void loadSampleData() {
		        db.addStudent("Logeshwaran",    20, "CSE",  8.75, "logesh11@gmail.com");
		        db.addStudent("Raja krishnan",      21, "ECE",  9.10, "priya23@gmail.com");
		        db.addStudent("Rahul ",     19, "MECH", 7.45, "rahul46@gmail.com");
		        db.addStudent("Sneha ",    22, "CSE",  8.20, "sneha93@gmail.com");
		        db.addStudent("Vikram ",    20, "CIVIL",6.80, "vikram76@gmail.com");
		        System.out.println("  [Sample data loaded: 5 students]\n");
		    }
		

	}


