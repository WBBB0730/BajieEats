// pages/information/editName.js
Page({
  data: {
    name: "",
  },

  onLoad(options) {
    let _this = this;
    if (options.name) {
      _this.setData({
        name: JSON.parse(decodeURIComponent(options.name))
      });
    }
  },

  onInput(e) {
    let _this = this;
    let name = e.detail.value;
    _this.setData({
      name: name
    });
    // _this.checkNameValid();
  },

  saveName() {
    let _this = this;
    if (!_this.checkNameValid()) {
      wx.showModal({
        title: '提示',
        content: '昵称不能为空',
        showCancel: false
      })
      return;
    }
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/user/updateUser',
      method: "POST",
      header: {
        token: getApp().globalData.token
      },
      data: {
        nickName: _this.data.name
      },
      success: (res) => {
        if (res.data.code == 200) {
          wx.showToast({
            title: '修改成功',
            icon: "success",
            mask: true
          });
          setTimeout(wx.navigateBack, 1000);
          
        } else {
          wx.showToast({
            title: res.data.message,
            icon: "error"
          })
        }
      }
    })
  },

  checkNameValid() {
    let _this = this;
    let name = _this.data.name;
    if (name.length == 0) {
      return false;
    }
    return true;
  }
})