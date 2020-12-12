<template>
  <div class="flex p-4 justify-between">
    <div>
      <button class="text-gray-300 focus:outline-none hover:text-gray-200">
        <svg
          class="w-10 h-10"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M4 6h16M4 12h16M4 18h7"
          ></path>
        </svg>
      </button>
    </div>
    <div>
      <LoginButton v-if="!$auth.loggedIn"></LoginButton>
      <LogoutButton v-else></LogoutButton>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style></style>
