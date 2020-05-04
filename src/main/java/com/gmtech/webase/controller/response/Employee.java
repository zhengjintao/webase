package com.gmtech.webase.controller.response;

import java.math.BigDecimal;

public class Employee {
  BigDecimal salaryPerYear;
  BigDecimal bankAccounts;
  BigDecimal workingHoursPerDay;
  public Employee(BigDecimal salaryPerYear, BigDecimal bankAccounts, BigDecimal workingHoursPerDay) {
    this.salaryPerYear = salaryPerYear;
    this.bankAccounts = bankAccounts;
    this.workingHoursPerDay = workingHoursPerDay;
  }
public BigDecimal getSalaryPerYear() {
	return salaryPerYear;
}
public void setSalaryPerYear(BigDecimal salaryPerYear) {
	this.salaryPerYear = salaryPerYear;
}
public BigDecimal getBankAccounts() {
	return bankAccounts;
}
public void setBankAccounts(BigDecimal bankAccounts) {
	this.bankAccounts = bankAccounts;
}
public BigDecimal getWorkingHoursPerDay() {
	return workingHoursPerDay;
}
public void setWorkingHoursPerDay(BigDecimal workingHoursPerDay) {
	this.workingHoursPerDay = workingHoursPerDay;
}
}
