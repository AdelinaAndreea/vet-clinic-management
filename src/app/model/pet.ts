import { Client } from "./client";

export class Pet {
    id?: number;
    name?: string;
    race?: string;
    dateOfBirth?: Date;
    kilos?: number;
    isVaccinated?: Boolean;
    owner?: Client;
    ownerId?: number;
}
