<template>
  <li
    class="bg-center bg-cover rounded-lg"
    :style="{ 'background-image': `url(${course.pictureUrl})` }"
    @click="toDetail"
  >
    <div class="h-24 bg-gray-800 bg-opacity-50 rounded-t-lg" @click="toDetail">
      <div class="inline-block float-right p-4">
        <button
          v-if="listtype === 'featured'"
          class="text-gray-400 rounded-full focus:outline-none"
          @click="addCourse"
        >
          <svg
            class="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M12 6v6m0 0v6m0-6h6m-6 0H6"
            ></path>
          </svg>
        </button>
        <button
          v-if="listtype !== 'featured'"
          class="inline-block text-gray-400 rounded-full focus:outline-none"
          @click="removeCourse"
        >
          <svg
            class="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
            ></path>
          </svg>
        </button>
      </div>
      <!-- Thumbnail image -->
    </div>
    <div
      class="flex flex-col p-8 text-center bg-gray-800 bg-opacity-50 rounded-b-lg shadow-md"
    >
      <div class="text-left">
        <p class="text-xl font-medium text-white">{{ course.name }}</p>
        <p class="text-gray-200">{{ course.description }}</p>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: {
    listtype: {
      default: '',
      type: String,
    },
    userid: {
      type: Number,
      default: -1,
    },
    course: {
      type: Object,
      default() {
        return {
          id: 1,
          name: 'Default',
          lessons: [],
          description: 'Default',
        }
      },
    },
  },
  computed: {
    courses() {
      return this.$store.state.personalCourses
    },
  },
  methods: {
    toDetail() {
      this.$router.push('CourseView')
    },
    async addCourse(e) {
      if (this.listtype === 'featured' && this.$auth.loggedIn) {
        if (this.courses.some((c) => c.id === this.course.id)) {
          return 0
        }
        this.$store.commit('addPersonalCourse', this.course)
        await fetch(
          process.env.backendUrl +
            '/users/' +
            this.userid +
            '/courses/' +
            this.course.id,
          {
            method: 'post',
          }
        )
      }
    },
    async removeCourse(e) {
      if (this.listtype !== 'featured' && this.$auth.loggedIn) {
        this.$store.commit('removePersonalCourse', this.course)
        await fetch(
          process.env.backendUrl +
            '/users/' +
            this.userid +
            '/courses/' +
            this.course.id,
          {
            method: 'delete',
          }
        )
      }
    },
  },
}
</script>

<style scoped>
li {
  background-image: url('https://images.unsplash.com/photo-1513644183929-03d571e0bf5c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
}
</style>
