<template>
  <div class="p-4">
    <button class="bg-gray-400 p-4 rounded-lg" @click="logout">Logout</button>
  </div>
</template>

<script>
export default {
  methods: {
    async logout() {
      try {
        const response = await this.$auth.logout('keycloak')
        console.log(response)
      } catch (err) {
        console.log(err)
      }
    },
  },
}
</script>
