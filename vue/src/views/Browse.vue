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
        <button @click="goToVisualization">数据可视化</button>
        <button @click="goToUserInfo">用户信息</button>
      </div>
    </header>

    <main class="content">
      <div class="filter-sort">
        <div class="filter-group">
          <label>类型：</label>
          <select v-model="selectedType">
            <option value="">全部</option>
            <option value="青铜器">青铜器</option>
            <option value="陶瓷">陶瓷</option>
            <option value="玉器">玉器</option>
            <option value="书法">书法</option>
            <option value="雕塑">雕塑</option>
            <option value="漆器">漆器</option>
          </select>

          <label>年代：</label>
          <select v-model="selectedEra">
            <option value="">全部</option>
            <option value="汉代">汉代</option>
            <option value="唐代">唐代</option>
            <option value="宋代">宋代</option>
            <option value="元代">元代</option>
            <option value="明代">明代</option>
          </select>

          <label>博物馆：</label>
          <select v-model="selectedMuseum">
            <option value="">全部</option>
            <option value="大英博物馆">大英博物馆</option>
            <option value="大都会博物馆">大都会博物馆</option>
            <option value="故宫博物院">故宫博物院</option>
          </select>
        </div>

        <div class="sort-group">
          <label>排序：</label>
          <select v-model="sortKey">
            <option value="">默认</option>
            <option value="name">按名称</option>
            <option value="era">按年代</option>
          </select>
        </div>
      </div>

      <div class="artifact-list">
        <div
          v-for="artifact in paginatedArtifacts"
          :key="artifact.id"
          class="artifact-item"
          @click="goToDetail(artifact.id)"
        >
          <img :src="artifact.image || 'https://via.placeholder.com/150'" alt="文物图片" class="artifact-image" />
          <h2>{{ artifact.name }}</h2>
          <p><strong>类型：</strong>{{ artifact.type }}</p>
          <p><strong>年代：</strong>{{ artifact.era }}</p>
          <p><strong>博物馆：</strong>{{ artifact.museum }}</p>
          <p><strong>介绍：</strong>{{ artifact.description }}</p>
        </div>
      </div>

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
const selectedType = ref('')
const selectedEra = ref('')
const selectedMuseum = ref('')
const sortKey = ref('')


const artifacts = ref([
  { id: 1, name: '青铜鼎', type: '青铜器', era: '商代', museum: '大英博物馆', description: '商代使用的礼器。', image: '' },
  { id: 2, name: '陶瓷碗', type: '陶瓷', era: '宋代', museum: '故宫博物院', description: '宋代瓷器，造型优美。', image: '' },
  { id: 3, name: '玉佩', type: '玉器', era: '汉代', museum: '大都会博物馆', description: '汉代贵族佩戴的饰品。', image: '' },
  { id: 4, name: '铜镜', type: '青铜器', era: '唐代', museum: '故宫博物院', description: '唐代女性使用的化妆镜。', image: '' },
  { id: 5, name: '陶俑', type: '陶瓷', era: '汉代', museum: '大英博物馆', description: '汉代墓葬中的陪葬品。', image: '' },
  { id: 6, name: '白瓷杯', type: '陶瓷', era: '唐代', museum: '大都会博物馆', description: '唐代白瓷制作的酒杯。', image: '' },
  { id: 7, name: '玉龙', type: '玉器', era: '新石器时代', museum: '故宫博物院', description: '红山文化代表性文物。', image: '' },
  { id: 8, name: '书法手卷', type: '书法', era: '宋代', museum: '大都会博物馆', description: '宋代文人墨宝。', image: '' },
  { id: 9, name: '佛像', type: '雕塑', era: '北魏', museum: '大英博物馆', description: '北魏时期佛教造像。', image: '' },
  { id: 10, name: '漆盒', type: '漆器', era: '汉代', museum: '故宫博物院', description: '汉代精美漆器盒子。', image: '' },
  { id: 11, name: '青铜剑', type: '青铜器', era: '战国', museum: '大英博物馆', description: '战国时期兵器。', image: '' },
  { id: 12, name: '青花瓷盘', type: '陶瓷', era: '明代', museum: '大都会博物馆', description: '明代青花瓷作品。', image: '' },
  { id: 13, name: '玉璧', type: '玉器', era: '周代', museum: '故宫博物院', description: '周代宗教祭祀用器。', image: '' },
  { id: 14, name: '石狮雕像', type: '雕塑', era: '宋代', museum: '大都会博物馆', description: '守护建筑的大型石雕。', image: '' },
  { id: 15, name: '漆盘', type: '漆器', era: '战国', museum: '大英博物馆', description: '战国漆器工艺代表作。', image: '' },
  { id: 16, name: '铜编钟', type: '青铜器', era: '周代', museum: '大英博物馆', description: '古代乐器编钟。', image: '' },
  { id: 17, name: '瓷枕', type: '陶瓷', era: '宋代', museum: '故宫博物院', description: '宋代瓷制生活用品。', image: '' },
  { id: 18, name: '玉环', type: '玉器', era: '商代', museum: '大都会博物馆', description: '商代玉石饰物。', image: '' },
  { id: 19, name: '楷书碑文', type: '书法', era: '唐代', museum: '大都会博物馆', description: '唐代名家碑帖。', image: '' },
  { id: 20, name: '木雕佛像', type: '雕塑', era: '明代', museum: '故宫博物院', description: '明代佛教木雕艺术。', image: '' },
  { id: 21, name: '黑漆盒', type: '漆器', era: '宋代', museum: '大英博物馆', description: '宋代漆器代表。', image: '' },
  { id: 22, name: '青铜斧', type: '青铜器', era: '夏代', museum: '故宫博物院', description: '夏代工具或武器。', image: '' },
  { id: 23, name: '瓷瓶', type: '陶瓷', era: '元代', museum: '大英博物馆', description: '元代外销瓷器。', image: '' },
  { id: 24, name: '玉剑饰', type: '玉器', era: '战国', museum: '大都会博物馆', description: '装饰剑柄用玉饰。', image: '' },
  { id: 25, name: '草书手卷', type: '书法', era: '明代', museum: '大英博物馆', description: '明代草书书法作品。', image: '' },
  { id: 26, name: '石雕佛龛', type: '雕塑', era: '北齐', museum: '大都会博物馆', description: '佛教艺术石雕。', image: '' },
  { id: 27, name: '朱漆盘', type: '漆器', era: '汉代', museum: '故宫博物院', description: '汉代朱红色漆盘。', image: '' },
  { id: 28, name: '铜人像', type: '青铜器', era: '西汉', museum: '大英博物馆', description: '汉代人物雕像。', image: '' },
  { id: 29, name: '陶壶', type: '陶瓷', era: '战国', museum: '大都会博物馆', description: '战国陶器代表。', image: '' },
  { id: 30, name: '玉带钩', type: '玉器', era: '唐代', museum: '故宫博物院', description: '用于束带的玉钩。', image: '' }
])

const currentPage = ref(1)
const itemsPerPage = 30

const filteredArtifacts = computed(() => {
  let result = artifacts.value

  if (searchQuery.value.trim()) {
    const query = searchQuery.value.trim()
    result = result.filter(a =>
      a.name.includes(query) || a.type.includes(query) || a.description.includes(query)
    )
  }

  if (selectedType.value) {
    result = result.filter(a => a.type === selectedType.value)
  }

  if (selectedEra.value) {
    result = result.filter(a => a.era === selectedEra.value)
  }

  if (selectedMuseum.value) {
    result = result.filter(a => a.museum === selectedMuseum.value)
  }

  if (sortKey.value === 'name') {
    result = [...result].sort((a, b) => a.name.localeCompare(b.name))
  } else if (sortKey.value === 'era') {
    result = [...result].sort((a, b) => a.era.localeCompare(b.era))
  }

  return result
})

const totalPages = computed(() => Math.ceil(filteredArtifacts.value.length / itemsPerPage))

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
  router.push({ name: 'ArtifactDetail', query: { id } })
}

function goToUserInfo() {
  router.push({ name: 'profile' })
}

function goToVisualization() {
  router.push({ name: 'visualization' })
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

.user-info {
  display: flex;
  gap: 10px;
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

.filter-sort {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  margin-bottom: 20px;
  gap: 10px;
}

.filter-group,
.sort-group {
  display: flex;
  align-items: center;
  gap: 10px;
}

.filter-group label,
.sort-group label {
  font-weight: bold;
}

.filter-group select,
.sort-group select {
  padding: 5px;
  font-size: 14px;
}

.artifact-list {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
}

.artifact-item {
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 8px;
  text-align: center;
  transition: transform 0.2s;
  cursor: pointer;
  height: 320px;
  overflow: hidden;
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
