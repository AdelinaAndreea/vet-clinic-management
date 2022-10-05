import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './security/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Pet Clinic(nota 10!)';
  data = {} as any;
  public isLogged: boolean = false;
  imagePath = "../assets/animals.jpg";

  constructor(private http: HttpClient, private authService: AuthService) {
    // http.get('resource').subscribe(data => this.data = data);
  }

  public ngOnInit(): void {
    this.isLogged = false;
    this.authService.checkIsLoggedIn$.subscribe((data) => {
      this.isLogged = data;
    });
  }
}
