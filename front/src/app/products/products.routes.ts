import { Routes } from "@angular/router";
import { adminGuard } from "../shared/guards/admin.guard";
import { ProductRouterComponent } from "./features/product-router/product-router.component";


export const PRODUCTS_ROUTES: Routes = [
	{
		path: "list",
		component: ProductRouterComponent
	},

	{ path: "**", redirectTo: "list" },
];
