package java8streams.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class Employee_Queries {
	static List<Employee> employeeList = new ArrayList<Employee>();
	
	static {
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
	}

	public static void main(String[] strs) {
		
		// How many male and female employees are there in the organization?
		Map<String, Long> noOfMaleAndFemaleEmployees=
				employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
				         
				System.out.println(noOfMaleAndFemaleEmployees);
				
		//Print the name of all departments in the organization?
		employeeList.stream().map(Employee::getDepartment).distinct()
	            .forEach(System.out::println);
		//What is the average age of male and female employees?
		Map<String, Double> avgAgeOfMaleAndFemaleEmployees=
				employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
				         
			System.out.println(avgAgeOfMaleAndFemaleEmployees);
			
		//Get the details of highest paid employee in the organization?
			Optional<Employee> highestPaidEmployeeWrapper=
					employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
					         
					Employee highestPaidEmployee = highestPaidEmployeeWrapper.get();
					         
					System.out.println("Details Of Highest Paid Employee : ");
					
		//Get the names of all employees who have joined after 2015?
			Map<String, Long> employeeCountByDepartment=
							employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
							         
			Set<Entry<String, Long>> entrySet = employeeCountByDepartment.entrySet();
							         
							for (Entry<String, Long> entry : entrySet)
							{
							    System.out.println(entry.getKey()+" : "+entry.getValue());
							}
				
		//What is the average salary of each department?
			Map<String, Double> avgSalaryOfDepartments= employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
			         
						         
			for (Entry<String, Double> entry : avgSalaryOfDepartments.entrySet()) 
			{
			    System.out.println(entry.getKey()+" : "+entry.getValue());
			}
			
		//Get the details of youngest male employee in the product development department?
			
			Optional<Employee> youngestMaleEmployeeInProductDevelopmentWrapper=
					employeeList.stream()
					            .filter(e -> e.getGender()=="Male" && e.getDepartment()=="Product Development")
					            .min(Comparator.comparingInt(Employee::getAge));
					         
					Employee youngestMaleEmployeeInProductDevelopment = youngestMaleEmployeeInProductDevelopmentWrapper.get();
					         
					System.out.println("Details Of Youngest Male Employee In Product Development");
					 
		//Who has the most working experience in the organization?
					
					Optional<Employee> seniorMostEmployeeWrapper=
							employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
							         
							Employee seniorMostEmployee = seniorMostEmployeeWrapper.get();
							         
							System.out.println("Senior Most Employee Details :");
							
							
		//How many male and female employees are there in the sales and marketing team?
					Map<String, Long> countMaleFemaleEmployeesInSalesMarketing=
									employeeList.stream()
									            .filter(e -> e.getDepartment()=="Sales And Marketing")
									            .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
									 
					System.out.println(countMaleFemaleEmployeesInSalesMarketing);
		//What is the average salary of male and female employees?
					
					Map<String, Double> avgSalaryOfMaleAndFemaleEmployees=
							employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
							         
							System.out.println(avgSalaryOfMaleAndFemaleEmployees);	
		 //List down the names of all employees in each department?
					Map<String, List<Employee>> employeeListByDepartment=
									employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
					
		 //What is the average salary and total salary of the whole organization?
					DoubleSummaryStatistics employeeSalaryStatistics=
									employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
									         
					System.out.println("Average Salary = "+employeeSalaryStatistics.getAverage());
									         
					System.out.println("Total Salary = "+employeeSalaryStatistics.getSum());
		 
					
		// Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
					Map<Boolean, List<Employee>> partitionEmployeesByAge=
							employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));
							         
							Set<Entry<Boolean, List<Employee>>> entrySet1 = partitionEmployeesByAge.entrySet();
							         
							for (Entry<Boolean, List<Employee>> entry : entrySet1) 
							{
							    System.out.println("----------------------------");
							             
							    if (entry.getKey()) 
							    {
							        System.out.println("Employees older than 25 years :");
							    }
							    else
							    {
							        System.out.println("Employees younger than or equal to 25 years :");
							    }
							             
							    System.out.println("----------------------------");
							             
							    List<Employee> list = entry.getValue();
							             
							    for (Employee e : list) 
							    {
							        System.out.println(e.getName());
							    }
							}
		//Who is the oldest employee in the organization? What is his age and which department he belongs to?
					Optional<Employee> oldestEmployeeWrapper = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
					         
							Employee oldestEmployee = oldestEmployeeWrapper.get();
							         
							System.out.println("Name : "+oldestEmployee.getName());
							         
							System.out.println("Age : "+oldestEmployee.getAge());
							         
							System.out.println("Department : "+oldestEmployee.getDepartment());
		
	}				         
				
}
