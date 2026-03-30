
public class Student {
	    private int id;
	    private String name;
	    private int age;
	    private String department;
	    private double cgpa;
	    private String email;

	    public Student(int id, String name, int age, String department, double cgpa, String email) {
	        this.id = id;
	        this.name = name;
	        this.age = age;
	        this.department = department;
	        this.cgpa = cgpa;
	        this.email = email;
	    }

	    // Getters
	    public int getId()           { return id; }
	    public String getName()      { return name; }
	    public int getAge()          { return age; }
	    public String getDepartment(){ return department; }
	    public double getCgpa()      { return cgpa; }
	    public String getEmail()     { return email; }

	    // Setters
	    public void setName(String name)           { this.name = name; }
	    public void setAge(int age)                { this.age = age; }
	    public void setDepartment(String dept)     { this.department = dept; }
	    public void setCgpa(double cgpa)           { this.cgpa = cgpa; }
	    public void setEmail(String email)         { this.email = email; }

	    @Override
	    public String toString() {
	        return String.format(
	            "| %-5d | %-20s | %-4d | %-15s | %-6.2f | %-25s |",
	            id, name, age, department, cgpa, email
	        );
	    }
	}


