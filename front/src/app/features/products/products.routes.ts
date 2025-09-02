import { Routes } from "@angular/router";
import { adminGuard, authGuard } from "../../core";
import { ProductListComponent, UserProductViewComponent } from "./components";




export const PRODUCTS_ROUTES: Routes = [
  {
    path: "list-for-admin",
    component: ProductListComponent,
    canActivate: [adminGuard]
  },
  {
    path: "list-for-user", 
    component: UserProductViewComponent,
    canActivate: [authGuard]
  },
  { path: "", redirectTo: "list-for-user", pathMatch: "full" },
  { path: "**", redirectTo: "list-for-user" }
];
