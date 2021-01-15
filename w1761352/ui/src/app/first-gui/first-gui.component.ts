import { Component, OnInit } from '@angular/core';
import { IScore } from '../models';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-first-gui',
  templateUrl: './first-gui.component.html',
  styleUrls: ['./first-gui.component.scss']
})
export class FirstGuiComponent implements OnInit {

  scores: IScore[] = [];

  constructor(private dataService: DataService) {
  }

  // Sending request to get teams
  ngOnInit() {
    this.dataService.sendGetRequest('team/teams').subscribe((data: any[]) => {
      this.scores = data;
    });
  }

  // Sending request to get teams by order to display data in table
  sort(event: string) {
    this.dataService.sendGetRequest('team/teams', { orderBy: event }).subscribe((data: IScore[]) => {
      this.scores = data;
    });
  }
}
