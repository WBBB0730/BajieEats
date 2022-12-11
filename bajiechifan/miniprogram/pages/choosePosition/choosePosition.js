Page({
  data: {

  },
  onLoad() {
    let that = this;
    let location = wx.getStorageSync('location');
    console.log(location)
    wx.chooseLocation({
      latitude: location.latitude,
      longitude: location.longitude,
      success(res) {
        console.log(res.longitude, res.latitude, res);
        let location = {longitude: res.longitude, latitude: res.latitude, name: res.name};
        wx.setStorageSync('location', location);
        getApp().globalData.location = location;
        wx.navigateBack();
      },
      fail(err) {
        console.log(err)
        wx.navigateBack();
      }
    })

  },

})