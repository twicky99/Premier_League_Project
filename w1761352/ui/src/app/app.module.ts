import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FirstGuiComponent } from './first-gui/first-gui.component';
import { SecondGuiComponent } from './second-gui/second-gui.component';
import { ThirdGuiComponent } from './third-gui/third-gui.component';
import { FourthGuiComponent } from './fourth-gui/fourth-gui.component';
import { MainGuiComponent } from './main-gui/main-gui.component';
import { ErrorComponent } from './error/error.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SecondGuiModalComponent } from './second-gui-modal/second-gui-modal.component';
import { MatDialogModule, MatButtonModule, MatCardModule , MatIconModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    FirstGuiComponent,
    SecondGuiComponent,
    ThirdGuiComponent,
    FourthGuiComponent,
    MainGuiComponent,
    ErrorComponent,
    SecondGuiModalComponent
  ],
  entryComponents: [
    SecondGuiModalComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    MatCardModule,
	  MatIconModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
