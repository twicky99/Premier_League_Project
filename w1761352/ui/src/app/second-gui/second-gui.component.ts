import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { IMatch, IScore } from '../models';
import { SecondGuiModalComponent } from '../second-gui-modal/second-gui-modal.component';
import { DataService } from '../services/data.service';
@Component({
  selector: 'app-second-gui',
  templateUrl: './second-gui.component.html',
  styleUrls: ['./second-gui.component.scss']
})
export class SecondGuiComponent implements OnInit {

  scores: IScore[] = [];

  constructor(
    public dialog: MatDialog, private dataService: DataService
  ) { }

  ngOnInit() {
    this.getTeams();
  }

  // Sending request to get teams order by points for display in data table
  private getTeams(): void {
    this.dataService.sendGetRequest('team/teams', { orderBy: 'points' }).subscribe((data: IScore[]) => {
      this.scores = data;
    });
  }

  // Sending request to get random method to display in Mat Dialog
  randomMatch() {
    this.dataService.sendGetRequest('match/random').subscribe((data: IMatch) => {
      this.dialog.open(SecondGuiModalComponent, {
        data
      }).afterClosed().subscribe((evt: any) => {
        this.getTeams();
      });
    });
  }
}
