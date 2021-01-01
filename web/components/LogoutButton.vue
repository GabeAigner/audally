<template>
  <div class="">
    <button class="bg-gray-400 px-3 py-2 rounded-lg" @click="logout">
      Logout
    </button>
  </div>
</template>

<script>
export default {
  methods: {
    async logout() {
      try {
        await this.$auth.logout('keycloak')
      } catch (err) {}
    },
  },
}
</script>
