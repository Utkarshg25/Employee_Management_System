import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-create-emplyee',
  templateUrl: './create-emplyee.component.html',
  styleUrls: ['./create-emplyee.component.css']
})
export class CreateEmplyeeComponent implements OnInit {

  employee : Employee = new Employee();
  constructor(private employeeService : EmployeeService , 
    private router : Router) { }

  ngOnInit(): void {
  }

  saveEmployee(){
    this.employeeService.addEmployee(this.employee).subscribe(data =>{
      console.log(data);
      this.goToEmployee();
    },
    error => console.log(error));
  }

  goToEmployee(){
    this.router.navigate(["/employees"]);
  }

  onSubmit(){
    console.log(this.employee);
    this.saveEmployee();
  }

}
