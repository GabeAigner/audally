<template>
  <div class="p-4">
    <button class="bg-gray-200 p-4 rounded-lg" @click="login">Login</button>
  </div>
</template>

<script>
export default {
  name: 'LoginVue',
  auth: 'guest',
  methods: {
    async login() {
      try {
        const response = await this.$auth.loginWith('keycloak')
        // eslint-ignore-next-line
        console.log(response)
      } catch (err) {
        // eslint-ignore-next-line
        console.log(err)
      }
    },
  },
}
</script>
