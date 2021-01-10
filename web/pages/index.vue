<template>
  <div class="min-h-screen bg-gray-800">
    <Toast
      class="z-20"
      :show="showToast"
      :positive="positiveToast"
      @toggleToast="showToast = false"
    ></Toast>
    <New></New>
    <button @click="showToast = !showToast">Toggle</button>
    <button @click="positiveToast = !positiveToast">Toggle</button>
    <FeaturedCourses
      v-if="this.$store.state.courses.length != 0"
      :userid="user.id"
      @toggleToast="showToast = true"
    ></FeaturedCourses>
    <div v-else class="p-4">
      <CoursePreviewSkeleton></CoursePreviewSkeleton>
      <!--<li
        class="flex flex-col p-8 text-center bg-gray-700 rounded-lg shadow-md"
      >
        <div class="text-left">
          <p class="text-gray-400">Searching for courses...</p>
        </div>
      </li>-->
    </div>
    <PersonalCourses
      v-if="this.$store.state.personalCourses.length != 0"
      :userid="user.id"
      @toggleToast="showToast = true"
    ></PersonalCourses>
  </div>
</template>

<script>
/*
::-webkit-scrollbar {
  width: 2px;
}
html {
  scrollbar-width: 2px;
}
*/

export default {
  layout: 'next',
  async fetch() {
    await fetch('http://localhost:8080/api/courses')
      .then((response) => response.json())
      .then((data) => (this.courses = data))
    this.$store.commit('setCourses', this.courses)
    if (this.$auth.loggedIn) {
      await fetch(
        'http://localhost:8080/api/users/email/' + this.$auth.user.email
      ) //  + this.$auth.user.email
        .then((response) => response.json())
        .then((data) => (this.user = data))
      if (this.user !== undefined) {
        await fetch(
          'http://localhost:8080/api/users/' + this.user.id + '/courses'
        )
          .then((response) => response.json())
          .then((data) => (this.personalCourses = data))
        // console.log(this.personalCourses)
        this.$store.commit('setPersonalCourses', this.personalCourses)
      }
    }
  },
  data() {
    return {
      user: {},
      showToast: false,
      personalCourses: null,
      positiveToast: null,
    }
  },
  fetchOnServer: false,
}
</script>

<style>
.slider {
  @apply w-full;
  text-align: center;
  overflow: hidden;
}

.slides {
  display: flex;

  overflow-x: scroll;
  scroll-snap-type: x none;

  scroll-behavior: smooth;
  -webkit-overflow-scrolling: touch;

  /*
  scroll-snap-points-x: repeat(300px);
  scroll-snap-type: mandatory;
  */
}

.slides::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

.slides::-webkit-scrollbar-thumb :hover {
  background: black;
  border-radius: 10px;
}

.slides::-webkit-scrollbar-track {
  background: transparent;
}

.slides > div {
  scroll-snap-align: center;
  flex-shrink: 0;
  width: 300px;
  height: 300px;
  margin-right: 50px;
  border-radius: 10px;
  background: #eee;
  transform-origin: center center;
  transform: scale(1);
  transition: transform 0.5s;
  position: relative;

  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 100px;
}
/* https://www.brunildo.org/test/overscrollback.html
https://stackoverflow.com/questions/13471910/no-padding-when-using-overflow-auto */

.slides > :last-child::after {
  content: '';
  display: block;
  position: absolute;
  right: -2rem;
  width: 5rem;
  height: 1px;
}
.slides > :last-child {
  scroll-snap-align: end;
}
.slide:after {
  content: '';
  display: block;
  position: absolute;
  right: -2rem;
  width: 5rem;
  height: 1px;
}
</style>
