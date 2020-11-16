<template>
  <div class="bg-gray-800 h-screen">
    <ul
      class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 p-4"
    >
      <CoursePreview
        v-for="course in courses"
        :key="course.id"
        :name="course.name"
        :description="course.description"
      ></CoursePreview>
    </ul>
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
