import { Component, OnInit, inject, signal } from "@angular/core";
import { Product, ProductFormData } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { TableModule } from "primeng/table";
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { TagModule } from 'primeng/tag';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { ConfirmationService, MessageService } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [TableModule, ButtonModule, DialogModule, ProductFormComponent, InputTextModule, DropdownModule, TagModule, ConfirmDialogModule, ToastModule, ToolbarModule, CommonModule, FormsModule],
  providers: [ConfirmationService, MessageService],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly confirmationService = inject(ConfirmationService);
  private readonly messageService = inject(MessageService);

  public readonly products = this.productsService.products;
  public readonly productResponse = this.productsService.productResponse;



  public selectedProducts: Product[] = [];
  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  ngOnInit() {
    this.productsService.get().subscribe();
  }


    loadProducts(event: any) {
        console.log("test med : ",event);
        const page = event.first / event.rows;
        const size = event.rows;
        this.productsService.get(page, size).subscribe();
    }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.confirmationService.confirm({
      message: `Êtes-vous sûr de vouloir supprimer le produit "${product.name}" ?`,
      header: 'Confirmation de suppression',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel: 'Oui',
      rejectLabel: 'Non',
      accept: () => {
        this.productsService.delete(product.id).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Succès',
              detail: 'Produit supprimé avec succès'
            });
          },
          error: () => {
            this.messageService.add({
              severity: 'error',
              summary: 'Erreur',
              detail: 'Erreur lors de la suppression'
            });
          }
        });
      }
    });
  }



  public getStatusSeverity(status: string): 'success' | 'warning' | 'danger' {
    switch (status) {
      case 'INSTOCK': return 'success';
      case 'LOWSTOCK': return 'warning';
      case 'OUTOFSTOCK': return 'danger';
      default: return 'success';
    }
  }

  public getStatusLabel(status: string): string {
    switch (status) {
      case 'INSTOCK': return 'En Stock';
      case 'LOWSTOCK': return 'Stock Faible';
      case 'OUTOFSTOCK': return 'Rupture';
      default: return status;
    }
  }



  public onSave(formData: ProductFormData) {
    if (this.isCreation) {
      const newProduct: Product = {
        ...formData,
        id: 0,
        createdAt: Date.now(),
        updatedAt: Date.now()
      };
      this.productsService.create(newProduct).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Succès',
            detail: 'Produit créé avec succès'
          });
          this.closeDialog();
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Erreur lors de la création'
          });
        }
      });
    } else {
      const updatedProduct: Product = {
        ...formData,
        id: this.editedProduct().id,
        createdAt: this.editedProduct().createdAt,
        updatedAt: Date.now()
      };
      this.productsService.update(updatedProduct).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Succès',
            detail: 'Produit modifié avec succès'
          });
          this.closeDialog();
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Erreur lors de la modification'
          });
        }
      });
    }
  }

  public onDeleteSelected() {
    if (this.selectedProducts.length === 0) return;

    this.confirmationService.confirm({
      message: `Êtes-vous sûr de vouloir supprimer ${this.selectedProducts.length} produit(s) ?`,
      header: 'Suppression en masse',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel: 'Oui',
      rejectLabel: 'Non',
      accept: () => {
        this.selectedProducts.forEach(product => {
          this.productsService.delete(product.id).subscribe();
        });
        this.selectedProducts = [];
        this.messageService.add({
          severity: 'success',
          summary: 'Succès',
          detail: 'Produits supprimés avec succès'
        });
      }
    });
  }

  public isSelected(product: Product): boolean {
    return this.selectedProducts.some(p => p.id === product.id);
  }

  public isAllSelected(): boolean {
    return this.products().length > 0 && this.selectedProducts.length === this.products().length;
  }

  public toggleSelection(product: Product, event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      if (!this.isSelected(product)) {
        this.selectedProducts = [...this.selectedProducts, product];
      }
    } else {
      this.selectedProducts = this.selectedProducts.filter(p => p.id !== product.id);
    }
  }

  public toggleAllSelection(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.selectedProducts = [...this.products()];
    } else {
      this.selectedProducts = [];
    }
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }
}