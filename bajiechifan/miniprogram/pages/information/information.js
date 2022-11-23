Page({
  data: {
    avatarUrl:"",
    name:"",
    phone:"11000000000",
  },

  onLoad: function () {
    var useInfo = wx.getStorageSync('userInfo')
    var that = this;
    that.setData({
    avatarUrl:useInfo.avatarUrl,
    name:useInfo.nickName,})
  },
})