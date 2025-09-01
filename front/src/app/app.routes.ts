import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import { SignInComponent } from "./shared/features/auth/sign-in/sign-in.component";
import { SignUpComponent } from "./shared/features/auth/sign-up/sign-up.component";
import { CartComponent } from "./paniers/cart.component";
import { WishlistComponent } from "./wishList/wishlist.component";
import { ContactComponent } from "./contact/contact.component";

export const APP_ROUTES: Routes = [
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "sign-in",
    component: SignInComponent,
  },
  {
    path: "sign-up",
    component: SignUpComponent,
  },
  {
    path: "products",
    loadChildren: () =>

      import("./products/products.routes").then((m) => m.PRODUCTS_ROUTES)
  },
  {
    path: "cart",
    component: CartComponent,
  },
  {
    path: "wishlist",
    component: WishlistComponent,
  },
  {
    path: "contact",
    component: ContactComponent,
  },
  { path: "", redirectTo: "home", pathMatch: "full" },
];
