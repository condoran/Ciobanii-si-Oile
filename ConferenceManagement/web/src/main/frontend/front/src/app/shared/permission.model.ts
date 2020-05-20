import {User} from "./user.model";
import {Conference} from "./conference.model";

export class Permission {
  id:number;
  conference:Conference;
  user: User;
  isAuthor:boolean;
  isPCMember:boolean;
  isSectionChair:boolean;

}
