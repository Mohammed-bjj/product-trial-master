import { Routes } from "@angular/router";
import { CartComponent } from "./features/cart.component";



export const PANIERS_ROUTES: Routes = [
  {
    path: "list", 
    component:  CartComponent 
  },
  { path: "", redirectTo: "list", pathMatch: "full" },
  { path: "**", redirectTo: "list" }
];
