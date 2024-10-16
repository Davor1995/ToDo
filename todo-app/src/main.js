import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config';  // PrimeVue importieren
import 'primeicons/primeicons.css'; // PrimeIcons
import Aura from '@primevue/themes/aura';
import Button from "primevue/button";
import 'primevue/resources/primevue.min.css';

const app = createApp(App);
app.component('But-ton', Button);
app.use(PrimeVue, {
    theme: {
        preset: Aura,
        options: {
            prefix: 'p',
            darkModeSelector: 'system',
            cssLayer: false
        }
    }
});

app.mount('#app');