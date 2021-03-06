import colors from 'vuetify/es5/util/colors';

const baseHref = process.env.BASE_HREF || '/';
const isProd = process.env.NODE_ENV === 'production';

export default {
  mode: 'universal',
  /*
  ** Headers of the page
  */
  head: {
    titleTemplate: '%s - ' + process.env.npm_package_name,
    title: process.env.npm_package_name || '',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: process.env.npm_package_description || '' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: baseHref + 'favicon.ico' }
    ]
  },
  /*
  ** Customize the progress-bar color
  */
  loading: { color: '#fff' },
  /*
  ** Global CSS
  */
  css: [
    'material-design-icons-iconfont/dist/material-design-icons.css',
  ],
  /*
  ** Plugins to load before mounting the App
  */
  plugins: [],
  /*
  ** Nuxt.js dev-modules
  */
  buildModules: [
    '@nuxtjs/vuetify',
  ],
  /*
  ** Nuxt.js modules
  */
  webfontloader: {
    google: {
      // https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900&display=swap
      families: ['Roboto:100,300,400,500,700,900&display=swap'],
    }
  },
  modules: [
    'nuxt-webfontloader',
    // Doc: https://axios.nuxtjs.org/usage
    '@nuxtjs/axios',
    // Doc: https://github.com/nuxt-community/dotenv-module
    '@nuxtjs/dotenv',
    // Just for now disable PWA:
    // '@nuxtjs/pwa',
    '@nuxtjs/proxy',
  ],
  proxy: process.env.NODE_ENV === 'production' ? {} : {
    '/api': { target: 'http://127.0.0.1:8080/', },
    '/actuator': { target: 'http://127.0.0.1:8080/', },
  },
  /*
  ** Axios module configuration
  ** See https://axios.nuxtjs.org/options
  */
  axios: {},
  /*
  ** vuetify module configuration
  ** https://github.com/nuxt-community/vuetify-module
  */
  vuetify: {
    customVariables: ['~/assets/variables.scss'],
    theme: {
      dark: true,
      themes: {
        dark: {
          primary: colors.blue.darken2,
          accent: colors.grey.darken3,
          secondary: colors.amber.darken3,
          info: colors.teal.lighten1,
          warning: colors.amber.base,
          error: colors.deepOrange.accent4,
          success: colors.green.accent3
        },
      },
    },
    icons: {
      iconfont: 'mdiSvg' // || 'md' || 'fa' || 'fa4' || 'mdi', // default - only for display purposes
    },
  },
  /*
  ** Build configuration
  */
  build: {
    // analyze: process.env.NODE_ENV === 'development',
    /*
    ** You can extend webpack config here
    */
    extend(config, { isClient }) {
      if (isProd && isClient) {
        config.optimization.splitChunks.maxSize = 249856; // 244 Kib
      }
    }
  },
  generate: {
    routes: [
      '/',
    ],
    dir: '../src/main/resources/static'
  },
  router: {
    base: baseHref,
    mode: 'history',
  },
};
