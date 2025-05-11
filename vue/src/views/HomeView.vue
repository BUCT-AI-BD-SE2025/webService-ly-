<template>
  <div class="home">
    <h1>海外文物知识服务子系统</h1>
    <router-link to="/login">
      <button class="login-btn">去登录</button>
    </router-link>
    <router-link to="/register">
      <button class="login-reg">去注册</button>
    </router-link>
  </div>
</template>

<style scoped>
.home {
  position: absolute;         /* 让它脱离文档流占满整个页面 */
  top: 0;
  left: 0;
  width: 100vw;               /* 宽度 100% 的视口宽度 */
  height: 100vh;              /* 高度 100% 的视口高度 */
  background-image: url('@/assets/Eng.jpg'); /* 根据你实际图片路径调整 */
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding-top:100px;
}
.login-btn {
  padding: 16px 32px;
  font-size: 20px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  min-width: 200px;
  min-height: 60px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
  transition: all 0.3s ease;
}
.login-reg {
  padding: 16px 32px;
  font-size: 20px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  min-width: 200px;
  min-height: 60px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
  transition: all 0.3s ease;
}
.login-btn:hover {
  background-color: #66b1ff;
  transform: scale(1.05);
}
</style>
<script setup lang="ts">
</script>
