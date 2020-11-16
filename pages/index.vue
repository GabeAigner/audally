<template>
  <div class="bg-gray-800 min-h-screen">
    <ul
      v-if="courses.length != 0"
      class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 p-4"
    >
      <CoursePreview
        v-for="course in courses"
        :key="course.id"
        :name="course.name"
        :description="course.description"
      ></CoursePreview>
    </ul>
    <div v-else class="p-4">
      <li
        class="flex flex-col text-center bg-gray-700 rounded-lg shadow-md p-8"
      >
        <div class="text-left">
          <p class="text-gray-400">No courses found...</p>
        </div>
      </li>
    </div>
  </div>
</template>

<script>
export default {
  async fetch() {
    await fetch('http://localhost:8080/api/courses')
      .then((response) => response.json())
      .then((data) => (this.courses = data))
  },
  data() {
    return {
      courses: [],
    }
  },
  fetchOnServer: false,
}
</script>

<style></style>
