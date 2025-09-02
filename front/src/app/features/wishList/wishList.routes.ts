import { Routes } from "@angular/router";
import { WishlistComponent } from "./components/wishlist.component";
import { authGuard } from "../../core";



export const WISHLIST_ROUTES: Routes = [
  {
    path: "wishlist", 
    component:  WishlistComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "wishlist", pathMatch: "full" },
  { path: "**", redirectTo: "wishlist" }
];
