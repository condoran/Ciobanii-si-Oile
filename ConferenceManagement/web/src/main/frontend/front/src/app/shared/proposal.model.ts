import {Conference} from "./conference.model";

export class Proposal {
  id:number;
  name:string;
  keywords:string;
  topics:string;
  abstractPaper: File;
  fullPaper: File;
  status: string;
  conference:Conference;

  constructor(id, name, keywords, topics, abstractPaper, fullPaper, status, conference) {
    this.id = id;
    this.name = name;
    this.keywords = keywords;
    this.topics = topics;
    this.abstractPaper = abstractPaper;
    this.fullPaper = fullPaper;
    this.status = status;
    this.conference = conference;
  }
}
