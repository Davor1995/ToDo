<script>
export default {
  props: {
    task: Object // Die Aufgabe wird als Prop übergeben
  }
}
</script>

<template>
  <h2 :class="{ completed: task.completed }" class="task-title">{{ task.title }}</h2>
</template>

<style scoped>
h2 {
  font-size: 18px;
  font-weight: bold;
  font-family: Arial, Helvetica, sans-serif;
  color: #2c3e50;
  text-align: left;
}

.completed {
  text-decoration: line-through;
  color: green;
}

</style>