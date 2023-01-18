import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, tap } from 'rxjs';
import * as xml2js from 'xml2js';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  logged$ = new BehaviorSubject<boolean>(this.isLoggedIn());

  rola$ = new BehaviorSubject<string>(this.getUserRole());

  email$ = new BehaviorSubject<string>(this.getUserEmail());

  constructor(private http:HttpClient, private router:Router) { }

  private isLoggedIn():boolean{
    if(localStorage.getItem("id")=== null){
      return false;
    }
    return true;
  }

  public login(email:string, password:string){
    const body = "<login><email>"+email+"</email><password>"+password+"</password></login>";
    return this.http.post("users/login", body,{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}}).pipe(tap(res => this.loginData(res)))
  }

  public signUp(email:string, password:string, firstName:string, lastName:string, role:string){
    const body = '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><user xmlns="http://users.com/model/user">'+
      "<email>"+email+"</email>"+
      "<sifra>"+password+"</sifra>"+
      "<ime>"+firstName+"</ime>"+
      "<prezime>"+lastName+"</prezime>"+
      "<rola>"+role+"</rola>"+
      "</user>";
      return this.http.post("users/signUp", body,{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});

  }

  public logout(){
    localStorage.removeItem("id");
    localStorage.removeItem("rola");
    localStorage.removeItem("email");
    this.rola$.next("");
    this.email$.next("");
    this.logged$.next(false);
    this.router.navigate(["/"]);
  }

  private loginData(res:any){
    xml2js.parseString(res, (err, result) => {
      localStorage.setItem("id", result["login"]["id"][0]);
      localStorage.setItem("email", result["login"]["email"][0]);
      localStorage.setItem("rola", result["login"]["rola"][0]);
      this.logged$.next(true);
      this.rola$.next(this.getUserRole());
      this.email$.next(this.getUserEmail());
      if(this.getUserRole() === "Klijent"){
        this.router.navigate(["/userProfile"]);
      }else if(this.getUserRole() === "Sluzbenik"){
        this.router.navigate(["/employeeProfile"]);
      }
   });
  }

  public getUserRole(){
    let rola = localStorage.getItem("rola");
    return rola===null  ? '': rola;
  }

  public getUserEmail(){
    let email = localStorage.getItem("email");
    return email===null  ? '': email;
  }
}
