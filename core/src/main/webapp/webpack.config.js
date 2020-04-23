const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const babel = require("@babel/register");
const WebpackPwaManifest = require('webpack-pwa-manifest');

module.exports = (env) => {
  return{
    mode: env.production ? 'production' : 'development',
    entry: './src/app.js',
    output: {
      filename: 'app.js'
    },
    plugins: [new HtmlWebpackPlugin({
        title: 'Covid19'
      }),
      new CleanWebpackPlugin(),
      new WebpackPwaManifest({
        name: 'My Progressive Web App',
        short_name: 'MyPWA',
        description: 'My awesome Progressive Web App!',
        background_color: '#ffffff',
        icons: [
          {
            src: path.resolve(__dirname,'src','res','openmymed.png'),
            sizes: [96, 128, 192, 256, 384, 512] // multiple sizes
          }
        ]
      })
    ],
    optimization: {
      minimize: env.production
    },
    watchOptions: {
      aggregateTimeout: 1000,
      poll: 2000
    },
    module: {
      rules: [
        {
          test: /\.js$/,
          exclude: /(node_modules|\.test\.js)/,
          use: ['babel-loader']
        },
        {
          test: /\.css$/,
          use: ['style-loader', {loader: 'css-loader', options: {url: true}}]
        }, {
          test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
          use: [
            {
              loader: 'url-loader',
              options: {
                limit: 10000,
                mimetype: 'application/font-woff'
              }
            }
          ]
        },
        {
          test: /\.(png|jpe?g|gif)$/i,
          use: [
            {
              loader: 'file-loader',
            },
          ],
        },
        {
          test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
          use: [
            {loader: 'file-loader'}
          ]
        }
      ]
    }
  };
};

