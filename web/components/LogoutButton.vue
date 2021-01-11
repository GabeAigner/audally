<template>
  <button
    href="#"
    class="block w-full px-4 py-2 text-sm text-left text-gray-700 hover:bg-gray-100 hover:text-gray-900"
    role="menuitem"
    @click="logout"
  >
    Logout
  </button>
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
