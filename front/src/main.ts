import { enableProdMode, importProvidersFrom, ErrorHandler } from "@angular/core";

import { registerLocaleData } from "@angular/common";
import {
  provideHttpClient,
  withInterceptors,
} from "@angular/common/http";
import localeFr from "@angular/common/locales/fr";
import { BrowserModule, bootstrapApplication } from "@angular/platform-browser";
import { provideAnimations } from "@angular/platform-browser/animations";
import { provideRouter } from "@angular/router";
import { APP_ROUTES } from "app/app.routes";
import { ConfirmationService, MessageService } from "primeng/api";
import { DialogService } from "primeng/dynamicdialog";
import { AppComponent } from "./app/app.component";
import { environment } from "./environments/environment";
import { GlobalErrorHandler } from "./app/shared/services/handlerError/global-error-handler.service";
import { authInterceptor } from "./app/shared/interceptors/auth.interceptor";
if (environment.production) {
  enableProdMode();
}

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(BrowserModule),
    provideHttpClient(
      withInterceptors([authInterceptor]),
    ),
    provideAnimations(),
    provideRouter(APP_ROUTES),
    ConfirmationService,
    MessageService,
    DialogService,
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    }

  ],
}).catch((err) => console.log(err));

registerLocaleData(localeFr, "fr-FR");
