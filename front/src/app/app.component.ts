import {
  Component,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { HeaderComponent } from "./shared/ui/header/header.component";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, PanelMenuComponent, HeaderComponent],
})
export class AppComponent {
  title = "ALTEN SHOP";
}
