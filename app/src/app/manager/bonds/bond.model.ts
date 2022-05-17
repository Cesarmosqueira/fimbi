export class Bond {
  id?: number;
  issuer_id?: number;
  emission_date?: Date;
  due_date?: Date;
  nominal_value?: number;
  interest_rate?: number;
  capitalization_rate?: number;
  external_interest_rate?: string;
  available?: boolean;
}

