import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot } from '@angular/router';

@Injectable({
    providedIn: 'root'
})

export class AuthGuard implements CanActivate {

    canActivate(route: ActivatedRouteSnapshot): boolean {

        const expectedRoles = route.data.roles;
        const loginInfo = JSON.parse(localStorage.getItem('user'));
        let role;
        for (let index in expectedRoles) {
            if (expectedRoles[index] === loginInfo.user.role) {
                role = expectedRoles[index];
            }
        }
        if (loginInfo && loginInfo.user.role === role) {
            return true;
        } else {
            return false;
        }
    }
}
