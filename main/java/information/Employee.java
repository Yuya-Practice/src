package information;

// json用の型
public class Employee {
	private boolean checker = true;
	private int employeeno;
	private String name;
	private String department;
	
	// コンストラクタ
	public Employee(int employeeno, String name, String department) {
		super();
		this.employeeno = employeeno;
		this.name = name;
		this.department = department;
	}

	public boolean isChecker() {
		return checker;
	}
	
	public int getEmployeeno() {
		return employeeno;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}
	
}
