<template>
  <div class="visualization-page">
    <header class="header">
      <div class="logo">
        <img src="@/assets/buct.webp" alt="Logo" />
      </div>
      <div class="title">
        <h1 class="system-title">海外文物数据可视化分析</h1>
      </div>
      <div class="nav-buttons">
        <button @click="goToBrowse">返回浏览</button>
        <button @click="goToUserInfo">用户信息</button>
      </div>
    </header>

    <main class="content">
      <div class="chart-container">
        <div class="chart-box">
          <h2>文物朝代分布</h2>
          <div ref="eraChart" class="chart"></div>
        </div>
        <div class="chart-box">
          <h2>文物类型分布</h2>
          <div ref="typeChart" class="chart"></div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import * as echarts from 'echarts'

const router = useRouter()
const eraChart = ref(null)
const typeChart = ref(null)

const artifactData = [
  { era: '商代', type: '青铜器' },
  { era: '宋代', type: '陶瓷' },
  { era: '汉代', type: '玉器' },
  { era: '唐代', type: '青铜器' },
  { era: '宋代', type: '陶瓷' },
  { era: '汉代', type: '玉器' },
  { era: '明代', type: '陶瓷' },
  { era: '战国', type: '青铜器' },
  { era: '北魏', type: '雕塑' },
  { era: '周代', type: '青铜器' },
  { era: '唐代', type: '书法' },
  { era: '汉代', type: '漆器' },
  { era: '宋代', type: '陶瓷' },
  { era: '元代', type: '陶瓷' },
  { era: '战国', type: '漆器' },
  { era: '北齐', type: '雕塑' },
  { era: '西汉', type: '青铜器' },
  { era: '新石器时代', type: '玉器' }
]

onMounted(() => {
  renderCharts()
})

function renderCharts() {

  const eraCounts = {}
  artifactData.forEach(item => {
    eraCounts[item.era] = (eraCounts[item.era] || 0) + 1
  })
  const eraData = Object.entries(eraCounts).map(([name, value]) => ({ name, value }))


  const typeCounts = {}
  artifactData.forEach(item => {
    typeCounts[item.type] = (typeCounts[item.type] || 0) + 1
  })
  const typeData = Object.entries(typeCounts).map(([name, value]) => ({ name, value }))


  const eraChartInstance = echarts.init(eraChart.value)
  eraChartInstance.setOption({
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      right: 10,
      top: 'center',
      data: eraData.map(item => item.name)
    },
    series: [
      {
        name: '朝代分布',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '18',
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: eraData
      }
    ]
  })


  const typeChartInstance = echarts.init(typeChart.value)
  typeChartInstance.setOption({
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      right: 10,
      top: 'center',
      data: typeData.map(item => item.name)
    },
    series: [
      {
        name: '类型分布',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '18',
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: typeData
      }
    ]
  })


  window.addEventListener('resize', function() {
    eraChartInstance.resize()
    typeChartInstance.resize()
  })
}

function goToBrowse() {
  router.push({ name: 'Browse' })
}

function goToUserInfo() {
  router.push({ name: 'profile' })
}
</script>

<style scoped>
.visualization-page {
  background-color: #f4f4f4;
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  width: 100%;
  background-color: #1e90ff;
  height: 80px;
  padding: 0 20px;
  display: flex;
  align-items: center;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.logo img {
  width: 60px;
  height: 60px;
  object-fit: cover;
}

.title {
  flex: 1;
  margin-left: 20px;
}

.system-title {
  margin: 0;
  font-size: 22px;
  font-weight: bold;
  color: white;
}

.nav-buttons {
  display: flex;
  gap: 10px;
}

.nav-buttons button {
  background-color: #ff7f50;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.nav-buttons button:hover {
  background-color: #ff4500;
}

.content {
  flex: 1;
  padding: 20px;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.chart-container {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}

.chart-box {
  flex: 1;
  min-width: 400px;
  background-color: #ffffff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.chart-box h2 {
  text-align: center;
  margin-bottom: 15px;
  color: #333;
}

.chart {
  width: 100%;
  height: 400px;
}

@media (max-width: 768px) {
  .chart-container {
    flex-direction: column;
  }

  .chart-box {
    min-width: 100%;
  }
}
</style>
