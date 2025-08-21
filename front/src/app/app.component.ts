import {
  Component,
  computed,
  inject
} from "@angular/core";
import { Router, RouterModule, NavigationEnd } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { HeaderComponent } from "./shared/ui/header/header.component";
import { CommonModule } from '@angular/common';
import { filter, map, startWith } from 'rxjs/operators';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, PanelMenuComponent, HeaderComponent, CommonModule],
})
export class AppComponent {
  private readonly router = inject(Router);
  
  title = "ALTEN SHOP";
  
  // Détecter si on est sur une page d'authentification
  readonly isAuthPage = toSignal(
    this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd),
      map((event: NavigationEnd) => event.url.includes('/sign-in') || event.url.includes('/sign-up')),
      startWith(this.router.url.includes('/sign-in') || this.router.url.includes('/sign-up'))
    ),
    { initialValue: false }
  );
}
