export default {
  // Target (https://go.nuxtjs.dev/config-target)
  target: 'static',

  // Global page headers (https://go.nuxtjs.dev/config-head)
  head: {
    title: 'audally-web',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
  },

  // Environment variables (https://nuxtjs.org/docs/2.x/configuration-glossary/configuration-env)
  env: {
    baseUrl: process.env.BASE_URL || 'http://localhost:3000',
    backendUrl: 'http://localhost:8080/api',
  },

  // Global CSS (https://go.nuxtjs.dev/config-css)
  css: [],

  // Plugins to run before rendering page (https://go.nuxtjs.dev/config-plugins)
  plugins: [{ src: '~/plugins/vue-plyr', mode: 'client' }],

  // Auto import components (https://go.nuxtjs.dev/config-components)
  components: true,

  // Modules for dev and build (recommended) (https://go.nuxtjs.dev/config-modules)
  buildModules: [
    // https://go.nuxtjs.dev/eslint
    '@nuxtjs/eslint-module',
    // https://go.nuxtjs.dev/tailwindcss
    '@nuxtjs/tailwindcss',
  ],

  // Modules (https://go.nuxtjs.dev/config-modules)
  modules: ['@nuxtjs/axios', '@nuxtjs/auth-next'],

  axios: {
    /* baseURL: 'https://localhost:8180/',
    browserBaseURL: 'https://localhost:8180/', */
    proxyHeaders: true,
    proxy: true,
  },

  auth: {
    strategies: {
      local: false,
      keycloak: {
        scheme: 'oauth2',
        endpoints: {
          authorization:
            'http://localhost:8180/auth/realms/audally/protocol/openid-connect/auth',
          token:
            'http://localhost:8180/auth/realms/audally/protocol/openid-connect/token',
          userInfo:
            'http://localhost:8180/auth/realms/audally/protocol/openid-connect/userinfo',
          logout:
            'http://localhost:8180/auth/realms/audally/protocol/openid-connect/logout?redirect_uri=' +
            encodeURIComponent('http://localhost:3000'),
        },
        token: {
          property: 'access_token',
          type: 'Bearer',
          name: 'Authorization',
          maxAge: 300,
        },
        refreshToken: {
          property: 'refresh_token',
          maxAge: 60 * 60 * 24 * 30,
        },
        clientId: 'web',
        responseType: 'code',
        grantType: 'authorization_code',
        scope: ['openid', 'profile', 'email'],
        codeChallengeMethod: 'S256',
      },
    },
    redirect: {
      login: '/',
      logout: '/',
      home: false, // if not false, redirects like the menu items won't work
    },
  },

  router: {
    middleware: ['auth'],
  },

  // Build Configuration (https://go.nuxtjs.dev/config-build)
  build: {
    cache: true,
    hardSource: true,
    parallel: true,
  },

  /* server: {
    host: '192.168.1.47', // get it from here hostname -I | awk '{print $1}'
    port: 3000, // default: 3000
  }, */
}
