import { Routes } from "@angular/router";
import { adminGuard, authGuard } from "../shared/core/guards/admin.guard";
import { ProductListComponent } from "./features/product-list/admin/product-list.component";
import { UserProductViewComponent } from "./features/product-list/user/user-product-view.component";




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
