import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  isAdmin() {
    const userDetails = JSON.parse(localStorage.getItem('user'));
    if (userDetails && userDetails.user.role === 'admin') {
      return true;
    } else {
      return false;
    }
  }
  isUser() {
    const userDetails = JSON.parse(localStorage.getItem('user'));
    if (userDetails && userDetails.user.role === 'student') {
      return true;
    } else {
      return false;
    }
  }
  logout() {
    localStorage.removeItem('user');
    this.router.navigateByUrl('/login');
  }
}

