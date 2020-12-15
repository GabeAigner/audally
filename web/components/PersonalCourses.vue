<template>
  <div>
    <h1 class="px-4 mt-8 text-3xl font-bold text-gray-300 sm:mb-0">
      Personal Courses
    </h1>
    <ul
      class="grid grid-cols-1 gap-6 sm:p-4 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4"
    >
      <CoursePreview
        v-for="course in courses"
        :key="course.id"
        :course="course"
        :userid="userid"
      ></CoursePreview>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    userid: {
      type: Number,
      default: -1,
    },
  },
  computed: {
    courses() {
      return this.$store.state.personalCourses
    },
  },
}
</script>

<style></style>
