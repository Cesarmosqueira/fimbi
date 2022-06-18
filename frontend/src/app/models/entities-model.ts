export class Login {
  username : string;
  email : string;
  password : string;
}
export class LoginOptional {
  username : string | null;
  email : string | null;
  password : string | null;
}

export class User {
  username : string;
  password : string;
  email : string;
  birth_date : string;
  image_url : string;
}
export class FimbiResponse {
  description : string;
  code : number;
}

export class Bond {
  id: number;
  capitalization_rate: string;
  due_date: Date;
  external_interest_rate: string;
  interest_rate: number;
  issuer_identifier: string;
  nominal_value: number;
  splits: number;
}

export class IssuerReq {
  market_identifier : string;
  description : string;
  image_url : string;
}

export class Issuer {
  market_identifier : string;
  description : string;
  date_joined : string;
  image_url : string;
}

export class Purchase {
  userDto : User;
  bondDto : Bond;
  purchase_date : Date;
}
