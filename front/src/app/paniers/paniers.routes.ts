import { Routes } from "@angular/router";
import { CartComponent } from "./features/cart.component";
import { authGuard } from "../shared/core/guards/admin.guard";


export const PANIERS_ROUTES: Routes = [
  {
    path: "panier", 
    component:  CartComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "panier", pathMatch: "full" },
  { path: "**", redirectTo: "panier" }
];
