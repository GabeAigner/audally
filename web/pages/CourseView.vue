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
    <div
      class="bg-center bg-cover rounded-lg w-full h-64"
      :style="{ 'background-image': `url(${course.pictureUrl})` }"
    ></div>
    <div class="bg-gray-800 rounded-2xl -mt-8 p-4">
      <h1 class="text-2xl tracking-wider text-white font-bold">
        {{ course.name }}
      </h1>
      <p class="mt-4 text-lg tracking-wider text-gray-300">
        {{ course.description }}
      </p>
    </div>
    <div>
      <!-- This example requires Tailwind CSS v2.0+ -->
      <div class="p-4">
        <h2 class="text-gray-500 text-xs font-medium uppercase tracking-wide">
          Course Lessons
        </h2>
        <ul
          class="mt-3 grid grid-cols-1 gap-5 sm:gap-6 sm:grid-cols-2 lg:grid-cols-4"
        >
          <li class="col-span-1 flex shadow-sm rounded-md">
            <div
              class="flex-shrink-0 flex items-center justify-center w-16 bg-gray-700 text-white text-2xl font-medium rounded-l-md"
            >
              1.
            </div>
            <div
              class="flex-1 flex items-center justify-between bg-gray-700 rounded-r-md truncate"
            >
              <div class="flex-1 px-4 py-2 text-sm truncate">
                <a
                  href="#"
                  class="text-gray-900 font-medium hover:text-gray-600"
                  >Graph API</a
                >
                <p class="text-gray-500">16 Members</p>
              </div>
              <div class="flex-shrink-0 pr-2">
                <button
                  class="w-8 h-8 bg-white inline-flex items-center justify-center text-gray-400 rounded-full bg-transparent hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  <span class="sr-only">Open options</span>
                  <!-- Heroicon name: dots-vertical -->
                  <svg
                    class="w-5 h-5"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      d="M10 6a2 2 0 110-4 2 2 0 010 4zM10 12a2 2 0 110-4 2 2 0 010 4zM10 18a2 2 0 110-4 2 2 0 010 4z"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </li>

          <li class="col-span-1 flex shadow-sm rounded-md">
            <div
              class="flex-shrink-0 flex items-center justify-center w-16 bg-purple-600 text-white text-sm font-medium rounded-l-md"
            >
              CD
            </div>
            <div
              class="flex-1 flex items-center justify-between border-t border-r border-b border-gray-200 bg-white rounded-r-md truncate"
            >
              <div class="flex-1 px-4 py-2 text-sm truncate">
                <a
                  href="#"
                  class="text-gray-900 font-medium hover:text-gray-600"
                  >Component Design</a
                >
                <p class="text-gray-500">12 Members</p>
              </div>
              <div class="flex-shrink-0 pr-2">
                <button
                  class="w-8 h-8 bg-white inline-flex items-center justify-center text-gray-400 rounded-full bg-transparent hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  <span class="sr-only">Open options</span>
                  <!-- Heroicon name: dots-vertical -->
                  <svg
                    class="w-5 h-5"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      d="M10 6a2 2 0 110-4 2 2 0 010 4zM10 12a2 2 0 110-4 2 2 0 010 4zM10 18a2 2 0 110-4 2 2 0 010 4z"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </li>

          <li class="col-span-1 flex shadow-sm rounded-md">
            <div
              class="flex-shrink-0 flex items-center justify-center w-16 bg-yellow-500 text-white text-sm font-medium rounded-l-md"
            >
              T
            </div>
            <div
              class="flex-1 flex items-center justify-between border-t border-r border-b border-gray-200 bg-white rounded-r-md truncate"
            >
              <div class="flex-1 px-4 py-2 text-sm truncate">
                <a
                  href="#"
                  class="text-gray-900 font-medium hover:text-gray-600"
                  >Templates</a
                >
                <p class="text-gray-500">16 Members</p>
              </div>
              <div class="flex-shrink-0 pr-2">
                <button
                  class="w-8 h-8 bg-white inline-flex items-center justify-center text-gray-400 rounded-full bg-transparent hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  <span class="sr-only">Open options</span>
                  <!-- Heroicon name: dots-vertical -->
                  <svg
                    class="w-5 h-5"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      d="M10 6a2 2 0 110-4 2 2 0 010 4zM10 12a2 2 0 110-4 2 2 0 010 4zM10 18a2 2 0 110-4 2 2 0 010 4z"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </li>

          <li class="col-span-1 flex shadow-sm rounded-md">
            <div
              class="flex-shrink-0 flex items-center justify-center w-16 bg-green-500 text-white text-sm font-medium rounded-l-md"
            >
              RC
            </div>
            <div
              class="flex-1 flex items-center justify-between border-t border-r border-b border-gray-200 bg-white rounded-r-md truncate"
            >
              <div class="flex-1 px-4 py-2 text-sm truncate">
                <a
                  href="#"
                  class="text-gray-900 font-medium hover:text-gray-600"
                  >React Components</a
                >
                <p class="text-gray-500">8 Members</p>
              </div>
              <div class="flex-shrink-0 pr-2">
                <button
                  class="w-8 h-8 bg-white inline-flex items-center justify-center text-gray-400 rounded-full bg-transparent hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  <span class="sr-only">Open options</span>
                  <!-- Heroicon name: dots-vertical -->
                  <svg
                    class="w-5 h-5"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      d="M10 6a2 2 0 110-4 2 2 0 010 4zM10 12a2 2 0 110-4 2 2 0 010 4zM10 18a2 2 0 110-4 2 2 0 010 4z"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </li>

          <li class="col-span-1 flex shadow-sm rounded-md">
            <div
              class="flex-shrink-0 flex items-center justify-center w-16 bg-green-500 text-white text-sm font-medium rounded-l-md"
            >
              RC
            </div>
            <div
              class="flex-1 flex items-center justify-between border-t border-r border-b border-gray-200 bg-white rounded-r-md truncate"
            >
              <div class="flex-1 px-4 py-2 text-sm truncate">
                <a
                  href="#"
                  class="text-gray-900 font-medium hover:text-gray-600"
                  >React Components</a
                >
                <p class="text-gray-500">8 Members</p>
              </div>
              <div class="flex-shrink-0 pr-2">
                <button
                  class="w-8 h-8 bg-white inline-flex items-center justify-center text-gray-400 rounded-full bg-transparent hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  <span class="sr-only">Open options</span>
                  <!-- Heroicon name: dots-vertical -->
                  <svg
                    class="w-5 h-5"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      d="M10 6a2 2 0 110-4 2 2 0 010 4zM10 12a2 2 0 110-4 2 2 0 010 4zM10 18a2 2 0 110-4 2 2 0 010 4z"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <span class="amplitude-play"></span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      course: '',
    }
  },
  created() {
    this.course = this.$store.state.currentCourse
  },
}
</script>

<style></style>
