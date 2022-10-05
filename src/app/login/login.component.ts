import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from '../security/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  public myGroup!: FormGroup;
  public hasError: boolean = false;
  public subscription = new Subscription();

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.createForm();
  }

  private createForm() {
    this.myGroup = this.formBuilder.group(
      {
        formUserName: new FormControl(),
        formPassword: new FormControl(),
      }
    )
  }

  public login() {
    this.hasError = false;
    let username = this.myGroup.get('formUserName')?.value;
    let password = this.myGroup.get('formPassword')?.value;
    this.subscription.add(
      this.authService.login(username, password).subscribe({
        next: data => {
          console.log(data);
          this.authService.setToken(data);
          this.authService.setCheckIsLoggedIn(true);
          this.router.navigateByUrl('/');
        },

        error: err => {
          console.log(err);
          this.hasError = true;
          this.authService.setCheckIsLoggedIn(false);
        }
      })
    );
  }

  public ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
