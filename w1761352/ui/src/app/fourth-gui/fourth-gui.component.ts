import { Component, OnInit } from '@angular/core';
import { IMatch } from '../models';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-fourth-gui',
  templateUrl: './fourth-gui.component.html',
  styleUrls: ['./fourth-gui.component.scss']
})
export class FourthGuiComponent implements OnInit {

  matches : IMatch[] = [];

  constructor(private dataService: DataService) {
  }

  //Sending request to get all matches to display in data table
  ngOnInit() {
    this.dataService.sendGetRequest('match/matches').subscribe((data: IMatch[]) => {
      this.matches = data;
    });
  }

  //Sending request to get all matches in ascending order of date
  sort() {
    this.dataService.sendGetRequest('match/matches/order-by-date').subscribe((data: IMatch[]) => {
      this.matches = data;
    });
  }

}
