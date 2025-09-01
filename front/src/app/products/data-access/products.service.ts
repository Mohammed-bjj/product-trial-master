import { Injectable, inject, signal } from "@angular/core";
import { Product } from "./product.model";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap, map } from "rxjs";
import { environment } from "../../../environments/environment";
import { AuthService } from "../../shared/services/auth.service";

interface ProductResponse {
    content: Product[];
    page: {
        size: number;
        totalElements: number;
        totalPages: number;
        number: number;
    };
}


@Injectable({
    providedIn: "root"
}) export class ProductsService {

    private readonly http = inject(HttpClient);
    private readonly authService = inject(AuthService);
    
    private get path() {
        const basePath = `${environment.apiBaseUrl}/products`;
        return this.authService.isAdmin() ? `${basePath}/admin` : basePath;
    }
    
    private readonly _products = signal<Product[]>([]);
    private readonly _productResponse = signal<ProductResponse | null>(null);

    public readonly products = this._products.asReadonly();
    public readonly productResponse = this._productResponse.asReadonly();

    public get(page: number = 0, size: number = 10): Observable<ProductResponse> {
        return this.http.get<ProductResponse>(`${this.path}/search?page=${page}&size=${size}`).pipe(
            catchError((error) => {
                console.error('Failed to fetch products from API, loading from local JSON file.', error);
                return this.http.get<Product[]>("assets/products.json").pipe(
                    map(products => ({ 
                        content: products, 
                        page: {
                            size: products.length,
                            totalElements: products.length,
                            totalPages: 1,
                            number: 0
                        }
                    }))
                );
            }),
            tap((response) => {
                console.log(response.page.totalElements);
                this._products.set(response.content);
                this._productResponse.set(response);
            })
        );
    }

    public create(product: Product): Observable<boolean> {
        return this.http.post<boolean>(`${this.path}/newProduct`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => [product, ...products])),
        );
    }

    public update(product: Product): Observable<boolean> {
        return this.http.patch<boolean>(`${this.path}/update/${product.id}`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => {
                return products.map(p => p.id === product.id ? product : p)
            })),
        );
    }

    public delete(productId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.path}/delete/${productId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
        );
    }
}