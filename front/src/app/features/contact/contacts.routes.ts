import { Routes } from "@angular/router";
import { ContactComponent } from "./components/contact.component";
import { authGuard } from "../../shared";



export const CONTACT_ROUTES: Routes = [
  {
    path: "form", 
    component:  ContactComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "form", pathMatch: "full" },
  { path: "**", redirectTo: "form" }
];
