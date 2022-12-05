Page({
  data: {

  },
  onLoad() {
    let that = this;
    let location = wx.getStorageSync('location');
    console.log(location)
    wx.chooseLocation({
      latitude: location[0],
      longitude: location[1],
      success(res) {
        console.log(res.longitude, res.latitude, res),
        wx.navigateBack({
          delta: 1,
        })
        wx.setStorageSync('location', [res.longitude, res.latitude])
      },
      fail(err) {
        console.log(err)
        wx.navigateBack({
          delta: 1,
        })
      }
    })

  },

})