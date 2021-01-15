import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-second-gui-modal',
  templateUrl: './second-gui-modal.component.html',
  styleUrls: ['./second-gui-modal.component.scss']
})
export class SecondGuiModalComponent implements OnInit {

  // Configuration for Mat Dialog Component
  constructor(
    public dialogRef: MatDialogRef<SecondGuiModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: SecondGuiModalComponent
  ) { }

  ngOnInit() {
  }

}
