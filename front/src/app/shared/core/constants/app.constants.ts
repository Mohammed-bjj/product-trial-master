export const APP_CONSTANTS = {
  STORAGE_KEYS: {
    AUTH_TOKEN: 'auth_token',
    USER_PREFERENCES: 'user_preferences',
    THEME: 'theme'
  },
  
  API_ENDPOINTS: {
    AUTH: {
      LOGIN: '/account/token',
      REGISTER: '/account/register'
    },
    PRODUCTS: {
      SEARCH: '/products/search',
      ADMIN_SEARCH: '/products/admin/search'
    },
    CART: '/panier',
    WISHLIST: '/wishlist'
  },

  PAGINATION: {
    DEFAULT_PAGE_SIZE: 10,
    MAX_PAGE_SIZE: 100
  },

  VALIDATION: {
    PASSWORD_MIN_LENGTH: 6,
    MESSAGE_MAX_LENGTH: 300
  }
} as const;

export type AppConstants = typeof APP_CONSTANTS;