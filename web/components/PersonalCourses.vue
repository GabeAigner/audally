<template>
  <div>
    <h1 class="px-4 text-gray-300 text-3xl font-bold">Personal Courses</h1>
    <ul
      class="sm:p-4 grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4"
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
  props: ['courses'],
}
</script>

<style></style>
