<template>
  <div class="bg-gray-800 min-h-screen">
    <LoginButton v-if="!$auth.loggedIn"></LoginButton>
    <LogoutButton v-else></LogoutButton>
    <FeaturedCourses
      v-if="courses.length != 0 && !$auth.loggedIn"
      :courses="courses"
    ></FeaturedCourses>
    <PersonalCourses
      v-else-if="$auth.loggedIn"
      :courses="courses"
    ></PersonalCourses>
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
