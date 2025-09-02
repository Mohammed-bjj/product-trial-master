import { Routes } from "@angular/router";
import { SignInComponent, SignUpComponent } from "./index";

export const AUTH_ROUTES: Routes = [
  {
    path: "sign-in",
    component: SignInComponent,
  },
  {
    path: "sign-up", 
    component: SignUpComponent
  },
  { path: "", redirectTo: "sign-in", pathMatch: "full" },
  { path: "**", redirectTo: "sign-in" }
];
