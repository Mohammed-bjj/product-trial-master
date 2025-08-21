import { Injectable, signal, inject } from '@angular/core';
import { GlobalErrorHandler } from './handlerError/global-error-handler.service';
import { ERROR_MESSAGES } from './handlerError/constantes-error-messages';
import { ThemeConfigError } from './handlerError/custom-errors.model';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private readonly _isDark = signal(false);
  public readonly isDark = this._isDark.asReadonly();
  private readonly errorHandler = inject(GlobalErrorHandler);
  private readonly THEME_LIGHT = 'light';
  private readonly THEME_DARK = 'dark';
  private readonly STORAGE_THEME_KEY = 'theme';
  private readonly THEME_CSS_ID = 'app-theme';
  private readonly DATA_ATTRIBUTE_THEME = 'data-theme';

  constructor() {
    const savedTheme = localStorage.getItem('theme');
    this.setTheme(savedTheme === 'dark');
  }

  toggleTheme() {
    this.setTheme(!this._isDark());
  }

  private setTheme(isDark: boolean) {
    const theme = isDark ? this.THEME_DARK : this.THEME_LIGHT;
    const curentTheme = document.documentElement.getAttribute(this.DATA_ATTRIBUTE_THEME);

    if(curentTheme != theme){
      document.documentElement.setAttribute(this.DATA_ATTRIBUTE_THEME, theme);
      this._isDark.set(isDark);
      localStorage.setItem(this.STORAGE_THEME_KEY, theme);
      this.loadTheme(theme);

    }
  }

  private loadTheme(theme: 'light' | 'dark') {
    let themeLink = document.getElementById(this.THEME_CSS_ID) as HTMLLinkElement;
    if (themeLink) {
      themeLink.href = `mdc-${theme}-deeppurple.css`;
    } else {
      this.errorHandler.handleError(new ThemeConfigError(ERROR_MESSAGES.THEME.LINK_NOT_FOUND));
    }
  }
}
