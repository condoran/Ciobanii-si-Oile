import {User} from "./user.model";
import {Conference} from "./conference.model";

export class Section {
  id:number;
  chair:User;
  speakers:User[];
  conference:Conference;
}
