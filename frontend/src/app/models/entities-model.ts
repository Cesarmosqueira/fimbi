export class Login {
  username : string;
  email : string;
  password : string;
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
  msg : string;
}

export class Issuer {
  market_identifier : string;
  description : string;
  date_joined : string;
  image_url : string;
}
