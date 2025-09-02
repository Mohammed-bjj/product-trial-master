import { Routes } from "@angular/router";
import { CartComponent } from "./components/cart.component";
import { authGuard } from "../../shared";


export const PANIERS_ROUTES: Routes = [
  {
    path: "panier", 
    component:  CartComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "panier", pathMatch: "full" },
  { path: "**", redirectTo: "panier" }
];
