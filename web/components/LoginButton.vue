<template>
  <div>
    <button
      class="bg-gray-700 shadow-md px-3 py-2 rounded-lg text-white hover:shadow-none"
      @click="login"
    >
      Login
    </button>
  </div>
</template>

<script>
export default {
  methods: {
    async login() {
      try {
        await this.$auth.loginWith('keycloak')
      } catch (err) {}
    },
  },
}
</script>
