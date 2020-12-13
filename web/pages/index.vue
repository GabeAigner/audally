<template>
  <div class="bg-gray-800 min-h-screen">
    <ButtonBar></ButtonBar>
    <FeaturedCourses
      v-if="courses.length != 0"
      :courses="courses"
    ></FeaturedCourses>
    <div v-else class="p-4">
      <CoursePreviewSkeleton></CoursePreviewSkeleton>
      <!--<li
        class="flex flex-col text-center bg-gray-700 rounded-lg shadow-md p-8"
      >
        <div class="text-left">
          <p class="text-gray-400">Searching for courses...</p>
        </div>
      </li>-->
    </div>
    <PersonalCourses v-if="$auth.loggedIn" :courses="courses"></PersonalCourses>
  </div>
</template>

<script>
export default {
  async fetch() {
    if (this.$auth.loggedIn) {
      /* fetch personal courses */
    }
    await fetch('http://localhost:8080/api/courses')
      .then((response) => response.json())
      .then((data) => (this.courses = data))
  },
  data() {
    return {
      courses: [],
      personalCourses: [],
    }
  },
  fetchOnServer: false,
}
</script>

<style></style>
