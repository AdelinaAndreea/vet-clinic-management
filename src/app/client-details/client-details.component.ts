import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from '../model/client';
import { ClientService } from '../service/client.service';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  id!: number
  client!: Client;
  public myGroup!: FormGroup;

  constructor(private clientService: ClientService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder) {
    this.client = this.router.getCurrentNavigation()?.extras.state?.['clientJson'];

  }

  ngOnInit(): void {
    this.createForm();
  }

  private createForm() {
    this.myGroup = this.formBuilder.group(
      {
        formClientFirstName: new FormControl(this.client.firstName),
        formClientLastName: new FormControl(this.client.lastName),
        formEmail: new FormControl(this.client.email),
        formPhoneNumber: new FormControl(this.client.phoneNumber),
      }
    )
  }

  updateClient() {
    let clientTemp: Client = new Client();
    clientTemp.id = this.client.id;
    clientTemp.firstName = this.myGroup.get('formClientFirstName')?.value;
    clientTemp.lastName = this.myGroup.get('formClientLastName')?.value;
    clientTemp.email = this.myGroup.get('formEmail')?.value;
    clientTemp.phoneNumber = this.myGroup.get('formPhoneNumber')?.value;

    this.clientService.updateClientById(clientTemp).subscribe(clientDto => {
      this.client.firstName = clientDto.firstName;
      this.client.lastName = clientDto.lastName;
      this.client.email = clientDto.email;
      this.client.phoneNumber = clientDto.phoneNumber;

    });
    this.router.navigateByUrl('/clients');
  }
}