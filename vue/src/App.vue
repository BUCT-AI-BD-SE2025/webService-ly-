<template>

  <router-view />
</template>

<script>
export default {
  name: 'App'
}
</script>

<!--<style>-->
<!--/* 全局基础样式 (所有页面生效) */-->
<!--#app {-->
<!--  font-family: Arial, 'Microsoft YaHei', sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  color: #2c3e50;-->
<!--}-->

<!--/* 强制重置默认样式 */-->
<!--html, body {-->
<!--  height: 100%;-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--  box-sizing: border-box;-->
<!--}-->

<!--/* 👇加上这段就完美了！ */-->
<!--*, *::before, *::after {-->
<!--  box-sizing: inherit;-->
<!--}-->

<!--/* 创建全局布局系统 */-->
<!--.page {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--  min-height: 100vh;-->
<!--  width: 100%;-->
<!--  padding: 20px;-->
<!--  position: relative;-->
<!--  background: #f8f9fa; /* 默认背景色 */-->
<!--}-->

<!--/* 卡片通用样式 */-->
<!--.card {-->
<!--  background: white;-->
<!--  border-radius: 12px;-->
<!--  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);-->
<!--  padding: 2rem;-->
<!--  width: 100%;-->
<!--  max-width: 400px;-->
<!--  margin: 0 auto 1.5rem;-->
<!--}-->

<!--/* 输入框统一样式 */-->
<!--input {-->
<!--  display: block;-->
<!--  width: 100%;-->
<!--  padding: 0.75rem;-->
<!--  border: 1px solid #ced4da;-->
<!--  border-radius: 4px;-->
<!--  margin: 0.5rem 0;-->
<!--}-->

<!--/* 按钮基础样式 */-->
<!--button {-->
<!--  cursor: pointer;-->
<!--  padding: 0.75rem 1.5rem;-->
<!--  border: none;-->
<!--  border-radius: 4px;-->
<!--  transition: all 0.2s;-->
<!--}-->

<!--/* 底部链接定位 */-->
<!--.link-below {-->
<!--  position: absolute;-->
<!--  bottom: 2rem;-->
<!--  text-align: center;-->
<!--}-->
<!--</style>-->

