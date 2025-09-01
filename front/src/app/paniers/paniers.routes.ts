import { Routes } from "@angular/router";
import { CartComponent } from "./features/cart.component";
import { authGuard } from "../shared/core/guards/admin.guard";


export const PANIERS_ROUTES: Routes = [
  {
    path: "list", 
    component:  CartComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "list", pathMatch: "full" },
  { path: "**", redirectTo: "list" }
];
