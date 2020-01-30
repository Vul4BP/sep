export class CreatePlanRequest {
  price: number;
  currency: string;
  nameOfJournal: string;
  description: string;
  typeOfPlan: string;
  frequencyPayment: string;
  frequencyInterval: number;
  cycles: number;

  constructor(p: number, c: string, n: string, d: string, t: string, f: string, i: number, cy: number) {
    this.price = p;
    this.currency = c;
    this.nameOfJournal = n;
    this.description = d;
    this.typeOfPlan = t;
    this.frequencyPayment = f;
    this.frequencyInterval = i;
    this.cycles = cy;
  }
}
