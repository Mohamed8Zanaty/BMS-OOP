package code.bmsp.Bank;

import code.bmsp.Enums.Grade;

import java.time.Year;

public class Applicants extends User{
    private String graduateCollege;
    private Year yearOfGraduation;
    private Grade totalGrade;
    private String position;
    private double initialSalary=0;
    Applicants(String name, String SSN, String phone, String graduateCollege, Year yearOfGraduation, Grade totalGrade,String position){
        super(name,SSN,phone);
        this.yearOfGraduation=yearOfGraduation;
        this.graduateCollege=graduateCollege;
        this.position=position;
        this.totalGrade=totalGrade;
    }

    public double getInitialSalary() {
        return initialSalary;
    }

    public void setInitialSalary(double initialSalary) {
        this.initialSalary = initialSalary;
    }

    public String getGraduateCollege() {
        return graduateCollege;
    }

    public void setGraduateCollege(String graduateCollege) {
        this.graduateCollege = graduateCollege;
    }

    public Year getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(Year yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public Grade getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Grade totalGrade) {
        this.totalGrade = totalGrade;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
