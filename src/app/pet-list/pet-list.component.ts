import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Pet } from '../model/pet';
import { PetServiceService } from '../service/pet-service.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Consult } from '../model/consult';
import { ConsultService } from '../service/consult.service';


@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.component.html',
  styleUrls: ['./pet-list.component.css']
})
export class PetListComponent implements OnInit {

  pets !: Pet[];
  public isVisible: boolean = false;
  ownerId: any;
  public isAddButtonVisible: boolean = false;
  public isNewConsultFormVisible: boolean = false;
  public myGroup!: FormGroup;
  public myGroup2!: FormGroup;
  public isShowPetFormVisible: boolean = false;
  public selectedPetId?: number = -1;
  imagePath = "../assets/img-pets.jpg";

  constructor(private petService: PetServiceService, private consultService: ConsultService, private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.ownerId = this.route.snapshot.paramMap.get('ownerId');
    console.log(this.ownerId);
    this.isShowPetFormVisible = false;
    this.isNewConsultFormVisible = false;
    if (this.ownerId == null) {
      this.isAddButtonVisible = false;
      this.petService.findAll().subscribe(data => {
        this.pets = data;
      });
    } else {
      this.isAddButtonVisible = true;
      this.petService.findPetById(this.ownerId).subscribe(data => {
        this.pets = data;

      });
    }
  }

  deletePet(id: any) {
    console.log("deletePetById");
    this.petService.deletePetById(id).subscribe(data => {
      this.ngOnInit();
      console.log("Pet with id " + id + " has been removed");
    })
  }

  addPet() {
    let petTemp: Pet = new Pet();
    petTemp.name = this.myGroup.get('formPetName')?.value;
    petTemp.race = this.myGroup.get('formRace')?.value;
    petTemp.dateOfBirth = this.myGroup.get('formDateOfBirth')?.value;
    petTemp.kilos = this.myGroup.get('formKilos')?.value;
    petTemp.isVaccinated = this.myGroup.get('formVaccinated')?.value;
    petTemp.ownerId = this.ownerId;

    this.petService.addPet(petTemp).subscribe(data => {
      this.ngOnInit();

    })
  }

  showPetForm() {
    this.isShowPetFormVisible = true;
    this.myGroup = this.formBuilder.group(
      {
        formPetName: new FormControl(),
        formRace: new FormControl(),
        formDateOfBirth: new FormControl(),
        formKilos: new FormControl(),
        formVaccinated: new FormControl(),
      }
    )
  }

  viewDetails(pet: Pet) {
    console.log("viewDetails");
    this.router.navigateByUrl('/pets/details', { state: { petJson: pet } });
  }

  showConsultForm(pet: Pet) {
    this.isNewConsultFormVisible = true;
    this.myGroup2 = this.formBuilder.group(
      {
        formDate: new FormControl(),
        formDescription: new FormControl(),
        formPetName: new FormControl(pet.name),
        formPrice: new FormControl(),
      }
    )
    this.selectedPetId = pet.id;
  }

  addConsult() {
    let consultTemp: Consult = new Consult();
    this.isNewConsultFormVisible = false;
    consultTemp.date = this.myGroup2.get('formDate')?.value;
    consultTemp.description = this.myGroup2.get('formDescription')?.value;
    consultTemp.petId = this.selectedPetId;
    consultTemp.price = this.myGroup2.get('formPrice')?.value;
    this.consultService.addConsult(consultTemp).subscribe(data => {
      this.ngOnInit();
    })
    this.selectedPetId = -1;
  }
}
