<template>
  <div>
    <DataTable :value="tasks" responsiveLayout="scroll">
      <Column field="title" header="Aufgabe" headerClass="header-bold"></Column>
      <Column header="Status" headerClass="header-bold" >
        <template #body="{ data }">
          {{ data.completed ? 'Erledigt' : 'Zu Erledigen' }}
          </template>
      </Column>
      <Column header="Aktionen" headerClass="header-bold" :body="actionsTemplate">
        <template #body="{ data }">
        <Button label="Delete" icon="pi pi-trash" @click="deleteTask(data.id)">Löschen</Button>
        <button @click="toggleCompleted(data)">Erledigen</button>
      </template></Column>

    </DataTable>

    <input v-model="newTaskTitle" Class="add-task" placeholder="Neue Aufgabe" >
    <Button icon="pi pi-check" outlined aria-label="Filter" @click="addTask({title: newTaskTitle, completed: false})" text raised   >Aufgabe hinzufügen</Button>
  </div>
</template>

<script>
import axios from 'axios';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import 'primeicons/primeicons.css'; // PrimeIcons


export default {
  data(){
    return {
      tasks: [],
      newTaskTitle: ''
    };
  },
  components: {
    //titleComponent,
    DataTable,
    Column
  },
  methods: {
    fetchTasks() {
      axios.get('http://localhost:8082/api/tasks').then(response => {
        this.tasks = response.data;
      })
          .catch(error => {
            console.log("There was an error fetching the tasks!", error);
          });
    },

    // Task hinzufügen
    addTask(newTask) {
      axios.post('http://localhost:8082/api/tasks', newTask).then(response => {
        this.tasks.push(response.data);
      })
          .catch(error => {
            console.log("There was an error adding the task!", error);
          });
    },

    //Task updaten
    updateTask(updatedTask) {
      axios.put('http://localhost:8082/api/tasks/${updatedTask.id}').then(response => {
        const index = this.tasks.findIndex(task => task.id === updatedTask.id);
        if (index !== -1) {
          this.$set(this.tasks, index, response.data);
        }
      }).catch(error => {
        console.error("There was an error updating the task!", error);
      });
    },

    // Task löschen
    deleteTask(id) {
      axios.delete(`http://localhost:8082/api/tasks/${id}`).then(() => {
        this.tasks = this.tasks.filter(task => task.id !== id);
      }).catch(error => {
        console.error("There was an error deleting this task!", error);
      });
    },

    // Task abschliessen
    toggleCompleted(task) {
      task.completed = !task.completed;
      this.updateTask(task);
    }
  },
  mounted() {
    this.fetchTasks();
  },
  computed: {
    statusTemplate(){
      return (rowData) => {
        return rowData.completed ? 'Completed' : 'Not Completed';
      };
    },
    actionsTemplate(){
      return (rowData) => {
        return `
            <button @click="deleteTask(${rowData.id})">Löschen</button>
            <button @click="toggleCompleted(${rowData})">Aufgabe erledigt</button>`
      }
    }
  }
};
</script>



<style>

.task-item {
  display: table;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}

.task-item > * {
  margin-right: 10px;
}

.status {
  margin-right: auto;
  margin-left: 10px;
}

.header-bold {
  font-weight: bold;
}

.add-task {
  margin-top: 20px;
}
</style>