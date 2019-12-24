export class Magazine {
  price: number;
  name: string;
  id: string;

  constructor(n: string, p: number, i: string) {
    this.name = n;
    this.price = p;
    this.id = i;
  }
}
