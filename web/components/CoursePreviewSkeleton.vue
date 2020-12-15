<template>
  <div>
    <h1 class="px-4 mb-8 text-3xl font-bold text-gray-300 sm:mb-0">
      Featured Courses
    </h1>
    <ul
      class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4"
    >
      <li v-for="x in 3" :key="x" class="rounded-lg animate-pulse">
        <div class="h-24 bg-gray-700 rounded-t-lg opacity-60">
          <div class="inline-block float-right p-4">
            <button class="rounded-full focus:outline-none">
              <svg
                class="w-10 h-10 text-gray-500 duration-200 hover:text-gray-100"
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
          class="flex flex-col p-8 text-center bg-gray-700 rounded-b-lg shadow-md opacity-60"
        >
          <div class="text-left">
            <p class="w-40 h-3 bg-gray-500 rounded-md"></p>
            <p class="w-64 h-3 mt-2 bg-gray-600 rounded-md"></p>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>
