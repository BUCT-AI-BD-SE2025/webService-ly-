<template>
  <div class="profile-page">
    <header class="profile-header">
      <h1>用户个人信息</h1>
      <button class="back-button" @click="goBack">返回</button>
    </header>

    <main class="profile-content">
      <div class="profile-card">
        <img :src="user.avatar" alt="用户头像" class="avatar" />
        <h2>{{ user.name }}</h2>
        <p><strong>邮箱：</strong>{{ user.email }}</p>
        <p><strong>注册时间：</strong>{{ user.registerDate }}</p>
      </div>
    </main>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue'

const router = useRouter()

// 假设这是从后端拿到的用户数据
const user = ref({
  name: '张三',
  email: 'zhangsan@example.com',
  registerDate: '2024-05-01',
  avatar: 'https://via.placeholder.com/120'
})


function goBack() {
  router.back()
}
</script>

<style scoped>
.profile-page {
  width: 100%;
  min-height: 100vh;
  background-color: white;
  display: flex;
  flex-direction: column;
}

.profile-header {
  background-color: #87cefa;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.profile-header h1 {
  margin: 0;
  font-size: 24px;
}

.back-button {
  background-color: #1e90ff;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
}

.profile-content {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.profile-card {
  background-color: #f0f0f0;
  padding: 30px;
  border-radius: 10px;
  text-align: center;
  width: 300px;
}

.avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 20px;
}
</style>
