<template>
  <div id="app">
    <h1>To Do Liste</h1>
      <TaskList />
  </div>
</template>

<script>

import TaskList from "@/components/TaskList.vue";


export default {
  components: {
    TaskList
  },
  data(){
    return {
      tasks: [],
      newTaskTitle: ''
    };
  },}


</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
