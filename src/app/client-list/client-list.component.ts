import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../model/client';
import { ClientService } from '../service/client.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ConnectableObservable } from 'rxjs';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})

export class ClientListComponent implements OnInit, OnChanges {
  clients!: Client[];
  public myGroup!: FormGroup;
  public isAddClientFormVisible: boolean = false;

  constructor(private clientService: ClientService, private router: Router, private formBuilder: FormBuilder) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }

  ngOnInit() {
    this.isAddClientFormVisible = false;
    this.loadData();
  }

  private loadData() {
    this.clientService.findAll().subscribe(data => {
      this.clients = data;
      console.log(data);
    });
  }

  deleteClient(id: any) {
    this.clientService.deleteClientById(id).subscribe(data => {
      this.ngOnInit();
      console.log("Client with " + id + " has been removed")
    })
  }

  onEdit(client: Client) {
    this.router.navigateByUrl('/clients/details', { state: { clientJson: client } });
  }

  onViewPets(ownerId: any) {
    this.router.navigateByUrl('/pets/' + ownerId);
  }

  addClient() {
    this.isAddClientFormVisible = true;
    this.myGroup = this.formBuilder.group(
      {
        formClientFirstName: new FormControl(),
        formClientLastName: new FormControl(),
        formEmail: new FormControl(),
        formPhoneNumber: new FormControl(),
      }
    )
  }

  saveClient() {
    let clientTemp: Client = new Client();
    clientTemp.firstName = this.myGroup.get('formClientFirstName')?.value;
    clientTemp.lastName = this.myGroup.get('formClientLastName')?.value;
    clientTemp.email = this.myGroup.get('formEmail')?.value;
    clientTemp.phoneNumber = this.myGroup.get('formPhoneNumber')?.value;

    this.clientService.createClient(clientTemp).subscribe(data => {
      this.ngOnInit();
    })
  }
}