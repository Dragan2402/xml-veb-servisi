import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AuthenticationService } from '../authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'euprava-app-nav',
  templateUrl: './app-nav.component.html',
  styleUrls: ['./app-nav.component.css']
})
export class AppNavComponent implements OnInit {

  isHandset$: Observable<boolean> = this.breakpointObserver
  .observe(Breakpoints.Handset)
  .pipe(
    map((result) => result.matches),
    shareReplay()
  );

  logged! : boolean;

  firstName! : string;

  lastName! :string;

  rola! :string;

  constructor(
    private breakpointObserver: BreakpointObserver, private authService :AuthenticationService, private router: Router

  ) {
    this.authService.logged$.subscribe((attr:boolean) =>{
      this.logged = attr;
    });
    this.authService.firstName$.subscribe((attr:string) =>{
      this.firstName = attr;
    });
    this.authService.lastName$.subscribe((attr:string) =>{
      this.lastName = attr;
    });
  }

  ngOnInit(): void {
  }


  navigateToProfile(){
    if(this.rola === "Sluzbenik"){
      this.router.navigate(["/employeeProfile"]);
    }else if(this.rola ==="Klijent"){
      this.router.navigate(["/clientProfile"]);
    }
    this.router.navigate(["/"]);
  }

  isClient(){
    return this.rola ==="Klijent";
  }

  logout(){
    this.authService.logout();
  }
}
