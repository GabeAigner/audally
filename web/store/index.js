export const state = () => ({
  currentCourse: {},
  courses: [],
  personalCourses: [],
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
}
