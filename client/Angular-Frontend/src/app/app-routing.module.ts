import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateEmplyeeComponent } from './create-emplyee/create-emplyee.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { EmployeeDetailsComponent} from './employee-details/employee-details.component';

const routes : Routes =[
  {path:'employees' , component:EmployeeListComponent},
  {path:'create-employee' , component: CreateEmplyeeComponent},
  {path:'', redirectTo:'employees' , pathMatch:'full'},
  {path:'update-employee/:id' , component:UpdateEmployeeComponent},
  {path:'employee-details/:id' , component:EmployeeDetailsComponent}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports:[RouterModule]
})
export class AppRoutingModule { }
