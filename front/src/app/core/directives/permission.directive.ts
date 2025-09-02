import { Directive, Input, TemplateRef, ViewContainerRef, inject, effect } from '@angular/core';
import { AuthService } from '../../shared/index';

@Directive({
  selector: '[appPermission]',
  standalone: true
})
export class PermissionDirective {
  private readonly templateRef = inject(TemplateRef<any>);
  private readonly viewContainer = inject(ViewContainerRef);
  private readonly authService = inject(AuthService);

  @Input() set appPermission(permission: 'admin' | 'user' | 'authenticated') {
    effect(() => {
      const hasPermission = this.checkPermission(permission);
      
      if (hasPermission) {
        this.viewContainer.createEmbeddedView(this.templateRef);
      } else {
        this.viewContainer.clear();
      }
    });
  }

  private checkPermission(permission: string): boolean {
    switch (permission) {
      case 'admin':
        return this.authService.isAdmin();
      case 'user':
        return this.authService.isAuthenticated() && !this.authService.isAdmin();
      case 'authenticated':
        return this.authService.isAuthenticated();
      default:
        return false;
    }
  }
}