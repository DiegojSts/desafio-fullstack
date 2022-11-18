export interface Person {
  id?: number;
  name: string;
  cpf: string;
  birthDate?: Date;
  contacts: [
    {
      contactId?: number;
      contactName: string;
      email: string;
      phone: string;
    }
  ];
}
