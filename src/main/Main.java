package main;

import java.util.Scanner;

class Employee {
    String kode;
    String nama;
    String jenisKelamin;
    String jabatan;
    double gaji;
    
  Employee(String kode, String nama, String jenisKelamin, String jabatan, double gaji) {
    this.kode = kode;
    this.nama = nama;
    this.jenisKelamin = jenisKelamin;
    this.jabatan = jabatan;
    this.gaji = gaji;
    }
}

public class Main {
	static int employeeCount = 0;
	static Employee[] employees = {
           /*new Employee("AA-5512", "Aaron Sebastian", "Laki-laki", "Supervisor", 6000000),
           new Employee("GJ-1901", "Alicia Gabrielle", "Perempuan", "Supervisor", 6450000),
           new Employee("AL-0991", "Calvin Nicholas", "Laki-laki", "Supervisor", 6450000),
           new Employee("ZZ-1123", "Felix Jonathan", "Laki-laki", "Manager", 8000000),
           new Employee("JO-9912", "Herman Kuding", "Laki-laki", "Supervisor", 6450000)*/
	   };
	static String namaKaryawan= "";
	static String jenisKelamin = "";
	static String jabatan = "";
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int choice;
		
		do {
			
			System.out.println("========================");
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karayawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karyawan");
			System.out.println("5. Exit");
			System.out.print("Pilih Menu (1-5): ");
			
			while (!scan.hasNextInt()) {
	            System.out.println("Invalid input. Please enter an integer (1-5).");
	            scan.next();
	        }
			
			choice = scan.nextInt();
			scan.nextLine();
			
			switch (choice) {
			case 1:
				insertEmployeeData();			
			break;
			case 2:
				viewEmployeeData();
			break;
			case 3:
				updateEmployeeData();
			break;
			case 4:
				deleteEmployeeData();
			break;
			case 5:
				System.out.println("Exited the Program.");
			default:
			break;
			}
			System.out.println("");
			
			
			
		}while(choice != 5);
		
		scan.close();
	}
	
	static void insertEmployeeData() {
	    Scanner scan = new Scanner(System.in);

	    do {
	        System.out.print("Input nama karyawan [>= 3]: ");
	        namaKaryawan = scan.nextLine();
	    } while (namaKaryawan.length() < 3);

	    do {
	        System.out.print("Input jenis kelamin [Laki-laki / Perempuan]: ");
	        jenisKelamin = scan.nextLine().toLowerCase();
	    } while (!jenisKelamin.equals("laki-laki") && !jenisKelamin.equals("perempuan"));

	    do {
	        System.out.print("Input Jabatan [Manager / Supervisor / Admin]: ");
	        jabatan = scan.nextLine().toLowerCase();
	    } while (!(jabatan.equals("manager") || jabatan.equals("supervisor") || jabatan.equals("admin")));

	    StringBuilder kodeKaryawan = new StringBuilder();

	    System.out.print("Berhasil menambahkan karyawan dengan id ");
	    for (int i = 0; i < 2; i++) {
	        kodeKaryawan.append((char) (Math.random() * 26 + 65));
	    }

	    kodeKaryawan.append("-");
	    kodeKaryawan.append((int) (Math.random() * 9000 + 1000));

	    String idKaryawan = kodeKaryawan.toString();

	    System.out.println(idKaryawan);
	    int baseGaji = gajiJabatan(jabatan);
	    double bonus = bonusGaji(jabatan, employeeCount);
	    double totalGaji = (baseGaji * (1 + bonus));

	    Employee newEmployee = new Employee(idKaryawan, namaKaryawan, jenisKelamin, jabatan, totalGaji);

	    employees[employeeCount++] = newEmployee;
	    System.out.println("ENTER to return");
	    scan.nextLine();

	}

static int gajiJabatan(String jabatan) {

if (jabatan.equalsIgnoreCase("manager")) {
    return 8000000;
} else if (jabatan.equalsIgnoreCase("supervisor")) {
    return 6000000;
} else if (jabatan.equalsIgnoreCase("admin")) {
    return 4000000;
} else {
    return 0;
}
}

static double bonusGaji(String jabatan, int employeeCount) {
    double bonus = 0.0;
    int bonusThreshold = 3;
    int bonusCount;
    
    if (jabatan.equalsIgnoreCase("manager")) {
    	bonusCount = employeeCount / bonusThreshold;
        if (employeeCount <= bonusThreshold) {
        	for (int i = 0 ; i < bonusCount ; i++){
        		   for (int j = 0 ; i < 3 ; i++){
        		   bonus = 0.1;}
        	}
        }
    } else if (jabatan.equalsIgnoreCase("supervisor")) {
        if (employeeCount < bonusThreshold) {
            bonus = 0.075;
        }
    } else if (jabatan.equalsIgnoreCase("admin")) {
        int bonusRecipientCount = Math.min(employeeCount, bonusThreshold);
        bonus = 0.05 * bonusRecipientCount;
    } else {
        bonus = 0;
    }

    return bonus;
}

static void viewEmployeeData() {
	Scanner scan = new Scanner(System.in);
	System.out.println("|----|-----------------|--------------------|--------------|-----------|-----------|");
    System.out.println("|No  |Kode Karyawan    |Nama Karyawan       |Jenis Kelamin |Jabatan    |Gaji       |");
    System.out.println("|----|-----------------|--------------------|--------------|-----------|-----------|");

    
    for (int i = 0; i < employeeCount ; i++) {
        Employee employee = employees[i];
        System.out.printf("|%-4d|%-17s|%-20s|%-14s|%-11s|%-11.2f|\n", i+1, employee.kode, employee.nama, employee.jenisKelamin, employee.jabatan, employee.gaji);
    }

    System.out.println("|----|-----------------|--------------------|--------------|-----------|-----------|");
    System.out.println("ENTER to return");
    scan.nextLine();
}



static void updateEmployeeData() {
	viewEmployeeData();
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter the employee number you want to update: ");
    int employeeNumber = scan.nextInt();

    if (employeeNumber >= 1 && employeeNumber <= employeeCount) {
        Employee employeeToUpdate = employees[employeeNumber - 1];

        System.out.print("Enter new name: ");
        String newName = scan.next();
        employeeToUpdate.nama = newName;

        System.out.println("Employee data updated successfully.");
    } else {
        System.out.println("Invalid employee number.");
    }
    System.out.println("ENTER to return");
    scan.nextLine();
}

static void deleteEmployeeData() {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter the employee number you want to delete: ");
    int employeeNumber = scan.nextInt();

    if (employeeNumber >= 1 && employeeNumber <= employeeCount) {
        for (int i = employeeNumber - 1; i < employeeCount - 1; i++) {
            employees[i] = employees[i + 1];
        }
        employees[employeeCount - 1] = null;
        employeeCount--;

        System.out.println("Employee data deleted successfully.");
    } else {
        System.out.println("Invalid employee number.");
    }
    System.out.println("ENTER to return");
    scan.nextLine();
}

}
