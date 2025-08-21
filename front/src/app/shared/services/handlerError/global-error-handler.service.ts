import { ErrorHandler, Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { ThemeConfigError, ValidationError, BusinessError } from './custom-errors.model';

@Injectable({
    providedIn: 'root'
  })
export class GlobalErrorHandler implements ErrorHandler {

  handleError(error: any): void {
    const errorType = this.categorizeError(error);
    if (environment.enableLogging && !environment.production) {
      this.logError(errorType, error);
    }

    // En production, envoyer vers un service de monitoring
    if (environment.production) {
      // this.sendToMonitoring(error);
    }
  }

  private categorizeError(error: any): string {
    if (error instanceof ThemeConfigError) {
      return error.type;
    }
    if (error instanceof ValidationError) {
      return error.type;
    }
    if (error instanceof BusinessError) {
      return error.type;
    }
    return 'UNKNOWN_ERROR';
  }

  private logError(type: string, error: any): void {
    switch (type) {
      case 'THEME_CONFIG_ERROR':
        console.warn('[THEME WARNING]', error.message);
        break;
      default:
        console.error('[GLOBAL ERROR]', error);
    }
  }
}
