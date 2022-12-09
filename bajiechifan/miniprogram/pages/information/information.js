Page({
  data: {
    avatarUrl: "",
    name: "",
    // phone: "11000000000",
  },

  onShow() {
    let _this = this;
    // 先从缓存预加载用户信息
    let userInfo = wx.getStorageSync('userInfo');
    if (userInfo) {
      _this.setData({
        avatarUrl: userInfo.avatarUrl,
        name: userInfo.nickName,
      });
    }
    // 从服务器加载用户信息
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/user',
      header: {
        token: getApp().globalData.token
      },
      success: (res) => {
        if (res.data.code == 200) {
          let userInfo = res.data.data;
          _this.setData({
            name: userInfo.nickName,
            avatarUrl: userInfo.avatarUrl
          });
          wx.setStorageSync('userInfo', userInfo);
        } else {
          wx.showToast({
            title: res.data.message,
            icon: "error"
          });
        }
      }
    })
  },

  editAvatar() {
    let _this = this;
    wx.showActionSheet({
      itemList: ["查看图片", "更换头像"],
      success: (res) => {
        switch (res.tapIndex) {
          case 0:
            // 查看图片
            wx.previewImage({
              urls: [_this.data.avatarUrl]
            });
            break;
          case 1:
            // 从相册选择图片
            wx.chooseMedia({
              count: 1,
              mediaType: ["image"],
              success: (res) => {
                wx.showLoading({
                  mask: true
                });
                let type = "." + res.tempFiles[0].tempFilePath.split(".").pop();
                // 上传到云存储获取url
                wx.cloud.uploadFile({
                  cloudPath: "userAvatars/" + Date.now() + Math.round(Math.random() * 2e9) + type,
                  filePath: res.tempFiles[0].tempFilePath,
                  // 将url上传到服务器，更新头像
                  success: (res) => {
                    let avatarUrl = res.fileID;
                    wx.request({
                      url: 'http://114.132.234.161:8888/bajie/user/updateUser',
                      method: "POST",
                      header: {
                        token: getApp().globalData.token,
                      },
                      data: {
                        avatarUrl: avatarUrl,
                        nickname: _this.data.name
                      },
                      success: (res) => {
                        if (res.data.code == 200) {
                          wx.showToast({
                            title: "修改成功",
                            icon: "success"
                          });
                          // 修改本地头像
                          _this.setData({
                            avatarUrl: avatarUrl
                          });
                          // 存入缓存
                          wx.setStorageSync('userInfo', {
                            avatarUrl: avatarUrl,
                            nickname: _this.data.name
                          });
                        } else {
                          console.log("修改失败", res);
                          wx.showToast({
                            title: res.data.message,
                            icon: "error"
                          });
                        }
                      }
                    });
                  }
                })
              }
            });
            break;
        }
      }
    })
  },

  // 修改昵称
  editName() {
    let _this = this;
    wx.navigateTo({
      url: "editName?name=" + encodeURIComponent(JSON.stringify(_this.data.name))
    })
  },

  // 退出登录
  logout() {
    getApp().globalData.token = null;
    wx.setStorageSync('token', null);
    wx.navigateBack();
  }
})