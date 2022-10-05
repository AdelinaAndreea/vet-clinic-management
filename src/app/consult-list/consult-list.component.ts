import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Consult } from '../model/consult';
import { ConsultService } from '../service/consult.service';

@Component({
  selector: 'app-consult-list',
  templateUrl: './consult-list.component.html',
  styleUrls: ['./consult-list.component.css']
})

export class ConsultListComponent implements OnInit {
  consults!: Consult[];
  constructor(private consultSerice: ConsultService, private router: Router) { }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData() {
    this.consultSerice.findAll().subscribe(data => {
      this.consults = data;
      console.log(this.consults);
    });
  }
}
