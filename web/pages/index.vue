<template>
  <div class="min-h-screen bg-gray-800">
    <PageHeader name="Home"></PageHeader>
    <Toast
      class="z-20"
      :show="showToast"
      :positive="positiveToast"
      @toggleToast="showToast = false"
    ></Toast>
    <div class="mt-4 lg:px-4">
      <FeaturedCourses
        v-if="this.$store.state.courses.length != 0"
        :userid="user.id"
        @toggleToast="toggleToast(true)"
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
        @toggleToast="toggleToast(false)"
      ></PersonalCourses>
    </div>
    <!-- Bottom Bar for Audio Player -->
    <div
      class="fixed bottom-0 right-0 flex p-2 border-t-2 border-gray-700 left-64"
    >
      <div
        class="flex flex-col justify-center w-1/4 px-4 py-2 font-medium text-center text-white hover:text-gray-300"
      >
        <h3 class="truncate ...">
          Hello my name is lesson from Gabriel Aigner and Sandro Tadic
        </h3>
      </div>
      <div class="w-3/4">
        <vue-plyr class="relative">
          <audio controls crossorigin playsinline preload="auto">
            <source id="plyr" :src="current" type="audio/mp3" />
          </audio>
        </vue-plyr>
      </div>
    </div>
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
        'http://localhost:8080/api/users/email/' + this.$auth.user.email // !: I also get a completely wrong User object!
      ) //  + this.$auth.user.email                                 // ?: Please test eh endpoints next time first!! :D
        .then((response) => response.json())
        .then((data) => (this.user = data))
      if (this.user !== undefined) {
        await fetch(
          'http://localhost:8080/api/users/' + this.user.id + '/courses'
        )
          .then((response) => response.json())
          .then((data) => {
            this.personalCourses = data // !: I get a double nested courses array from the backend. Please fix
            // this.personalCourses = this.personalCourses.courses
          })

        if (
          this.personalCourses.length > 0 ||
          this.personalCourses.length !== undefined
        ) {
          this.$store.commit('setPersonalCourses', this.personalCourses)
        }
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
  methods: {
    toggleToast(posOrNot) {
      this.positiveToast = posOrNot
      this.showToast = true
      /* this cant be used in setTimeout so I need to preserve the value of this in that
      I found the solution here: https://stackoverflow.com/a/14571933/12722918 */
      const that = this
      setTimeout(function () {
        that.showToast = false
      }, 4000)
    },
  },
  fetchOnServer: false,
}
</script>

<style>
body {
  --plyr-color-main: #6b46c1;
  --plyr-audio-controls-background: #2d3748;
  --plyr-audio-control-color: white;
}

.left-64 {
  left: 16rem;
}
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
