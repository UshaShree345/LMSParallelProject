import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  message;
  error;
  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }
  registerUser(form: NgForm){
    this.authService.register(form.value).subscribe(response => {
      console.log(response);
      if (response.error){
        this.error = response.message;
        alert('Failed to Register');
        setTimeout( () => {
         this.error = null;
        }, 5000);
      }else{
        // this.message='Registered successfully';
        alert('Registered Successfully');
        this.router.navigateByUrl('/login');
        setTimeout( () => {
          this.message = null;
         }, 3000);
      }
      form.reset();
    });
  }
}
