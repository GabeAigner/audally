<template>
  <ul
    class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4"
  >
    <li v-for="x in 3" :key="x" class="rounded-lg animate-pulse">
      <div class="h-24 bg-gray-700 rounded-t-lg opacity-60">
        <div class="inline-block float-right p-4">
          <button class="focus:outline-none rounded-full">
            <svg
              class="w-10 h-10 text-gray-500 hover:text-gray-100 duration-200"
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
      </div>
      <div
        class="flex flex-col text-center bg-gray-700 rounded-b-lg shadow-md p-8 opacity-60"
      >
        <div class="text-left">
          <p class="w-40 h-3 rounded-md bg-gray-500"></p>
          <p class="mt-2 w-64 h-3 rounded-md bg-gray-600"></p>
        </div>
      </div>
    </li>
  </ul>
</template>
