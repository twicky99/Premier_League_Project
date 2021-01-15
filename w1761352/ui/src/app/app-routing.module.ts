import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { FirstGuiComponent } from './first-gui/first-gui.component';
import { FourthGuiComponent } from './fourth-gui/fourth-gui.component';
import { MainGuiComponent } from './main-gui/main-gui.component';
import { SecondGuiComponent } from './second-gui/second-gui.component';
import { ThirdGuiComponent } from './third-gui/third-gui.component';

//routes for Angular Premier League App
const routes: Routes = [
  {
    path: '',
    redirectTo: 'main-gui',
    pathMatch: 'full'
  },
  {
    path: 'main-gui',
    component: MainGuiComponent
  },
  {
    path: 'first-gui',
    component: FirstGuiComponent
  },
  {
    path: 'second-gui',
    component: SecondGuiComponent
  },
  {
    path: 'third-gui',
    component: ThirdGuiComponent
  },
  {
    path: 'fourth-gui',
    component: FourthGuiComponent
  },
  {
    path: '**',
    component: ErrorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
