import { Routes } from "@angular/router";
import { WishlistComponent } from "./features/wishlist.component";
import { authGuard } from "../shared/core/guards/admin.guard";



export const PANIERS_ROUTES: Routes = [
  {
    path: "list", 
    component:  WishlistComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "list", pathMatch: "full" },
  { path: "**", redirectTo: "list" }
];
