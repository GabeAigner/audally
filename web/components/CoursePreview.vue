<template>
  <li>
    <div class="h-24 bg-gray-700 rounded-t-lg">
      <div class="inline-block float-right p-4">
        <button class="focus:outline-none rounded-full">
          <svg
            class="w-10 h-10 text-gray-400 hover:text-gray-100 duration-200"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z"
            ></path>
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
            ></path>
          </svg>
        </button>
      </div>
      <!-- Thumbnail image -->
    </div>
    <div
      class="flex flex-col text-center bg-gray-700 rounded-b-lg shadow-md p-8"
    >
      <div class="text-left">
        <p class="text-white font-medium text-xl">{{ name }}</p>
        <p class="text-gray-400">{{ description }}</p>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: {
    name: String,
    description: String,
  },
}
</script>

<style></style>
