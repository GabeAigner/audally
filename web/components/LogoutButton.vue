<template>
  <div class="">
    <button class="bg-gray-400 p-4 rounded-lg" @click="logout">Logout</button>
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
