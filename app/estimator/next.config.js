module.exports = {
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'http://localhost:9001/api/v1/:path*',
      },
    ]
  },
}