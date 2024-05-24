const webpack = require('webpack');

config.resolve.modules.push("../../processedResources/js/main");
config.resolve.conditionNames = ['import', 'require', 'default'];

if (config.devServer) {
    config.devServer.historyApiFallback = true;
    config.devServer.host = "localhost"
    config.devServer.hot = true;
    config.devtool = 'eval-cheap-source-map';

} else {
    config.devtool = undefined;
}

// disable bundle size warning
config.performance = {
    assetFilter: function (assetFilename) {
      return !assetFilename.endsWith('.js');
    },
};

// Add IgnorePlugin to ignore 'moment' module
config.plugins = (config.plugins || []).concat([
    new webpack.IgnorePlugin({
        resourceRegExp: /^moment$/,
    })
]);


config.output.publicPath = '/'

