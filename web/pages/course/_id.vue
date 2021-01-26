<template>
  <div class="h-screen bg-gray-800">
    <PageHeader :name="course.name" :back="true"></PageHeader>
    <div class="items-center justify-between hidden w-full lg:flex">
      <div
        class="flex flex-col items-start w-full p-4 rounded lg:p-8 lg:flex-row lg:items-center"
      >
        <div class="w-full pt-4 lg:pt-0 lg:w-2/3">
          <h1
            class="hidden text-4xl font-bold tracking-wider text-white lg:block"
          >
            {{ course.name }}
          </h1>
          <p class="mt-4 text-xl tracking-wider text-gray-300">
            {{ course.description }}
          </p>
          <p class="mt-4 text-lg tracking-wider text-gray-500">
            Lessons:
            {{ course.lessons.length }}
          </p>
        </div>
        <div
          :style="{ 'background-image': `url(${course.pictureUrl})` }"
          class="w-full h-24 bg-center bg-cover rounded-lg lg:w-1/3 lg:h-64"
        ></div>
      </div>
    </div>

    <div class="flex items-center justify-between w-full lg:hidden">
      <div
        class="flex flex-col items-start w-full p-4 rounded lg:p-8 lg:flex-row lg:items-center"
      >
        <div
          :style="{ 'background-image': `url(${course.pictureUrl})` }"
          class="w-full h-24 bg-center bg-cover rounded-lg lg:w-1/3 lg:h-64"
        ></div>
        <div class="w-full pt-4 lg:pt-0 lg:pl-8 lg:w-2/3">
          <h1
            class="hidden text-4xl font-bold tracking-wider text-white lg:block"
          >
            {{ course.name }}
          </h1>
          <p class="mt-4 text-xl tracking-wider text-gray-300">
            {{ course.description }}
          </p>
          <p class="mt-4 text-lg tracking-wider text-gray-500">
            Lessons:
            {{ course.lessons.length }}
          </p>
        </div>
      </div>
    </div>

    <!-- This example requires Tailwind CSS v2.0+ -->
    <div class="h-20 px-4 py-4 mx-auto max-w-7xl lg:px-8">
      <!-- We've used 3xl here, but feel free to try other max-widths based on your needs -->
      <div class="h-20 max-w-4xl">
        <h2 class="font-medium tracking-wide text-gray-500 uppercase text-md">
          Course Lessons
        </h2>
        <ul class="">
          <li
            v-for="(lesson, i) in course.lessons"
            :key="i"
            class="flex col-span-1 mt-3 rounded-md shadow-sm"
          >
            <div
              class="flex items-center justify-center flex-shrink-0 p-4 text-2xl font-medium text-white bg-gray-700 w-13 rounded-l-md"
            >
              {{ i + 1 }}.
            </div>
            <div
              class="flex items-center justify-between flex-1 truncate bg-gray-700 rounded-r-md"
            >
              <div class="flex-1 px-4 py-2 truncate text-md">
                <a href="#" class="font-medium text-white hover:text-gray-300">
                  {{ lesson.name }}
                </a>
                <p class="hidden text-sm text-gray-500 lg:block">
                  {{ lesson.description }}
                </p>
                <p class="block text-sm text-gray-500 lg:hidden">
                  {{ lesson.duration }}
                </p>
              </div>
              <div
                class="flex items-center justify-center flex-shrink-0 p-4 text-gray-400"
              >
                <p class="hidden mr-4 lg:block">
                  {{ lesson.duration }}
                </p>
                <button
                  v-if="!isPlaying(lesson)"
                  class="inline-flex items-center justify-center w-8 h-8 text-gray-400 bg-transparent rounded-full hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                  @click="play(lesson)"
                >
                  <span class="sr-only">Open options</span>
                  <!-- Heroicon name: dots-vertical -->
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
                <button
                  v-if="isPlaying(lesson)"
                  class="inline-flex items-center justify-center w-8 h-8 text-gray-400 bg-transparent rounded-full hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                  @click="stop(lesson)"
                >
                  <span class="sr-only">Open options</span>
                  <!-- Heroicon name: dots-vertical -->
                  <svg
                    class="w-8 h-8"
                    :class="
                      isPlaying(lesson) ? 'animate-spin duration-2000' : ''
                    "
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M10 9v6m4-6v6m7-3a9 9 0 11-18 0 9 9 0 0118 0z"
                    ></path>
                  </svg>
                </button>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  layout: 'next',
  data() {
    return {
      player: null,
      playing: false,
      playingLesson: '',
      currentAudioUrl: '',
    }
  },
  computed: {
    course() {
      return this.$store.state.currentCourse
    },
  },
  mounted() {
    this.player = document.getElementById('myAudio')
  },
  methods: {
    isPlaying(lesson) {
      if (this.playingLesson === lesson) {
        if (this.playing === true) return true
      }
      return false
    },
    play(lesson) {
      this.playingLesson = lesson
      this.playing = true
      this.currentAudioUrl = lesson.audioUrl
      this.player.play()
    },
    stop(lesson) {
      this.playingLesson = ''
      this.playing = false
      this.player.pause()
    },
  },
}
</script>

<style>
.left-64 {
  left: 16rem;
}
</style>
