import {Conference} from "./conference.model";

export class Proposal {
  id:number;
  name:string;
  keywords:string;
  topics:string;
  conference:Conference;

  constructor(id, name, keywords, topics, conference) {
    this.id = id;
    this.name = name;
    this.keywords = keywords;
    this.topics = topics;
    this.conference = conference;
  }
}
