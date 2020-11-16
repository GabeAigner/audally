<template>
  <li class="flex flex-col text-center bg-white rounded-lg shadow-md p-8">
    <div class="text-left">
      <p class="font-medium text-xl">{{ name }}</p>
      <p class="text-gray-700">{{ description }}</p>
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
