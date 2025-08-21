import { Component, inject } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { ThemeService } from '../../services/theme.service';

@Component({
  selector: 'app-theme-toggle',
  standalone: true,
  imports: [ButtonModule, TooltipModule],
  template: `
    <p-button 
      [icon]="themeService.isDark() ? 'pi pi-sun' : 'pi pi-moon'"
      severity="secondary"
      [text]="true"
      (onClick)="themeService.toggleTheme()"
      [pTooltip]="themeService.isDark() ? 'Mode clair' : 'Mode sombre'"
    />
  `
})
export class ThemeToggleComponent {
  public readonly themeService = inject(ThemeService);
}