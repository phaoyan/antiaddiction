import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import '@/assets/reset.css'
import '@/assets/element.css'
import '@/assets/default.css'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App)
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
app.mount("#app")
