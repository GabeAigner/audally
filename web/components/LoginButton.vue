<template>
  <button
    type="button"
    class="inline-flex items-center justify-center w-full px-6 py-3 text-base font-medium text-gray-200 bg-purple-700 border border-transparent rounded-md shadow-sm hover:bg-purple-800 focus:outline-none"
    @click="login"
  >
    Login / Register
    <!-- Heroicon name: login -->
    <svg
      class="w-5 h-5 ml-3 -mr-1"
      fill="none"
      stroke="currentColor"
      viewBox="0 0 24 24"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width="2"
        d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"
      ></path>
    </svg>
  </button>
  <!--<div>
    <button
      class="px-3 py-2 text-white bg-gray-700 rounded-lg shadow-md hover:shadow-none"
      @click="login"
    >
      Login
    </button>
  </div>-->
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
