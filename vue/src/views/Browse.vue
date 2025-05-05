<template>
  <div class="browse-page">
    <header class="header">
      <div class="logo">
        <img src="@/assets/buct.webp" alt="Logo" />
      </div>
      <div class="title-search">
        <h1 class="system-title">海外文物知识服务子系统</h1>
        <div class="search-bar">
          <input type="text" placeholder="请输入查询内容" v-model="searchQuery" />
          <button @click="search">查询</button>
        </div>
      </div>
      <div class="user-info">
        <button @click="goToUserInfo">用户信息</button>
      </div>
    </header>

    <main class="content">

      <div class="artifact-list">
        <div
          v-for="artifact in paginatedArtifacts"
          :key="artifact.id"
          class="artifact-item"
          @click="goToDetail(artifact.id)"
        >
          <img :src="artifact.image" alt="文物图片" class="artifact-image" />
          <h2>{{ artifact.name }}</h2>
          <p><strong>类型：</strong>{{ artifact.type }}</p>
          <p><strong>介绍：</strong>{{ artifact.description }}</p>
        </div>
      </div>

      <!-- 分页按钮 -->
      <div class="pagination">
        <button @click="prevPage" :disabled="currentPage === 1">上一页</button>
        <span>第 {{ currentPage }} 页 / 共 {{ totalPages }} 页</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const searchQuery = ref('')

const artifacts = ref([
  { id: 1, name: '青铜鼎', type: '青铜器', description: '青铜时代的器物', image: 'https://via.placeholder.com/150' },
  { id: 2, name: '陶瓷碗', type: '陶瓷', description: '宋代瓷器', image: 'https://via.placeholder.com/150' },
  { id: 3, name: '玉佩', type: '玉器', description: '古代佩饰', image: 'https://via.placeholder.com/150' },
  { id: 4, name: '书法卷轴', type: '书法', description: '明代书法艺术', image: 'https://via.placeholder.com/150' },
  { id: 5, name: '石雕佛像', type: '雕塑', description: '汉代石雕艺术', image: 'https://via.placeholder.com/150' },
  { id: 6, name: '漆器盒', type: '漆器', description: '精美的漆器盒', image: 'https://via.placeholder.com/150' },
  { id: 7, name: '青花瓷瓶', type: '陶瓷', description: '元代青花瓷', image: 'https://via.placeholder.com/150' },
  // 更多文物数据
])

const currentPage = ref(1)
const itemsPerPage = 6

const filteredArtifacts = computed(() => {
  if (!searchQuery.value.trim()) return artifacts.value
  return artifacts.value.filter(artifact =>
    artifact.name.includes(searchQuery.value) ||
    artifact.type.includes(searchQuery.value) ||
    artifact.description.includes(searchQuery.value)
  )
})

const totalPages = computed(() => {
  return Math.ceil(filteredArtifacts.value.length / itemsPerPage)
})

const paginatedArtifacts = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  return filteredArtifacts.value.slice(start, start + itemsPerPage)
})

function search() {
  currentPage.value = 1
}

function prevPage() {
  if (currentPage.value > 1) currentPage.value--
}

function nextPage() {
  if (currentPage.value < totalPages.value) currentPage.value++
}

function goToDetail(id) {
  router.push({ name: 'ArtifactDetail', params: { id } })
}

function goToUserInfo() {
  router.push({ name: 'profile' })
}
</script>

<style scoped>
.browse-page {
  background-color: black;
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  width: 100%;
  background-color: #87cefa;
  height: 80px;
  padding: 0 20px;
  display: flex;
  align-items: center;
}

.logo img {
  width: 60px;
  height: 60px;
  object-fit: cover;
}

.title-search {
  flex: 1;
  margin-left: 20px;
}

.system-title {
  margin: 0;
  font-size: 20px;
  font-weight: bold;
}

.search-bar {
  margin-top: 5px;
  display: flex;
  align-items: center;
}

.search-bar input {
  padding: 5px;
  font-size: 14px;
  width: 200px;
}

.search-bar button {
  margin-left: 10px;
  padding: 5px 10px;
  font-size: 14px;
}

.user-info button {
  background-color: #1e90ff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.content {
  flex: 1;
  background-color: white;
  padding: 20px;
}

.artifact-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.artifact-item {
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 8px;
  text-align: center;
  transition: transform 0.2s;
  cursor: pointer;
}

.artifact-item:hover {
  transform: scale(1.05);
}

.artifact-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
  margin-bottom: 10px;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 15px;
}

.pagination button {
  padding: 5px 10px;
  font-size: 14px;
  background-color: #1e90ff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.pagination button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
