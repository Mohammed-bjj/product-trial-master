export class ThemeConfigError extends Error {
  readonly type = 'THEME_CONFIG_ERROR';
  
  constructor(message: string) {
    super(message);
    this.name = 'ThemeConfigError';
  }
}

export class ValidationError extends Error {
  readonly type = 'VALIDATION_ERROR';
  
  constructor(message: string, public field?: string) {
    super(message);
    this.name = 'ValidationError';
  }
}

export class BusinessError extends Error {
  readonly type = 'BUSINESS_ERROR';
  
  constructor(message: string, public code?: string) {
    super(message);
    this.name = 'BusinessError';
  }
}