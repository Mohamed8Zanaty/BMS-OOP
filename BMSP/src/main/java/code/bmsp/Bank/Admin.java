package code.bmsp.Bank;

import code.bmsp.Enums.Grade;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Admin extends Employee{
    private static final String username= new String("admin");
    private static final String password= new String("admin");
    private static ArrayList<Employee> acceptedEmployees = new ArrayList<>();
    private static ArrayList<Applicants> applicants = new ArrayList<>();
    // private static ArrayList<Transaction> watitiedTransactions = new ArrayList<>();//in_employee
    private static ArrayList<Transaction> acceptedTransactions = new ArrayList<>();
    private static Admin admin = null;
    private Admin() {}
    public static Admin getInstance() {
        if(admin == null) {
            admin = new Admin();
            return admin;
        }
        return admin;
    }

    private Year acceptedYear(){
        Year currentYear=Year.now();
        Year limit=Year.of(40);
        Year year=Year.of(currentYear.getValue()-limit.getValue());
        return year;
    }
    //METHODS
    public void insertEmployee(int num) {//number he want to insert
        final int index=0;
        int counter = 0;
        while (counter < num) {
            if (applicants.get(index).getYearOfGraduation().isAfter(acceptedYear())&&applicants.get(index).getYearOfGraduation().isBefore(Year.now())){
                if(applicants.get(index).getPosition().equals("HR") && !(applicants.get(index).getGraduateCollege().equals("COMMERCE")))
                {
                    if (applicants.get(index).getTotalGrade().equals(Grade.EXCELLENT)||applicants.get(index).getTotalGrade().equals(Grade.VERY_GOOD)) {
                        applicants.get(index).setPosition("HR");
                        applicants.get(index).setInitialSalary(5000);
                    }
                }
                if(applicants.get(index).getPosition().equals("ACCOUNTANT") && (applicants.get(index).getGraduateCollege().equals("COMMERCE")))
                {
                    if (applicants.get(index).getTotalGrade().equals(Grade.EXCELLENT)||applicants.get(index).getTotalGrade().equals(Grade.VERY_GOOD)) {
                        applicants.get(index).setPosition("ACCOUNTANT");
                        applicants.get(index).setInitialSalary(10000);
                    }
                }
//                acceptedEmployees.add(new Employee(applicants.get(index).getFirstName(),applicants.get(index).getLastName(),applicants.get(index).getPhone(),
//                        applicants.get(index).getPosition(),applicants.get(index).getGraduateCollege(),
//                        applicants.get(index).getYearOfGraduation().getValue(),applicants.get(index).getTotalGrade()));
//                      applicants.remove(index);
//                     counter++;
                //give him ID && username
            }
            else{
                applicants.remove(index);
            }
        }
    }
    public void deleteAccountEmployee(String username,String id){
        acceptedEmployees.stream()
                .filter(Emp->Emp.getAccount().getUserName().equals(username)&&Emp.getSSN().equals(id))
                .forEach(Emp -> {
                    acceptedEmployees.remove(Emp);
                });
    }


    public ArrayList<Client> showAllClients(){
        ArrayList<Client> sortedByBalance =
                acceptedClients().stream()
                        .sorted(Comparator.comparing(Client::getTotalBalance).reversed()) // Sort by balance reversed
                        .collect(Collectors.toCollection(ArrayList::new));
        return sortedByBalance;
    }

    public ArrayList<Employee> showAllEmployees(){
        return acceptedEmployees;
    }

    public ArrayList<Transaction> showTodayTransactions(){
        ArrayList<Transaction>Transactions=new ArrayList<>();
        getListOfTransactions().stream()
                .filter(transaction->transaction.getDate().equals(LocalDate.now()))
                .forEach(transaction -> {
                    Transactions.add(transaction);
                });
        return Transactions ;
    }

    public void confirmTransactions(){} //based  on type of transaction
    public void modifyBenefits()  {}   //based  on type of account

//    public void getFeedBacks(){}
//    public void sendEmails(){}
//salary employee
//whose total balance grater than ...//make him choose sympole

}
