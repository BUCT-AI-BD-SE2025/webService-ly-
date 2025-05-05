import { createApp } from 'vue'
import App from './App.vue'
import router from './router'



const app = createApp(App)

app.use(router)
app.mount('#app') // 挂载到 index.html 中的 <div id="app"></div>
