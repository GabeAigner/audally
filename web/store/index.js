export const state = () => ({
  currentCourse: {},
  courses: [],
  personalCourses: [],
  isPlaying: false,
  lesson: null,
})

export const mutations = {
  updateCourse(state, course) {
    state.currentCourse = course
  },
  addCourse(state, course) {
    state.courses.push(course)
  },
  addPersonalCourse(state, course) {
    state.personalCourses.push(course)
  },
  setCourses(state, courses) {
    state.courses = courses
  },
  setPersonalCourses(state, courses) {
    state.personalCourses = courses
  },
  removePersonalCourse(state, course) {
    const course2 = state.personalCourses.find((c) => c.id === course.id)
    const index = state.personalCourses.indexOf(course2)
    state.personalCourses.splice(index, 1)
  },
  updateLesson(state, lesson) {
    state.lesson = lesson
    if (lesson === null) {
      state.isPlaying = false
    } else {
      state.isPlaying = true
    }
  },
}

export const getters = {
  isAuthenticated(state) {
    return state.auth.loggedIn
  },
  loggedInUser(state) {
    return state.auth.user
  },
}
