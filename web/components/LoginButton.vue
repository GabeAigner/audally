<template>
  <div class="sm:p-4 mb-8 sm:mb-0 rounded-lg flex justify-end">
    <button
      class="bg-gray-700 shadow-md p-4 rounded-lg text-white hover:shadow-none"
      @click="login"
    >
      Login / Register
    </button>
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
