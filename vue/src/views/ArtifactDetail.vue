<template>
  <div class="artifact-detail-page">
    <header class="header">
      <button @click="goBack">返回</button>
      <h1>文物详情</h1>
    </header>

    <main class="content" v-if="artifact">
      <img
        :src="artifact.image || placeholder"
        alt="文物图片"
        class="artifact-image"
        @click="openImageViewer"
      />
      <h2>{{ artifact.name }}</h2>
      <p><strong>类型：</strong>{{ artifact.type }}</p>
      <p><strong>介绍：</strong>{{ artifact.description }}</p>
    </main>

    <div v-else class="loading">
      正在加载文物信息...
    </div>


    <div v-if="showImageViewer" class="image-viewer" @click="closeImageViewer">
      <img :src="artifact.image || placeholder" alt="文物大图" class="large-image" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const artifact = ref(null)
const showImageViewer = ref(false)

const placeholder = 'https://via.placeholder.com/300x300?text=暂无图片'

const allArtifacts = [
  { id: 1, name: '青铜鼎', type: '青铜器', description: '青铜时代的器物', image: '' },
  { id: 2, name: '陶瓷碗', type: '陶瓷', description: '宋代瓷器', image: '' },
  { id: 3, name: '玉佩', type: '玉器', description: '古代佩饰', image: '' },
  { id: 4, name: '书法卷轴', type: '书法', description: '明代书法艺术', image: '' },
  { id: 5, name: '石雕佛像', type: '雕塑', description: '汉代石雕艺术', image: '' },
  { id: 6, name: '漆器盒', type: '漆器', description: '精美的漆器盒', image: '' },
  { id: 7, name: '青花瓷瓶', type: '陶瓷', description: '元代青花瓷', image: '' }
]

onMounted(() => {
  const id = parseInt(route.query.id)
  artifact.value = allArtifacts.find(item => item.id === id)
})

function goBack() {
  router.back()
}

function openImageViewer() {
  showImageViewer.value = true
}

function closeImageViewer() {
  showImageViewer.value = false
}
</script>

<style scoped>
.artifact-detail-page {
  background-color: white;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background-color: #87cefa;
  padding: 10px;
  display: flex;
  align-items: center;
  gap: 20px;
}

.header button {
  padding: 5px 10px;
  background-color: #1e90ff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.content {
  padding: 20px;
  text-align: center;
}

.artifact-image {
  width: 300px;
  height: 300px;
  object-fit: cover;
  margin-bottom: 20px;
  cursor: pointer;
  border: 2px solid #ccc;
  border-radius: 8px;
  transition: transform 0.2s;
}

.artifact-image:hover {
  transform: scale(1.05);
}

.loading {
  text-align: center;
  padding: 50px;
  font-size: 18px;
}

/* 大图查看器样式 */
.image-viewer {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.large-image {
  max-width: 90%;
  max-height: 90%;
  border-radius: 10px;
}
</style>
