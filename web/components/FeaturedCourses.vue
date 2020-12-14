<template>
  <div>
    <h1 class="px-4 text-gray-300 text-3xl font-bold">Featured Courses</h1>
    <ul
      class="sm:p-4 grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4"
    >
      <CoursePreview
        v-for="course in courses"
        :key="course.id"
        :course="course"
        listtype="featured"
        :user="userId"
      ></CoursePreview>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    userId: {
      type: Number,
      default: -1,
    },
  },
  computed: {
    courses() {
      if (this.$store.state.courses === undefined) {
        return null
      }
      return this.$store.state.courses
    },
  },
}
</script>

<style></style>
