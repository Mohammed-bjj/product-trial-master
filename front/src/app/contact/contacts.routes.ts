import { Routes } from "@angular/router";
import { ContactComponent } from "./features/contact.component";
import { authGuard } from "../shared/core/guards/admin.guard";



export const CONTACT_ROUTES: Routes = [
  {
    path: "form", 
    component:  ContactComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "form", pathMatch: "full" },
  { path: "**", redirectTo: "form" }
];
