<template>
  <div>
    <h1 class="px-4 mb-8 text-3xl font-bold text-gray-300 sm:mb-0">
      Featured Courses
    </h1>
    <ul
      class="grid grid-cols-1 gap-6 sm:p-4 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4"
    >
      <CoursePreview
        v-for="course in courses"
        :key="course.id"
        :course="course"
        listtype="featured"
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
      if (this.$store.state.courses === undefined) {
        return null
      }
      return this.$store.state.courses
    },
  },
}
</script>

<style></style>
