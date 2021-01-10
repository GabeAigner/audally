<template>
  <div
    class="px-4 py-6 border-b-2 border-gray-700 shadow lg:px-8 md:flex md:items-center md:justify-between"
  >
    <h2 class="text-3xl font-bold leading-7 text-white sm:truncate">
      {{ name }}
    </h2>
  </div>
</template>

<script>
export default {
  props: {
    name: {
      type: String,
      default: 'default',
    },
  },
}
</script>

<style></style>
