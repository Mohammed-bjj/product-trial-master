import { Routes } from "@angular/router";
import { WishlistComponent } from "./features/wishlist.component";



export const PANIERS_ROUTES: Routes = [
  {
    path: "paniers", 
    component:  WishlistComponent 
  },
  { path: "", redirectTo: "list", pathMatch: "full" },
  { path: "**", redirectTo: "list" }
];
