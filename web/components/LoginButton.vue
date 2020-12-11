<template>
  <div class="p-4">
    <button class="bg-gray-200 p-4 rounded-lg" @click="login">Login</button>
  </div>
</template>

<script>
export default {
  methods: {
    async login() {
      try {
        const response = await this.$auth.loginWith('keycloak')
        console.log(response)
      } catch (err) {
        console.log(err)
      }
    },
  },
}
</script>
