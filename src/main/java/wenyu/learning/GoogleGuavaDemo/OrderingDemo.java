package wenyu.learning.GoogleGuavaDemo;

import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

class Employee implements Comparable  <Employee> {
	public int id;
	public String name;
	public int yearsOfService;

	public Employee(int id, String name, int yearsOfService){
		this.id = id;
		this.name = name;
		this.yearsOfService = yearsOfService;
	}

	public int compareTo(Employee employee) {
		return this.name.compareTo(employee.name);
	}

	@Override
	public String toString() {
		String toString = "id="+id+"-name="+name+"-years of service="+yearsOfService;
		return toString;
	}	 
}

public class OrderingDemo {

	private void printList(String comment, List<Employee> list) {
		System.out.println(comment);
		for(Employee employee : list) {
			System.out.println(employee.toString());
		}
	}
	
	private List<Employee> initData() {
		Employee sezinKarli = new Employee(4, "Sezin Karli", 4);
		Employee darthVader =  new Employee(3, "Darth Vader", 5);
		Employee hanSolo =  new Employee(2, "Han Solo", 10);
		List<Employee> employeeList = Lists.newArrayList(sezinKarli, hanSolo, darthVader);
		printList("Init Data" ,employeeList);
		
		return employeeList;
	}
	
	private void process(List<Employee> employeeList) {
		Comparator<Employee> yearsComparator = new Comparator < Employee >(){

			public int compare(Employee employee1, Employee employee2) {
				return (employee1.yearsOfService - employee2.yearsOfService); 
			}
		};
			
		Comparator < Employee > idComparator = new Comparator < Employee >(){

			public int compare(Employee employee1, Employee employee2) {
				return (employee1.id - employee2.id);
			}
		};
		
		//Create an Ordering from a Comparator
		Ordering<Employee> orderUsingYearsComparator = Ordering.from(yearsComparator);
		//Sort the employee list using years comparator
		List<Employee> sortedCopy = orderUsingYearsComparator.sortedCopy(employeeList);
		printList("Sorted Data", sortedCopy);
			
	}
	
	public void demoEntry() {
		List<Employee> list = initData();
		
		process(list);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OrderingDemo().demoEntry();
	}

}
