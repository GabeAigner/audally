<template>
  <li class="bg-cover bg-center rounded-lg">
    <div class="h-24 bg-gray-700 rounded-t-lg bg-opacity-50">
      <div class="inline-block float-right p-4">
        <button class="focus:outline-none rounded-full" @click="addCourse">
          <svg
            class="w-10 h-10 text-gray-400 hover:text-gray-100 duration-200"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z"
            ></path>
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
            ></path>
          </svg>
        </button>
      </div>
      <!-- Thumbnail image -->
    </div>
    <div
      class="flex flex-col text-center bg-gray-700 rounded-b-lg shadow-md p-8 bg-opacity-50"
    >
      <div class="text-left">
        <p class="text-white font-medium text-xl">{{ course.name }}</p>
        <p class="text-gray-400">{{ course.description }}</p>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: {
    listtype: {
      default: 'featured',
      type: String,
    },
    userId: {
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
  methods: {
    addCourse(e) {},
  },
}
</script>

<style scoped>
li {
  background-image: url('https://images.unsplash.com/photo-1513644183929-03d571e0bf5c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
}
</style>
