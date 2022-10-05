import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PetServiceService } from '../service/pet-service.service';
import { Pet } from '../model/pet';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
    selector: 'app-pet-details',
    templateUrl: './pet-details.component.html',
    styleUrls: ['./pet-details.component.css']
})

export class PetDetailsComponent implements OnInit {
    id!: number;
    public pet!: Pet;
    public isVisible: boolean = false;
    public myGroup!: FormGroup;

    constructor(private petService: PetServiceService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder) {
        this.pet = this.router.getCurrentNavigation()?.extras.state?.['petJson'];
    }

    ngOnInit(): void {

        this.createForm();
    }

    reatPetByName() { }

    onEdit(item: Pet) {
        this.isVisible = true;
    }

    private createForm() {
        this.myGroup = this.formBuilder.group(
            {
                formPetName: new FormControl(this.pet.name),
                formRace: new FormControl(this.pet.race),
                formDateOfBirth: new FormControl(this.pet.dateOfBirth),
                formKilos: new FormControl(this.pet.kilos),
                formVaccinated: new FormControl(this.pet.isVaccinated),

            }
        )
    }

    updatePet() {
        this.isVisible = false;
        let petTemp: Pet = new Pet();
        petTemp.id = this.pet.id;
        petTemp.name = this.myGroup.get('formPetName')?.value;
        petTemp.race = this.myGroup.get('formRace')?.value;
        petTemp.dateOfBirth = this.myGroup.get('formDateOfBirth')?.value;
        petTemp.kilos = this.myGroup.get('formKilos')?.value;
        petTemp.isVaccinated = this.myGroup.get('formVaccinated')?.value;
        this.petService.updatePetById(petTemp).subscribe(petDto => {
            this.pet.name = petDto.name;
            this.pet.race = petDto.race;
            this.pet.dateOfBirth = petDto.dateOfBirth;
            this.pet.kilos = petDto.kilos;
            this.pet.isVaccinated = petDto.isVaccinated;
        });
    }
}


