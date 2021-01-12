<template>
  <div
    class="px-4 py-6 border-b border-gray-700 shadow lg:px-8 md:flex md:items-center md:justify-between"
  >
    <div class="flex">
      <button
        v-if="back"
        class="pr-1 mr-2 text-white rounded-full hover:text-gray-300"
        @click="$router.back()"
      >
        <svg
          class="w-6 h-6"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M15 19l-7-7 7-7"
          ></path>
        </svg>
      </button>
      <h2 class="text-3xl font-bold leading-7 text-white sm:truncate">
        {{ name }}
      </h2>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    back: {
      type: Boolean,
      default: false,
    },
    name: {
      type: String,
      default: 'default',
    },
  },
}
</script>

<style></style>
