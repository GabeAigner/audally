<template>
  <div class="h-screen bg-gray-800">
    <NuxtLink to="/" class="flex p-4 justify-between items-center">
      <div>
        <button
          class="text-gray-300 items-center flex focus:outline-none hover:text-gray-200"
        >
          <svg
            class="w-8 h-8"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M10 19l-7-7m0 0l7-7m-7 7h18"
            ></path>
          </svg>
        </button>
      </div>
      <div>
        <LoginButton v-if="!$auth.loggedIn"></LoginButton>
        <LogoutButton v-else></LogoutButton>
      </div>
    </NuxtLink>
    <span class="amplitude-play"></span>
  </div>
</template>

<script>
export default {}
</script>

<style></style>
