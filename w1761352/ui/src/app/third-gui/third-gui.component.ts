import { Component, OnInit } from '@angular/core';
import { IMatch } from '../models';
import { DataService } from '../services/data.service';
import { DatePipe } from "@angular/common";

@Component({
  selector: 'app-third-gui',
  templateUrl: './third-gui.component.html',
  styleUrls: ['./third-gui.component.scss']
})
export class ThirdGuiComponent implements OnInit {

  matches: IMatch[] = [];
  day: number;
  month: number;
  year: number;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.matchRequest();
  }

  //if the date is empty get all matches and if it is not empty try to get the matches by date parameter
  searchByDate() {
	let date;
	if(!(this.day || this.month || this.year)){
		date = "";
	}
    else if(this.validateDate()){
		date = `${this.day}/${this.month}/${this.year}`;
	}
    this.matchRequest(date);
  }

  // Sending request to get matches by date
  private matchRequest(date: string = ""): void {
    this.dataService.sendGetRequest('match/matches', { date })
      .subscribe((data: IMatch[]) => {
        this.matches = data;
      }, (error) => {
        if (error.status === 409)
          alert(error.error)
      });
  }
  
  // Validation of fields day,month and year before sending the match request
  private validateDate(): boolean{
	  let dateString = `${this.day}/${this.month}/${this.year}`;
	  
	  if(dateString.includes("undefined") || dateString.includes("null") ){
		  alert("Fill the rest of fields")
		  return false;
	  }
	  if(!(this.day >= 1 && this.day <= 31)){
		  alert("Days are between 1 and 31")
		  return false;
	  }
	  
	  if(!(this.month >= 1 && this.month <= 12)){
		  alert("Months are between 1 and 12")
		  return false;
	  }
	  
	  if(!(this.year >= 2018 && this.year <= 2022)){
		  alert("Years are between 2019 and 2021")
		  return false;
	  }
	  return true;
  }

  // clear all the text fields
  private clear():void{
	  this.day = null;
	  this.month = null;
	  this.year = null;
  }
}
