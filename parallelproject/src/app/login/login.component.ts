import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error: string;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser(form: NgForm){
    this.authService.login(form.value).subscribe(response => {
      console.log(response);
      if (response.error){
        alert('Invalid Credentials,Please Login Again!!');
        this.error = response.message;
        setTimeout(() => {
          this.error = null;
        }, 5000);
      } else{
        localStorage.setItem('user', JSON.stringify(response));
        if (response.user.role === 'admin'){
        this.router.navigateByUrl('/admin');
        }else if (response.user.role === 'student'){
          this.router.navigateByUrl('/student');
        }
      }
    });
  }
}
