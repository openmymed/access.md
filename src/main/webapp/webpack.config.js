const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const babel = require("@babel/register");
module.exports = (env) => {
    return{
        mode: env.production ? 'production' : 'development',
        entry: './src/app.js',
        output: {
            filename: 'app.js'
        },
        plugins: [new HtmlWebpackPlugin({
                title: 'قوجي'
            }),
            new CleanWebpackPlugin()
        ],
        optimization: {
            minimize: env.production
        },
        watchOptions: {
            aggregateTimeout: 50,
            poll: 100
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

