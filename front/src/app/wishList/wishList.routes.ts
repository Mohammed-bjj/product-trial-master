import { Routes } from "@angular/router";
import { WishlistComponent } from "./features/wishlist.component";
import { authGuard } from "../shared/core/guards/admin.guard";



export const WISHLIST_ROUTES: Routes = [
  {
    path: "wishlist", 
    component:  WishlistComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "wishlist", pathMatch: "full" },
  { path: "**", redirectTo: "wishlist" }
];
