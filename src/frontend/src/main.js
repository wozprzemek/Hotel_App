import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "ag-grid-vue3"
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";

createApp(App).use(router).mount("#app");
