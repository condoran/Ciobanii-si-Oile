import {User} from './user.model';
import {Section} from './section.model';

export class Conference {
  id: number;
  name: string;
  password: string;
  startDate: Date;
  endDate: Date;
  callForPapers: string;
  abstractPaperDeadline: Date;
  fullPaperDeadline: Date;
  biddingDeadline: Date;
  chair: User;
  fistCoChair: User;
  secondCoChair: User;
  sections: Section[];
}
