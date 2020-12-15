<template>
  <div>
    <h1 class="px-4 text-3xl font-bold text-gray-300">Personal Courses</h1>
    <ul
      class="grid grid-cols-1 gap-6 sm:p-4 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4"
    >
      <CoursePreview
        v-for="course in courses"
        :key="course.id"
        :course="course"
      ></CoursePreview>
    </ul>
  </div>
</template>

<script>
export default {
  computed: {
    courses() {
      return this.$store.state.personalCourses
    },
  },
}
</script>

<style></style>
