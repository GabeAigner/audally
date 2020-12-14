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
    <PersonalCourses
      v-if="personalCourses != 0"
      :courses="personalCourses"
    ></PersonalCourses>
  </div>
</template>

<script>
export default {
  async fetch() {
    await fetch('http://localhost:8080/api/courses')
      .then((response) => response.json())
      .then((data) => (this.courses = data))
    if (this.$auth.loggedIn) {
      let user
      await fetch('http://localhost:8080/api/users/1') //  + this.$auth.user.email
        .then((response) => response.json())
        .then((data) => (user = data))
      if (user !== undefined) {
        await fetch('http://localhost:8080/api/users/' + user.id + '/courses')
          .then((response) => response.json())
          .then((data) => (this.personalCourses = data))
      }
    }
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
