<template>
  <div class="min-h-screen bg-gray-800">
    <PageHeader name="My Courses"></PageHeader>
    <Toast
      class="z-20"
      :show="showToast"
      :positive="positiveToast"
      @toggleToast="showToast = false"
    ></Toast>
    <div class="mt-4 lg:px-4">
      <PersonalCourses
        v-if="this.$store.state.personalCourses.length != 0 && $auth.loggedIn"
        :userid="user.id"
        @toggleToast="toggleToast(false)"
      ></PersonalCourses>
    </div>
  </div>
</template>

<script>
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
        this.$store.commit('setUser', this.user)
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
