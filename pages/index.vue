<template>
  <div></div>
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
