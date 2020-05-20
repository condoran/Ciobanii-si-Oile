import {User} from "./user.model";
import {Conference} from "./conference.model";

export class Proposal {
  id:number;
  name:string;
  keywords:string;
  topics:string;
  authors:User[];
  conference:Conference;
}
