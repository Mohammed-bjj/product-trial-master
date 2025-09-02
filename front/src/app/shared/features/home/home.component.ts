import { Component, inject, OnInit, computed } from "@angular/core";
import { RouterLink } from "@angular/router";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { ChartModule } from "primeng/chart";
import { CommonModule } from "@angular/common";
import { UserProductViewComponent, ProductListComponent, ProductsService } from "../../../features";
import { AuthService } from "../../../shared";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
  standalone: true,
  imports: [CardModule, RouterLink, ButtonModule, CommonModule, UserProductViewComponent, ProductListComponent, ChartModule],
})
export class HomeComponent implements OnInit {
  private readonly authService = inject(AuthService);
  private readonly productsService = inject(ProductsService);
  
  public readonly appTitle = "ALTEN SHOP";
  public readonly isAdmin = this.authService.isAdmin;

  public stockData = computed(() => {
    const products = this.productsService.products();
    const inStock = products.filter(p => p.inventoryStatus === 'INSTOCK').length;
    const lowStock = products.filter(p => p.inventoryStatus === 'LOWSTOCK').length;
    const outOfStock = products.filter(p => p.inventoryStatus === 'OUTOFSTOCK').length;

    return {
      labels: ['En Stock', 'Stock Faible', 'Rupture'],
      datasets: [{
        data: [inStock, lowStock, outOfStock],
        backgroundColor: ['#4CAF50', '#FF9800', '#F44336'],
        hoverBackgroundColor: ['#66BB6A', '#FFB74D', '#EF5350']
      }]
    };
  });

  public chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'bottom'
      }
    }
  };

  ngOnInit() {
    if (this.isAdmin()) {
      this.productsService.get().subscribe();
    }
  }
}
