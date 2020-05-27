import {Conference} from "./conference.model";

export class Proposal {
  id:number;
  name:string;
  keywords:string;
  topics:string;
  abstractPaper: File;
  fullPaper: File;
  accepted: boolean;
  conference:Conference;

  constructor(id, name, keywords, topics, abstractPaper, fullPaper, accepted, conference) {
    this.id = id;
    this.name = name;
    this.keywords = keywords;
    this.topics = topics;
    this.abstractPaper = abstractPaper;
    this.fullPaper = fullPaper;
    this.accepted = accepted;
    this.conference = conference;
  }
}
