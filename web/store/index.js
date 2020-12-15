export const state = () => ({
  courses: [],
  personalCourses: [],
})

export const mutations = {
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
    const index = state.personalCourses.indexOf((c) => c === course.id)
    state.personalCourses.splice(index, 1)
  },
}
