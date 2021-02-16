<template>
  <div></div>
</template>

<script>
export default {
  created() {
    this.$router.push('/')
    setTimeout(() => {
      location.reload()
    }, 200)
  },
}
</script>

<style></style>
