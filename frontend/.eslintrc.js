module.exports = {
  'env': {
    'browser': true,
    'es6': true,
  },
  'extends': [
    'plugin:react/recommended',
    'google',
  ],
  'globals': {
    'Atomics': 'readonly',
    'SharedArrayBuffer': 'readonly',
  },
  'parserOptions': {
    'ecmaFeatures': {
      'jsx': true,
    },
    'ecmaVersion': 2018,
    'sourceType': 'module',
  },
  'plugins': [
    'react',
  ],
  'rules': {
    "strict": 0,
    "object-curly-spacing": [2, "always"],
    "require-jsdoc": "off",
    "comma-dangle": "off",
    "max-len": ["error", { code: 90 }]
  },
  "parser": "babel-eslint",
  "settings": {
    "react": {
      "version": "detect"
    }
  }
};
