<template>
  <div class="bg-gray-800 min-h-screen">
    <ButtonBar></ButtonBar>
    <FeaturedCourses
      v-if="this.$store.state.courses.length != 0"
      :user-id="user.id"
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
      v-if="this.$store.state.personalCourses.length != 0"
    ></PersonalCourses>
  </div>
</template>

<script>
export default {
  async fetch() {
    await fetch('http://localhost:8080/api/courses')
      .then((response) => response.json())
      .then((data) => (this.courses = data))
    this.$store.commit('setCourses', this.courses)
    if (this.$auth.loggedIn) {
      await fetch('http://localhost:8080/api/users/' + this.$auth.user.email) //  + this.$auth.user.email
        .then((response) => response.json())
        .then((data) => (this.user = data))
      if (this.user !== undefined) {
        await fetch(
          'http://localhost:8080/api/users/' + this.user.id + '/courses'
        )
          .then((response) => response.json())
          .then((data) => (this.personalCourses = data))
        this.$store.commit('setPersonalCourses', this.personalCourses)
      }
    }
  },
  data() {
    return {
      user: {},
    }
  },
  fetchOnServer: false,
}
</script>

<style></style>
