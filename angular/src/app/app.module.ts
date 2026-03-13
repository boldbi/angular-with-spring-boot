import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { appService } from './app.service';

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot([]),
    HttpClientModule
  ],
  providers: [appService],
  declarations: [
    AppComponent
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
