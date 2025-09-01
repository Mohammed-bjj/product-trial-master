import { Product } from '../../products/data-access/product.model';
import { CartItem } from '../services/cart.service';
import { Order, OrderItem } from '@app/orders/data-access/order.model';

export const MOCK_PRODUCTS: Product[] = [
  {
    id: 1,
    code: 'LAPTOP001',
    name: 'MacBook Pro 14"',
    description: 'Ordinateur portable haute performance avec puce M2',
    image: 'https://via.placeholder.com/300x200?text=MacBook+Pro',
    category: 'Informatique',
    price: 2299.99,
    quantity: 15,
    internalReference: 'REF-LAPTOP-001',
    shellId: 101,
    inventoryStatus: 'INSTOCK',
    rating: 4.8,
    createdAt: Date.now() - 86400000,
    updatedAt: Date.now()
  },
  {
    id: 2,
    code: 'PHONE001',
    name: 'iPhone 15 Pro',
    description: 'Smartphone dernière génération avec appareil photo professionnel',
    image: 'https://via.placeholder.com/300x200?text=iPhone+15',
    category: 'Téléphonie',
    price: 1199.00,
    quantity: 8,
    internalReference: 'REF-PHONE-001',
    shellId: 102,
    inventoryStatus: 'LOWSTOCK',
    rating: 4.7,
    createdAt: Date.now() - 172800000,
    updatedAt: Date.now()
  },
  {
    id: 3,
    code: 'HEADSET001',
    name: 'AirPods Pro 2',
    description: 'Écouteurs sans fil avec réduction de bruit active',
    image: 'https://via.placeholder.com/300x200?text=AirPods+Pro',
    category: 'Audio',
    price: 279.00,
    quantity: 25,
    internalReference: 'REF-AUDIO-001',
    shellId: 103,
    inventoryStatus: 'INSTOCK',
    rating: 4.6,
    createdAt: Date.now() - 259200000,
    updatedAt: Date.now()
  },
  {
    id: 4,
    code: 'TABLET001',
    name: 'iPad Air 11"',
    description: 'Tablette polyvalente pour le travail et les loisirs',
    image: 'https://via.placeholder.com/300x200?text=iPad+Air',
    category: 'Tablettes',
    price: 699.00,
    quantity: 0,
    internalReference: 'REF-TABLET-001',
    shellId: 104,
    inventoryStatus: 'OUTOFSTOCK',
    rating: 4.5,
    createdAt: Date.now() - 345600000,
    updatedAt: Date.now()
  },
  {
    id: 5,
    code: 'WATCH001',
    name: 'Apple Watch Series 9',
    description: 'Montre connectée avec suivi santé avancé',
    image: 'https://via.placeholder.com/300x200?text=Apple+Watch',
    category: 'Wearables',
    price: 449.00,
    quantity: 12,
    internalReference: 'REF-WATCH-001',
    shellId: 105,
    inventoryStatus: 'INSTOCK',
    rating: 4.4,
    createdAt: Date.now() - 432000000,
    updatedAt: Date.now()
  }
];

export const MOCK_CART_ITEMS: CartItem[] = [
  {
    product: MOCK_PRODUCTS[0],
    quantity: 1
  },
  {
    product: MOCK_PRODUCTS[1],
    quantity: 2
  },
  {
    product: MOCK_PRODUCTS[2],
    quantity: 3
  }
];

export const MOCK_ORDER_ITEMS: OrderItem[] = [
  {
    id: 1,
    productId: 1,
    productName: 'MacBook Pro 14"',
    productCode: 'LAPTOP001',
    quantity: 1,
    unitPrice: 2299.99,
    totalPrice: 2299.99
  },
  {
    id: 2,
    productId: 2,
    productName: 'iPhone 15 Pro',
    productCode: 'PHONE001',
    quantity: 2,
    unitPrice: 1199.00,
    totalPrice: 2398.00
  },
  {
    id: 3,
    productId: 3,
    productName: 'AirPods Pro 2',
    productCode: 'HEADSET001',
    quantity: 1,
    unitPrice: 279.00,
    totalPrice: 279.00
  }
];

export const MOCK_ORDERS: Order[] = [
  {
    id: 1,
    orderNumber: 'ORD-2024-001',
    customerId: 1,
    customerName: 'Jean Dupont',
    customerEmail: 'jean.dupont@email.com',
    orderDate: new Date('2024-01-15'),
    status: 'DELIVERED',
    totalAmount: 2299.99,
    shippingAddress: '123 Rue de la Paix, 75001 Paris',
    items: [MOCK_ORDER_ITEMS[0]],
    createdAt: Date.now() - 86400000,
    updatedAt: Date.now()
  },
  {
    id: 2,
    orderNumber: 'ORD-2024-002',
    customerId: 2,
    customerName: 'Marie Martin',
    customerEmail: 'marie.martin@email.com',
    orderDate: new Date('2024-01-20'),
    status: 'SHIPPED',
    totalAmount: 2677.00,
    shippingAddress: '456 Avenue des Champs, 69000 Lyon',
    items: [MOCK_ORDER_ITEMS[1], MOCK_ORDER_ITEMS[2]],
    createdAt: Date.now() - 172800000,
    updatedAt: Date.now()
  },
  {
    id: 3,
    orderNumber: 'ORD-2024-003',
    customerId: 3,
    customerName: 'Pierre Durand',
    customerEmail: 'pierre.durand@email.com',
    orderDate: new Date('2024-01-25'),
    status: 'PROCESSING',
    totalAmount: 1199.00,
    shippingAddress: '789 Boulevard Saint-Germain, 33000 Bordeaux',
    items: [{
      id: 4,
      productId: 2,
      productName: 'iPhone 15 Pro',
      productCode: 'PHONE001',
      quantity: 1,
      unitPrice: 1199.00,
      totalPrice: 1199.00
    }],
    createdAt: Date.now() - 259200000,
    updatedAt: Date.now()
  }
];

export class MockDataHelper {
  static initializeCartWithMockData(cartService: any): void {
    cartService.clearCart().subscribe();
    
    MOCK_CART_ITEMS.forEach(item => {
      cartService.addToCart(item.product, item.quantity).subscribe();
    });
  }

  static initializeWishlistWithMockData(wishlistService: any): void {
    wishlistService.clearWishlist();
    
    // Ajouter quelques produits à la wishlist
    [MOCK_PRODUCTS[0], MOCK_PRODUCTS[2], MOCK_PRODUCTS[4]].forEach(product => {
      wishlistService.addToWishlist(product);
    });
  }

  static getRandomProduct(): Product {
    const randomIndex = Math.floor(Math.random() * MOCK_PRODUCTS.length);
    return MOCK_PRODUCTS[randomIndex];
  }

  static initializeOrdersWithMockData(orderService: any): void {
    // Simuler l'ajout des commandes mock
    MOCK_ORDERS.forEach(order => {
      orderService.addOrder(order);
    });
  }

  static getRandomOrder(): Order {
    const randomIndex = Math.floor(Math.random() * MOCK_ORDERS.length);
    return MOCK_ORDERS[randomIndex];
  }
}